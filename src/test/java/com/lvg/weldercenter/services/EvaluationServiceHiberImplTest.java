package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class EvaluationServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private EvaluationService service;

    @Override
    public void testGet() {
        Evaluation record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Evaluation> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Evaluation record = new Evaluation();
        record.setType("Брак");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Брак", record.getType());

    }

    @Override
    public void testDelete() {
        Evaluation record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Evaluation record = service.get(1L);
        record.setType("Брак");

        service.update(record);

        record = service.get(1L);
        assertEquals("Брак", record.getType());

    }

}
