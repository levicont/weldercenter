package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Section;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class SectionServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private SectionService service;

    @Override
    public void testGet() {
        Section record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Section> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Section record = new Section();
        record.setTitle("Производственная практика");
        record.setDescription("Производственная практика на предприятии");
        record.setOrderIndex(3);

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Производственная практика", record.getTitle());

    }

    @Override
    public void testDelete() {
        Section record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Section record = service.get(1L);
        record.setTitle("Производственная практика");

        service.update(record);

        record = service.get(1L);
        assertEquals("Производственная практика", record.getTitle());

    }
}
