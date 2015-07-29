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
    private static EvaluationServiceUI evaluationServiceUI;
    private static RadiationTestServiceUI radiationTestServiceUI;
    private static VisualTestServiceUI visualTestServiceUI;
    private static MechanicalTestServiceUI mechanicalTestServiceUI;
    private static WeldDetailServiceUI weldDetailServiceUI;
    private static SteelTypeServiceUI steelTypeServiceUI;
    private static SteelGroupServiceUI steelGroupServiceUI;
    private static WeldPositionServiceUI weldPositionServiceUI;
    private static WeldMethodServiceUI weldMethodServiceUI;
    private static ElectrodeServiceUI electrodeServiceUI;
    private static WeldWireServiceUI weldWireServiceUI;
    private static WeldGasServiceUI weldGasServiceUI;
    private static TheoryTestServiceUI theoryTestServiceUI;
    private static NDTDocumentServiceUI ndtDocumentServiceUI;
    private static ResolutionCertificationServiceUI resolutionCertificationServiceUI;
    private static WeldPatternServiceUI weldPatternServiceUI;
    private static CommissionCertificationServiceUI commissionCertificationServiceUI;
    private static WeldJoinTypeServiceUI weldJoinTypeServiceUI;
    private static OrganizationServiceUI organizationServiceUI;


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

    public static WeldDetailServiceUI getWeldDetailServiceUI() {
        if (weldDetailServiceUI != null) {
            return weldDetailServiceUI;
        }else {
            weldDetailServiceUI = (WeldDetailServiceUI)context.getBean("weldDetailServiceUI");
            return weldDetailServiceUI;
        }
    }

    public static SteelTypeServiceUI getSteelTypeServiceUI() {
        if (steelTypeServiceUI != null) {
            return steelTypeServiceUI;
        }else {
            steelTypeServiceUI = (SteelTypeServiceUI)context.getBean("steelTypeServiceUI");
            return steelTypeServiceUI;
        }
    }

    public static SteelGroupServiceUI getSteelGroupServiceUI() {
        if (steelGroupServiceUI != null) {
            return steelGroupServiceUI;
        }else {
            steelGroupServiceUI = (SteelGroupServiceUI)context.getBean("steelGroupServiceUI");
            return steelGroupServiceUI;
        }
    }

    public static WeldPositionServiceUI getWeldPositionServiceUI() {
        if (weldPositionServiceUI != null) {
            return weldPositionServiceUI;
        }else {
            weldPositionServiceUI = (WeldPositionServiceUI)context.getBean("weldPositionServiceUI");
            return weldPositionServiceUI;
        }
    }

    public static WeldMethodServiceUI getWeldMethodServiceUI() {
        if (weldMethodServiceUI != null) {
            return weldMethodServiceUI;
        }else {
            weldMethodServiceUI = (WeldMethodServiceUI)context.getBean("weldMethodServiceUI");
            return weldMethodServiceUI;
        }

    }

    public static ElectrodeServiceUI getElectrodeServiceUI() {
        if (electrodeServiceUI != null){
            return electrodeServiceUI;
        }else {
            electrodeServiceUI = (ElectrodeServiceUI)context.getBean("electrodeServiceUI");
            return electrodeServiceUI;
        }
    }

    public static WeldWireServiceUI getWeldWireServiceUI() {
        if(weldWireServiceUI != null) {
            return weldWireServiceUI;
        }else {
            weldWireServiceUI = (WeldWireServiceUI)context.getBean("weldWireServiceUI");
            return weldWireServiceUI;
        }
    }

    public static WeldGasServiceUI getWeldGasServiceUI() {
        if (weldGasServiceUI != null) {
            return weldGasServiceUI;
        }else {
            weldGasServiceUI = (WeldGasServiceUI)context.getBean("weldGasServiceUI");
            return weldGasServiceUI;
        }
    }

    public static TheoryTestServiceUI getTheoryTestServiceUI() {
        if (theoryTestServiceUI != null) {
            return theoryTestServiceUI;
        }else {
            theoryTestServiceUI = (TheoryTestServiceUI)context.getBean("theoryTestServiceUI");
            return theoryTestServiceUI;
        }
    }

    public static NDTDocumentServiceUI getNdtDocumentServiceUI() {
        if (ndtDocumentServiceUI != null){
            return ndtDocumentServiceUI;
        }else{
            ndtDocumentServiceUI = (NDTDocumentServiceUI)context.getBean("ndtDocumentServiceUI");
            return ndtDocumentServiceUI;
        }
    }

    public static ResolutionCertificationServiceUI getResolutionCertificationServiceUI() {
        if (resolutionCertificationServiceUI != null) {
            return resolutionCertificationServiceUI;
        }else {
            resolutionCertificationServiceUI = (ResolutionCertificationServiceUI)context.
                    getBean("resolutionCertificationServiceUI");
            return resolutionCertificationServiceUI;
        }
    }

    public static WeldPatternServiceUI getWeldPatternServiceUI() {
        if (weldPatternServiceUI != null) {
            return weldPatternServiceUI;
        }else {
            weldPatternServiceUI = (WeldPatternServiceUI)context.getBean("weldPatternServiceUI");
            return weldPatternServiceUI;
        }
    }

    public static CommissionCertificationServiceUI getCommissionCertificationServiceUI() {
        if (commissionCertificationServiceUI != null) {
            return commissionCertificationServiceUI;
        }else {
            commissionCertificationServiceUI = (CommissionCertificationServiceUI)context.
                    getBean("commissionCertificationServiceUI");
            return commissionCertificationServiceUI;
        }
    }

    public static WeldJoinTypeServiceUI getWeldJoinTypeServiceUI() {
        if (weldJoinTypeServiceUI != null) {
            return weldJoinTypeServiceUI;
        }else {
            weldJoinTypeServiceUI  = (WeldJoinTypeServiceUI)context.getBean("weldJoinTypeServiceUI");
            return weldJoinTypeServiceUI;
        }
    }

    public static OrganizationServiceUI getOrganizationServiceUI(){
        if (organizationServiceUI != null){
            return organizationServiceUI;
        }else {
            organizationServiceUI = (OrganizationServiceUI)context.getBean("organizationServiceUI");
            return organizationServiceUI;
        }
    }
}
