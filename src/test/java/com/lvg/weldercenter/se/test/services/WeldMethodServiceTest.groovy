package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.WeldMethod
import com.lvg.weldercenter.se.models.WeldMethodType
import com.lvg.weldercenter.se.services.WeldMethodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class WeldMethodServiceTest extends GenericServiceTest{

    @Autowired
    WeldMethodService weldMethodService

    @Override
    void saveTest() {
        def WELD_METHOD_ID
        def weldMethod = getWeldMethod()
        weldMethod = weldMethodService.save(weldMethod)
        WELD_METHOD_ID = weldMethod.id
        assert WELD_METHOD_ID != null

        WeldMethod updWeldMethod = weldMethodService.get(WELD_METHOD_ID)
        assert updWeldMethod != null
        assert updWeldMethod instanceof WeldMethod
        updWeldMethod.name = WeldMethodType.GW.weldMethod.name
        weldMethodService.save(weldMethod)

        WeldMethod chkWeldMethod = weldMethodService.get(WELD_METHOD_ID)
        assert chkWeldMethod != null
        assert chkWeldMethod instanceof WeldMethod
        assert chkWeldMethod.name == WeldMethodType.GW.weldMethod.name
    }

    @Override
    void deleteTest() {
        def WELD_METHOD_ID
        def weldMethod = getWeldMethod()
        weldMethod = weldMethodService.save(weldMethod)
        WELD_METHOD_ID = weldMethod.id
        assert WELD_METHOD_ID != null

        WeldMethod delWeldMethod = weldMethodService.get(WELD_METHOD_ID)
        assert delWeldMethod != null
        assert delWeldMethod instanceof WeldMethod
        weldMethodService.delete(delWeldMethod)

        WeldMethod chkWeldMethod = weldMethodService.get(WELD_METHOD_ID)
        assert chkWeldMethod == null
    }

    @Override
    void getTest() {
        def WELD_METHOD_ID
        def weldMethod = getWeldMethod()
        weldMethod = weldMethodService.save(weldMethod)
        WELD_METHOD_ID = weldMethod.id
        assert WELD_METHOD_ID != null

        WeldMethod chkWeldMethod = weldMethodService.get(WELD_METHOD_ID)
        assert chkWeldMethod != null
        assert chkWeldMethod instanceof WeldMethod
    }

    @Override
    void getAllTest() {
        def WELD_METHOD_ID
        def weldMethod = getWeldMethod()
        weldMethod = weldMethodService.save(weldMethod)
        WELD_METHOD_ID = weldMethod.id
        assert WELD_METHOD_ID != null

        def list = weldMethodService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
