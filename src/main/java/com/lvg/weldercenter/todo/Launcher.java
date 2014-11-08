package com.lvg.weldercenter.todo;

import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.services.JobService;
import com.lvg.weldercenter.ui.MainFrame;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class Launcher {
    public void launch(){
        String[] contextPaths = new String[]{"app-context-db.xml"};
        System.out.println("\n Start loading spring application context... \n");
        ApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);
        System.out.println("\n Loading spring application context is done... \n");
        System.out.println("\n Start loading UI context... \n");

    }
    public void launchUI(String[] args){
        Application.launch(MainFrame.class, args);
    }
}
