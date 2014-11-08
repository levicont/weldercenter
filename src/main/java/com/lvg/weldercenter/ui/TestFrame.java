package com.lvg.weldercenter.ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Victor Levchenko LVG Corp. on 06.11.14.
 */
public class TestFrame extends Frame {
    public TestFrame(){
        super("Test Frame");
        setSize(new Dimension(550,400));
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Event thread name is: "+Thread.currentThread().getName());
                System.exit(0);
            }
        });
    }
}
