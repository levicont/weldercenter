package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.TeacherUI;
import com.lvg.weldercenter.ui.entities.WeldMethodUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.util.DateUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 26.05.2015.
 */
public class JournalReportEntity extends GenericReportEntity{

    private final Map<String, Object> parameters = new HashMap<String, Object>(){{
        put(constants.JOURNAL_KEY_ID,null);
        put(constants.JOURNAL_KEY_NUMBER,null);
        put(constants.JOURNAL_KEY_DATE_BEGIN, null);
        put(constants.JOURNAL_KEY_DATE_BEGIN_FORMAT,null);
        put(constants.JOURNAL_KEY_DATE_END, null);
        put(constants.JOURNAL_KEY_DATE_END_FORMAT, null);
        put(constants.JOURNAL_KEY_CURRICULUM_TITLE, null);
        put(constants.JOURNAL_KEY_WELDERS_COUNT, null);
        put(constants.JOURNAL_KEY_TEACHERS, null);
    }};

    private String jrnNumber ="";
    private String jrnDateBegin="";
    private String jrnDateEnd="";
    private String jrnCurriculumTitle="";

    private String jrnWelderName="";
    private String jrnWelderSurname="";
    private String jrnWelderSecname="";
    private String jrnWelderEducation="";
    private String jrnWelderBirthday="";
    private String jrnWelderExperience="";
    private String jrnWelderWorkPlace="";
    private String jrnWelderWeldMethods="";
    private String jrnWelderHomeAdress="";


    public JournalReportEntity(JournalUI journalUI, WelderUI welderUI){
        this(journalUI);
        this.jrnNumber = journalUI.getNumber();
        this.jrnDateBegin = journalUI.getDateBeginFormat()+constants.GENERIC_DATE_SUFFIX;
        this.jrnDateEnd = journalUI.getDateEndFormat()+constants.GENERIC_DATE_SUFFIX;
        this.jrnCurriculumTitle = journalUI.getCurriculum();

        this.jrnWelderName = welderUI.getName();
        this.jrnWelderSurname = welderUI.getSurname();
        this.jrnWelderSecname = welderUI.getSecname();
        this.jrnWelderEducation = welderUI.getEducation();
        this.jrnWelderBirthday = welderUI.getBirthdayFormat()+constants.GENERIC_DATE_SUFFIX;
        this.jrnWelderExperience = getExpirience(welderUI.getDateBegin());
        this.jrnWelderWorkPlace = welderUI.getOrganization();
        this.jrnWelderHomeAdress = welderUI.getAddress();
        this.jrnWelderWeldMethods = getFormatedWeldMethods(welderUI.getWeldMethodUIList());

    }

    public JournalReportEntity(JournalUI journalUI){
        parameters.replace(constants.JOURNAL_KEY_ID,journalUI.getId());
        parameters.replace(constants.JOURNAL_KEY_NUMBER, journalUI.getNumber());
        parameters.replace(constants.JOURNAL_KEY_DATE_BEGIN, journalUI.getDateBegin());
        parameters.replace(constants.JOURNAL_KEY_DATE_BEGIN_FORMAT, journalUI.getDateBeginFormat()+constants.GENERIC_DATE_SUFFIX);
        parameters.replace(constants.JOURNAL_KEY_DATE_END,journalUI.getDateEnd());
        parameters.replace(constants.JOURNAL_KEY_DATE_END_FORMAT, journalUI.getDateEndFormat()+constants.GENERIC_DATE_SUFFIX);
        if(journalUI.getCurriculum()!=null){
            parameters.replace(constants.JOURNAL_KEY_CURRICULUM_TITLE, journalUI.getCurriculum());
        }
        parameters.replace(constants.JOURNAL_KEY_WELDERS_COUNT, journalUI.getWeldersCount());
        StringBuilder teachersAll = new StringBuilder();
        for(TeacherUI teacherUI: journalUI.getTeachers()){
            teachersAll.append(teacherUI.getFormatTeacherFullName("SUR-nn-sec")+", ");
        }
        if (teachersAll.length()>0){
            teachersAll.deleteCharAt(teachersAll.lastIndexOf(","));
        }
        parameters.replace(constants.JOURNAL_KEY_TEACHERS, teachersAll.toString());
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    private String getFormatedWeldMethods(List<WeldMethodUI> weldMethodUIList){
        if (weldMethodUIList==null)
            return "";
        if (weldMethodUIList.isEmpty()){
            return "";
        }
        StringBuilder result = new StringBuilder();

        for (WeldMethodUI wm : weldMethodUIList){
          result.append(wm.getCode()+"; ");
        }
        if (result.toString().endsWith("; "))
            result.deleteCharAt(result.lastIndexOf(";"));
        return result.toString();
    }

    private String getExpirience(Date dateBegin){
        String result = "";
        if(dateBegin == null)
            return result;
        LocalDate today = LocalDate.now();
        int expirience = today.getYear()- DateUtil.getLocalDate(dateBegin).getYear();
        result = expirience>0?DateUtil.format(dateBegin)+" ("+expirience+" "+ DateUtil.formatedYearEndString(expirience)+") ":"";
        return result;
    }

    public String getJrnNumber() {
        return jrnNumber;
    }

    public void setJrnNumber(String jrnNumber) {
        this.jrnNumber = jrnNumber;
    }

    public String getJrnDateBegin() {
        return jrnDateBegin;
    }

    public void setJrnDateBegin(String jrnDateBegin) {
        this.jrnDateBegin = jrnDateBegin;
    }

    public String getJrnDateEnd() {
        return jrnDateEnd;
    }

    public void setJrnDateEnd(String jrnDateEnd) {
        this.jrnDateEnd = jrnDateEnd;
    }

    public String getJrnCurriculumTitle() {
        return jrnCurriculumTitle;
    }

    public void setJrnCurriculumTitle(String jrnCurriculumTitle) {
        this.jrnCurriculumTitle = jrnCurriculumTitle;
    }

    public String getJrnWelderName() {
        return jrnWelderName;
    }

    public void setJrnWelderName(String jrnWelderName) {
        this.jrnWelderName = jrnWelderName;
    }

    public String getJrnWelderSurname() {
        return jrnWelderSurname;
    }

    public void setJrnWelderSurname(String jrnWelderSurname) {
        this.jrnWelderSurname = jrnWelderSurname;
    }

    public String getJrnWelderSecname() {
        return jrnWelderSecname;
    }

    public void setJrnWelderSecname(String jrnWelderSecname) {
        this.jrnWelderSecname = jrnWelderSecname;
    }

    public String getJrnWelderEducation() {
        return jrnWelderEducation;
    }

    public void setJrnWelderEducation(String jrnWelderEducation) {
        this.jrnWelderEducation = jrnWelderEducation;
    }

    public String getJrnWelderBirthday() {
        return jrnWelderBirthday;
    }

    public void setJrnWelderBirthday(String jrnWelderBirthday) {
        this.jrnWelderBirthday = jrnWelderBirthday;
    }

    public String getJrnWelderExperience() {
        return jrnWelderExperience;
    }

    public void setJrnWelderExperience(String jrnWelderExperience) {
        this.jrnWelderExperience = jrnWelderExperience;
    }

    public String getJrnWelderWorkPlace() {
        return jrnWelderWorkPlace;
    }

    public void setJrnWelderWorkPlace(String jrnWelderWorkPlace) {
        this.jrnWelderWorkPlace = jrnWelderWorkPlace;
    }

    public String getJrnWelderWeldMethods() {
        return jrnWelderWeldMethods;
    }

    public void setJrnWelderWeldMethods(String jrnWelderWeldMethods) {
        this.jrnWelderWeldMethods = jrnWelderWeldMethods;
    }

    public String getJrnWelderHomeAdress() {
        return jrnWelderHomeAdress;
    }

    public void setJrnWelderHomeAdress(String jrnWelderHomeAdress) {
        this.jrnWelderHomeAdress = jrnWelderHomeAdress;
    }
}
