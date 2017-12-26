package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.services.EducationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional


@Transactional
class EducationServiceTest extends GenericServiceTest{

    @Autowired
    EducationService educationService

    void saveTest(){
        def EDUCATION_ID
        def education = getEducation()
        education = educationService.save(education)
        EDUCATION_ID = education.id
        assert EDUCATION_ID != null

        Education updEducation = educationService.get(EDUCATION_ID)
        assert updEducation != null
        assert updEducation instanceof Education
        updEducation.education = 'высшее'
        educationService.save(updEducation)

        Education chkEducation = educationService.get(EDUCATION_ID)
        assert chkEducation != null
        assert chkEducation instanceof Education
        assert chkEducation.education == 'высшее'
    }

    void getTest(){
        def EDUCATION_ID
        def education = getEducation()
        education = educationService.save(education)
        EDUCATION_ID = education.id
        assert EDUCATION_ID != null

        Education chkEducation = educationService.get(EDUCATION_ID)
        assert chkEducation != null
        assert chkEducation instanceof Education
    }

    void getAllTest(){
        def education = getEducation()
        education = educationService.save(education)
        assert education.id != null

        def list = educationService.getAll()
        assert list instanceof List
        assert list.size() == 5
    }

    @Override
    void countTest() {
        def education = getEducation()
        education = educationService.save(education)
        assert education.id != null

        def count = educationService.count()
        assert count instanceof Long
        assert count == 5
    }

    void deleteTest(){
        def EDUCATION_ID
        def education = getEducation()
        education = educationService.save(education)
        EDUCATION_ID = education.id
        assert EDUCATION_ID != null

        Education delEducation = educationService.get(EDUCATION_ID)
        assert delEducation != null
        assert delEducation instanceof Education
        educationService.delete(delEducation)

        Education chkEducation = educationService.get(EDUCATION_ID)
        assert chkEducation == null
    }
}
