package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.TotalProtocol;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 03.02.15.
 */
public class TotalProtocolServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private TotalProtocolService service;

    @Autowired
    private JournalService journalService;

    @Autowired
    private CommissionCertificationService commissionCertificationService;

    @Override
    public void testGet() {
        TotalProtocol record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<TotalProtocol> list = service.getAll();
        assertNotNull(list);
    }

    @Override
    public void testInsert() {
        TotalProtocol record = new TotalProtocol();
        record.setNumber("15-077");
        record.setDateCert(new Date());
        record.setJournal(journalService.get(1L));
        record.setCommissionCertification(commissionCertificationService.get(1l));

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("15-077", record.getNumber());

    }

    @Override
    public void testDelete() {
        TotalProtocol record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        TotalProtocol record = service.get(1l);
        record.setNumber("15-077");

        service.update(record);
        record = service.get(1L);
        assertEquals("15-077", record.getNumber());
    }
}
