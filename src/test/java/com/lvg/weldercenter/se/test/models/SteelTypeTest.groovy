package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.SteelGroup
import com.lvg.weldercenter.se.models.SteelType
import com.lvg.weldercenter.se.services.SteelTypeService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class SteelTypeTest extends GenericModelTest{

    @Autowired
    SteelTypeService service

    @Override
    @Test
    void insertItemTest() {
        def STEEL_TYPE_ID
            SteelType steelType = getSteelType()
            service.save(steelType)
            STEEL_TYPE_ID = steelType.id

        assert STEEL_TYPE_ID != null

            SteelType steelType1 = service.get(STEEL_TYPE_ID)
            assert steelType1.id != null
            assert steelType1.type == 'сталь 20'

    }

    @Override
    @Test
    void updateItemTest() {
        def STEEL_TYPE_ID
            SteelType steelType = getSteelType()
            service.save(steelType)
            STEEL_TYPE_ID = steelType.id

        assert STEEL_TYPE_ID != null

            SteelType steelTypeUpd = service.get(STEEL_TYPE_ID)
            steelTypeUpd.type = 'сталь 40'
            steelTypeUpd.steelGroup = SteelGroup.W02
            service.save(steelTypeUpd)


            SteelType chkSteelType = service.get(STEEL_TYPE_ID)
            assert chkSteelType.type == 'сталь 40'
            assert chkSteelType.steelGroup == SteelGroup.W02

    }

    @Override
    @Test
    void deleteItemTest() {
        def STEEL_TYPE_ID
            SteelType steelType = getSteelType()
            service.save(steelType)
            STEEL_TYPE_ID = steelType.id

        assert STEEL_TYPE_ID != null

            SteelType steelTypeUpd = service.get(STEEL_TYPE_ID)
            service.delete(steelTypeUpd)


            SteelType chkSteelType = service.get(STEEL_TYPE_ID)
            assert chkSteelType == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def steelType1 = getSteelType()
        def steelType2 = getSteelType()

        assert steelType1 == steelType2

        steelType1.id = 100
        steelType2.id = 101

        assert steelType1 != steelType2

        def list = new HashSet<SteelType>()
        list.add(steelType1)
        steelType2.id = 100
        list.add(steelType2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def steelType = getSteelType()
        steelType.type = 'сталь 09Г2С'
        assert steelType.toString() == 'сталь 09Г2С'
    }
}
