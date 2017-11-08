package com.lvg.weldercenter.se.test.ui.models

import com.lvg.weldercenter.se.ui.models.ModelsConstants
import com.lvg.weldercenter.se.ui.models.WelderUI
import org.junit.Test

import java.time.LocalDate

import static com.lvg.weldercenter.se.test.models.ModelsGenerator.*

class WelderUITest extends GenericModelUITest{

    @Override
    @Test
    void constructorTest() {
        WelderUI welderUI = new WelderUI(getWelder())
        assert welderUI.id == ModelsConstants.NULL_ID_FIELD_DEFAULT
        assert welderUI.name == 'Иван'
        assert welderUI.surname == 'Иванов'
        assert welderUI.secondName == 'Иванович'
        assert welderUI.birthday == LocalDate.of(1984, 10, 28)
        assert welderUI.dateBegin == LocalDate.of(2000, 10, 28)
        assert welderUI.documentNumber == '17-033/17'
        assert welderUI.address == 'Michigan City 12066'
        assert welderUI.education == 'среднее-специальное'
        assert welderUI.qualification == 'электросварщик'
        assert welderUI.job == 'элекросварщик'
        assert welderUI.organization.name.get() == 'IBM'
        assert welderUI.organizationName == 'IBM'
        assert welderUI.birthdayFormat == '28.10.1984'
        assert welderUI.dateBeginFormat == '28.10.2000'

    }

    @Test(expected = ReadOnlyPropertyException.class)
    void checkUpdated(){
        WelderUI welderUI = new WelderUI(getWelder())
        //Exception
        welderUI.setProperty('id', 100l)

        def welder = welderUI.welder
        assert welderUI.id == ModelsConstants.NULL_ID_FIELD_DEFAULT
        welder.id == 100l
        assert welderUI.id == 100l
    }

    @Test
    void setterTest(){
        WelderUI welderUI = new WelderUI(getWelder())
        welderUI.name = 'Паша'
        welderUI.surname = ''
        welderUI.secondName = ''
        welderUI.birthday = LocalDate.of(1987, 10, 28)
        welderUI.dateBegin = LocalDate.of(2002, 10, 28)
        welderUI.documentNumber = '17-000'
        welderUI.address = '12066'
        welderUI.education = 'среднее'
        welderUI.qualification = 'сварщик'
        welderUI.job = 'сварщик'

        assert welderUI.id == ModelsConstants.NULL_ID_FIELD_DEFAULT
        assert welderUI.name == 'Паша'
        assert welderUI.surname == ''
        assert welderUI.secondName == ''
        assert welderUI.birthday == LocalDate.of(1987, 10, 28)
        assert welderUI.dateBegin == LocalDate.of(2002, 10, 28)
        assert welderUI.documentNumber == '17-000'
        assert welderUI.address == '12066'
        assert welderUI.education == 'среднее'
        assert welderUI.qualification == 'сварщик'
        assert welderUI.job == 'сварщик'

    }

}
