package com.flagsibh.jarsearch;

import com.flagsibh.jarsearch.cfg.Configurator;
import com.flagsibh.jarsearch.event.FinishedExecutionEventArgs;
import com.flagsibh.jarsearch.event.NoElementsProducedEventArgs;
import com.flagsibh.jarsearch.event.SearchingFileEventArgs;
import com.flagsibh.jarsearch.io.FileAnalyzerThreadsManager;
import com.flagsibh.jarsearch.io.FileCrawler;
import com.flagsibh.jarsearch.io.JarZipFilter;
import com.flagsibh.jarsearch.model.BrowseHistoryComboBoxDataModel;
import com.flagsibh.jarsearch.model.ClassNameHistoryComboBoxDataModel;
import com.flagsibh.jarsearch.model.JarSearchTableModel;
import com.flagsibh.jarsearch.util.SystemClipboard;
import com.flagsibh.jarsearch.util.UnzipUtility;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author ibandera
 */
public class JarSearchUI extends javax.swing.JFrame implements Observer {

    private static final long serialVersionUID = -819198209478469877L;
    private final JarSearchTableModel dataTableModel = new JarSearchTableModel();
    private final ClassNameHistoryComboBoxDataModel classNameHistoryComboBoxDataModel = new ClassNameHistoryComboBoxDataModel();
    private final BrowseHistoryComboBoxDataModel browseHistoryComboBoxDataModel = new BrowseHistoryComboBoxDataModel();
    private FileCrawler fileCrawler;

    /**
     * Creates new form JarSearchUI
     */
    public JarSearchUI() {
        initComponents();
        setIconImages();
    }

    private void setIconImages() {
        try {
            List<Image> icons = new ArrayList<>();
            icons.add(javax.imageio.ImageIO.read(getClass().getResource("/jaricon_16x16.png")));
            icons.add(javax.imageio.ImageIO.read(getClass().getResource("/jaricon_32x32.png")));
            icons.add(javax.imageio.ImageIO.read(getClass().getResource("/jaricon_64x64.png")));
            icons.add(javax.imageio.ImageIO.read(getClass().getResource("/jaricon_128x128.png")));
            super.setIconImages(icons);
        } catch (IOException ex) {
            Logger.getLogger(JarSearchUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        menuItemOpenInJD = new javax.swing.JMenuItem();
        menuItemOpenInZip = new javax.swing.JMenuItem();
        menuItemOpenInTextEditor = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemOpenJarInJD = new javax.swing.JMenuItem();
        menuItemOpenJarZipInZip = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuItemCopyFileClassNameToClipboard = new javax.swing.JMenuItem();
        menuItemCopyJarZipNameToClipboard = new javax.swing.JMenuItem();
        tabbedPane = new javax.swing.JTabbedPane();
        searchTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        resultsPanel = new javax.swing.JPanel();
        resultsScrollPane = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        stopButton = new javax.swing.JButton();
        searchTextComboBox = new javax.swing.JComboBox();
        searchDirectoryComboBox = new javax.swing.JComboBox();
        configTab = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtJavaDecompilerPath = new javax.swing.JTextField();
        txtTextEditorPath = new javax.swing.JTextField();
        txtZipDecompressorPath = new javax.swing.JTextField();
        numberOfThreadsSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        queueCapacitySpinner = new javax.swing.JSpinner();
        btnJavaDecompilerPath = new javax.swing.JButton();
        btnTextEditorPath = new javax.swing.JButton();
        btnZipDecompressorPath = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        menuItemOpenInJD.setText("Open in J-Decompiler");
        menuItemOpenInJD.setToolTipText("Open in J-Decompiler");
        menuItemOpenInJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenInJDActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemOpenInJD);

        menuItemOpenInZip.setText("Open in Zip-Decompressor");
        menuItemOpenInZip.setToolTipText("Open in Zip-Decompressor");
        menuItemOpenInZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenInZipActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemOpenInZip);

        menuItemOpenInTextEditor.setText("Open in Text Editor");
        menuItemOpenInTextEditor.setToolTipText("Open in Text Editor");
        menuItemOpenInTextEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenInTextEditorActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemOpenInTextEditor);
        popupMenu.add(jSeparator1);

        menuItemOpenJarInJD.setText("Open Jar/Zip in J-Decompiler");
        menuItemOpenJarInJD.setToolTipText("Open Jar/Zip in J-Decompiler");
        menuItemOpenJarInJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenJarInJDActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemOpenJarInJD);

        menuItemOpenJarZipInZip.setText("Open Jar/Zip in Zip-Decompressor");
        menuItemOpenJarZipInZip.setToolTipText("Open Jar/Zip in Zip-Decompressor");
        menuItemOpenJarZipInZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenJarZipInZipActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemOpenJarZipInZip);
        popupMenu.add(jSeparator2);

        menuItemCopyFileClassNameToClipboard.setText("Copy File/Class Name To Clipboard");
        menuItemCopyFileClassNameToClipboard.setToolTipText("Copy File/Class Name To Clipboard");
        menuItemCopyFileClassNameToClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCopyFileClassNameToClipboardActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemCopyFileClassNameToClipboard);

        menuItemCopyJarZipNameToClipboard.setText("Copy Jar/Zip Name To Clipboard");
        menuItemCopyJarZipNameToClipboard.setToolTipText("Copy Jar/Zip Name To Clipboard");
        menuItemCopyJarZipNameToClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCopyJarZipNameToClipboardActionPerformed(evt);
            }
        });
        popupMenu.add(menuItemCopyJarZipNameToClipboard);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jar Search");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabbedPane.setName(""); // NOI18N

        searchTab.setName(""); // NOI18N

        jLabel1.setText("File / Class Name:");

        jLabel2.setText("Directory to Search:");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        resultsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Results", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N

        resultsTable.setModel(dataTableModel);
        resultsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resultsTableMousePressed(evt);
            }
        });
        resultsScrollPane.setViewportView(resultsTable);

        javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
        );
        resultsPanelLayout.setVerticalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );

        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        searchTextComboBox.setEditable(true);
        searchTextComboBox.setModel(classNameHistoryComboBoxDataModel);

        searchDirectoryComboBox.setEditable(true);
        searchDirectoryComboBox.setModel(browseHistoryComboBoxDataModel);

        javax.swing.GroupLayout searchTabLayout = new javax.swing.GroupLayout(searchTab);
        searchTab.setLayout(searchTabLayout);
        searchTabLayout.setHorizontalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(searchTabLayout.createSequentialGroup()
                        .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchTabLayout.createSequentialGroup()
                                .addComponent(searchDirectoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton))
                            .addComponent(searchTextComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        searchTabLayout.setVerticalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchButton)
                    .addComponent(searchTextComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(browseButton)
                    .addComponent(stopButton)
                    .addComponent(searchDirectoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Search", null, searchTab, "Search");

        jLabel3.setText("Number of Threads:");

        jLabel4.setText("Java Decompiler Path:");

        jLabel5.setText("Text Editor Path:");

        jLabel6.setText("ZIP Decompressor Path:");

        txtJavaDecompilerPath.setText(Configurator.getInstance().getJavaDecompilerPath());

        txtTextEditorPath.setText(Configurator.getInstance().getTextEditorPath());

        txtZipDecompressorPath.setText(Configurator.getInstance().getZipDecompressorPath());

        numberOfThreadsSpinner.setValue(Configurator.getInstance().getNumberOfThreads());

        jLabel7.setText("Queue Capacity:");

        queueCapacitySpinner.setValue(Configurator.getInstance().getQueueCapacity());

        btnJavaDecompilerPath.setText("...");
        btnJavaDecompilerPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJavaDecompilerPathActionPerformed(evt);
            }
        });

        btnTextEditorPath.setText("...");
        btnTextEditorPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextEditorPathActionPerformed(evt);
            }
        });

        btnZipDecompressorPath.setText("...");
        btnZipDecompressorPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZipDecompressorPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configTabLayout = new javax.swing.GroupLayout(configTab);
        configTab.setLayout(configTabLayout);
        configTabLayout.setHorizontalGroup(
            configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTextEditorPath, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                    .addComponent(txtJavaDecompilerPath, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numberOfThreadsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(queueCapacitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZipDecompressorPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(configTabLayout.createSequentialGroup()
                        .addComponent(btnZipDecompressorPath)
                        .addContainerGap())
                    .addComponent(btnJavaDecompilerPath)
                    .addComponent(btnTextEditorPath)))
        );
        configTabLayout.setVerticalGroup(
            configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numberOfThreadsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(queueCapacitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtJavaDecompilerPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJavaDecompilerPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTextEditorPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTextEditorPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtZipDecompressorPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZipDecompressorPath))
                .addContainerGap(385, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Configuration", null, configTab, "Configuration");

        statusPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        statusPanel.setPreferredSize(new java.awt.Dimension(0, 16));

        statusLabel.setText("status");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnJavaDecompilerPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJavaDecompilerPathActionPerformed
        javax.swing.JFileChooser jdChooser = new JFileChooser();
        jdChooser.setDialogTitle("Select Java Decompiler");
        jdChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (jdChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            txtJavaDecompilerPath.setText(jdChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnJavaDecompilerPathActionPerformed

    private void btnTextEditorPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextEditorPathActionPerformed
        javax.swing.JFileChooser textChooser = new JFileChooser();
        textChooser.setDialogTitle("Select Text Editor");
        textChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (textChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            txtTextEditorPath.setText(textChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnTextEditorPathActionPerformed

    private void btnZipDecompressorPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZipDecompressorPathActionPerformed
        javax.swing.JFileChooser zipChooser = new JFileChooser();
        zipChooser.setDialogTitle("Select Text Editor");
        zipChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (zipChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            txtZipDecompressorPath.setText(zipChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnZipDecompressorPathActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_browseButtonActionPerformed
        javax.swing.JFileChooser searchDirectoryChooser = new javax.swing.JFileChooser();
        searchDirectoryChooser.setDialogTitle("Select the Directory to Search");
        searchDirectoryChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (searchDirectoryChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String browsePath = searchDirectoryChooser.getSelectedFile().getAbsolutePath();
            addBrowseHistory(browsePath);
        }
    }// GEN-LAST:event_browseButtonActionPerformed

    private void addBrowseHistory(String browsePath) {
        if (browseHistoryComboBoxDataModel.getIndexOf(browsePath) == -1) {
            browseHistoryComboBoxDataModel.insertElementAt(browsePath, 0);
        }
        browseHistoryComboBoxDataModel.setSelectedItem(browsePath);
    }

    private void addSearchHistory(String searchText) {
        if (classNameHistoryComboBoxDataModel.getIndexOf(searchText) == -1) {
            classNameHistoryComboBoxDataModel.insertElementAt(searchText, 0);
        }
        classNameHistoryComboBoxDataModel.setSelectedItem(searchText);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
        saveConfiguration();
        saveHistory();
    }// GEN-LAST:event_formWindowClosing

    private void saveConfiguration() throws NumberFormatException {
        Configurator.getInstance().setNumberOfThreads(Integer.valueOf(numberOfThreadsSpinner.getValue().toString()));
        Configurator.getInstance().setQueueCapacity(Integer.valueOf(queueCapacitySpinner.getValue().toString()));
        Configurator.getInstance().setJavaDecompilerPath(txtJavaDecompilerPath.getText());
        Configurator.getInstance().setTextEditorPath(txtTextEditorPath.getText());
        Configurator.getInstance().setZipDecompressorPath(txtZipDecompressorPath.getText());
        Configurator.getInstance().saveSettings();
    }

    private void saveHistory() {
        classNameHistoryComboBoxDataModel.writeHistory();
        browseHistoryComboBoxDataModel.writeHistory();
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchButtonActionPerformed
        String searchClassName = searchTextComboBox.getSelectedItem().toString();
        String rootDirName = searchDirectoryComboBox.getSelectedItem().toString();
        File rootDir = new File(rootDirName);
        if (rootDir.isDirectory()) {
            dataTableModel.clear();
            statusLabel.setText("Searching ...");
            BlockingQueue<File> queue = new ArrayBlockingQueue<>(Configurator.getInstance().getQueueCapacity());
            fileCrawler = new FileCrawler(queue, new JarZipFilter(), rootDir);
            new Thread(fileCrawler).start();

            FileAnalyzerThreadsManager manager = new FileAnalyzerThreadsManager(queue, searchClassName);
            manager.addObserver(this);
            manager.addAnalyzerObserver(this);
            manager.addAnalyzerObserver(dataTableModel);
            new Thread(manager).start();

            addBrowseHistory(rootDirName);
            addSearchHistory(searchClassName);

            // disable search button
            searchButton.setEnabled(false);
            stopButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "There's a problem with search directory '" + rootDirName + "'.\nPlease, verify it exists and is accesible.",
                    "Search directory", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_searchButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_stopButtonActionPerformed
        if (fileCrawler != null) {
            fileCrawler.shutdown();
            statusLabel.setText("Stopped");

            // enable search button
            searchButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }// GEN-LAST:event_stopButtonActionPerformed

    private void resultsTableMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_resultsTableMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            int column = source.columnAtPoint(evt.getPoint());

            if (!source.isRowSelected(row)) {
                source.changeSelection(row, column, false, false);
            }

            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }// GEN-LAST:event_resultsTableMousePressed

    private void menuItemOpenInJDActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemOpenInJDActionPerformed
        openFile(txtJavaDecompilerPath.getText());
    }// GEN-LAST:event_menuItemOpenInJDActionPerformed

    private void menuItemOpenInTextEditorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemOpenInTextEditorActionPerformed
        openFile(txtTextEditorPath.getText());
    }// GEN-LAST:event_menuItemOpenInTextEditorActionPerformed

    private void menuItemOpenInZipActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemOpenInZipActionPerformed
        openFile(txtZipDecompressorPath.getText());
    }// GEN-LAST:event_menuItemOpenInZipActionPerformed

    private void menuItemOpenJarInJDActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemOpenJarInJDActionPerformed
        openJarZipFile(txtJavaDecompilerPath.getText());
    }// GEN-LAST:event_menuItemOpenJarInJDActionPerformed

    private void menuItemOpenJarZipInZipActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemOpenJarZipInZipActionPerformed
        openJarZipFile(txtZipDecompressorPath.getText());
    }// GEN-LAST:event_menuItemOpenJarZipInZipActionPerformed

    private void menuItemCopyFileClassNameToClipboardActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemCopyFileClassNameToClipboardActionPerformed
        String entryName = resultsTable.getValueAt(resultsTable.getSelectedRow(), 0).toString();
        SystemClipboard.copy(entryName);
    }// GEN-LAST:event_menuItemCopyFileClassNameToClipboardActionPerformed

    private void menuItemCopyJarZipNameToClipboardActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuItemCopyJarZipNameToClipboardActionPerformed
        String zipfilePath = resultsTable.getValueAt(resultsTable.getSelectedRow(), 1).toString();
        SystemClipboard.copy(zipfilePath);
    }// GEN-LAST:event_menuItemCopyJarZipNameToClipboardActionPerformed

    private void openFile(String executor) {
        if (executor != null && executor.length() > 0) {
            String zipfilePath = resultsTable.getValueAt(resultsTable.getSelectedRow(), 1).toString();
            String entryName = resultsTable.getValueAt(resultsTable.getSelectedRow(), 0).toString();

            try {
                String extractedFileName = UnzipUtility.extractFile(zipfilePath, entryName);
                String[] params = {executor, extractedFileName};

                new ProcessBuilder(params).start();
            } catch (IOException ex) {
                Logger.getLogger(JarSearchUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must define an executor program for this option in the Configuration tab.");
        }
    }

    private void openJarZipFile(String executor) {
        if (executor != null && executor.length() > 0) {
            String zipfilePath = resultsTable.getValueAt(resultsTable.getSelectedRow(), 1).toString();
            try {
                String[] params = {executor, zipfilePath};
                new ProcessBuilder(params).start();
            } catch (IOException ex) {
                Logger.getLogger(JarSearchUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must define an executor program for this option in the Configuration tab.");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JarSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JarSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JarSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JarSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JarSearchUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton btnJavaDecompilerPath;
    private javax.swing.JButton btnTextEditorPath;
    private javax.swing.JButton btnZipDecompressorPath;
    private javax.swing.JPanel configTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem menuItemCopyFileClassNameToClipboard;
    private javax.swing.JMenuItem menuItemCopyJarZipNameToClipboard;
    private javax.swing.JMenuItem menuItemOpenInJD;
    private javax.swing.JMenuItem menuItemOpenInTextEditor;
    private javax.swing.JMenuItem menuItemOpenInZip;
    private javax.swing.JMenuItem menuItemOpenJarInJD;
    private javax.swing.JMenuItem menuItemOpenJarZipInZip;
    private javax.swing.JSpinner numberOfThreadsSpinner;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JSpinner queueCapacitySpinner;
    private javax.swing.JPanel resultsPanel;
    private javax.swing.JScrollPane resultsScrollPane;
    private javax.swing.JTable resultsTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox searchDirectoryComboBox;
    private javax.swing.JPanel searchTab;
    private javax.swing.JComboBox searchTextComboBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton stopButton;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField txtJavaDecompilerPath;
    private javax.swing.JTextField txtTextEditorPath;
    private javax.swing.JTextField txtZipDecompressorPath;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof SearchingFileEventArgs) {
            SearchingFileEventArgs args = (SearchingFileEventArgs) arg;
            statusLabel.setText(String.format("Searching %s ...", args.getJarFileName()));
        } else if (arg instanceof FinishedExecutionEventArgs) {
            FinishedExecutionEventArgs args = (FinishedExecutionEventArgs) arg;
            statusLabel.setText(String.format("Found %d match(es) out of %d scanned file(s) in %.1f second(s) ...",
                    dataTableModel.getRowCount(), fileCrawler.getFileCount(), args.getExecutionTime()));

            // enable search button
            searchButton.setEnabled(true);
            stopButton.setEnabled(false);
        } else if (arg instanceof NoElementsProducedEventArgs) {
            NoElementsProducedEventArgs args = (NoElementsProducedEventArgs) arg;
            stopButtonActionPerformed(null);
            JOptionPane.showMessageDialog(this, String.format(
                    "No JAR/ZIP files were found within %d seconds.%nPlease, consider changing the search directory.", args.getTimeout()));
        }
    }
}