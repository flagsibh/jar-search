package com.flagsibh.jarsearch.event;

/**
 *
 * @author flags, Jan 9, 2016, 4:56:26 PM
 */
public class SearchingFileEventArgs {

    private String jarFileName;

    public SearchingFileEventArgs(String jarFileName) {
        this.jarFileName = jarFileName;
    }

    public String getJarFileName() {
        return jarFileName;
    }

    public void setJarFileName(String jarFileName) {
        this.jarFileName = jarFileName;
    }

}
