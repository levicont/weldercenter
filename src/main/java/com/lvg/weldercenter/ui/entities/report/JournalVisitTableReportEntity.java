package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TimeTableUtil;
import com.lvg.weldercenter.ui.util.managers.TimeTableUtilManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 27.05.2015.
 */
public class JournalVisitTableReportEntity extends JournalReportEntity {
    private final String DAY_PREFIX_FIELD_NAME = "day";
    private final String MONTH_PREFIX_FIELD_NAME = "month";

    private String day0 = "";
    private String day1 = "";
    private String day2 = "";
    private String day3 = "";
    private String day4 = "";
    private String day5 = "";
    private String day6 = "";
    private String day7 = "";
    private String day8 = "";
    private String day9 = "";
    private String day10 = "";
    private String day11 = "";
    private String day12 = "";
    private String day13 = "";
    private String day14 = "";

    private String month0 = "";
    private String month1 = "";
    private String month2 = "";
    private String month3 = "";
    private String month4 = "";
    private String month5 = "";
    private String month6 = "";
    private String month7 = "";
    private String month8 = "";
    private String month9 = "";
    private String month10 = "";
    private String month11 = "";
    private String month12 = "";
    private String month13 = "";
    private String month14 = "";
    private String month15 = "";


    private String curriculumUI;
    private TimeTableUtil timeTableUtil = new TimeTableUtilManager();
    private JournalUI journalUI;


    public JournalVisitTableReportEntity(JournalUI journalUI, WelderUI welderUI) {
        super(journalUI, welderUI);
        this.journalUI = journalUI;
        curriculumUI = journalUI.getCurriculum();
        initDays(curriculumUI, journalUI);
        //initMonths(curriculumUI);

    }

    private void initDays(String curriculumUI, JournalUI journalUI){
        if (curriculumUI==null || journalUI==null)
            return;
        List<TopicUI> topicUIs = timeTableUtil.getTimeTable(curriculumUI, DateUtil.getLocalDate(journalUI.getDateBegin()));
        List<Date> dates = new ArrayList<Date>();
        for (TopicUI topicUI : topicUIs){
            if (!dates.contains(topicUI.getDate())){
                dates.add(topicUI.getDate());
            }
        }
        setDays(dates);
        setMonths(dates);

    }

    private void setDays(List<Date> dates){
        Field field;
        for (int i =0; i<dates.size();i++){
            try {
                field = this.getClass().getDeclaredField(""+DAY_PREFIX_FIELD_NAME+i);
                field.set(this, DateUtil.getLocalDate(dates.get(i)).getDayOfMonth() + "");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                break;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void setMonths(List<Date> dates){
        Field field;
        for (int i =0; i<dates.size();i++){
            try {
                field = this.getClass().getDeclaredField(""+MONTH_PREFIX_FIELD_NAME+i);
                field.set(this, DateUtil.getLocalDate(dates.get(i)).getMonthValue() + "");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                break;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }

    public String getDay8() {
        return day8;
    }

    public void setDay8(String day8) {
        this.day8 = day8;
    }

    public String getDay9() {
        return day9;
    }

    public void setDay9(String day9) {
        this.day9 = day9;
    }

    public String getDay10() {
        return day10;
    }

    public void setDay10(String day10) {
        this.day10 = day10;
    }

    public String getDay11() {
        return day11;
    }

    public void setDay11(String day11) {
        this.day11 = day11;
    }

    public String getDay12() {
        return day12;
    }

    public void setDay12(String day12) {
        this.day12 = day12;
    }

    public String getDay13() {
        return day13;
    }

    public void setDay13(String day13) {
        this.day13 = day13;
    }

    public String getDay14() {
        return day14;
    }

    public void setDay14(String day14) {
        this.day14 = day14;
    }

    public String getDay0() {
        return day0;
    }

    public void setDay0(String day0) {
        this.day0 = day0;
    }

    public String getMonth1() {
        return month1;
    }

    public void setMonth1(String month1) {
        this.month1 = month1;
    }

    public String getMonth0() {
        return month0;
    }

    public void setMonth0(String month0) {
        this.month0 = month0;
    }

    public String getMonth2() {
        return month2;
    }

    public void setMonth2(String month2) {
        this.month2 = month2;
    }

    public String getMonth3() {
        return month3;
    }

    public void setMonth3(String month3) {
        this.month3 = month3;
    }

    public String getMonth4() {
        return month4;
    }

    public void setMonth4(String month4) {
        this.month4 = month4;
    }

    public String getMonth5() {
        return month5;
    }

    public void setMonth5(String month5) {
        this.month5 = month5;
    }

    public String getMonth6() {
        return month6;
    }

    public void setMonth6(String month6) {
        this.month6 = month6;
    }

    public String getMonth7() {
        return month7;
    }

    public void setMonth7(String month7) {
        this.month7 = month7;
    }

    public String getMonth8() {
        return month8;
    }

    public void setMonth8(String month8) {
        this.month8 = month8;
    }

    public String getMonth9() {
        return month9;
    }

    public void setMonth9(String month9) {
        this.month9 = month9;
    }

    public String getMonth10() {
        return month10;
    }

    public void setMonth10(String month10) {
        this.month10 = month10;
    }

    public String getMonth11() {
        return month11;
    }

    public void setMonth11(String month11) {
        this.month11 = month11;
    }

    public String getMonth12() {
        return month12;
    }

    public void setMonth12(String month12) {
        this.month12 = month12;
    }

    public String getMonth13() {
        return month13;
    }

    public void setMonth13(String month13) {
        this.month13 = month13;
    }

    public String getMonth14() {
        return month14;
    }

    public void setMonth14(String month14) {
        this.month14 = month14;
    }

    public String getMonth15() {
        return month15;
    }

    public void setMonth15(String month15) {
        this.month15 = month15;
    }
}
