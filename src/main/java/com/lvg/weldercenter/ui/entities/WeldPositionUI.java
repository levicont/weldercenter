package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldPosition;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class WeldPositionUI extends GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleStringProperty code = new SimpleStringProperty();

    public WeldPositionUI() {
        this.id.set(0);
        this.type.set("");
        this.code.set("");
    }

    public WeldPositionUI(WeldPosition weldPosition){
        this.id.set(weldPosition.getWeldPositionId());
        this.type.set(weldPosition.getType());
        this.code.set(weldPosition.getCode());
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

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    @Override
    public String toString() {
        return "WeldPositionUI{" +
                "type=" + type +
                ", code=" + code +
                '}';
    }
}
