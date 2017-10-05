package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.models.Welder
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

    abstract void insertItemTest()
    abstract void updateItemTest()
    abstract void deleteItemTest()
    abstract void equalsHashCodeTest()
    abstract void toStringTest()
}
