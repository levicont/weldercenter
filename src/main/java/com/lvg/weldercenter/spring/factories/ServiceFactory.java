package com.lvg.weldercenter.spring.factories;

import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.models.PatternDiameter;
import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.models.WeldPosition;
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
    private static WeldDetailService weldDetailService;
    private static EducationService educationService;
    private static OrganizationService organizationService;
    private static QualificationService qualificationService;
    private static JobService jobService;
    private static JournalService journalService;
    private static CurriculumService curriculumService;
    private static SectionService sectionService;
    private static TopicService topicService;
    private static TeacherService teacherService;
    private static PersonalProtocolService personalProtocolService;
    private static TotalProtocolService totalProtocolService;
    private static CommissionCertificationService commissionCertificationService;
    private static TheoryTestService theoryTestService;
    private static NDTDocumentService ndtDocumentService;
    private static PatternDiameterService patternDiameterService;
    private static PatternThicknessService patternThicknessService;
    private static SteelTypeService steelTypeService;
    private static SteelGroupService steelGroupService;
    private static WeldPositionService weldPositionService;
    private static ElectrodeService electrodeService;
    private static WeldWireService weldWireService;
    private static WeldGasService weldGasService;
    private static EvaluationService evaluationService;
    private static RadiationTestService radiationTestService;
    private static VisualTestService visualTestService;
    private static MechanicalTestService mechanicalTestService;

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

    public static WeldDetailService getWeldDetailService(){
        if(null != weldDetailService){
            return weldDetailService;
        }
        else{
            weldDetailService = (WeldDetailService)context.getBean("weldDetailService");
            return weldDetailService;
        }
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

    public static PersonalProtocolService getPersonalProtocolService(){
        if(null != personalProtocolService){
            return personalProtocolService;
        }
        else{
            personalProtocolService = (PersonalProtocolService)context.getBean("personalProtocolService");
        }
        return personalProtocolService;
    }

    public static TotalProtocolService getTotalProtocolService(){
        if(null != totalProtocolService){
            return totalProtocolService;
        }
        else{
            totalProtocolService = (TotalProtocolService)context.getBean("totalProtocolService");
        }
        return totalProtocolService;
    }

    public static CommissionCertificationService getCommissionCertificationService(){
        if(null != commissionCertificationService){
            return commissionCertificationService;
        }
        else{
            commissionCertificationService =
                    (CommissionCertificationService)context.getBean("commissionCertificationService");
        }
        return commissionCertificationService;
    }

    public static TheoryTestService getTheoryTestService(){
        if(null != theoryTestService){
            return theoryTestService;
        }
        else {
            theoryTestService = (TheoryTestService)context.getBean("theoryTestService");
            return theoryTestService;
        }
    }

    public static NDTDocumentService getNdtDocumentService(){
        if(null != ndtDocumentService){
            return ndtDocumentService;
        }
        else {
            ndtDocumentService = (NDTDocumentService)context.getBean("ndtDocumentService");
            return ndtDocumentService;
        }
    }

    public static PatternDiameterService getPatternDiameterService(){
        if(null != patternDiameterService){
            return patternDiameterService;
        }
        else {
            patternDiameterService = (PatternDiameterService)context.getBean("patternDiameterService");
            return patternDiameterService;
        }
    }

    public static PatternThicknessService getPatternThicknessService(){
        if(null != patternThicknessService){
            return patternThicknessService;
        }
        else {
            patternThicknessService = (PatternThicknessService)context.getBean("patternThicknessService");
            return patternThicknessService;
        }
    }

    public static SteelTypeService getSteelTypeService() {
        if(null!= steelTypeService) {
            return steelTypeService;
        }else{
            steelTypeService = (SteelTypeService)context.getBean("steelTypeService");
            return steelTypeService;
        }
    }

    public static SteelGroupService getSteelGroupService() {
        if(null!= steelGroupService) {
            return steelGroupService;
        }else {
            steelGroupService = (SteelGroupService)context.getBean("steelGroupService");
            return steelGroupService;
        }
    }

    public static WeldPositionService getWeldPositionService() {
        if(null!=weldPositionService){
            return weldPositionService;
        }else {
            weldPositionService = (WeldPositionService)context.getBean("weldPositionService");
            return weldPositionService;
        }
    }

    public static ElectrodeService getElectrodeService() {
        if(null!= electrodeService) {
            return electrodeService;
        }else {
            electrodeService = (ElectrodeService)context.getBean("electrodeService");
            return electrodeService;
        }
    }

    public static WeldWireService getWeldWireService() {
        if(null!= weldWireService) {
            return weldWireService;
        }else {
            weldWireService = (WeldWireService)context.getBean("weldWireService");
            return weldWireService;
        }
    }

    public static WeldGasService getWeldGasService() {
        if(null!=weldGasService) {
            return weldGasService;
        }else {
            weldGasService = (WeldGasService)context.getBean("weldGasService");
            return weldGasService;
        }
    }

    public static EvaluationService getEvaluationService(){
        if(null!=evaluationService){
            return evaluationService;
        }else{
            evaluationService = (EvaluationService)context.getBean("evaluationService");
            return evaluationService;
        }
    }

    public static RadiationTestService getRadiationTestService(){
        if (null!=radiationTestService){
            return radiationTestService;
        }else {
            radiationTestService = (RadiationTestService)context.getBean("radiationTestService");
            return radiationTestService;
        }
    }

    public static VisualTestService getVisualTestService() {
        if(null!=visualTestService) {
            return visualTestService;
        }else {
            visualTestService =(VisualTestService)context.getBean("visualTestService");
            return visualTestService;
        }

    }

    public static MechanicalTestService getMechanicalTestService() {
        if (null!=mechanicalTestService) {
            return mechanicalTestService;
        }else{
            mechanicalTestService = (MechanicalTestService)context.getBean("mechanicalTestService");
            return mechanicalTestService;
        }
    }
}
