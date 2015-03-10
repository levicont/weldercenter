package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.models.TheoryTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Victor Levchenko LVG Corp. on 03.11.14.
 */
public class PersonalProtocolServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private PersonalProtocolService service;
    @Autowired
    private WelderService welderService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private ResolutionCertificationService resolServ;
    @Autowired
    private CommissionCertificationService commissServ;
    @Autowired
    private TheoryTestService theoryTestService;
    @Autowired
    private NDTDocumentService ndtDocumentService;
    @Autowired
    private WeldPatternService weldPatternService;


    @Override
    public void testGet() {
        PersonalProtocol record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<PersonalProtocol> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        PersonalProtocol record = new PersonalProtocol();
        record.setWelder(welderService.get(1l));
        record.setJournal(journalService.get(1l));
        record.setResolutionCertification(resolServ.get(1l));
        record.setTheoryTest(theoryTestService.get(1l));
        record.setNdtDocuments(ndtDocumentService.getAll());
        record.setDatePeriodicalCert(new Date());
        record.setWeldPatterns(weldPatternService.getAll());

        String journalNumber = journalService.get(1l).getNumber();

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals(journalNumber, record.getJournal().getNumber());

    }

    @Override
    public void testDelete() {
        PersonalProtocol record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        PersonalProtocol record = service.get(1L);
        record.setWelder(welderService.get(2l));

        String welderName = welderService.get(2l).getName();

        service.update(record);

        record = service.get(1L);
        assertEquals(welderName, record.getWelder().getName());

    }

}
