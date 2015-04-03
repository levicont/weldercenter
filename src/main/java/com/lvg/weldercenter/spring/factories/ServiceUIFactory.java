package com.lvg.weldercenter.spring.factories;

import com.lvg.weldercenter.services.VisualTestService;
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
    private static EvaluationServiceUI evaluationServiceUI;
    private static RadiationTestServiceUI radiationTestServiceUI;
    private static VisualTestServiceUI visualTestServiceUI;
    private static MechanicalTestServiceUI mechanicalTestServiceUI;



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

    public static EvaluationServiceUI getEvaluationServiceUI() {
        if (evaluationServiceUI != null) {
            return evaluationServiceUI;
        }else{
            evaluationServiceUI = (EvaluationServiceUI)context.getBean("evaluationServiceUI");
            return evaluationServiceUI;
        }
    }

    public static RadiationTestServiceUI getRadiationTestServiceUI() {
        if (radiationTestServiceUI != null) {
            return radiationTestServiceUI;
        }else{
            radiationTestServiceUI = (RadiationTestServiceUI)context.getBean("radiationTestServiceUI");
            return radiationTestServiceUI;
        }
    }

    public static VisualTestServiceUI getVisualTestServiceUI() {
        if (visualTestServiceUI != null) {
            return visualTestServiceUI;
        }else {
            visualTestServiceUI = (VisualTestServiceUI)context.getBean("visualTestServiceUI");
            return visualTestServiceUI;
        }
    }

    public static MechanicalTestServiceUI getMechanicalTestServiceUI() {
        if (mechanicalTestServiceUI != null) {
            return mechanicalTestServiceUI;
        }else {
            mechanicalTestServiceUI = (MechanicalTestServiceUI)context.getBean("mechanicalTestServiceUI");
            return mechanicalTestServiceUI;
        }
    }
}
