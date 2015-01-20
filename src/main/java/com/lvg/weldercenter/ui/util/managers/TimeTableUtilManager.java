package com.lvg.weldercenter.ui.util.managers;

import com.lvg.weldercenter.models.Curriculum;
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
            topics.addAll(section.getTopics());
        }
        double currentHours = 0.0;
        double topicHours = 0.0;
        double diffHours = 0;
        LocalDate currDate = dateStart;

        for (Topic topic : topics){
            topicHours = topic.getTimelong();
            if (topic.getTimelong() >= HOURS_IN_WORK_DAY){
                while (topicHours > 0){
                    if (topicHours >= HOURS_IN_WORK_DAY){
                        TopicUI topicUI = new TopicUI(topic,journal);
                        topicUI.setTimeLong(HOURS_IN_WORK_DAY-currentHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        topicHours = topicHours - topicUI.getTimeLong();
                        currentHours = currentHours+topicUI.getTimeLong();
                        currDate = getNextWorkDay(currDate);
                        if (currentHours == HOURS_IN_WORK_DAY)
                            currentHours = 0;
                    }else {
                        TopicUI topicUI = new TopicUI(topic,journal);
                        topicUI.setTimeLong(topicHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        currentHours = currentHours + topicUI.getTimeLong();
                        topicHours = topicHours - HOURS_IN_WORK_DAY;
                    }
                }
            }
            if (topic.getTimelong() < HOURS_IN_WORK_DAY) {
                while (topicHours > 0 ) {
                    if ((topicHours + currentHours) < HOURS_IN_WORK_DAY) {
                        TopicUI topicUI = new TopicUI(topic, journal);
                        topicUI.setTimeLong(topicHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        currentHours = currentHours + topicHours;
                        topicHours = 0;
                    } else {
                        TopicUI topicUI = new TopicUI(topic, journal);
                        topicUI.setTimeLong(HOURS_IN_WORK_DAY - currentHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        currentHours = currentHours + topicUI.getTimeLong();
                        currDate = getNextWorkDay(currDate);
                        topicHours = topicHours - topicUI.getTimeLong();
                        if(currentHours == HOURS_IN_WORK_DAY)
                            currentHours = 0;

                    }
                }
            }

        }
        return result;
    }

@Override
    public List<TopicUI> getTimeTable(String curriculumTitle, LocalDate startDate){
        List<Curriculum> curriculums = curriculumService.getAll();
        List<TopicUI> result = new ArrayList<TopicUI>();
        Curriculum curriculum = null;
        for (Curriculum cur : curriculums){
            if (curriculumTitle.equals(cur.getTitle())){
                curriculum = cur;
                break;
            }

        }
        if(null == curriculum)
            return result;

        List<Section> sections = curriculum.getSections();
        List<Topic> topics = new ArrayList<Topic>();
        LocalDate dateStart = startDate;

        for (Section section : sections){
            topics.addAll(section.getTopics());
        }
        double currentHours = 0.0;
        double topicHours = 0.0;
        double diffHours = 0;
        LocalDate currDate = dateStart;

        for (Topic topic : topics){
            topicHours = topic.getTimelong();
            if (topic.getTimelong() >= HOURS_IN_WORK_DAY){
                while (topicHours > 0){
                    if (topicHours >= HOURS_IN_WORK_DAY){
                        TopicUI topicUI = new TopicUI(topic);
                        topicUI.setTimeLong(HOURS_IN_WORK_DAY-currentHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        topicHours = topicHours - topicUI.getTimeLong();
                        currentHours = currentHours+topicUI.getTimeLong();
                        currDate = getNextWorkDay(currDate);
                        if (currentHours == HOURS_IN_WORK_DAY)
                            currentHours = 0;
                    }else {
                        TopicUI topicUI = new TopicUI(topic);
                        topicUI.setTimeLong(topicHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        currentHours = currentHours + topicUI.getTimeLong();
                        topicHours = topicHours - HOURS_IN_WORK_DAY;
                    }
                }
            }
            if (topic.getTimelong() < HOURS_IN_WORK_DAY) {
                while (topicHours > 0 ) {
                    if ((topicHours + currentHours) < HOURS_IN_WORK_DAY) {
                        TopicUI topicUI = new TopicUI(topic);
                        topicUI.setTimeLong(topicHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        currentHours = currentHours + topicHours;
                        topicHours = 0;
                    } else {
                        TopicUI topicUI = new TopicUI(topic);
                        topicUI.setTimeLong(HOURS_IN_WORK_DAY - currentHours);
                        topicUI.setDate(DateUtil.getDate(currDate));
                        topicUI.setDateFormat(DateUtil.format(topicUI.getDate()));
                        result.add(topicUI);
                        currentHours = currentHours + topicUI.getTimeLong();
                        currDate = getNextWorkDay(currDate);
                        topicHours = topicHours - topicUI.getTimeLong();
                        if(currentHours == HOURS_IN_WORK_DAY)
                            currentHours = 0;

                    }
                }
            }

        }
        return result;
    }

    private LocalDate getNextWorkDay(LocalDate date){
        LocalDate result = date.plusDays(1l);
        while (DateUtil.isHoliday(result)){
            result = result.plusDays(1l);
        }
        return result;
    }
}
