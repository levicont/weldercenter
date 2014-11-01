package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Organization;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
public class OrganizationServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private OrganizationService service;

    @Override
    public void testGet() {
        Organization record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Organization> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Organization record = new Organization();
        record.setName("ООО Техника");
        record.setAdress("г. Харьков");
        record.setPhone("13-14-15");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("ООО Техника", record.getName());

    }

    @Override
    public void testDelete() {
        Organization record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Organization record = service.get(1L);
        record.setName("ПП Стройка");

        service.update(record);

        record = service.get(1L);
        assertEquals("ПП Стройка", record.getName());

    }
}
