package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.RadiationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class RadiationTestServiceHiberImpl extends GenericServiceHibernateTest {


    @Autowired
    private RadiationTestService service;

    @Autowired
    private EvaluationService evaServ;

    @Override
    public void testGet() {
        RadiationTest record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<RadiationTest> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        RadiationTest record = new RadiationTest();
        record.setNumber("14-001RT");
        record.setDefects("Нк200 2Ш4х5");
        record.setProtDate(new Date());
        record.setSensitivity("0.3");
        record.setEvaluation(evaServ.get(1L));

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("0.3", record.getSensitivity());

    }

    @Override
    public void testDelete() {
        RadiationTest record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        RadiationTest record = service.get(1L);
        record.setSensitivity("0.3");

        service.update(record);

        record = service.get(1L);
        assertEquals("0.3", record.getSensitivity());

    }

}
