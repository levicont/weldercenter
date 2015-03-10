package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldMethod;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 16.11.14.
 */
public class WeldMethodUI extends GenericEntityUI{

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty code = new SimpleStringProperty();

    public WeldMethodUI() {
        this.id.set(0);
        this.name.set("");
        this.code.set("");
    }

    public WeldMethodUI(WeldMethod weldMethod){
        this.id.set(weldMethod.getWeldMethodId());
        this.name.set(weldMethod.getName());
        this.code.set(weldMethod.getCode());

    }

    //Getters and Setters

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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
}
