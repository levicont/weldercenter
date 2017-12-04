package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.services.QualificationService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class QualificationServiceTest extends GenericServiceTest {

    @Autowired
    QualificationService qualificationService

    @Override
    void saveTest() {
        def QUALIFICATION_ID
        def qualification = getQualification()
        qualification = qualificationService.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        Qualification updQualification = qualificationService.get(QUALIFICATION_ID)
        assert updQualification != null
        assert updQualification instanceof Qualification
        updQualification.type = 'сварщик'
        qualificationService.save(updQualification)

        Qualification chkQualification = qualificationService.get(QUALIFICATION_ID)
        assert chkQualification != null
        assert chkQualification instanceof Qualification
        assert chkQualification.type == 'сварщик'
    }

    @Override
    void deleteTest() {
        def QUALIFICATION_ID
        def qualification = getQualification()
        qualification = qualificationService.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        Qualification delQualification = qualificationService.get(QUALIFICATION_ID)
        assert delQualification != null
        assert delQualification instanceof Qualification
        qualificationService.delete(delQualification)

        Qualification chkQualification = qualificationService.get(QUALIFICATION_ID)
        assert chkQualification == null

    }

    @Override
    void getTest() {
        def QUALIFICATION_ID
        def qualification = getQualification()
        qualification = qualificationService.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        Qualification updQualification = qualificationService.get(QUALIFICATION_ID)
        assert updQualification != null
        assert updQualification instanceof Qualification
    }

    @Override
    void getAllTest() {
        def QUALIFICATION_ID
        def qualification = getQualification()
        qualification = qualificationService.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        def list = qualificationService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }

    @Test
    void countTest() {
        def QUALIFICATION_ID
        def qualification = getQualification()
        qualification = qualificationService.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        def count = qualificationService.count()
        assert count != null
        assert count instanceof Long
        assert count == 1
    }
}