package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldWire
import com.lvg.weldercenter.se.services.WeldWireService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class WeldWireTest extends GenericModelTest {

    @Autowired
    WeldWireService service

    @Override
    @Test
    void insertItemTest() {
        def WELD_WIRE_ID

        WeldWire weldWire = getWeldWire()
        service.save(weldWire)
        WELD_WIRE_ID = weldWire.id

        assert WELD_WIRE_ID != null

        def weldWire1 = service.get(WELD_WIRE_ID)
        assert weldWire1.id != null
        assert weldWire1.type == 'св08Г2С'

    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_WIRE_ID

        WeldWire weldWire = getWeldWire()
        service.save(weldWire)
        WELD_WIRE_ID = weldWire.id

        assert WELD_WIRE_ID != null

        WeldWire weldWire1Upd = service.get(WELD_WIRE_ID)
        weldWire1Upd.type = 'св08Г'
        service.save(weldWire1Upd)

        WeldWire chkWeldWire = service.get(WELD_WIRE_ID)
        assert chkWeldWire.type == 'св08Г'

    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_WIRE_ID

        WeldWire weldWire = getWeldWire()
        service.save(weldWire)
        WELD_WIRE_ID = weldWire.id

        assert WELD_WIRE_ID != null

        WeldWire weldWireUpd = service.get(WELD_WIRE_ID)
        service.delete(weldWireUpd)

        WeldWire chkWeldWire = service.get(WELD_WIRE_ID)
        assert chkWeldWire == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def weldWire2 = getWeldWire()
        def weldWire1 = getWeldWire()

        assert weldWire2 == weldWire1

        weldWire2.id = 100
        weldWire1.id = 101

        assert weldWire2 != weldWire1

        def list = new HashSet<WeldWire>()
        list.add(weldWire2)
        weldWire1.id = 100
        list.add(weldWire1)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def weldWire = getWeldWire()
        weldWire.type = 'св08Г2С'
        assert weldWire.toString() == 'св08Г2С'
    }
}
