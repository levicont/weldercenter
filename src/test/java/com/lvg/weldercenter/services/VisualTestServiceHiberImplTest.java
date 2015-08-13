package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.VisualTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class VisualTestServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private VisualTestService service;

    @Autowired
    private EvaluationService evaServ;

    @Override
    public void testGet() {
        VisualTest record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<VisualTest> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        VisualTest record = new VisualTest();
        record.setDefects("Трещины и подрезы отсутсвуют");
        record.setNumber("14-002VT");
        record.setProtDate(new Date());
        record.setEvaluation(evaServ.get(1L));

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("14-002VT", record.getNumber());

    }

    @Override
    public void testDelete() {
        VisualTest record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        VisualTest record = service.get(1L);
        record.setNumber("14-002VT");

        service.update(record);

        record = service.get(1L);
        assertEquals("14-002VT", record.getNumber());

    }

}
