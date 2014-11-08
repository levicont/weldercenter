package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Curriculum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class CurriculumServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private CurriculumService service;

    @Override
    public void testGet() {
        Curriculum record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Curriculum> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Curriculum record = new Curriculum();
        record.setTitle("Программа 36 часов");
        record.setDescription("Программа подготовки сварщиков 36 часов");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Программа 36 часов", record.getTitle());

    }

    @Override
    public void testDelete() {
        Curriculum record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Curriculum record = service.get(1L);
        record.setTitle("Программа 36 часов");
        record.setDescription("Программа подготовки сварщиков 36 часов");


        service.update(record);

        record = service.get(1L);
        assertEquals("Программа 36 часов", record.getTitle());

    }
}
