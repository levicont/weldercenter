package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.services.EducationService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import javax.transaction.Transactional

@Transactional
class EducationServiceTest extends GenericServiceTest{

    @Autowired
    EducationService educationService

    @Test
    void addEducationTest(){
        def education = getEducation()
        education = educationService.addEducation(education)
        assert education.id != null
    }

    @Test
    void findByIdEducationTest(){
        def EDUCATION_ID
        def education = getEducation()
        education = educationService.addEducation(education)
        EDUCATION_ID = education.id
        assert EDUCATION_ID != null

        Education chkEducation = educationService.findById(EDUCATION_ID)
        assert chkEducation != null
        assert chkEducation instanceof Education
    }

    @Test
    void getAllEducationTest(){
        def education = getEducation()
        education = educationService.addEducation(education)
        assert education.id != null

        def list = educationService.getAll()
        assert list instanceof List
        assert list.size() == 1
    }

    @Test
    void deleteEducationTest(){
        def EDUCATION_ID
        def education = getEducation()
        education = educationService.addEducation(education)
        EDUCATION_ID = education.id
        assert EDUCATION_ID != null

        Education delEducation = educationService.findById(EDUCATION_ID)
        assert delEducation != null
        assert delEducation instanceof Education

        educationService.delete(delEducation)

        Education chkEducation = educationService.findById(EDUCATION_ID)
        assert chkEducation == null
    }
}
