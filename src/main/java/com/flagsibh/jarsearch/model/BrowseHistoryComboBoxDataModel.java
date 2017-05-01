package com.flagsibh.jarsearch.model;

/**
 *
 * @author ibandera, 02-feb-2016, 17:38:30
 */
public class BrowseHistoryComboBoxDataModel extends HistoryComboBoxModel {

    private static final String BROWSE_HISTORY_FILE = "./browseHistory.txt";

    public BrowseHistoryComboBoxDataModel() {
        super(BROWSE_HISTORY_FILE);
    }

}
