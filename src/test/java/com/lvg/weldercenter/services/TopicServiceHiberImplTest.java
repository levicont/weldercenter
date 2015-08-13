package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Topic;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class TopicServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private TopicService service;

    @Override
    public void testGet() {
        Topic record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Topic> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Topic record = new Topic();
        record.setTitle("Техника газовой сварки");
        record.setDescription("Виды и типы газов для сварки");
        record.setOrderIndex(3);
        record.setTimelong(4.0);

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Техника газовой сварки", record.getTitle());

    }

    @Override
    public void testDelete() {
        Topic record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Topic record = service.get(1L);
        record.setTitle("Техника газовой сварки");

        service.update(record);

        record = service.get(1L);
        assertEquals("Техника газовой сварки", record.getTitle());

    }
}
