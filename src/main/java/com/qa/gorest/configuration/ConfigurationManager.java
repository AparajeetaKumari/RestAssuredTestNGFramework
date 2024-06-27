package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationManager {
    private Properties prop;
    private FileInputStream fis;

    public Properties readInitConfig(){
        try {
            prop = new Properties();
            fis = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(fis);

        }catch(Exception e){
            e.printStackTrace();
        }
        return prop;
    }
}
