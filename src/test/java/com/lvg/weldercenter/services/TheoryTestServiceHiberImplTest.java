package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.TheoryTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 03.11.14.
 */
public class TheoryTestServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private TheoryTestService service;

    @Override
    public void testGet() {
        TheoryTest record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<TheoryTest> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        TheoryTest record = new TheoryTest();
        record.setTicketNumber("14");
        record.setRating("сдано");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("14", record.getTicketNumber());

    }

    @Override
    public void testDelete() {
        TheoryTest record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        TheoryTest record = service.get(1L);
        record.setTicketNumber("5");

        service.update(record);

        record = service.get(1L);
        assertEquals("5", record.getTicketNumber());

    }

}
