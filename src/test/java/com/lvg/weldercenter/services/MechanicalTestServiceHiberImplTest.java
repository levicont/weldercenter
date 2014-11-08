package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.MechanicalTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class MechanicalTestServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private MechanicalTestService service;

    @Autowired
    private EvaluationService evaServ;

    @Override
    public void testGet() {
        MechanicalTest record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<MechanicalTest> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        MechanicalTest record = new MechanicalTest();
        record.setAngle(120.0);
        record.setProtDate(new Date());
        record.setNumber("14-008Mech");
        record.setEvaluation(evaServ.get(1L));

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("14-008Mech", record.getNumber());

    }

    @Override
    public void testDelete() {
        MechanicalTest record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        MechanicalTest record = service.get(1L);
        record.setNumber("14-008Mech");

        service.update(record);

        record = service.get(1L);
        assertEquals("14-008Mech", record.getNumber());

    }

}
