package com.lvg.weldercenter.spring.factories;

import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.spring.ContextFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Victor Levchenko LVG Corp. on 14.11.14.
 */
public class ServiceFactory {

    private static ApplicationContext context = ContextFactory.getApplicationContext();
    private static WelderService welderService;
    private static WeldMethodService weldMethodService;
    private static EducationService educationService;
    private static OrganizationService organizationService;
    private static QualificationService qualificationService;
    private static JobService jobService;
    private static JournalService journalService;
    private static CurriculumService curriculumService;
    private static SectionService sectionService;
    private static TopicService topicService;
    private static TeacherService teacherService;

    private ServiceFactory(){

    }

    public static WelderService getWelderService(){
        if(null != welderService){
            return welderService;
        }
        else {
            welderService = (WelderService)context.getBean("welderService");
        }
        return welderService;
    }

    public static WeldMethodService getWeldMethodService(){
        if(null != weldMethodService){
            return weldMethodService;
        }
        else{
            weldMethodService = (WeldMethodService)context.getBean("weldMethodService");
        }
        return weldMethodService;
    }

    public static EducationService getEducationService(){
        if(null != educationService){
            return educationService;
        }
        else{
            educationService = (EducationService)context.getBean("educationService");
        }
        return educationService;
    }

    public static OrganizationService getOrganizationService(){
        if(null != organizationService){
            return organizationService;
        }
        else {
            organizationService = (OrganizationService)context.getBean("organizationService");
        }
        return organizationService;
    }

    public static QualificationService getQualificationService(){
        if(null != qualificationService){
            return qualificationService;
        }
        else {
            qualificationService = (QualificationService)context.getBean("qualificationService");
        }
        return qualificationService;
    }

    public static JobService getJobService(){
        if(null != jobService){
            return jobService;
        }
        else {
            jobService = (JobService)context.getBean("jobService");
        }
        return jobService;
    }

    public static JournalService getJournalService(){
        if(null != journalService){
            return journalService;
        }
        else {
            journalService = (JournalService)context.getBean("journalService");
        }
        return journalService;
    }

    public static CurriculumService getCurriculumService(){
        if(null != curriculumService){
            return curriculumService;
        }
        else {
            curriculumService = (CurriculumService)context.getBean("curriculumService");
        }
        return curriculumService;
    }

    public static SectionService getSectionService(){
        if(null != sectionService){
            return sectionService;
        }
        else {
            sectionService = (SectionService)context.getBean("sectionService");
        }
        return sectionService;
    }

    public static TopicService getTopicService(){
        if(null != topicService){
            return topicService;
        }
        else {
            topicService = (TopicService)context.getBean("topicService");
        }
        return topicService;
    }

    public static TeacherService getTeacherService(){
        if(null != teacherService){
            return teacherService;
        }
        else {
            teacherService = (TeacherService)context.getBean("teacherService");
        }
        return teacherService;
    }
}
