package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldDetail;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class WeldDetailUI extends GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleStringProperty code = new SimpleStringProperty();

    public WeldDetailUI(){
        this.id.set(0);
        this.type.set("");
        this.code.set("");
    }

    public WeldDetailUI(WeldDetail weldDetail){
        this.id.set(weldDetail.getWeldDetailId());
        this.type.set(weldDetail.getType());
        this.code.set(weldDetail.getCode());
    }

    //Getters and Setters
    public String getDetailTypeCode(){
        return "( "+code.get()+" ) "+type.get();
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
        return getDetailTypeCode();
    }
}
