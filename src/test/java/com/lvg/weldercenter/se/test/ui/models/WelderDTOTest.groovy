package com.lvg.weldercenter.se.test.ui.models


import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import org.apache.log4j.Logger
import org.junit.Test


import java.time.LocalDate

import static com.lvg.weldercenter.se.test.models.ModelsGenerator.*

class WelderDTOTest extends GenericModelDTOTest{
    private static final Logger LOGGER = Logger.getLogger(WelderDTOTest.class)

    @Override
    @Test
    void constructorTest() {
        WelderDTO welderUI = new WelderDTO(getWelder())
        assert welderUI.id == DTOConstants.NULL_ID_FIELD_DEFAULT
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
        assert welderUI.organizationDTO.name == 'IBM'
        assert welderUI.organizationName == 'IBM'
        assert welderUI.birthdayFormat == '28.10.1984'
        assert welderUI.dateBeginFormat == '28.10.2000'

    }

    @Test(expected = ReadOnlyPropertyException.class)
    void checkUpdated(){
        WelderDTO welderUI = new WelderDTO(getWelder())


        def welder = welderUI.welder
        assert !welderUI.isWelderDTOChangedProperty.get()
        assert welderUI.id == DTOConstants.NULL_ID_FIELD_DEFAULT

        welder.id == 100l
        assert welderUI.id != 100l
        assert !welderUI.isWelderDTOChangedProperty.get()

        welderUI.nameProperty.set("George")
        assert welderUI.nameProperty.get() != welderUI.originalWelderProperty().get().name
        assert welderUI.isWelderDTOChangedProperty.get()
        welderUI.nameProperty.set("Иван")
        LOGGER.debug("isWelderDTOChangedProperty: ${welderUI.isWelderDTOChangedProperty}")
        assert !welderUI.isWelderDTOChangedProperty.get()

        welderUI.nameProperty.set("George")
        assert welderUI.isWelderDTOChangedProperty.get()
        welderUI.surnameProperty.set("Johnson")
        assert welderUI.isWelderDTOChangedProperty.get()
        welderUI.nameProperty.set("Иван")
        assert welderUI.isWelderDTOChangedProperty.get()
        welderUI.surnameProperty.set("Иванов")
        assert !welderUI.isWelderDTOChangedProperty.get()

        //Exception
        welderUI.setProperty('id', 100l)


    }

    @Test
    void setterTest(){
        WelderDTO welderUI = new WelderDTO(getWelder())
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

        assert welderUI.id == DTOConstants.NULL_ID_FIELD_DEFAULT
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

    @Test(expected = IllegalArgumentException.class)
    void nullTest(){
        new WelderDTO(null)
    }

    @Test
    void propertiesTest(){
        WelderDTO welderUI = new WelderDTO(getWelder())
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

        assert welderUI.id == DTOConstants.NULL_ID_FIELD_DEFAULT
        assert welderUI.nameProperty.get() == 'Паша'
        assert welderUI.surnameProperty.get() == ''
        assert welderUI.secondNameProperty.get() == ''
        assert welderUI.birthdayProperty.get() == LocalDate.of(1987, 10, 28)
        assert welderUI.dateBeginProperty.get() == LocalDate.of(2002, 10, 28)
        assert welderUI.documentNumberProperty.get() == '17-000'
        assert welderUI.addressProperty.get() == '12066'
        assert welderUI.educationProperty.get() == 'среднее'
        assert welderUI.qualificationProperty.get() == 'сварщик'
        assert welderUI.jobProperty.get() == 'сварщик'

    }

    @Test
    void cloneTest(){
        WelderDTO welderUI = new WelderDTO(getWelder())

        assert welderUI.id == DTOConstants.NULL_ID_FIELD_DEFAULT
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
        assert welderUI.organizationDTO.name == 'IBM'
        assert welderUI.organizationName == 'IBM'
        assert welderUI.birthdayFormat == '28.10.1984'
        assert welderUI.dateBeginFormat == '28.10.2000'

        WelderDTO clone = (WelderDTO)welderUI.clone()

        clone.name = 'Паша'
        clone.surname = ''
        clone.secondName = ''
        clone.birthday = LocalDate.of(1987, 10, 28)
        clone.dateBegin = LocalDate.of(2002, 10, 28)
        clone.documentNumber = '17-000'
        clone.address = '12066'
        clone.education = 'среднее'
        clone.qualification = 'сварщик'
        clone.job = 'сварщик'

        assert clone.name != welderUI.name
        assert clone.surname != welderUI.surname
        assert clone.secondName != welderUI.secondName
        assert clone.birthday != welderUI.birthday
        assert clone.dateBegin != welderUI.dateBegin
        assert clone.documentNumber != welderUI.documentNumber
        assert clone.address != welderUI.address
        assert clone.education != welderUI.education
        assert clone.qualification != welderUI.qualification
        assert clone.job != welderUI.job


    }

}
