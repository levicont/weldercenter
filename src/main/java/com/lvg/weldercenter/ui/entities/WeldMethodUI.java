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
    public String getNameCode(){
        return name.get()+" ("+code.get()+")";
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

    @Override
    public String toString() {
        return getNameCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WeldMethodUI that = (WeldMethodUI) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
