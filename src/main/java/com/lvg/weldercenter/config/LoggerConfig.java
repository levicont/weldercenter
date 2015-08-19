package com.lvg.weldercenter.config;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Victor on 19.08.2015.
 */
public class LoggerConfig  {
    private static String LOG4J_PROPERTIES_FILE = "properties/log4j.properties";
    private static Properties logProperties = new Properties();

    public LoggerConfig(){
        try {
            logProperties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(LOG4J_PROPERTIES_FILE));
            PropertyConfigurator.configure(logProperties);
        }catch (IOException ex){
            System.err.println("Can not load Log4j properties file");
            ex.printStackTrace();
        }

    }
}
