package com.lvg.weldercenter.ui.button;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Victor Levchenko LVG Corp. on 26.10.14.
 */
public class ActionListenerButton extends JButton {

    private ActionListener actionListener;

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void init(){
        addActionListener(this.actionListener);
    }
}
