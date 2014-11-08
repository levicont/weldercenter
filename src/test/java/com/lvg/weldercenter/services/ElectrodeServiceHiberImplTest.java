package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Electrode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class ElectrodeServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private ElectrodeService service;

    @Override
    public void testGet() {
        Electrode record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Electrode> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Electrode record = new Electrode();
        record.setType("УОНИ 45");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("УОНИ 45", record.getType());

    }

    @Override
    public void testDelete() {
        Electrode record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Electrode record = service.get(1L);
        record.setType("УОНИ 45");

        service.update(record);

        record = service.get(1L);
        assertEquals("УОНИ 45", record.getType());

    }
}
