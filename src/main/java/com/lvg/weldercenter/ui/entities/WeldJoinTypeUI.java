package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldJoinType;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor on 22.06.2015.
 */
public class WeldJoinTypeUI extends GenericEntityUI {

    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();

    public WeldJoinTypeUI(){
        this.id.set(0);
        this.type.set("");
        this.description.set("");
    }

    public WeldJoinTypeUI(WeldJoinType weldJoinType){
        this.id.set(weldJoinType.getWeldJoinTypeId());
        this.type.set(weldJoinType.getType());
        this.description.set(weldJoinType.getDescription());
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
        return "WeldJoinTypeUI{" +
                "type=" + type +
                ", description=" + description +
                '}';
    }
}
