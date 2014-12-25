package com.lvg.weldercenter.ui.util.managers;

import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.Section;
import com.lvg.weldercenter.models.Topic;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.services.JournalService;
import com.lvg.weldercenter.services.SectionService;
import com.lvg.weldercenter.services.TopicService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TimeTableUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 17.12.14.
 */
public class TimeTableUtilManager implements TimeTableUtil {

    private JournalService journalService = ServiceFactory.getJournalService();
    private CurriculumService curriculumService = ServiceFactory.getCurriculumService();
    private SectionService sectionService = ServiceFactory.getSectionService();
    private TopicService topicService = ServiceFactory.getTopicService();


    @Override
    public List<TopicUI> getTimeTable(Journal journal) {
        List<TopicUI> result = new ArrayList<TopicUI>();
        List<Section> sections = journal.getCurriculum().getSections();
        List<Topic> topics = new ArrayList<Topic>();
        LocalDate dateStart = DateUtil.getLocalDate(journal.getDateBegin());

        for (Section section : sections){
            topics.addAll(topics.size(), section.getTopics());
        }
        double currentHours = 0.0;
        double topicHours = 0.0;
        double diffHours = 0;
        for (Topic topic : topics){
            TopicUI topicUI = new TopicUI(topic, journal);
            topicHours = topicUI.getTimeLong();

            if(topicHours>HOURS_IN_WORK_DAY){
                diffHours = topicHours - HOURS_IN_WORK_DAY;
                currentHours = HOURS_IN_WORK_DAY;
//TODO make correct method
                while (diffHours>0) {
                    topicUI.setTimeLong(diffHours);
                    topicUI.setDate(DateUtil.getDate(dateStart));

                    result.add(topicUI);
                    currentHours = HOURS_IN_WORK_DAY-diffHours;
                }




                dateStart = dateStart.plusDays(1);
                currentHours = 0;
                topicUI.setDate(DateUtil.getDate(dateStart));
                topicUI.setDateFormat(DateUtil.format(dateStart));
            }else {
                topicUI.setDate(DateUtil.getDate(dateStart));
                topicUI.setDateFormat(DateUtil.format(dateStart));
            }
            result.add(topicUI);
        }
        return result;
    }
}
