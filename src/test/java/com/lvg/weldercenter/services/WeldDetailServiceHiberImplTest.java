package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.WeldDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
public class WeldDetailServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private WeldDetailService service;

    @Override
    public void testGet() {
        WeldDetail record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<WeldDetail> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        WeldDetail record = new WeldDetail();
        record.setType("Уголок");
        record.setCode("U");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Уголок", record.getType());

    }

    @Override
    public void testDelete() {
        WeldDetail record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        WeldDetail record = service.get(1L);
        record.setType("Уголок");

        service.update(record);

        record = service.get(1L);
        assertEquals("Уголок", record.getType());

    }

}
