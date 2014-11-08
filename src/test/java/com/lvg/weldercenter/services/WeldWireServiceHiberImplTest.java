package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.WeldWire;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class WeldWireServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private WeldWireService service;

    @Override
    public void testGet() {
        WeldWire record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<WeldWire> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        WeldWire record = new WeldWire();
        record.setType("СВ09");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("СВ09", record.getType());

    }

    @Override
    public void testDelete() {
        WeldWire record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        WeldWire record = service.get(1L);
        record.setType("СВ09");

        service.update(record);

        record = service.get(1L);
        assertEquals("СВ09", record.getType());

    }

}
