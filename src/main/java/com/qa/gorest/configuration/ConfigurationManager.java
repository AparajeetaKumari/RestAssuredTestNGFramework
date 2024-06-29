package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private Properties prop;
    private FileInputStream fis;

    public Properties readInitConfig(){

        String envName = System.getProperty("env");
        prop = new Properties();
        if(envName==null){
            System.out.println("No valid environment shared, setting default environment as QA");
            envName = "qa";
        }else{
            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                        fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "dev":
                        fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "prod":
                        fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    default:
                        break;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}
