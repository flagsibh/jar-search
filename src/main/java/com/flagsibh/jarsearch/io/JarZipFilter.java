package com.flagsibh.jarsearch.io;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author ibandera, 07-ene-2016, 18:21:40
 */
public class JarZipFilter implements FileFilter {

    private final String[] supportedExtensions = new String[]{".jar", ".zip"};

    @Override
    public boolean accept(File pathname) {
        for (String supportedExtension : supportedExtensions) {
            if (pathname.getName().toLowerCase().endsWith(supportedExtension)) {
                return true;
            }
        }
        return false;
    }

}
