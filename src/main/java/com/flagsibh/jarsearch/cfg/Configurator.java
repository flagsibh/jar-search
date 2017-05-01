package com.flagsibh.jarsearch.cfg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author ibandera, 07-ene-2016, 10:28:09
 */
public class Configurator {

    private static final String CONFIG_FILE_PATH = "./app.config";
    private static final File configFile = new File(CONFIG_FILE_PATH);
    private Settings settings;

    private Configurator() {
        initialize();
    }

    public static Configurator getInstance() {
        return ConfiguratorHolder.INSTANCE;
    }

    private static class ConfiguratorHolder {

        private static final Configurator INSTANCE = new Configurator();
    }

    private void initialize() {
        if (configFile.exists() && configFile.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH));
                settings = new Gson().fromJson(reader, Settings.class);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(String.format("Unable to read settings from %s configuration file", CONFIG_FILE_PATH), ex);
            }
        } else {
            try {
                settings = new Settings();
                settings.numberOfThreads = 2;
                settings.queueCapacity = 4;
                configFile.createNewFile();
                saveSettings();
            } catch (IOException ex) {
                throw new RuntimeException(String.format("Unable to create configuration file %s", CONFIG_FILE_PATH), ex);
            }
        }
    }

    public void saveSettings() {
        try {
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(configFile))) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                writer.append(gson.toJson(settings));
            }
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Unable to save configuration settings to file %s", CONFIG_FILE_PATH), ex);
        }
    }

    public int getNumberOfThreads() {
        return settings.numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.settings.numberOfThreads = numberOfThreads;
    }

    public int getQueueCapacity() {
        return settings.queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.settings.queueCapacity = queueCapacity;
    }

    public String getJavaDecompilerPath() {
        return settings.javaDecompilerPath;
    }

    public void setJavaDecompilerPath(String javaDecompilerPath) {
        this.settings.javaDecompilerPath = javaDecompilerPath;
    }

    public String getTextEditorPath() {
        return settings.textEditorPath;
    }

    public void setTextEditorPath(String textEditorPath) {
        this.settings.textEditorPath = textEditorPath;
    }

    public String getZipDecompressorPath() {
        return settings.zipDecompressorPath;
    }

    public void setZipDecompressorPath(String zipDecompressorPath) {
        this.settings.zipDecompressorPath = zipDecompressorPath;
    }

    private class Settings {

        private int numberOfThreads;
        private int queueCapacity;
        private String javaDecompilerPath;
        private String textEditorPath;
        private String zipDecompressorPath;
    }

}
