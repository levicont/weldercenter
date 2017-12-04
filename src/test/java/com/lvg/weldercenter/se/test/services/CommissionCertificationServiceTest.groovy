package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.CommissionCertification
import com.lvg.weldercenter.se.models.Teacher
import com.lvg.weldercenter.se.services.CommissionCertificationService
import com.lvg.weldercenter.se.services.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class CommissionCertificationServiceTest extends GenericServiceTest{

    @Autowired
    CommissionCertificationService commissionCertificationService
    @Autowired
    TeacherService teacherService

    @Override
    void saveTest() {
        def COMMISSION_ID
        def teachers = new ArrayList<Teacher>()
        (1..4).each {teachers << getTeacher()}
        teachers.each{teacherService.save(it)}
        def commissionCertification = getCommissionCertification(teachers)
        commissionCertification = commissionCertificationService.save(commissionCertification)
        COMMISSION_ID = commissionCertification.id
        assert COMMISSION_ID != null

        CommissionCertification updCommissionCertification = commissionCertificationService.get(COMMISSION_ID)
        assert updCommissionCertification != null
        assert updCommissionCertification instanceof CommissionCertification
        updCommissionCertification.head.name = 'Самсон'
        commissionCertificationService.save(updCommissionCertification)

        CommissionCertification chkCommissionCertification = commissionCertificationService.get(COMMISSION_ID)
        assert chkCommissionCertification != null
        assert chkCommissionCertification instanceof CommissionCertification
        assert chkCommissionCertification.head.name == 'Самсон'
    }

    @Override
    void deleteTest() {
        def COMMISSION_ID
        def teachers = new ArrayList<Teacher>()
        (1..4).each {teachers << getTeacher()}
        teachers.each{teacherService.save(it)}
        def commissionCertification = getCommissionCertification(teachers)
        commissionCertification = commissionCertificationService.save(commissionCertification)
        COMMISSION_ID = commissionCertification.id
        assert COMMISSION_ID != null

        CommissionCertification delCommissionCertification = commissionCertificationService.get(COMMISSION_ID)
        assert delCommissionCertification != null
        assert delCommissionCertification instanceof CommissionCertification
        commissionCertificationService.delete(delCommissionCertification)

        CommissionCertification chkCommissionCertification = commissionCertificationService.get(COMMISSION_ID)
        assert chkCommissionCertification == null
    }

    @Override
    void getTest() {
        def COMMISSION_ID
        def teachers = new ArrayList<Teacher>()
        (1..4).each {teachers << getTeacher()}
        teachers.each{teacherService.save(it)}
        def commissionCertification = getCommissionCertification(teachers)
        commissionCertification = commissionCertificationService.save(commissionCertification)
        COMMISSION_ID = commissionCertification.id
        assert COMMISSION_ID != null

        CommissionCertification updCommissionCertification = commissionCertificationService.get(COMMISSION_ID)
        assert updCommissionCertification != null
        assert updCommissionCertification instanceof CommissionCertification
    }

    @Override
    void getAllTest() {
        def COMMISSION_ID
        def teachers = new ArrayList<Teacher>()
        (1..4).each {teachers << getTeacher()}
        teachers.each{teacherService.save(it)}
        def commissionCertification = getCommissionCertification(teachers)
        commissionCertification = commissionCertificationService.save(commissionCertification)
        COMMISSION_ID = commissionCertification.id
        assert COMMISSION_ID != null

        def list = commissionCertificationService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }


    void countTest() {
        def COMMISSION_ID
        def teachers = new ArrayList<Teacher>()
        (1..4).each {teachers << getTeacher()}
        teachers.each{teacherService.save(it)}
        def commissionCertification = getCommissionCertification(teachers)
        commissionCertification = commissionCertificationService.save(commissionCertification)
        COMMISSION_ID = commissionCertification.id
        assert COMMISSION_ID != null

        def count = commissionCertificationService.count()
        assert count != null
        assert count instanceof Long
        assert count == 1
    }
}
