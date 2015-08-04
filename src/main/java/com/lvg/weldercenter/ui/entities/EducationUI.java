package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Education;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor on 04.08.2015.
 */
public class EducationUI extends GenericEntityUI {

    private final SimpleStringProperty type = new SimpleStringProperty();

    public EducationUI(){
        id.set(0);
        type.set("");
    }

    public EducationUI(Education edu){
        id.set(edu.getEducationId());
        type.set(edu.getType());
    }

    //Getters and Setters

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EducationUI that = (EducationUI) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    @Override
    public String toString() {
        return getType();
    }
}
