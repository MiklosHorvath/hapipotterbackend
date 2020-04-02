package com.codecool.hp.config;

import java.util.Properties;

public class MyConfig {
    Properties configFile;
    private static MyConfig myConfig = null;

    public static MyConfig getInstance(){
        if(myConfig==null){
            myConfig = new MyConfig();
        }
        return myConfig;
    }

    private MyConfig() {
        configFile = new Properties();

        try {
            configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream("connection.properties.cfg"));
        } catch (Exception eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }
}