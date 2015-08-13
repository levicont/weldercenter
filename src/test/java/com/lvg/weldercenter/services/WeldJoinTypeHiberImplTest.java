package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.WeldJoinType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor on 22.06.2015.
 */
public class WeldJoinTypeHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    WeldJoinTypeService service;


    @Override
    public void testGet() {
        WeldJoinType wd = service.get(1L);
        assertNotNull(wd);
    }

    @Override
    public void testGetAll() {
        List<WeldJoinType> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        WeldJoinType wjt = new WeldJoinType();
        wjt.setType("WD");
        wjt.setDescription("Weld Detail");

        Long id = service.insert(wjt);
        wjt = service.get(id);
        assertNotNull(id);

        WeldJoinType record = service.get(id);
        assertSame("WD",record.getType());

    }

    @Override
    public void testDelete() {
        WeldJoinType record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);
    }

    @Override
    public void testUpdate() {
        WeldJoinType record = service.get(1L);
        record.setType("WD");
        service.update(record);

        record = service.get(1L);
        assertSame("WD",record.getType());

    }
}
