package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate

class WelderTest extends GenericModelTest {

    @Autowired
    private WelderService welderService

    @Test
    void insertItemTest() {
        def WELDER_ID
        Welder welder = getWelder()
        welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        Welder chkWelder = welderService.get(WELDER_ID)
        def org = chkWelder.getOrganization()
        assert chkWelder.id != null
        assert chkWelder.id == WELDER_ID
        assert chkWelder.name == 'Иван'
        assert chkWelder.surname == 'Иванов'
        assert chkWelder.secondName == 'Иванович'
        assert chkWelder.dateBegin == LocalDate.of(2000, 10, 28)
        assert chkWelder.birthday == LocalDate.of(1987, 10, 28)
        assert chkWelder.documentNumber == '17-033/17'
        assert chkWelder.address == 'Michigan City 12066'
        assert chkWelder.education == 'среднее-специальное'
        assert chkWelder.qualification == 'электросварщик'
        assert chkWelder.job == 'элекросварщик'
        assert org.id != null
        assert org.name == 'IBM'

    }

    @Test
    void updateItemTest() {
        def WELDER_ID

        Welder welder = getWelder()
        welderService.save(welder)
        WELDER_ID = welder.id

        assert WELDER_ID != null

        Welder welderUpd = welderService.get(WELDER_ID)
        welderUpd.surname = 'Петров'
        welderService.save(welderUpd)

        Welder chkWelder = welderService.get(WELDER_ID)
        assert chkWelder.surname == 'Петров'

    }


    @Test
    void deleteItemTest() {
        def WELDER_ID
        Welder welder = getWelder()
        welderService.save(welder)
        WELDER_ID = welder.id


        assert WELDER_ID != null
        Welder welderUpd = welderService.get(WELDER_ID)
        welderService.delete(welderUpd)

        Welder chkWelder = welderService.get(WELDER_ID)
        assert chkWelder == null


    }

    @Test
    void equalsHashCodeTest() {
        def welder1 = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        def welder2 = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')

        assert welder1 == welder2

        welder1.id = 100
        welder2.id = 101

        assert welder1 != welder2

        def list = new HashSet<Welder>()
        list.add(welder1)
        welder2.id = 100
        list.add(welder2)

        assert list.size() == 1

    }

    @Test
    void toStringTest() {
        def welder = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        assert welder.toString() == "Иванов Иван Иванович"
    }
}
