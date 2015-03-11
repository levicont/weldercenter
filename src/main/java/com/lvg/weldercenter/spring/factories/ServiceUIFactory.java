package com.lvg.weldercenter.spring.factories;

import com.lvg.weldercenter.spring.ContextFactory;
import com.lvg.weldercenter.ui.servicesui.*;
import org.springframework.context.ApplicationContext;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public class ServiceUIFactory {
    private static ApplicationContext context = ContextFactory.getApplicationContext();
    private static WelderServiceUI welderServiceUI;
    private static TeacherServiceUI teacherServiceUI;
    private static TotalProtocolServiceUI totalProtocolServiceUI;
    private static JournalServiceUI journalServiceUI;
    private static PersonalProtocolServiceUI personalProtocolServiceUI;


    private ServiceUIFactory(){

    }

    public static WelderServiceUI getWelderServiceUI(){
        if(welderServiceUI != null){
            return welderServiceUI;
        }else {
            welderServiceUI = (WelderServiceUI) context.getBean("welderServiceUI");
            return welderServiceUI;
        }
    }

    public static TeacherServiceUI getTeacherServiceUI(){
        if(teacherServiceUI != null){
            return teacherServiceUI;
        }else {
            teacherServiceUI = (TeacherServiceUI) context.getBean("teacherServiceUI");
            return teacherServiceUI;
        }
    }


    public static JournalServiceUI getJournalServiceUI(){
        if(journalServiceUI != null){
            return journalServiceUI;
        }else {
            journalServiceUI = (JournalServiceUI) context.getBean("journalServiceUI");
            return journalServiceUI;
        }
    }

    public static TotalProtocolServiceUI getTotalProtocolServiceUI(){
        if(totalProtocolServiceUI != null){
            return totalProtocolServiceUI;
        }else {
            totalProtocolServiceUI = (TotalProtocolServiceUI) context.getBean("totalProtocolServiceUI");
            return totalProtocolServiceUI;
        }
    }

    public static PersonalProtocolServiceUI getPersonalProtocolServiceUI(){
        if(personalProtocolServiceUI != null){
            return personalProtocolServiceUI;
        }else {
            personalProtocolServiceUI = (PersonalProtocolServiceUI) context.getBean("personalProtocolServiceUI");
            return personalProtocolServiceUI;
        }
    }
}
