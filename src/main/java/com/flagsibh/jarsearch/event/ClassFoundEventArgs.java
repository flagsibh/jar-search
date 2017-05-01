package com.flagsibh.jarsearch.event;

/**
 *
 * @author flags, Jan 9, 2016, 12:01:47 AM
 */
public class ClassFoundEventArgs {

    private String className;
    private String jarFileName;

    public ClassFoundEventArgs(String className, String jarFileName) {
        this.className = className;
        this.jarFileName = jarFileName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJarFileName() {
        return jarFileName;
    }

    public void setJarFileName(String jarFileName) {
        this.jarFileName = jarFileName;
    }

}
