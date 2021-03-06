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

    @Override
    public String toString() {
        return getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ElectrodeUI that = (ElectrodeUI) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
