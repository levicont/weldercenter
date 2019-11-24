package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.services.EducationService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class EducationTest extends GenericModelTest {
    @Autowired
    EducationService service

    @Override
    @Test
    void insertItemTest() {
        def EDUCATION_ID
        Education education = getEducation()
        service.save(education)
        EDUCATION_ID = education.id

        Education chkEducation = service.get(EDUCATION_ID)
        assert chkEducation.id != null
        assert chkEducation.education == 'среднее-специальное'

    }

    @Override
    @Test
    void updateItemTest() {
        def EDUCATION_ID

        Education education = getEducation()
        service.save(education)
        EDUCATION_ID = education.id

        assert EDUCATION_ID != null

        Education educationUpd = service.get(EDUCATION_ID)
        educationUpd.education = 'high'
        service.save(educationUpd)


        Education chkEducation = service.get(EDUCATION_ID)
        assert chkEducation.education == 'high'
    }

    @Override
    @Test
    void deleteItemTest() {
        def EDUCATION_ID

        Education education = getEducation()
        service.save(education)
        EDUCATION_ID = education.id

        assert EDUCATION_ID != null

        Education educationUpd = service.get(EDUCATION_ID)
        service.delete(educationUpd)

        Education chkEducation = service.get(EDUCATION_ID)
        assert chkEducation == null
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def org1 = getEducation()
        def org2 = getEducation()

        assert org1 == org2

        org1.id = 100
        org2.id = 101

        assert org1 != org2

        def list = new HashSet<Education>()
        list.add(org1)
        org2.id = 100
        list.add(org2)

        assert list.size() == 1

    }

    @Override
    @Test
    void toStringTest() {
        def education = getEducation()
        education.education = 'высшее'
        assert education.toString() == 'высшее'
    }
}
