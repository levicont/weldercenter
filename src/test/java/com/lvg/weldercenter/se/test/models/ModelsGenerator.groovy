package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.AttestType
import com.lvg.weldercenter.se.models.CommissionCertification
import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.models.Electrode
import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.models.MechanicalTest
import com.lvg.weldercenter.se.models.NDTDocument
import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.PersonalProtocol
import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.models.RadiationTest
import com.lvg.weldercenter.se.models.Section
import com.lvg.weldercenter.se.models.SteelGroup
import com.lvg.weldercenter.se.models.SteelType
import com.lvg.weldercenter.se.models.Teacher
import com.lvg.weldercenter.se.models.TheoryTest
import com.lvg.weldercenter.se.models.Topic
import com.lvg.weldercenter.se.models.VisualTest
import com.lvg.weldercenter.se.models.WeldDetailType
import com.lvg.weldercenter.se.models.WeldGas
import com.lvg.weldercenter.se.models.WeldJoinType
import com.lvg.weldercenter.se.models.WeldMethod
import com.lvg.weldercenter.se.models.WeldPattern
import com.lvg.weldercenter.se.models.WeldPositionType
import com.lvg.weldercenter.se.models.WeldWire
import com.lvg.weldercenter.se.models.Welder

import java.time.LocalDate

abstract class ModelsGenerator {

    static Organization getOrganization() {
        return new Organization(name: 'IBM', address: 'New-York', phone: '(0595)466-15-59')
    }

    static Welder getWelder() {
        def welder = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        welder.birthday = LocalDate.of(1984, 10, 28)
        welder.dateBegin = LocalDate.of(2000, 10, 28)
        welder.documentNumber = '17-033/17'
        welder.address = 'Michigan City 12066'
        welder.education = 'среднее-специальное'
        welder.qualification = 'электросварщик'
        welder.job = 'элекросварщик'
        welder.organization = getOrganization()
        return welder
    }

    static Education getEducation() {
        return new Education(education: 'среднее-специальное')
    }

    static Job getJob() {
        return new Job(name: 'электросварщик')
    }

    static Qualification getQualification() {
        return new Qualification(type: 'электросварщик')
    }

    static WeldMethod getWeldMethod() {
        return new WeldMethod(name: 'РДЭ', code: '111')
    }

    static Journal getJournal() {
        def journal = new Journal()
        journal.number = '17/001'
        journal.dateBegin = LocalDate.of(2017, 05, 25)
        journal.dateEnd = journal.dateBegin.plusWeeks(1)
        def curriculum = getCurriculumWithoutSections()
        def teachers = getTeachers()
        journal.curriculum = curriculum
        journal.teachers = teachers
        return journal
    }

    static Set<Topic> getTopics(SectionType sectionType) {
        def topics = new LinkedHashSet<Topic>()
        if (sectionType == SectionType.WELDING) {
            topics.add(new Topic(orderIndex: 0, title: 'Введение в сварочное дело',
                    description: 'Общие вопросы сварки', timeLongHours: 1.0))

            topics.add(new Topic(orderIndex: 1, title: 'Введение в методы сварки',
                    description: 'Общие вопросы методов сварки', timeLongHours: 3.5))

            topics.add(new Topic(orderIndex: 2, title: 'Введение в положения сварки',
                    description: 'Общие вопросы положений сварки', timeLongHours: 5.0))
        } else if (sectionType == SectionType.DEFECTS) {
            topics.add(new Topic(orderIndex: 0, title: 'Введение в дефекты',
                    description: 'Общие вопросы дефектоскопии', timeLongHours: 1.0))

            topics.add(new Topic(orderIndex: 1, title: 'Введение в методы контроля дефектов',
                    description: 'Общие вопросы методов контроля сварки', timeLongHours: 3.5))

            topics.add(new Topic(orderIndex: 2, title: 'Введение в исправление дефектор сварки',
                    description: 'Общие вопросы методов исправления дефектов сварки', timeLongHours: 5.0))
        } else if (sectionType == SectionType.HEALTH) {
            topics.add(new Topic(orderIndex: 0, title: 'Введение в охрану труда',
                    description: 'Общие вопросы охраны труда при сварке', timeLongHours: 2.0))

            topics.add(new Topic(orderIndex: 1, title: 'Электробезопасность',
                    description: 'Общие вопросы по электробезопасности', timeLongHours: 3.5))

            topics.add(new Topic(orderIndex: 2, title: 'Заземление',
                    description: 'Общие вопросы по заземлении', timeLongHours: 1.6))
        }
        return topics
    }

    static Set<Section> getSections(Curriculum curriculum) {
        Set<Section> sections = new HashSet<Section>()
        (0..2).each{
            switch (it) {
                case 0:
                    def section = new Section(curriculum, 'Дефекты металлопродукции')
                    section.orderIndex = it
                    section.description = 'Введение в дефекты металлопродукции'
                    sections.add(section)
                    break
                case 1:
                    def section = new Section(curriculum, 'Сварка')
                    section.orderIndex = it
                    section.description = 'Введение в основы сварки'
                    sections.add(section)
                    break
                case 2:
                    def section = new Section(curriculum, 'Охрана труда')
                    section.orderIndex = it
                    section.description = 'Введение в охрану труда при сварке'
                    sections.add(section)
                    break
            }

        }
        sections.each { section ->
            if (section.orderIndex == 0)
                section.topics.addAll(getTopics(SectionType.DEFECTS))
            if (section.orderIndex == 1)
                section.topics.addAll(getTopics(SectionType.WELDING))
            if (section.orderIndex == 2)
                section.topics.addAll(getTopics(SectionType.HEALTH))
        }

        return sections
    }

    static Section getSection(Curriculum curriculum) {
        def section = new Section(curriculum, 'Дефекты металлопродукции')
        section.orderIndex = 1
        section.description = 'Введение в дефекты металлопродукции'
        section.topics.addAll(getTopics(SectionType.DEFECTS))

        return section
    }

    static Curriculum getCurriculumWithoutSections() {
        def curriculum = new Curriculum(title: 'Подготовка 20 часов',
                description: 'Программа подготовки сварщиков перед аттестацией - 20 часов')
        return curriculum
    }

    static Teacher getTeacher() {
        return new Teacher(name: 'Амвросий', secondName: 'Федорович', surname: 'Кац')
    }

    static PersonalProtocol getPersonalProtocol(Welder welder, Journal journal) {
        def pProtocol = new PersonalProtocol(welder, journal)
        pProtocol.attestType = AttestType.PRIMARY
        pProtocol.number = '17/001'
        pProtocol.dateCertification = LocalDate.of(2017, 6, 1)
        pProtocol.resolutionCertification = '111 T BW  W02 В t4/t8 D57/D159 PF PC ss nb\n' +
                'Ручная дуговая сварка покрытым электродом технологических трубопроводов, трубопроводов пара и горячей воды, стальных конструкций t=3-16 мм, D>25 мм из н/у и н/л сталей во всех пространственных положениях, кроме сверху-вниз.  \n' +
                '111 T P BW FW W01 W02 A В R RA RВ  t3-16 D>25 PA PB PD PE PF PC  ss(nb, mb) bs(ng, gg)'
        pProtocol.theoryTest = new TheoryTest(ticketNumber: '1, 2, 9', rating: 'сдано')
        pProtocol.ndtDocuments = getNDTDocuments()
        return pProtocol
    }

    static Set<NDTDocument> getNDTDocuments() {
        def docs = new HashSet<NDTDocument>()
        docs << new NDTDocument(code: 'ДБН В.2.5-20-2001', fullTitle: 'Газоснабжение')
        docs << new NDTDocument(code: 'НПАОП 0.00-1.59-87', fullTitle: 'Правила устройства и безопасной эксплуатации сосудов, работающих под давлением')
        docs << new NDTDocument(code: 'ДСТУ-Н Б В.2.5-66:2012', fullTitle: 'Тепловые сети')
        return docs

    }

    static NDTDocument getNDTDocument() {
        getNDTDocuments()[0]
    }

    static Set<Teacher> getTeachers() {
        def result = new HashSet<Teacher>()
        result << new Teacher(name: 'Амвросий', secondName: 'Федорович', surname: 'Кац')
        result << new Teacher(name: 'Феликс', secondName: 'Давидович', surname: 'Соберицкий')
        result << new Teacher(name: 'Израиль', secondName: 'Аскольдович', surname: 'Новировский')
    }

    static Electrode getElectrode() {
        return new Electrode(type: 'АНО-21')
    }

    static WeldWire getWeldWire() {
        return new WeldWire(type: 'св08Г2С')
    }

    static WeldGas getWeldGas() {
        return new WeldGas(type: 'Аргон')
    }

    static SteelType getSteelType() {
        new SteelType(type: 'сталь 20', steelGroup: SteelGroup.W01)
    }

    static RadiationTest getRadiationTest() {
        new RadiationTest(protocolNumber: '17-001', defects: 'ДНО', sensitivity: 0.3)
    }

    static VisualTest getVisualTest() {
        new VisualTest(protocolNumber: '17-001', defects: 'ДНО')
    }

    static MechanicalTest getMechanicalTest() {
        new MechanicalTest(protocolNumber: '17-001', clearance: 9.0D)
    }

    static WeldPattern getWeldPattern(PersonalProtocol pp) {
        def wp = new WeldPattern(pp)
        wp.mark = '01'
        wp.diameter = 89.0D
        wp.thickness = 3.0D
        wp.electrode = getElectrode().type
        wp.radiationTest = getRadiationTest()
        wp.visualTest = getVisualTest()
        wp.mechanicalTest = getMechanicalTest()
        wp.weldWire = getWeldWire()
        wp.weldGas = getWeldGas()
        wp.steelType = getSteelType()

        wp.weldJoins.add(WeldJoinType.BS.value)
        wp.weldJoins.add(WeldJoinType.GG.value)

        wp.weldPositions.add(WeldPositionType.PA.value)
        wp.weldPositions.add(WeldPositionType.PB.value)

        wp.weldDetail = WeldDetailType.P.value
        return wp
    }

    static CommissionCertification getCommissionCertification(List<Teacher> teachers) {
        def result = new CommissionCertification(teachers[0], teachers[1], teachers[2], teachers[3])
        return result
    }

}
