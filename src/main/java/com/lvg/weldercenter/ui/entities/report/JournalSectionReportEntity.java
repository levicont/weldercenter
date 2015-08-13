package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.SectionUI;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.managers.TimeTableUtilManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 29.05.2015.
 */
public class JournalSectionReportEntity extends GenericReportEntity{

    private String sectionTitle = "";
    private List<JournalTopicReportEntity> topics = new ArrayList<JournalTopicReportEntity>();
    private Double sectionTotalHours = 0.0;

    public JournalSectionReportEntity(SectionUI sectionUI, JournalUI journalUI){
        sectionTitle = sectionUI.getTitle();
        initTopics(sectionUI.getTopics(), journalUI, sectionUI);
    }

    private void initTopics(List<TopicUI> topicUIs, JournalUI journalUI, SectionUI sectionUI){
        topics.clear();
        if (topicUIs==null || topicUIs.isEmpty() || journalUI==null)
            return;
        if(journalUI.getCurriculum()==null || journalUI.getDateBegin()==null)
            return;

        List<TopicUI> timeTable = new TimeTableUtilManager().getTimeTable(journalUI.getCurriculum(),
                DateUtil.getLocalDate(journalUI.getDateBegin()));

        for (TopicUI topic: timeTable){
            for (TopicUI t: topicUIs){
                if (t.getTitle().equals(topic.getTitle()))
                    topics.add(new JournalTopicReportEntity(topic,sectionTitle));
            }

        }
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public List<JournalTopicReportEntity> getTopics() {
        return topics;
    }

    public Double getSectionTotalHours() {
        Double result = 0.0;
        for (JournalTopicReportEntity topicUI: getTopics()){
            try{
                result+=Double.parseDouble(topicUI.getTopicHours());
            }catch (NumberFormatException ex){
                continue;
            }

        }
        sectionTotalHours = result;
        return sectionTotalHours;
    }
}
