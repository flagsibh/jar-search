package com.flagsibh.jarsearch.io;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibandera, 07-ene-2016, 19:00:44
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileQueue;
    private ConcurrentSkipListSet<File> indexedFiles = new ConcurrentSkipListSet<File>();
    private final FileFilter fileFilter;
    private final File root;
    private final ExecutorService exec = Executors.newCachedThreadPool();

    public FileCrawler(BlockingQueue<File> fileQueue, final FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.root = root;
        this.fileFilter = new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return (pathname.isDirectory() && !pathname.isHidden()) || fileFilter.accept(pathname);
            }
        };
    }

    @Override
    public void run() {
        submitCrawlTask(root);
    }

    private void submitCrawlTask(File f) {
        CrawlTask crawlTask = new CrawlTask(f);
        exec.execute(crawlTask);
    }

    public int getFileCount() {
        return indexedFiles.size();
    }

    public void shutdown() {
        exec.shutdownNow();
    }

    private class CrawlTask implements Runnable {

        private final File file;

        CrawlTask(File file) {

            this.file = file;
        }

        @Override
        public void run() {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            File[] entries = file.listFiles(fileFilter);

            if (entries != null) {
                for (File entry : entries) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    if (entry.isDirectory()) {
                        submitCrawlTask(entry);
                    } else if (!indexedFiles.contains(entry)) {
                        indexedFiles.add(entry);
                        try {
                            fileQueue.put(entry);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            Logger.getLogger(FileCrawler.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
            }
        }
    }
}
