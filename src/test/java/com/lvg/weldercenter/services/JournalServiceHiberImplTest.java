package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Journal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class JournalServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private JournalService service;
    @Autowired
    private CurriculumService currServ;

    @Override
    public void testGet() {
        Journal record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Journal> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Journal record = new Journal();
        record.setNumber("14-004");
        record.setDateBegin(new Date());
        record.setDateEnd(new Date());
        record.setCurriculum(currServ.get(1L));

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("14-004", record.getNumber());

    }

    @Override
    public void testDelete() {
        Journal record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Journal record = service.get(1L);
        record.setNumber("14-004");

        service.update(record);

        record = service.get(1L);
        assertEquals("14-004", record.getNumber());

    }
}
