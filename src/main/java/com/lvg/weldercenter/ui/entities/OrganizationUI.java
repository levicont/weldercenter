package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Organization;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 25.01.15.
 */
public class OrganizationUI extends GenericEntityUI {

    private final SimpleStringProperty adress = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty phone = new SimpleStringProperty();

    public OrganizationUI(){
        this.id.set(0);
        this.adress.set("");
        this.name.set("");
        this.phone.set("");
    }

    public OrganizationUI(Organization organization){
        this.id.set(organization.getOrganizationId());
        this.adress.set(organization.getAdress());
        this.name.set(organization.getName());
        this.phone.set(organization.getPhone());
    }

    //Getters and Setters


    public String getAdress() {
        return adress.get();
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
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

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}