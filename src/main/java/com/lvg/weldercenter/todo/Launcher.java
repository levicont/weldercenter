package com.lvg.weldercenter.todo;

import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.services.JobService;
import com.lvg.weldercenter.spring.ContextFactory;
import com.lvg.weldercenter.ui.MainFrame;
import com.lvg.weldercenter.ui.MainPreloader;
import com.lvg.weldercenter.ui.PreloadScreen;
import com.lvg.weldercenter.ui.util.PreloadFrame;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class Launcher {
    private ApplicationContext context;

    public Launcher(){
       context = ContextFactory.getApplicationContext();
    }
    public void launch(){

        System.out.println("\n Start loading spring application context... \n");
        ContextFactory.getApplicationContext();
        System.out.println("\n Loading spring application context is done... \n");


    }
    public void launchUI(String[] args){
        System.out.println("\n Start loading UI context... \n");
        Application app = getApplication();
        app.launch(MainFrame.class, args);
    }


    private Application getApplication(){
        Application app = (Application)context.getBean("fxApplication");
        return app;
    }
}
