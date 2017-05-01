package com.flagsibh.jarsearch.model;

/**
 *
 * @author ibandera, 02-feb-2016, 17:33:06
 */
public class ClassNameHistoryComboBoxDataModel extends HistoryComboBoxModel {

    private static final String CLASS_NAME_HISTORY_FILE = "./classNameHistory.txt";

    public ClassNameHistoryComboBoxDataModel() {
        super(CLASS_NAME_HISTORY_FILE);
    }

}
