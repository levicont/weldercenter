package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.TopicUI;

/**
 * Created by Victor on 29.05.2015.
 */
public class JournalTopicReportEntity extends GenericReportEntity{

    private String topicTitle = "";
    private String topicDate = "";
    private String topicHours = "";

    private String section = "";

    public JournalTopicReportEntity(TopicUI topicUI,String sectionTitle){
        topicTitle = topicUI.getTitle();
        topicDate = topicUI.getDateFormat();
        topicHours = topicUI.getTimeLong()+"";
        section = sectionTitle;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getTopicDate() {
        return topicDate;
    }

    public String getTopicHours() {
        return topicHours;
    }

    public String getSection() {
        return section;
    }
}
