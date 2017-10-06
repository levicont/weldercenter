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
        return new Qualification(name: 'электросварщик')
    }

    protected WeldMethod getWeldMethod(){
        return new WeldMethod(name: 'РДЭ', code: '111')
    }

    protected Journal getJournal(){
        def journal = new Journal()
        journal.number = '17/001'
        journal.dateBegin = LocalDate.of(2017, 05, 25)
        journal.dateEnd = journal.dateBegin.plusWeeks(1)
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
        return topics
    }

    abstract void insertItemTest()
    abstract void updateItemTest()
    abstract void deleteItemTest()
    abstract void equalsHashCodeTest()
    abstract void toStringTest()
}
