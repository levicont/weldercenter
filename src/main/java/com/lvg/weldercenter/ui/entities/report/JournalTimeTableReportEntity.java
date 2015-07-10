package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TimeTableUtil;
import com.lvg.weldercenter.ui.util.managers.TimeTableUtilManager;

import java.util.List;

/**
 * Created by Victor on 28.05.2015.
 */
public class JournalTimeTableReportEntity extends GenericReportEntity{

    private String teachers = "";
    private String section = "";
    private String topic = "";
    private String date = "";
    private String hours = "";

    private String curriculumUI;
    private TimeTableUtil timeTableUtil = new TimeTableUtilManager();
    private JournalUI journalUI;

    public JournalTimeTableReportEntity(JournalUI journalUI, TopicUI topicUI, SectionUI sectionUI) {

        this.journalUI = journalUI;
        this.curriculumUI = journalUI.getCurriculum();
        this.section = sectionUI.getTitle();
        this.topic = topicUI.getTitle();
        this.date = topicUI.getDateFormat()+DATE_SUFFIX;
        this.hours = topicUI.getTimeLong()+"";
        initTeachers(journalUI);

    }

    private void initTeachers(JournalUI journalUI){
        if (journalUI==null)
            return;
        StringBuilder teachersList = new StringBuilder();
        for (TeacherUI t : journalUI.getTeachers()){
            teachersList.append(t.getFormatTeacherFullName("SUR-nn-sec")+", ");
        }
        if (teachersList.length()>0)
            teachersList.deleteCharAt(teachersList.lastIndexOf(","));
        this.teachers = teachersList.toString();
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
