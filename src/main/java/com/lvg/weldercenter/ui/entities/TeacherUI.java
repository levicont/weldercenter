package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Teacher;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 17.12.14.
 */
public class TeacherUI extends GenericEntityUI {

    private final SimpleStringProperty surname = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty secname = new SimpleStringProperty();
    private final SimpleStringProperty nameShort = new SimpleStringProperty();
    private final SimpleStringProperty secnameShort = new SimpleStringProperty();

    public TeacherUI(){
        this.id.set(0);
        this.surname.set("");
        this.name.set("");
        this.secname.set("");
        this.nameShort.set("");
        this.secnameShort.set("");
    }

    public TeacherUI(Teacher teacher){
        this.id.set(teacher.getTeacherId());
        this.surname.set(teacher.getSurname());
        this.name.set(teacher.getName());
        this.secname.set(teacher.getSecname());
        if((name!=null) && (!name.get().isEmpty())){
            this.nameShort.set(name.get().substring(0,1).toUpperCase()+".");
        }
        if((secname!=null) && (!secname.get().isEmpty())){
            this.secnameShort.set(secname.get().substring(0,1).toUpperCase()+".");
        }
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getNameShort() {
        return nameShort.get();
    }

    public SimpleStringProperty nameShortProperty() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort.set(nameShort);
    }

    public String getSecnameShort() {
        return secnameShort.get();
    }

    public SimpleStringProperty secnameShortProperty() {
        return secnameShort;
    }

    public void setSecnameShort(String secnameShort) {
        this.secnameShort.set(secnameShort);
    }

    public String getFullTeacherName(){
        return this.getSurname()+" "+this.getName()+" "+this.getSecname();
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
    public String getFormatTeacherFullName(String patternString){
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
        return getFullTeacherName();
    }

    @Override
    public String toString() {
        return getFullTeacherName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TeacherUI teacherUI = (TeacherUI) o;

        if (name != null ? !name.equals(teacherUI.name) : teacherUI.name != null) return false;
        if (secname != null ? !secname.equals(teacherUI.secname) : teacherUI.secname != null) return false;
        if (surname != null ? !surname.equals(teacherUI.surname) : teacherUI.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secname != null ? secname.hashCode() : 0);
        return result;
    }
}
