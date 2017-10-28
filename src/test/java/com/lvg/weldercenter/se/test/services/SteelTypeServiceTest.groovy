package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.SteelGroup
import com.lvg.weldercenter.se.models.SteelType
import com.lvg.weldercenter.se.services.SteelTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class SteelTypeServiceTest extends GenericServiceTest{

    @Autowired
    SteelTypeService steelTypeService

    @Override
    void saveTest() {
        def STEEL_TYPE_ID
        def steelType = getSteelType()
        steelType = steelTypeService.save(steelType)
        STEEL_TYPE_ID = steelType.id
        assert STEEL_TYPE_ID != null

        SteelType updSteelType = steelTypeService.get(STEEL_TYPE_ID)
        assert updSteelType != null
        assert updSteelType instanceof SteelType
        updSteelType.type = 'ст2'
        updSteelType.steelGroup = SteelGroup.W02
        steelTypeService.save(steelType)

        SteelType chkSteelType = steelTypeService.get(STEEL_TYPE_ID)
        assert chkSteelType != null
        assert chkSteelType instanceof SteelType
        assert chkSteelType.type == 'ст2'
        assert chkSteelType.steelGroup == SteelGroup.W02
    }

    @Override
    void deleteTest() {
        def STEEL_TYPE_ID
        def steelType = getSteelType()
        steelType = steelTypeService.save(steelType)
        STEEL_TYPE_ID = steelType.id
        assert STEEL_TYPE_ID != null

        SteelType delSteelType = steelTypeService.get(STEEL_TYPE_ID)
        assert delSteelType != null
        assert delSteelType instanceof SteelType
        steelTypeService.delete(steelType)

        SteelType chkSteelType = steelTypeService.get(STEEL_TYPE_ID)
        assert chkSteelType == null

    }

    @Override
    void getTest() {
        def STEEL_TYPE_ID
        def steelType = getSteelType()
        steelType = steelTypeService.save(steelType)
        STEEL_TYPE_ID = steelType.id
        assert STEEL_TYPE_ID != null

        SteelType chkSteelType = steelTypeService.get(STEEL_TYPE_ID)
        assert chkSteelType != null
        assert chkSteelType instanceof SteelType
    }

    @Override
    void getAllTest() {
        def STEEL_TYPE_ID
        def steelType = getSteelType()
        steelType = steelTypeService.save(steelType)
        STEEL_TYPE_ID = steelType.id
        assert STEEL_TYPE_ID != null

        def list = steelTypeService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
