package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.PatternThickness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class PatternThicknessServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private PatternThicknessService service;

    @Override
    public void testGet() {
        PatternThickness record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<PatternThickness> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        PatternThickness record = new PatternThickness();
        record.setThickness(2.5);

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals(Double.valueOf(2.5), record.getThickness());

    }

    @Override
    public void testDelete() {
        PatternThickness record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        PatternThickness record = service.get(1L);
        record.setThickness(2.5);

        service.update(record);

        record = service.get(1L);
        assertEquals(Double.valueOf(2.5), record.getThickness());

    }

}
