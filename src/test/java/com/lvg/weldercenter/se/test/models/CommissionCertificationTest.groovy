package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.CommissionCertification
import com.lvg.weldercenter.se.models.Teacher
import com.lvg.weldercenter.se.services.CommissionCertificationService
import com.lvg.weldercenter.se.services.TeacherService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class CommissionCertificationTest extends GenericModelTest {
    @Autowired
    TeacherService teacherService
    @Autowired
    CommissionCertificationService commissionCertificationService

    @Override
    @Test
    void insertItemTest() {
        def COMMISSION_ID

            def teachers = new ArrayList<Teacher>()
            (1..4).each {teachers << getTeacher()}
            teachers.each{teacherService.save(it)}
            CommissionCertification commission = getCommissionCertification(teachers)
            commissionCertificationService.save(commission)
            COMMISSION_ID = commission.id


        assert COMMISSION_ID != null
        def chkCommission = null

            chkCommission = commissionCertificationService.get(COMMISSION_ID)
            assert chkCommission != null
            assert chkCommission.id == COMMISSION_ID
            assert chkCommission.safetySpecialist.surname == getTeacher().surname
            assert chkCommission.ndtSpecialist.name == getTeacher().name
            assert chkCommission.weldSpecialist.secondName == getTeacher().secondName
            assert chkCommission.head.toString() == getTeacher().toString()

    }

    @Override
    @Test
    void updateItemTest() {
        def COMMISSION_ID


            def teachers = new ArrayList<Teacher>()
            (1..4).each {teachers << getTeacher()}
            teachers.each{teacherService.save(it)}
            CommissionCertification commission = getCommissionCertification(teachers)
            commissionCertificationService.save(commission)
            COMMISSION_ID = commission.id

        assert COMMISSION_ID != null

        def updCommission

            updCommission = commissionCertificationService.get(COMMISSION_ID)
            def teacher = getTeachers().find { it.surname == 'Новировский' }
            assert teacher != null
            updCommission.head = teacher
            teacherService.save(teacher)
            commissionCertificationService.save(updCommission)


        def chkCommission

            chkCommission = commissionCertificationService.get(COMMISSION_ID)
            assert chkCommission != null
            assert chkCommission.head != null
            assert chkCommission.head.surname == 'Новировский'

    }

    @Override
    @Test
    void deleteItemTest() {
        def COMMISSION_ID

            def teachers = new ArrayList<Teacher>()
            (1..4).each {teachers << getTeacher()}
            teachers.each{teacherService.save(it)}
            CommissionCertification commission = getCommissionCertification(teachers)
            commissionCertificationService.save(commission)
            COMMISSION_ID = commission.id

        assert COMMISSION_ID != null

        def delCommission = null

            delCommission = commissionCertificationService.get(COMMISSION_ID)
            commissionCertificationService.delete(delCommission)

        def chkCommission = null

            chkCommission = commissionCertificationService.get(COMMISSION_ID)

        assert chkCommission == null


    }

    @Override
    void equalsHashCodeTest() {

    }

    @Override
    void toStringTest() {

    }
}
