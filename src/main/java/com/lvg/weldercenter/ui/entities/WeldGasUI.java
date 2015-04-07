package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldGas;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class WeldGasUI extends GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();

    public WeldGasUI(){
        this.id.set(0);
        this.type.set("");
    }

    public WeldGasUI(WeldGas weldGas){
        this.id.set(weldGas.getWeldGasId());
        this.type.set(weldGas.getType());
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

    @Override
    public String toString() {
        return "WeldGasUI{" +
                "type=" + type +
                '}';
    }
}
