package com.lvg.weldercenter.models;

import javax.persistence.*;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "topic")
public class Topic {

    private Long topicId;
    private Integer orderIndex;
    private String title;
    private String description;
    private Double timelong;

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_topic")
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
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

    public Double getTimelong() {
        return timelong;
    }

    public void setTimelong(Double timelong) {
        this.timelong = timelong;
    }
}
