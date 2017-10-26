package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.services.EducationService
import com.lvg.weldercenter.se.test.models.ModelsGenerator
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import javax.transaction.Transactional

@RunWith(SpringJUnit4ClassRunner)
@TestPropertySource("classpath:test.properties")
@ActiveProfiles("test")
@SpringBootTest
class EducationServiceTest extends ModelsGenerator{

    @Autowired
    EducationService educationService

    @Test(expected = RuntimeException.class)
    @Transactional(rollbackOn = RuntimeException.class)
    void addEducationTest(){
        def education = getEducation()
        education = educationService.addEducation(education)
        assert education.id != null
        throw new RuntimeException()
    }

    @Test
    @Transactional
    void getAllEducationTest(){
        def education = getEducation()
        education = educationService.addEducation(education)
        assert education.id != null

        def set = educationService.getAll()
        assert set instanceof Set
        assert set.size() == 1
    }
}
