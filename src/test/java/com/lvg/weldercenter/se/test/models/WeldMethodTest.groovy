package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldMethod
import com.lvg.weldercenter.se.models.WeldMethodType
import com.lvg.weldercenter.se.services.WeldMethodService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class WeldMethodTest extends GenericModelTest{
    @Autowired
    WeldMethodService service

    @Override
    @Test
    void insertItemTest() {
        def WELD_METHOD_ID
         WeldMethod weldMethod = getWeldMethod()
            service.save(weldMethod)
            WELD_METHOD_ID = weldMethod.id
        assert WELD_METHOD_ID != null

            def chkWeldMethod = service.get(WELD_METHOD_ID)
            assert chkWeldMethod != null
            assert chkWeldMethod.id != null
            assert chkWeldMethod.name == 'РДЭ'
            assert chkWeldMethod.code == '111'

    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_METHOD_ID
            WeldMethod weldMethod = getWeldMethod()
            service.save(weldMethod)
            WELD_METHOD_ID = weldMethod.id

        assert WELD_METHOD_ID != null

            WeldMethod weldMethodUpd = service.get(WELD_METHOD_ID)
            weldMethodUpd.name = 'ВИГ'
            service.save(weldMethodUpd)


            WeldMethod chkWeldMethod = service.get(WELD_METHOD_ID)
            assert chkWeldMethod.name == 'ВИГ'

    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_METHOD_ID
            WeldMethod weldMethod = getWeldMethod()
            service.save(weldMethod)
            WELD_METHOD_ID = weldMethod.id

        assert WELD_METHOD_ID != null

            WeldMethod weldMethodUpd = service.get(WELD_METHOD_ID)
            service.delete(weldMethodUpd)

            WeldMethod chkWeldMethod = service.get(WELD_METHOD_ID)
            assert chkWeldMethod == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def weldMethod1 = getWeldMethod()
        def weldMethod2 = getWeldMethod()

        assert weldMethod1 == weldMethod2

        weldMethod1.id = 100
        weldMethod2.id = 101

        assert weldMethod1 != weldMethod2

        def list = new HashSet<WeldMethod>()
        list.add(weldMethod1)
        weldMethod2.id = 100
        list.add(weldMethod2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def wm = getWeldMethod()
        def wmt = WeldMethodType.MMA
        assert wm.toString() == 'РДЭ (111)'
        assert "$wmt" == "$wm"

    }
}
