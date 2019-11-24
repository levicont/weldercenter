package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Electrode
import com.lvg.weldercenter.se.services.ElectrodeService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class ElectrodeTest extends GenericModelTest{

    @Autowired
    ElectrodeService service

    @Override
    @Test
    void insertItemTest() {
        def ELECTRODE_ID


            Electrode electrode = getElectrode()
            service.save(electrode)
            ELECTRODE_ID = electrode.id

        assert ELECTRODE_ID != null

            Electrode chkElectrode = service.get(ELECTRODE_ID)
            assert chkElectrode.id != null
            assert chkElectrode.type == 'АНО-21'



    }

    @Override
    @Test
    void updateItemTest() {
        def ELECTRODE_ID

            Electrode electrode = getElectrode()
            service.save(electrode)
            ELECTRODE_ID = electrode.id

        assert ELECTRODE_ID != null

            Electrode electrodeUpd = service.get(ELECTRODE_ID)
            electrodeUpd.type = 'УОНИ'
            service.save(electrodeUpd)

            Electrode chkElectrode = service.get(ELECTRODE_ID)
            assert chkElectrode.type == 'УОНИ'
    }

    @Override
    @Test
    void deleteItemTest() {
        def ELECTRODE_ID
            Electrode electrode = getElectrode()
            service.save(electrode)
            ELECTRODE_ID = electrode.id
        assert ELECTRODE_ID != null

            Electrode electrodeUpd = service.get(ELECTRODE_ID)
            service.delete(electrodeUpd)

            Electrode chkElectrode = service.get(ELECTRODE_ID)
            assert chkElectrode == null
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def electrode1 = getElectrode()
        def electrode2 = getElectrode()

        assert electrode1 == electrode2

        electrode1.id = 100
        electrode2.id = 101

        assert electrode1 != electrode2

        def list = new HashSet<Electrode>()
        list.add(electrode1)
        electrode2.id = 100
        list.add(electrode2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def electrode = getElectrode()
        electrode.type = 'АНО-21'
        assert electrode.toString() == 'АНО-21'
    }
}
