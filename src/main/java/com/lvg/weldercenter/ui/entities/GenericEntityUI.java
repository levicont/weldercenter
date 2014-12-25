package com.lvg.weldercenter.ui.entities;

import javafx.beans.property.SimpleLongProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 15.12.14.
 */
abstract public class GenericEntityUI {

    protected SimpleLongProperty id = new SimpleLongProperty();

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(null== obj)
            return false;
        if(WelderUI.class.equals(obj.getClass())){
            WelderUI welderUI = (WelderUI)obj;
            return welderUI.getId()==id.get();

        }
        return false;
    }
}
