package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Education;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
public class EducationServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private EducationService service;

    @Override
    public void testGet() {
        Education record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Education> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Education record = new Education();
        record.setType("Бакалавр");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Бакалавр", record.getType());

    }

    @Override
    public void testDelete() {
        Education record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Education record = service.get(1L);
        record.setType("Бакалавр");

        service.update(record);

        record = service.get(1L);
        assertEquals("Бакалавр", record.getType());

    }

    @Test
    public void testGetByType(){
        Education record = service.get(1L);
        Education record2 = service.getByType("Высшее");
        assertNotNull(record2);
        assertEquals(record.getEducationId(), record2.getEducationId());

    }
}
