package com.flagsibh.jarsearch.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author flags, Jan 23, 2016, 7:59:34 PM
 */
public class SystemClipboard {

    public static void copy(String text) {
        Clipboard clipboard = getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }

    private static Clipboard getSystemClipboard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }
}
