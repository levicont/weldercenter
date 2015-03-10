package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Electrode;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class ElectrodeUI extends GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();

    public ElectrodeUI(){
        this.id.set(0);
        this.type.set("");
    }

    public ElectrodeUI(Electrode electrode){
        this.id.set(electrode.getElectrodeId());
        this.type.set(electrode.getType());
    }

    //Getters and Setters

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
