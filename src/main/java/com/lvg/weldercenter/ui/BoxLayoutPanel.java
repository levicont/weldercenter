package com.lvg.weldercenter.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 25.10.14.
 */
public class BoxLayoutPanel extends JPanel {

    private List componentList;
    private int axis;

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public void setComponentList(List componentList) {
        this.componentList = componentList;
    }

    public void init(){
        setLayout(new BoxLayout(this,axis));

        for (Iterator iterator = componentList.iterator(); iterator.hasNext();){
            Component component = (Component)iterator.next();
            add(component);
        }

    }
}
