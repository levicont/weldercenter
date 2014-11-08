package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.ResolutionCertification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 03.11.14.
 */
public class ResolutionCertificationServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private ResolutionCertificationService service;

    @Override
    public void testGet() {
        ResolutionCertification record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<ResolutionCertification> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        ResolutionCertification record = new ResolutionCertification();
        record.setTextResolution("Сварщик аттестован");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Сварщик аттестован", record.getTextResolution());

    }

    @Override
    public void testDelete() {
        ResolutionCertification record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        ResolutionCertification record = service.get(1L);
        record.setTextResolution("Сварщик не аттестован");

        service.update(record);

        record = service.get(1L);
        assertEquals("Сварщик не аттестован", record.getTextResolution());

    }

}
