package com.lvg.weldercenter.models;

import javax.persistence.*;

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
}
