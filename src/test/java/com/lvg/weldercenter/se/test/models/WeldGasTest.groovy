package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldGas
import com.lvg.weldercenter.se.services.WeldGasService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class WeldGasTest extends GenericModelTest{

    @Autowired
    WeldGasService service

    @Override
    @Test
    void insertItemTest() {
        def WELD_GAS_ID
            WeldGas weldGas = getWeldGas()
            service.save(weldGas)
            WELD_GAS_ID = weldGas.id

        assert WELD_GAS_ID != null
            def weldGas1 = service.get(WELD_GAS_ID)
            assert weldGas1.id != null
            assert weldGas1.type == 'Аргон'

    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_GAS_ID
            WeldGas weldGas = getWeldGas()
            service.save(weldGas)
            WELD_GAS_ID = weldGas.id

        assert WELD_GAS_ID != null

            WeldGas weldGasUpd = service.get(WELD_GAS_ID)
            weldGasUpd.type = 'CO2'
            service.save(weldGasUpd)

            WeldGas chkWeldGas = service.get(WELD_GAS_ID)
            assert chkWeldGas.type == 'CO2'

    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_GAS_ID
            WeldGas weldGas = getWeldGas()
            service.save(weldGas)
            WELD_GAS_ID = weldGas.id

        assert WELD_GAS_ID != null

            WeldGas weldGasUpd = service.get(WELD_GAS_ID)
            service.delete(weldGasUpd)

            WeldGas chkWeldGas = service.get(WELD_GAS_ID)
            assert chkWeldGas == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def weldGas1 = getWeldGas()
        def weldGas2 = getWeldGas()

        assert weldGas1 == weldGas2

        weldGas1.id = 100
        weldGas2.id = 101

        assert weldGas1 != weldGas2

        def list = new HashSet<WeldGas>()
        list.add(weldGas1)
        weldGas2.id = 100
        list.add(weldGas2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def weldGas = getWeldGas()
        weldGas.type = 'Аргон'
        assert weldGas.toString() == 'Аргон'
    }
}
