package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.PatternDiameter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class PatternDiameterServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private PatternDiameterService service;

    @Override
    public void testGet() {
        PatternDiameter record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<PatternDiameter> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        PatternDiameter record = new PatternDiameter();
        record.setDiameter(89.0);

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals(Double.valueOf(89.0), record.getDiameter());

    }

    @Override
    public void testDelete() {
        PatternDiameter record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        PatternDiameter record = service.get(1L);
        record.setDiameter(89.0);

        service.update(record);

        record = service.get(1L);
        assertEquals(Double.valueOf(89.0), record.getDiameter());

    }

}
