package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.SteelGroup;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class SteelGroupUI extends GenericEntityUI {
    private final SimpleStringProperty group = new SimpleStringProperty();
    private final SimpleStringProperty description  = new SimpleStringProperty();

    public SteelGroupUI(){
        this.id.set(0);
        this.group.set("");
        this.description.set("");
    }

    public SteelGroupUI(SteelGroup steelGroup){
        this.id.set(steelGroup.getSteelGroupId());
        this.group.set(steelGroup.getStGroup());
        this.description.set(steelGroup.getDescription());
    }

    //Getters and Setters


    public String getGroup() {
        return group.get();
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
