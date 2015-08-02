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

    @Override
    public String toString() {
        return getGroup();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SteelGroupUI that = (SteelGroupUI) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
