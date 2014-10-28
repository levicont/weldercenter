package com.lvg.weldercenter.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class MainFrame extends JFrame {

   public void init() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 400));
        setVisible(true);
        setState(Frame.NORMAL);
    }
}
