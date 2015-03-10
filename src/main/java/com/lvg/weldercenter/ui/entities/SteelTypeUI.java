package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.SteelType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class SteelTypeUI extends  GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleObjectProperty<SteelGroupUI> steelGroup = new SimpleObjectProperty<SteelGroupUI>();

    public SteelTypeUI(){
        this.id.set(0);
        this.type.set("");
        this.steelGroup.set(new SteelGroupUI());
    }

    public SteelTypeUI(SteelType steelType){
        this.id.set(steelType.getSteelTypeId());
        this.type.set(steelType.getType());
        this.steelGroup.set(new SteelGroupUI(steelType.getStGroup()));
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

    public SteelGroupUI getSteelGroup() {
        return steelGroup.get();
    }

    public SimpleObjectProperty<SteelGroupUI> steelGroupProperty() {
        return steelGroup;
    }

    public void setSteelGroup(SteelGroupUI steelGroup) {
        this.steelGroup.set(steelGroup);
    }
}
