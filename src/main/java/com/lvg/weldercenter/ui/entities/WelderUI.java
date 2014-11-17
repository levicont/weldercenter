package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.*;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 14.11.14.
 */
public class WelderUI {

    private SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty surname = new SimpleStringProperty();
    private final SimpleStringProperty secname = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> birthday = new SimpleObjectProperty<Date>();

    private final SimpleStringProperty docNumber = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> dateBegin = new SimpleObjectProperty<Date>();

    private final SimpleStringProperty education = new SimpleStringProperty();
    private final SimpleStringProperty qualification = new SimpleStringProperty();
    private final SimpleStringProperty organization = new SimpleStringProperty();
    private final SimpleStringProperty job = new SimpleStringProperty();

    //TODO in future init this fields
    private List<String> journals;
    private List<String> personalProtocols;


    private final SimpleListProperty<String> weldMethods = new SimpleListProperty<String>();


    public WelderUI(com.lvg.weldercenter.models.Welder welder){

        this.id.set(welder.getWelderId());
        this.name.set(welder.getName());
        this.surname.set(welder.getSurname());
        this.secname.set(welder.getSecname());
        this.birthday.set(welder.getBirthday());
        this.dateBegin.set(welder.getDateBegin());
        this.docNumber.set(welder.getDocNumber());

        this.education.set(welder.getEducation().getType());
        this.qualification.set(welder.getQualification().getType());
        this.job.set(welder.getJob().getName());
        this.organization.set(welder.getOrganization().getName());

        this.weldMethods.set(getWeldMethodsNames(welder.getWeldMethods()));

    }

    public WelderUI(){

        this.id.set(0);
        this.name.set("noname");
        this.surname.set("no surname");
        this.secname.set("no secname");
        this.birthday.set(new Date());
        this.dateBegin.set(new Date());
        this.docNumber.set("no number");

        this.education.set("no education");
        this.qualification.set("no qualification");
        this.job.set("no job");
        this.organization.set("no organization");


        this.weldMethods.set(FXCollections.observableArrayList(new ArrayList<String>()));

    }

    private ObservableList<String> getWeldMethodsNames(List<WeldMethod> weldMethods){
        ObservableList<String> weldMethodNames = FXCollections.observableArrayList();
        for (WeldMethod wm : weldMethods){
            weldMethodNames.add(wm.getName());
        }

        return weldMethodNames;
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

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getSecname() {
        return secname.get();
    }

    public SimpleStringProperty secnameProperty() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname.set(secname);
    }

    public Date getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<Date> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday.set(birthday);
    }

    public String getDocNumber() {
        return docNumber.get();
    }

    public SimpleStringProperty docNumberProperty() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber.set(docNumber);
    }

    public Date getDateBegin() {
        return dateBegin.get();
    }

    public SimpleObjectProperty<Date> dateBeginProperty() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin.set(dateBegin);
    }

    public String getEducation() {
        return education.get();
    }

    public SimpleStringProperty educationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public String getQualification() {
        return qualification.get();
    }

    public SimpleStringProperty qualificationProperty() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification.set(qualification);
    }

    public String getOrganization() {
        return organization.get();
    }

    public SimpleStringProperty organizationProperty() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization.set(organization);
    }

    public String getJob() {
        return job.get();
    }

    public SimpleStringProperty jobProperty() {
        return job;
    }

    public void setJob(String job) {
        this.job.set(job);
    }

    public ObservableList<String> getWeldMethods() {
        return weldMethods.get();
    }

    public SimpleListProperty<String> weldMethodsProperty() {
        return weldMethods;
    }

    public void setWeldMethods(ObservableList<String> weldMethods) {
        this.weldMethods.set(weldMethods);
    }
}
