package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.services.QualificationService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class QualificationTest extends GenericModelTest {

    @Autowired
    QualificationService service

    @Override
    @Test
    void insertItemTest() {
        def QUALIFICATION_ID
        Qualification qualification = getQualification()
        service.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        Qualification chkQualification = service.get(QUALIFICATION_ID)
        assert chkQualification.id != null
        assert chkQualification.type == 'электросварщик'
    }

    @Override
    @Test
    void updateItemTest() {
        def QUALIFICATION_ID
        Qualification qualification = getQualification()
        service.save(qualification)
        QUALIFICATION_ID = qualification.id
        assert QUALIFICATION_ID != null

        Qualification qualificationUpd = service.get(QUALIFICATION_ID)
        qualificationUpd.type = 'газосварщик'
        service.save(qualificationUpd)

        Qualification chkQualification = service.get(QUALIFICATION_ID)
        assert chkQualification.type == 'газосварщик'

    }

    @Override
    @Test
    void deleteItemTest() {
        def QUALIFICATION_ID
        Qualification qualification = getQualification()
        service.save(qualification)
        QUALIFICATION_ID = qualification.id

        assert QUALIFICATION_ID != null

        Qualification qualificationUpd = service.get(QUALIFICATION_ID)
        service.delete(qualificationUpd)

        Qualification chkQualification = service.get(QUALIFICATION_ID)
        assert chkQualification == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def qualification1 = getQualification()
        def qualification2 = getQualification()

        assert qualification1 == qualification2

        qualification1.id = 100
        qualification2.id = 101

        assert qualification1 != qualification2

        def list = new HashSet<Qualification>()
        list.add(qualification1)
        qualification2.id = 100
        list.add(qualification2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def qualification = getQualification()
        qualification.type = 'газосварщик'
        assert qualification.toString() == 'газосварщик'
    }
}
