package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Qualification;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 04.08.15.
 */
public class QualificationUI extends GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();

    public QualificationUI(){
        id.set(0);
        type.set("");
    }

    public QualificationUI(Qualification qualification){
        id.set(qualification.getQualificationId());
        type.set(qualification.getType());
    }

    // Getters and setters


    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QualificationUI that = (QualificationUI) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getType();
    }
}
