package com.flagsibh.jarsearch.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ibandera, 02-feb-2016, 16:25:22
 */
public class HistoryComboBoxModel extends DefaultComboBoxModel<String> {

    private String fileName = "./history.txt";

    public HistoryComboBoxModel() {
        super();
        loadHistory();
    }

    public HistoryComboBoxModel(String fileName) {
        super();
        this.fileName = fileName;
        loadHistory();
    }

    private void loadHistory() {
        try {
            List<String> historyLines = FileUtils.readLines(new File(fileName));
            for (String historyLine : historyLines) {
                addElement(historyLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(HistoryComboBoxModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeHistory() {
        List<String> historyLines = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            historyLines.add(getElementAt(i));
        }

        try {
            FileUtils.writeLines(new File(fileName), historyLines);
        } catch (IOException ex) {
            Logger.getLogger(HistoryComboBoxModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
