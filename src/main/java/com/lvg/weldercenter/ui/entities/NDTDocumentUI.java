package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.NDTDocument;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class NDTDocumentUI extends GenericEntityUI {

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty fullName = new SimpleStringProperty();

    public NDTDocumentUI(){
        this.id.set(0);
        this.name.set("");
        this.fullName.set("");
    }

    public NDTDocumentUI(NDTDocument ndtDocument){
        this.id.set(ndtDocument.getNdtDocumentId());
        this.name.set(ndtDocument.getName());
        this.fullName.set(ndtDocument.getFullName());
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

    public String getFullName() {
        return fullName.get();
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
}
