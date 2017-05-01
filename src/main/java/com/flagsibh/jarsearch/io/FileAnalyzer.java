package com.flagsibh.jarsearch.io;

import com.flagsibh.jarsearch.event.ClassFoundEventArgs;
import com.flagsibh.jarsearch.event.SearchingFileEventArgs;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flags, Jan 8, 2016, 11:02:21 PM
 */
public class FileAnalyzer extends Observable implements Runnable {

	private final BlockingQueue<File> fileQueue;
	private final String searchText;

	public FileAnalyzer(BlockingQueue<File> queue, String searchText) {
		this.fileQueue = queue;
		this.searchText = searchText.replaceAll("\\.", "/").toLowerCase();
	}

	@Override
	public void run() {
		try {
			while (true) {
				analyze(this.fileQueue.take());
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			Logger.getLogger(FileAnalyzer.class.getName()).log(Level.SEVERE, "Interrupted while waiting", ex);
		}
	}

	private void analyze(File file) {
		setChanged();
		notifyObservers(new SearchingFileEventArgs(file.getAbsolutePath()));

		if (file.canRead()) {
			try {
				try (JarFile jarFile = new JarFile(file)) {
					for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements();) {
						JarEntry jarEntry = (JarEntry) entries.nextElement();
						String entryName = jarEntry.getName();

						if (!jarEntry.isDirectory() && !entryName.contains("$") && entryName.toLowerCase().contains(searchText)) {
							setChanged();
							notifyObservers(new ClassFoundEventArgs(entryName, file.getAbsolutePath()));
						}
					}
				}
			} catch (IOException ex) {
				Logger.getLogger(FileAnalyzer.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
			}
		}
	}

}
