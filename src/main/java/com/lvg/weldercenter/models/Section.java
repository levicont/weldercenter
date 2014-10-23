package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "section")
public class Section {

    private Long sectionId;
    private Integer orderIndex;
    private String title;
    private String description;

    private List<Curriculum> curriculums = new ArrayList<Curriculum>();
    private List<Topic> topics = new ArrayList<Topic>();

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id_section")
    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
    @Column(name = "order_index")
    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
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

    @ManyToMany
    @JoinTable(name = "curriculum_section",
            joinColumns = {@JoinColumn(name = "id_section")},
            inverseJoinColumns = {@JoinColumn(name = "id_curriculum")})
    public List<Curriculum> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    @ManyToMany
    @JoinTable(name = "section_topic",
            joinColumns = {@JoinColumn(name = "id_section")},
            inverseJoinColumns = {@JoinColumn(name = "id_topic")})
    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
