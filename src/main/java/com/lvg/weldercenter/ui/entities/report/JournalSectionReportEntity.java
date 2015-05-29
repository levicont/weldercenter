package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.SectionUI;
import com.lvg.weldercenter.ui.entities.TopicUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 29.05.2015.
 */
public class JournalSectionReportEntity {

    private String sectionTitle = "";
    private List<JournalTopicReportEntity> topics = new ArrayList<JournalTopicReportEntity>();

    public JournalSectionReportEntity(SectionUI sectionUI){
        sectionTitle = sectionUI.getTitle();
        initTopics(sectionUI.getTopics());
    }

    private void initTopics(List<TopicUI> topicUIs){
        topics.clear();
        if (topicUIs==null || topicUIs.isEmpty())
            return;
        for (TopicUI topic: topicUIs){
            topics.add(new JournalTopicReportEntity(topic,sectionTitle));
        }
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public List<JournalTopicReportEntity> getTopics() {
        return topics;
    }
}
