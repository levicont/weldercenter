package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.models.Section;
import com.lvg.weldercenter.models.Topic;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 25.01.15.
 */
public class SectionUI extends GenericEntityUI {
    private final SimpleIntegerProperty orderIndex = new SimpleIntegerProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();

    //private List<Curriculum> curriculums = new ArrayList<Curriculum>();
    private final SimpleListProperty<TopicUI> topics = new SimpleListProperty<TopicUI>();

    public SectionUI(){
        this.id.set(0);
        this.orderIndex.set(0);
        this.title.set("");
        this.description.set("");
        this.topics.set(FXCollections.observableArrayList(new ArrayList<TopicUI>()));
    }

    public SectionUI(Section section){
        this.id.set(section.getSectionId());
        this.orderIndex.set(section.getOrderIndex());
        this.title.set(section.getTitle());
        this.description.set(section.getDescription());
        this.topics.set(FXCollections.observableArrayList(getTopicUIList(section.getTopics())));
    }

    private List<TopicUI> getTopicUIList(List<Topic> dbTopicList){
        List<TopicUI> result = new ArrayList<TopicUI>();
        for (Topic t: dbTopicList){
            TopicUI topicUI = new TopicUI(t);
            result.add(topicUI);
        }
        return result;
    }

    //Getter and Setters


    public int getOrderIndex() {
        return orderIndex.get();
    }

    public SimpleIntegerProperty orderIndexProperty() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex.set(orderIndex);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public ObservableList<TopicUI> getTopics() {
        return topics.get();
    }

    public SimpleListProperty<TopicUI> topicsProperty() {
        return topics;
    }

    public void setTopics(ObservableList<TopicUI> topics) {
        this.topics.set(topics);
    }

    @Override
    public String toString() {
        return "SectionUI{" +
                "orderIndex=" + orderIndex +
                ", title=" + title +
                ", description=" + description +
                ", topics=" + topics +
                '}';
    }
}
