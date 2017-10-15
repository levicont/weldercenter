package com.lvg.weldercenter.se.test.models
import com.lvg.weldercenter.se.models.*
import com.lvg.weldercenter.se.test.utils.ConnectionManager
import com.lvg.weldercenter.se.utils.TransactionManagerSetup

import javax.persistence.EntityManagerFactory
import java.time.LocalDate

abstract class GenericModelTest {
    protected static final TransactionManagerSetup TMS = ConnectionManager.transactionManagerSetup()
    protected static final EntityManagerFactory EMF = ConnectionManager.entityManagerFactory()

    protected Organization getOragnization(){
        return new Organization(name: 'IBM', address: 'New-York', phone: '(0595)466-15-59')
    }

    protected Welder getWelder(){
        def welder = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        welder.birthday = LocalDate.of(1984,10,28)
        welder.dateBegin = LocalDate.of(2000,10,28)
        welder.documentNumber = '17-033/17'
        welder.address = 'Michigan City 12066'
        welder.education = 'среднее-специальное'
        welder.qualification = 'электросварщик'
        welder.job = 'элекросварщик'
        welder.organization = getOragnization()
        return welder
    }

    protected Education getEducation(){
        return new Education(education: 'среднее-специальное')
    }

    protected Job getJob(){
        return new Job(name: 'электросварщик')
    }

    protected Qualification getQualification(){
        return new Qualification(type: 'электросварщик')
    }

    protected WeldMethod getWeldMethod(){
        return new WeldMethod(name: 'РДЭ', code: '111')
    }

    protected Journal getJournal(){
        def journal = new Journal()
        journal.number = '17/001'
        journal.dateBegin = LocalDate.of(2017, 05, 25)
        journal.dateEnd = journal.dateBegin.plusWeeks(1)
        def curriculum = getCurriculum()
        def teachers = getTeachers()
        journal.curriculum = curriculum
        journal.teachers = teachers
        return journal
    }

    protected Set<Topic> getTopics(SectionType sectionType){
        def topics = new LinkedHashSet<Topic>()
        if (sectionType == SectionType.WELDING) {
            topics.add(new Topic(orderIndex: 0, title: 'Введение в сварочное дело',
                    description: 'Общие вопросы сварки', timeLongHours: 1.0))

            topics.add(new Topic(orderIndex: 1, title: 'Введение в методы сварки',
                    description: 'Общие вопросы методов сварки', timeLongHours: 3.5))

            topics.add(new Topic(orderIndex: 2, title: 'Введение в положения сварки',
                    description: 'Общие вопросы положений сварки', timeLongHours: 5.0))
        }
        else if (sectionType == SectionType.DEFECTS){
            topics.add(new Topic(orderIndex: 0, title: 'Введение в дефекты',
                    description: 'Общие вопросы дефектоскопии', timeLongHours: 1.0))

            topics.add(new Topic(orderIndex: 1, title: 'Введение в методы контроля дефектов',
                    description: 'Общие вопросы методов контроля сварки', timeLongHours: 3.5))

            topics.add(new Topic(orderIndex: 2, title: 'Введение в исправление дефектор сварки',
                    description: 'Общие вопросы методов исправления дефектов сварки', timeLongHours: 5.0))
        }
        else if (sectionType == SectionType.HEALTH){
            topics.add(new Topic(orderIndex: 0, title: 'Введение в охрану труда',
                    description: 'Общие вопросы охраны труда при сварке', timeLongHours: 2.0))

            topics.add(new Topic(orderIndex: 1, title: 'Электробезопасность',
                    description: 'Общие вопросы по электробезопасности', timeLongHours: 3.5))

            topics.add(new Topic(orderIndex: 2, title: 'Заземление',
                    description: 'Общие вопросы по заземлении', timeLongHours: 1.6))
        }
        return topics
    }

    protected Set<Section> getSections(){
        Set<Section> sections = new HashSet<Section>()
        sections << new Section(orderIndex: 0, title: 'Дефекты металлопродукции',
           description:  'Введение в дефекты металлопродукции')
        sections << new Section(orderIndex: 1, title: 'Сварка',
                description:  'Введение в основы сварки')
        sections << new Section(orderIndex: 2, title: 'Охрана труда',
                description:  'Введение в охрану труда при сварке')

        sections.each {section ->
            if (section.orderIndex == 0)
                section.topics.addAll(getTopics(SectionType.DEFECTS))
            if (section.orderIndex == 1)
                section.topics.addAll(getTopics(SectionType.WELDING))
            if (section.orderIndex == 2)
                section.topics.addAll(getTopics(SectionType.HEALTH))
        }

        return sections
    }

    protected Section getSingleSection(){
        def section = new Section(orderIndex: 1, title: 'Дефекты металлопродукции',
                description:  'Введение в дефекты металлопродукции')
        section.topics.addAll(getTopics(SectionType.DEFECTS))

        return section
    }

    protected Curriculum getCurriculum(){
        def curriculum = new Curriculum(title: 'Подготовка 20 часов',
                description: 'Программа подготовки сварщиков перед аттестацией - 20 часов')
        curriculum.sections.add(getSections().getAt(0))
        curriculum.sections.add(getSections().getAt(1))
        curriculum.sections.add(getSections().getAt(2))

        curriculum.sections.each {it.curriculum = curriculum}

        return curriculum
    }

    protected Teacher getTeacher(){
        return new Teacher(name: 'Амвросий', secondName: 'Федорович', surname: 'Кац')
    }

    protected PersonalProtocol getPersonalProtocol(Welder welder, Journal journal ){
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

    protected NDTDocument getNDTDocument(){
        return new NDTDocument( code:  'ДБН В.2.5-20-2001', fullTitle: 'Газоснабжение')
    }

    protected Set<NDTDocument> getNDTDocuments(){
        def docs = new HashSet<NDTDocument>()
        docs << new NDTDocument( code:  'ДБН В.2.5-20-2001', fullTitle: 'Газоснабжение')
        docs << new NDTDocument( code:  'НПАОП 0.00-1.59-87', fullTitle: 'Правила устройства и безопасной эксплуатации сосудов, работающих под давлением')
        docs << new NDTDocument( code:  'ДСТУ-Н Б В.2.5-66:2012', fullTitle: 'Тепловые сети')
        return docs

    }

    protected Set<Teacher> getTeachers(){
        def result = new HashSet<Teacher>()
        result << new Teacher(name: 'Амвросий', secondName: 'Федорович', surname: 'Кац')
        result << new Teacher(name: 'Феликс', secondName: 'Давидович', surname: 'Соберицкий')
        result << new Teacher(name: 'Израиль', secondName: 'Аскольдович', surname: 'Новировский')
    }

    protected Electrode getElectrode(){
        return new Electrode(type: 'АНО-21')
    }

    protected WeldWire getWeldWire(){
        return new WeldWire(type: 'св08Г2С')
    }

    protected WeldPattern getWeldPattern(){
        def wp = new WeldPattern(mark: '01', diametr: 89.0, thickness: 3.0)
        wp.electrode = getElectrode().type
        return wp
    }

    abstract void insertItemTest()
    abstract void updateItemTest()
    abstract void deleteItemTest()
    abstract void equalsHashCodeTest()
    abstract void toStringTest()
}
