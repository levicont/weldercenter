package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Qualification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
public class QualificationServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private QualificationService service;

    @Override
    public void testGet() {
        Qualification record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Qualification> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Qualification record = new Qualification();
        record.setType("Сварщик С2Н2");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Сварщик С2Н2", record.getType());

    }

    @Override
    public void testDelete() {
        Qualification record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Qualification record = service.get(1L);
        record.setType("Сварщик С2Н2");

        service.update(record);

        record = service.get(1L);
        assertEquals("Сварщик С2Н2", record.getType());

    }
}
