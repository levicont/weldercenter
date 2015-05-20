package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.WeldMethod;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleListProperty;
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
public class WelderUI extends GenericEntityUI{

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty surname = new SimpleStringProperty();
    private final SimpleStringProperty secname = new SimpleStringProperty();
    private final SimpleStringProperty nameShort = new SimpleStringProperty();
    private final SimpleStringProperty secnameShort = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> birthday = new SimpleObjectProperty<Date>();

    private final SimpleStringProperty docNumber = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> dateBegin = new SimpleObjectProperty<Date>();

    private final SimpleStringProperty education = new SimpleStringProperty();
    private final SimpleStringProperty qualification = new SimpleStringProperty();
    private final SimpleStringProperty organization = new SimpleStringProperty();
    private final SimpleStringProperty job = new SimpleStringProperty();
    private final SimpleStringProperty address = new SimpleStringProperty();

    private final SimpleStringProperty birthdayFormat = new SimpleStringProperty();
    private final SimpleStringProperty dateBeginFormat = new SimpleStringProperty();

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
        this.birthdayFormat.set(DateUtil.format(birthday.get()));
        if(null == welder.getEducation()){
            this.education.set("");
        }else
            this.education.set(welder.getEducation().getType());
        if(null == welder.getQualification()){
            this.qualification.set("");
        }else
        this.qualification.set(welder.getQualification().getType());
        if(null == welder.getJob()){
            this.job.set("");
        }else
            this.job.set(welder.getJob().getName());
        if (null == welder.getOrganization()){
            this.organization.set("");
        }else
            this.organization.set(welder.getOrganization().getName());

        this.address.set(welder.getAddress());
        this.weldMethods.set(getWeldMethodsNames(welder.getWeldMethods()));
        if((name!=null) && (!name.get().isEmpty())){
            this.nameShort.set(name.get().substring(0,1).toUpperCase()+".");
        }
        if((secname!=null) && (!secname.get().isEmpty())){
            this.secnameShort.set(secname.get().substring(0,1).toUpperCase()+".");
        }


    }

    public WelderUI(){

        this.id.set(0);
        this.name.set("");
        this.surname.set("");
        this.secname.set("");
        this.nameShort.set("");
        this.secnameShort.set("");
        this.birthday.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateBegin.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.docNumber.set("");

        this.education.set("");
        this.qualification.set("");
        this.job.set("");
        this.organization.set("");
        this.address.set("");


        this.weldMethods.set(FXCollections.observableArrayList(new ArrayList<String>()));

    }

    private ObservableList<String> getWeldMethodsNames(List<WeldMethod> weldMethods){
        ObservableList<String> weldMethodNames = FXCollections.observableArrayList();
        for (WeldMethod wm : weldMethods){
            weldMethodNames.add(wm.getName());
        }

        return weldMethodNames;
    }
    /**
     *
     * @param patternString
     * "NN-SEC-SUR" - full name secname surname
     * "SUR-NN-SEC" - full surname name secname
     * "SUR-nn-sec" - full surname short name short secname
     * "nn-sec-SUR" - short name short secname full surname
     * default - "SUR-NN-SEC"
     *
     * @return
     */
    public String getFormatName(String patternString){
        if (patternString.equals("NN-SEC-SUR")){
            return this.getName()+" "+this.getSecname()+" "+this.getSurname();
        }
        if (patternString.equals("SUR-NN-SEC")){
            return this.getSurname()+" "+this.getName()+" "+this.getSecname();
        }
        if (patternString.equals("SUR-nn-sec")){
            return this.getSurname()+" "+this.getNameShort()+" "+this.getSecnameShort();
        }
        if (patternString.equals("nn-sec-SUR")){
            return this.getNameShort()+" "+this.getSecnameShort()+" "+this.getSurname();
        }
        return getSurnameNameSecname();
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

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
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

    public String getBirthdayFormat() {
        return DateUtil.format(birthday.get());
    }

    public void setBirthdayFormat(Date birthdayFormat) {
        this.birthdayFormat.set(DateUtil.format(birthdayFormat));
    }

    public SimpleStringProperty birthdayFormatProperty() {
        return birthdayFormat;
    }

    public String getNameShort() {
        return nameShort.get();
    }

    public SimpleStringProperty nameShortProperty() {
        return nameShort;
    }

    public String getSecnameShort() {
        return secnameShort.get();
    }

    public SimpleStringProperty secnameShortProperty() {
        return secnameShort;
    }

    public String getDateBeginFormat() {
        return DateUtil.format(dateBegin.get());
    }

    public SimpleStringProperty dateBeginFormatProperty() {
        return dateBeginFormat;
    }

    public String getFullName(){
        return id.get()+"\t"+surname.get()+"\t\t"+name.get()+"\t\t"+secname.get();
    }

    public String getSurnameNameSecname(){
        return surname.get()+" "+name.get()+" "+secname.get();
    }
    @Override
    public String toString() {
        return "\n WelderUI{" +
                "\n id=" + id.get() +
                ",\n name=" + name.get() +
                ",\n surname=" + surname.get() +
                ",\n secname=" + secname.get() +
                ",\n birthday=" + birthday.get() +
                ",\n docNumber=" + docNumber.get() +
                ", dateBegin=" + dateBegin.get() +
                ", education=" + education.get() +
                ", qualification=" + qualification.get() +
                ", organization=" + organization.get() +
                ", job=" + job.get() +
                ",\n weldMethods=" + weldMethods.get() +
                '}';
    }

}
