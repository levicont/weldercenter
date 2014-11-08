package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable{


    private static final long serialVersionUID = 94300681078874266L;
    private Long teacherId;
    private String name;
    private String surname;
    private String secname;

    private List<Journal> journals = new ArrayList<Journal>();


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_teacher")
    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    @ManyToMany
    @JoinTable(name = "journal_teacher",
            joinColumns = {@JoinColumn(name = "id_teacher")},
            inverseJoinColumns = {@JoinColumn(name = "id_journal")})
    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (!name.equals(teacher.name)) return false;
        if (!secname.equals(teacher.secname)) return false;
        if (!surname.equals(teacher.surname)) return false;
        if (!teacherId.equals(teacher.teacherId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teacherId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + secname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secname='" + secname + '\'' +
                '}';
    }
}
