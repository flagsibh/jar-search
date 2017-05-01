package com.flagsibh.jarsearch.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This utility extracts files and directories of a standard zip file to a destination directory.
 *
 * @author flags, Jan 16, 2016, 2:05:52 PM
 */
public class UnzipUtility {

    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Temporary directory to extract single files when destination/output directory is not specified.
     */
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by destDirectory (will be created if does not exists)
     *
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    extractFile(zipIn, filePath);
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Extracts a file specified by zipEntryName from within the ZIP file specified by zipFilePath to a temporary directory specified by the
     * <b>java.io.tmpdir</b> system property.
     *
     * @param zipFilePath
     * @param zipEntryName
     * @return
     * @throws IOException
     */
    public static String extractFile(String zipFilePath, String zipEntryName) throws IOException {
        String filePath = null;
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null && filePath == null) {
                if (!entry.isDirectory() && entry.getName().equals(zipEntryName)) {
                    filePath = TEMP_DIR + File.separator + entry.getName().substring(entry.getName().lastIndexOf('/') + 1);
                    extractFile(zipIn, filePath);
                }
            }
        }

        return filePath;
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }

}
