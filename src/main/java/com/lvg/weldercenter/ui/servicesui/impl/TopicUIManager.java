package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Topic;
import com.lvg.weldercenter.services.TopicService;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.servicesui.TopicServiceUI;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.08.2015.
 */
public class TopicUIManager implements TopicServiceUI {

    @Autowired
    TopicService topicService;


    @Override
    public Topic getTopicFromUIModel(TopicUI topicUI) {
        if (topicUI == null)
            return null;
        Topic topic = topicService.get(topicUI.getId());
        if (topic != null){
            updateTopicFromUIModel(topic, topicUI);
        }else{
            topic = new Topic();
            updateTopicFromUIModel(topic, topicUI);
        }
        return topic;
    }

    @Override
    public List<Topic> getTopicsListFromObsList(ObservableList<TopicUI> obsList) {
        if (obsList == null)
            return null;
        List<Topic> topics = new ArrayList<>();
        for (TopicUI tUI : obsList){
            Topic topic = getTopicFromUIModel(tUI);
            if (topic != null) {
                topics.add(getTopicFromUIModel(tUI));
            }
        }
        return topics;
    }

    private void updateTopicFromUIModel(Topic updTopic, TopicUI uiModel){
        updTopic.setTitle(uiModel.getTitle());
        updTopic.setDescription(uiModel.getDecription());
        updTopic.setOrderIndex(uiModel.getOrder());
        updTopic.setTimelong(uiModel.getTimeLong());
    }
}
