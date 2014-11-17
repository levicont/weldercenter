package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldMethod;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 16.11.14.
 */
public class WeldMethodUI {

    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty code = new SimpleStringProperty();

    public WeldMethodUI(WeldMethod weldMethod){
        this.id.set(weldMethod.getWeldMethodId());
        this.name.set(weldMethod.getName());
        this.code.set(weldMethod.getCode());

    }

    //Getters and Setters


    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

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
