package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "teacher")
public class Teacher {

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
}
