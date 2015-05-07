package com.lvg.weldercenter.ui;

/**
 * Created by Victor on 07.05.2015.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class SplashSwingFrame extends JWindow{

    private JWindow mainWindow;
    private JLabel lbCaption;
    private JPanel pnCenter;
    private JPanel pnTop;
    private JPanel pnBottom;
    private JButton btClose;
    private JProgressBar progressBar;





    public SplashSwingFrame(){
        super();
        mainWindow = this;
        this.setSize(400, 300);
        setOnScreenCenter();
        init();
        this.setVisible(true);

    }

    private void init(){
        lbCaption = new JLabel("Loading...");
        pnCenter = new JPanel();
        pnTop = new JPanel();
        pnBottom = new JPanel();
        btClose = new JButton("close");
        btClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
            }
        });

        progressBar = new JProgressBar();

        progressBar.setPreferredSize(new Dimension(this.getSize().width-10,7));
        progressBar.setBorderPainted(false);
        progressBar.setMaximum(100);
        progressBar.setValue(10);
        progressBar.setForeground(Color.BLUE);

        pnTop.add(btClose);
        pnCenter.add(lbCaption);
        pnBottom.add(progressBar);


        Container pane = this.getContentPane();

        pane.add(pnTop, BorderLayout.NORTH);
        pane.add(pnCenter, BorderLayout.CENTER);
        pane.add(pnBottom, BorderLayout.SOUTH);

    }

    private void setOnScreenCenter(){
        int x = Toolkit.getDefaultToolkit().getScreenSize().width/2-this.getSize().width/2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height/2-this.getSize().height/2;
        this.setLocation(x,y);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
