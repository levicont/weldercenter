package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.NDTDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 03.11.14.
 */
public class NDTDocumentServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private NDTDocumentService service;

    @Override
    public void testGet() {
        NDTDocument record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<NDTDocument> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        NDTDocument record = new NDTDocument();
        record.setName("ВСН 012-82");
        record.setFullName("Строительство магистральных газопроводов");

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("ВСН 012-82", record.getName());

    }

    @Override
    public void testDelete() {
        NDTDocument record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        NDTDocument record = service.get(1L);
        record.setName("ВСН 012-82");

        service.update(record);

        record = service.get(1L);
        assertEquals("ВСН 012-82", record.getName());

    }

}
