package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.WeldMethod;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
public class WeldMethodServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private WeldMethodService service;

    @Override
    public void testGet() {
        WeldMethod record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<WeldMethod> list = service.getAll();
        assertNotNull(list);

    }

    @Test
    public void testGetByName(){
        WeldMethod wm = service.get(1l);
        WeldMethod wm1 = service.getByName(wm.getName());
        assertNotNull(wm1);
        assertEquals(wm.getWeldMethodId(), wm1.getWeldMethodId());
    }

    @Test
    public void testGetByCode(){
        WeldMethod wm = service.get(1l);
        WeldMethod wm1 = service.getByCode(wm.getCode());
        assertNotNull(wm1);
        assertEquals(wm.getWeldMethodId(), wm1.getWeldMethodId());
    }

    @Override
    public void testInsert() {
        WeldMethod record = new WeldMethod();
        record.setName("Плазменная");
        record.setCode("555");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("555", record.getCode());

    }

    @Override
    public void testDelete() {
        WeldMethod record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        WeldMethod record = service.get(1L);
        record.setName("Плазменная");

        service.update(record);

        record = service.get(1L);
        assertEquals("Плазменная", record.getName());

    }
}
