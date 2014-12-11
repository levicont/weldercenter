package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "journal")
public class Journal implements Serializable{


    private static final long serialVersionUID = 523878238992105131L;
    private Long journalId;
    private String number;
    private Date dateBegin;
    private Date dateEnd;

    private Curriculum curriculum;

    private List<Welder> welders = new ArrayList<Welder>();
    private List<Teacher> teachers = new ArrayList<Teacher>();
    private List<PersonalProtocol> personalProtocols = new ArrayList<PersonalProtocol>();
    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_journal")
    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "date_begin")
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }


    @Column(name = "date_end")
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @OneToMany(targetEntity = PersonalProtocol.class, mappedBy = "journal")
    public List<PersonalProtocol> getPersonalProtocols() {
        return personalProtocols;
    }

    public void setPersonalProtocols(List<PersonalProtocol> personalProtocols) {
        this.personalProtocols = personalProtocols;
    }

    @ManyToOne(targetEntity = Curriculum.class)
    @JoinColumn(name = "id_curriculum")
    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    @ManyToMany
    @JoinTable(name = "journal_welder",
        joinColumns = {@JoinColumn(name = "id_journal")},
        inverseJoinColumns = {@JoinColumn(name = "id_welder")})
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }

    @ManyToMany
    @JoinTable(name = "journal_teacher",
            joinColumns = {@JoinColumn(name = "id_journal")},
            inverseJoinColumns = {@JoinColumn(name = "id_teacher")})
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (!dateBegin.equals(journal.dateBegin)) return false;
        if (!dateEnd.equals(journal.dateEnd)) return false;
        if (!journalId.equals(journal.journalId)) return false;
        if (!number.equals(journal.number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = journalId.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + dateBegin.hashCode();
        result = 31 * result + dateEnd.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journalId=" + journalId +
                ", number='" + number + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
