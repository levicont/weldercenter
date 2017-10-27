package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Electrode
import com.lvg.weldercenter.se.services.ElectrodeService
import org.springframework.beans.factory.annotation.Autowired

import javax.transaction.Transactional

@Transactional
class ElectrodeServiceTest extends GenericServiceTest{

    @Autowired
    ElectrodeService electrodeService

    @Override
    void saveTest() {
        def ELECTRODE_ID
        def electrode = getElectrode()
        electrode = electrodeService.save(electrode)
        ELECTRODE_ID = electrode.id
        assert ELECTRODE_ID != null

        Electrode updElectrode = electrodeService.get(ELECTRODE_ID)
        assert updElectrode != null
        assert updElectrode instanceof Electrode
        updElectrode.type = 'УОНИ'
        electrodeService.save(electrode)

        Electrode chkElectrode = electrodeService.get(ELECTRODE_ID)
        assert chkElectrode != null
        assert chkElectrode instanceof Electrode
        assert chkElectrode.type == 'УОНИ'
    }

    @Override
    void deleteTest() {
        def ELECTRODE_ID
        def electrode = getElectrode()
        electrode = electrodeService.save(electrode)
        ELECTRODE_ID = electrode.id
        assert ELECTRODE_ID != null

        Electrode delElectrode = electrodeService.get(ELECTRODE_ID)
        assert delElectrode != null
        assert delElectrode instanceof Electrode
        electrodeService.delete(electrode)

        Electrode chkElectrode = electrodeService.get(ELECTRODE_ID)
        assert chkElectrode == null
    }

    @Override
    void getTest() {
        def ELECTRODE_ID
        def electrode = getElectrode()
        electrode = electrodeService.save(electrode)
        ELECTRODE_ID = electrode.id
        assert ELECTRODE_ID != null

        Electrode updElectrode = electrodeService.get(ELECTRODE_ID)
        assert updElectrode != null
        assert updElectrode instanceof Electrode
    }

    @Override
    void getAllTest() {
        def ELECTRODE_ID
        def electrode = getElectrode()
        electrode = electrodeService.save(electrode)
        ELECTRODE_ID = electrode.id
        assert ELECTRODE_ID != null

        def list = electrodeService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
