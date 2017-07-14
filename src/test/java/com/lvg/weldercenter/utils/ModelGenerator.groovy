package com.lvg.weldercenter.utils

import com.lvg.weldercenter.models.*
/**
 * Created by Victor on 06.07.2017.
 */
class ModelGenerator {

    static Welder getWelder(){
        Welder welder = new Welder()

        welder.name = 'Иван'
        welder.surname = 'Иванов'
        welder.secname = 'Иванович'
        welder.birthday = new Date()
        welder.address = 'г. Харьков, ул. Гагарина, 17'
        welder.dateBegin = new Date()
        welder.docNumber = 'ХА-001'

        welder.education = getEducation()
        welder.organization = getOrganization()
        welder.job = getJob()
        welder.qualification = getQualification()


        return welder
    }

    static Qualification getQualification() {
        Qualification qualification = new Qualification()
        qualification.type = 'электросварщик'
    }

    static Job getJob() {
        Job job = new Job()
        job.name = 'электросварщик'
        return job
    }

    static Organization getOrganization() {
        Organization organization = new Organization()
        organization.name = 'ООО \"Сварка и стройка\"'
        organization.adress = 'г. Киев, ул. Шевченко, 113'
        organization.phone = '044 253 55 56'
        return organization

    }

    static Education getEducation(){
        Education education = new Education()
        education.type = 'среднее-специальное'
        return education
    }

    static Electrode getEletrode(){
        Electrode electrode = new Electrode()
        electrode.type = 'АНО-21'
        return electrode
    }

    static Teacher getTeacher(){
        return  new Teacher(name: 'Петр', surname: 'Петров', secname: 'Петрович')
    }

    static CommissionCertification getCommissionCertification(){
        def commission = new CommissionCertification()
        commission.head = getTeacher()
        commission.ndtSpecialist = getTeacher()
        commission.safetySpecialist = getTeacher()
        commission.weldSpecialist = getTeacher()
        return commission
    }

    static Topic getTopic(){
        def topic = new Topic()
        topic.description = 'Topic description'
        topic.orderIndex = 1
        topic.title = 'Title'
        topic.timelong = 0.5
        return topic
    }

    static Section getSection(){
        def section = new Section()
        section.title = 'Section'
        section.orderIndex = 1
        section.description = 'Section description'
        section.topics.add(getTopic())
        return section
    }

    static Curriculum getCurriculum(){
        def curriculum = new Curriculum()
        curriculum.title = 'Curriculum'
        curriculum.description = 'Curriculum description'
        curriculum.sections.add(getSection())
        return curriculum
    }

    static Evaluation getEvaluation(){
        def evaluation = new Evaluation()
        evaluation.type = 'OK status'
        return evaluation
    }

    static Journal getJournal(){
        def journal = new Journal()
        journal.curriculum = getCurriculum()
        journal.number = '17-001'
        journal.dateBegin = new Date()
        journal.dateEnd = new Date()
        journal.teachers.add(getTeacher())
        journal.welders.add(getWelder())
        return journal
    }



    static SteelType getSteelType(){
        def steelType = new SteelType()
        steelType.stGroup = getSteelGroup()
        steelType.type = 'Сталь 20'
        return steelType
    }

    static SteelGroup getSteelGroup() {
        def steelGroup = new SteelGroup()
        steelGroup.stGroup = 'w01'
        steelGroup.description = 'Steel group description'
        return steelGroup
    }

    static WeldPattern getWeldPattern(){
        def weldPattern = new WeldPattern()
        weldPattern.diameter = 76.0
        weldPattern.thickness = 4.0
        weldPattern.electrode = getEletrode()
        weldPattern.mark = '01'
        weldPattern.steelType = getSteelType()
        weldPattern.weldDetail = getWeldDetail()
        weldPattern.weldGas = getWeldGas()
        weldPattern.weldJoinTypes.add(getWeldJoinType())
        weldPattern.weldMethod = getWeldMethod()
        weldPattern.weldPositions.add(getWeldPosition())
        weldPattern.weldWire = getWeldWire()

        weldPattern.mechanicalTest = getMechanicalTest()
        weldPattern.radiationTest = getRadiationTest()
        weldPattern.visualTest = getVisualTest()



    }

    static WeldWire getWeldWire() {
        def weldWire = new WeldWire()
        weldWire.type = 'Св08Г2С'
        return weldWire
    }

    static VisualTest getVisualTest() {
        def visualTest = new VisualTest()
        visualTest.number = '17-03'
        visualTest.defects = 'ДНО'
        visualTest.protDate = new Date()
        visualTest.evaluation = getEvaluation()
        return visualTest
    }

    static RadiationTest getRadiationTest() {
        def radiationTest = new RadiationTest()
        radiationTest.protDate = new Date()
        radiationTest.number = '17-02'
        radiationTest.defects = 'ДНО'
        radiationTest.sensitivity = 0.3
        radiationTest.evaluation = getEvaluation()
        return radiationTest
    }

    static MechanicalTest getMechanicalTest() {
        def mechanicalTest = new MechanicalTest()
        mechanicalTest.angle = 120.0
        mechanicalTest.clearance = 10.0
        mechanicalTest.number = '17-01'
        mechanicalTest.protDate = new Date()
        mechanicalTest.evaluation = getEvaluation()
        return mechanicalTest
    }

    static WeldPosition getWeldPosition() {
        def weldPosition = new WeldPosition()
        weldPosition.code = 'pf'
        weldPosition.type = 'pf description'
    }

    static WeldMethod getWeldMethod() {
        def weldMethod = new WeldMethod()
        weldMethod.code = '135'
        weldMethod.name = 'MAG'
        return weldMethod
    }

    static WeldGas getWeldGas() {
        def weldGas = new WeldGas()
        weldGas.type = 'C02'
        return weldGas
    }

    static WeldJoinType getWeldJoinType(){
        def weldJoinType = new WeldJoinType()
        weldJoinType.type = 'PC'
        weldJoinType.description = 'PC description'
    }

    static WeldDetail getWeldDetail() {
        def weldDetail = new WeldDetail()
        weldDetail.type = 'Tube'
        weldDetail.code = 'T'
        return weldDetail
    }

    static NDTDocument getNDTDocument(){
        def ndtDocument = new NDTDocument()
        ndtDocument.name = 'ISO 9712'
        ndtDocument.fullName = 'NDT Certification'
        return ndtDocument
    }

    
}
