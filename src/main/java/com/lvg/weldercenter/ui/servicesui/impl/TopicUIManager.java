package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.exceptions.WelderException;
import com.lvg.weldercenter.models.Topic;
import com.lvg.weldercenter.services.TopicService;
import com.lvg.weldercenter.ui.entities.CurriculumUI;
import com.lvg.weldercenter.ui.entities.SectionUI;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.servicesui.SectionServiceUI;
import com.lvg.weldercenter.ui.servicesui.TopicServiceUI;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.08.2015.
 */
public class TopicUIManager implements TopicServiceUI {
    private final Logger LOGGER = Logger.getLogger(TopicUIManager.class);
    @Autowired
    TopicService topicService;

    @Autowired
    SectionServiceUI sectionServiceUI;


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

    @Override
    public Long saveTopicUI(TopicUI topicUI, SectionUI parentSection, CurriculumUI superParent) throws WelderException {
        if (topicUI == null)
            throw new WelderException("SAVE TOPIC_UI: section is null");
        TopicUI updTopic = parentSection.getTopics().get(parentSection.getTopics().indexOf(topicUI));
        if (updTopic == null)
            throw new WelderException("SAVE TOPIC_UI: topic not find in parent section");

        Topic topic = getTopicFromUIModel(updTopic);
        Long id;
        if (topic.getTopicId() != null && topic.getTopicId() !=0){
            topicService.update(topic);
            id = topic.getTopicId();
            LOGGER.debug("SAVE TOPIC_UI: topic has been updated in DB");
        }else {
            id = topicService.insert(topic);
            updTopic.setId(id);
            LOGGER.debug("SAVE TOPIC_UI: topic has been inserted in DB");
        }
        sectionServiceUI.saveSectionUI(parentSection, superParent);
        return id;
    }

    @Override
    public void deleteTopicUI(TopicUI topicUI) throws WelderException {
        if (topicUI == null)
            throw new WelderException("DELETE TOPIC_UI: topic is null");
        Topic topic = getTopicFromUIModel(topicUI);
        if (topic.getTopicId() != null && topic.getTopicId() != 0){
            topicService.delete(topic);
            LOGGER.debug("DELETE TOPIC_UI: topic has been deleted from DB");
        }
    }

    private void updateTopicFromUIModel(Topic updTopic, TopicUI uiModel){
        updTopic.setTitle(uiModel.getTitle());
        updTopic.setDescription(uiModel.getDecription());
        updTopic.setOrderIndex(uiModel.getOrder());
        updTopic.setTimelong(uiModel.getTimeLong());
    }
}
