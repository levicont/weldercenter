package com.lvg.weldercenter.todo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class Launcher {
    public void launch(){
        String[] contextPaths = new String[]{"app-context.xml"};
        new ClassPathXmlApplicationContext(contextPaths);
    }
}
