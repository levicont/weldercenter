package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.WeldGas;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class WeldGasServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private WeldGasService service;

    @Override
    public void testGet() {
        WeldGas record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<WeldGas> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        WeldGas record = new WeldGas();
        record.setType("C2H2");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("C2H2", record.getType());

    }

    @Override
    public void testDelete() {
        WeldGas record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        WeldGas record = service.get(1L);
        record.setType("C2H2");

        service.update(record);

        record = service.get(1L);
        assertEquals("C2H2", record.getType());

    }

}
