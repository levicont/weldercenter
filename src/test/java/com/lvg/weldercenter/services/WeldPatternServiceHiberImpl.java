package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.RadiationTest;
import com.lvg.weldercenter.models.WeldPattern;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class WeldPatternServiceHiberImpl extends GenericServiceHibernateTest {


    @Autowired
    private WeldPatternService service;
    @Autowired
    private ElectrodeService elServ;
    @Autowired
    private MechanicalTestService mtServ;
    @Autowired
    private VisualTestService vtServ;
    @Autowired
    private RadiationTestService rtServ;
    @Autowired
    private PersonalProtocolService ppServ;
    @Autowired
    private SteelTypeService steelTypeServ;
    @Autowired
    private WeldMethodService weldMethodServ;
    @Autowired
    private WeldDetailService weldDetailServ;
    @Autowired
    private WeldPositionService weldPositionServ;
    @Autowired
    private WeldWireService weldWireServ;
    @Autowired
    private WeldGasService weldGasService;


    @Override
    public void testGet() {
        WeldPattern record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<WeldPattern> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        WeldPattern record = getWeldPattern();

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("12", record.getMark());

    }

    @Override
    public void testDelete() {
        WeldPattern record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        WeldPattern record = service.get(1L);
        record.setMark("12");

        service.update(record);

        record = service.get(1L);
        assertEquals("12", record.getMark());

    }

    @Test
    @Transactional
    public void testCascade(){
        WeldPattern wp = service.get(1l);
        RadiationTest rt = rtServ.get(1l);

        assertNotNull(rt.getWeldPattern());
        System.out.println("\n SYSOUT \n");
        System.out.println("\n **** wp.Mark: " + wp.getMark() + "\t rt.pattern.Mark: "
               + rt.getWeldPattern().getMark() + "\n");
        assertEquals(wp.getMark(),rt.getWeldPattern().getMark());
    }

    private WeldPattern getWeldPattern(){
        WeldPattern record = new WeldPattern();
        record.setDiameter(89.0);
        record.setThickness(5.0);
        record.setMark("12");
        record.setElectrode(elServ.get(1l));
        record.setWeldMethod(weldMethodServ.get(1l));
        record.setWeldPosition(weldPositionServ.get(1L));
        record.setWeldDetail(weldDetailServ.get(1L));
        record.setSteelType(steelTypeServ.get(1L));
        record.setWeldGas(weldGasService.get(1L));
        record.setWeldWire(weldWireServ.get(1L));
        record.setRadiationTest(rtServ.get(1L));
        record.setVisualTest(vtServ.get(1L));
        record.setMechanicalTest(mtServ.get(1L));
        record.setIsHeatTreatment(true);
        record.setIsHeating(false);
        record.setPersonalProtocol(ppServ.get(1l));

        return record;
    }

}
