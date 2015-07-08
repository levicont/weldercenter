package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.SteelGroup;
import com.lvg.weldercenter.models.SteelType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class SteelTypeUI extends  GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleObjectProperty<SteelGroup> steelGroup = new SimpleObjectProperty<SteelGroup>();
    private final SimpleObjectProperty<SteelGroupUI> steelGroupUI = new SimpleObjectProperty<SteelGroupUI>();

    public SteelTypeUI(){
        this.id.set(0);
        this.type.set("");
        this.steelGroup.set(null);
        this.steelGroupUI.set(new SteelGroupUI());
    }

    public SteelTypeUI(SteelType steelType){
        this.id.set(steelType.getSteelTypeId());
        this.type.set(steelType.getType());
        if (steelType.getStGroup() != null) {
            this.steelGroup.set(steelType.getStGroup());
            this.steelGroupUI.set(new SteelGroupUI(steelType.getStGroup()));
        }else {
            this.steelGroup.set(null);
            this.steelGroupUI.set(new SteelGroupUI());
        }
    }

    //Getters and Setters


    public SteelGroupUI getSteelGroupUI() {
        return steelGroupUI.get();
    }

    public SimpleObjectProperty<SteelGroupUI> steelGroupUIProperty() {
        return steelGroupUI;
    }

    public void setSteelGroupUI(SteelGroupUI steelGroupUI) {
        this.steelGroupUI.set(steelGroupUI);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SteelGroup getSteelGroup() {
        return steelGroup.get();
    }

    public SimpleObjectProperty<SteelGroup> steelGroupProperty() {
        return steelGroup;
    }

    public void setSteelGroup(SteelGroup steelGroup) {
        this.steelGroup.set(steelGroup);
    }

    @Override
    public String toString() {
        return "SteelTypeUI{" +
                "type=" + type +
                ", steelGroup=" + steelGroup +
                '}';
    }
}
