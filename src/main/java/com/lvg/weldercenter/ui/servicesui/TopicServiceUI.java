package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Topic;
import com.lvg.weldercenter.ui.entities.TopicUI;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Victor on 06.08.2015.
 */
public interface TopicServiceUI {
    public Topic getTopicFromUIModel(TopicUI topicUI);
    public List<Topic> getTopicsListFromObsList(ObservableList<TopicUI> obsList);
}
