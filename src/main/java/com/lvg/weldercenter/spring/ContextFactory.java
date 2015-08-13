package com.lvg.weldercenter.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

/**
 * Created by Victor Levchenko LVG Corp. on 14.11.14.
 */
public class ContextFactory {
    private static final URL CONTEXT_PATH_URL = ContextFactory.class.getResource("/app-context-db.xml");
    private static final String CONTEXT_PATH = "app-context-db.xml";
    private static ApplicationContext context;

    private ContextFactory(){

    }

    public static ApplicationContext getApplicationContext(){
        if(null != context){
            return context;
        }else {
            String[] contextPaths = new String[]{CONTEXT_PATH};
            context = new ClassPathXmlApplicationContext(contextPaths);
        }
        return context;
    }
}
