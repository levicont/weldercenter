package com.lvg.weldercenter.todo;

import com.lvg.weldercenter.config.LoggerConfig;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class ToDo {
    public static void main(String[] args) {
        new LoggerConfig();
        Launcher launcher = new Launcher();
        //launcher.launch();
        launcher.launchUI(args);
    }
}
