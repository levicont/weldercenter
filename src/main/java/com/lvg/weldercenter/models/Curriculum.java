package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "curriculum")
public class Curriculum implements Serializable{


    private static final long serialVersionUID = -4095312744432647608L;
    private Long curriculumId;
    private String title;
    private String description;

    private List<Section> sections = new ArrayList<Section>();
    private List<Journal> journals = new ArrayList<Journal>();

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_curriculum")
    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(targetEntity = Journal.class, mappedBy = "curriculum")
    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    @ManyToMany
    @JoinTable(name = "curriculum_section",
            joinColumns = {@JoinColumn(name = "id_curriculum")},
            inverseJoinColumns = {@JoinColumn(name = "id_section")})
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curriculum that = (Curriculum) o;

        if (!curriculumId.equals(that.curriculumId)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = curriculumId.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "curriculumId=" + curriculumId +
                ", title='" + title + '\'' +
                '}';
    }
}
