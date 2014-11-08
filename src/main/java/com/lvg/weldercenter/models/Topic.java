package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 16.10.2014.
 */
@Entity
@Table(name = "topic")
public class Topic implements Serializable{


    private static final long serialVersionUID = 2400513036359077252L;
    private Long topicId;
    private Integer orderIndex;
    private String title;
    private String description;
    private Double timelong;

    private List<Section> sections = new ArrayList<Section>();
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

    @ManyToMany
    @JoinTable(name = "section_topic",
            joinColumns = {@JoinColumn(name = "id_topic")},
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

        Topic topic = (Topic) o;

        if (!timelong.equals(topic.timelong)) return false;
        if (!title.equals(topic.title)) return false;
        if (!topicId.equals(topic.topicId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topicId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + timelong.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", title='" + title + '\'' +
                ", timelong=" + timelong +
                '}';
    }
}
