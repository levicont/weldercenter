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
        return getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WeldGasUI weldGasUI = (WeldGasUI) o;

        if (type != null ? !type.equals(weldGasUI.type) : weldGasUI.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
