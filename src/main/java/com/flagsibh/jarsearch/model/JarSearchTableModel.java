package com.flagsibh.jarsearch.model;

import com.flagsibh.jarsearch.event.ClassFoundEventArgs;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author flags, Jan 9, 2016, 11:26:32 AM
 */
public class JarSearchTableModel extends DefaultTableModel implements Observer {

    public JarSearchTableModel() {
        super(new String[][]{}, new String[]{"File / Class Name", "Jar / Zip File Name"});
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (arg instanceof ClassFoundEventArgs) {
            ClassFoundEventArgs args = (ClassFoundEventArgs) arg;
            addRow(new String[]{args.getClassName(), args.getJarFileName()});
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    /**
     * Clears all data/rows in the JTable.
     */
    public void clear() {
        // this will delete the rows and fire the TableModelEvent to update the GUI
        setRowCount(0);
    }

}
