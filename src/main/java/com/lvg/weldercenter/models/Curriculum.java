package com.lvg.weldercenter.models;

import javax.persistence.*;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "curriculum")
public class Curriculum {

    private Long curriculumId;
    private String title;
    private String description;

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
}
