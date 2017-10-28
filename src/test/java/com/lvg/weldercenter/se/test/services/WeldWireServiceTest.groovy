package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.WeldWire
import com.lvg.weldercenter.se.services.WeldWireService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class WeldWireServiceTest extends GenericServiceTest{

    @Autowired
    WeldWireService weldWireService

    @Override
    void saveTest() {
        def WELD_WIRE_ID
        def weldWire = getWeldWire()
        weldWire = weldWireService.save(weldWire)
        WELD_WIRE_ID = weldWire.id
        assert WELD_WIRE_ID != null

        WeldWire updWeldWire = weldWireService.get(WELD_WIRE_ID)
        assert updWeldWire != null
        assert updWeldWire instanceof WeldWire
        updWeldWire.type = 'СВ08'
        weldWireService.save(weldWire)

        WeldWire chkWeldWire = weldWireService.get(WELD_WIRE_ID)
        assert chkWeldWire != null
        assert chkWeldWire instanceof WeldWire
        assert chkWeldWire.type == 'СВ08'
    }

    @Override
    void deleteTest() {
        def WELD_WIRE_ID
        def weldWire = getWeldWire()
        weldWire = weldWireService.save(weldWire)
        WELD_WIRE_ID = weldWire.id
        assert WELD_WIRE_ID != null

        WeldWire delWeldWire = weldWireService.get(WELD_WIRE_ID)
        assert delWeldWire != null
        assert delWeldWire instanceof WeldWire
        weldWireService.delete(weldWire)

        WeldWire chkWeldWire = weldWireService.get(WELD_WIRE_ID)
        assert chkWeldWire == null
    }

    @Override
    void getTest() {
        def WELD_WIRE_ID
        def weldWire = getWeldWire()
        weldWire = weldWireService.save(weldWire)
        WELD_WIRE_ID = weldWire.id
        assert WELD_WIRE_ID != null

        WeldWire updWeldWire = weldWireService.get(WELD_WIRE_ID)
        assert updWeldWire != null
        assert updWeldWire instanceof WeldWire
    }

    @Override
    void getAllTest() {
        def WELD_WIRE_ID
        def weldWire = getWeldWire()
        weldWire = weldWireService.save(weldWire)
        WELD_WIRE_ID = weldWire.id
        assert WELD_WIRE_ID != null

        def list = weldWireService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
