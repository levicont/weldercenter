package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Education;
import com.lvg.weldercenter.models.Welder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
public class WelderServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private WelderService service;
    @Autowired
    private EducationService eduServ;
    @Autowired
    private QualificationService qualifServ;
    @Autowired
    private WeldMethodService weldMethodServ;
    @Autowired
    private OrganizationService orgServ;
    @Autowired
    private JobService jobServ;

    @Override
    public void testGet() {
        Welder record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<Welder> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        Welder record = getTestWelder();
        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Захаров", record.getSurname());
        assertEquals("Электросварщик", record.getJob().getName());


    }

    @Override
    public void testDelete() {
        Welder record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        Welder record = service.get(1L);
        record.setSurname("Захаров");

        service.update(record);

        record = service.get(1L);
        assertEquals("Захаров", record.getSurname());

    }

    @Test
    @Transactional
    public void testCascadeDelete(){
        Welder welder = getTestWelder();
        Long id = service.insert(welder);

        welder = service.get(id);
        assertNotNull(welder);

        Education edu = eduServ.get(1l);
        eduServ.delete(edu);

        assertNotNull(welder.getEducation());

        welder = service.get(id);
        assertNotNull(welder);

        edu = welder.getEducation();
        assertNotNull(edu);


    }

    private Welder getTestWelder(){
        Welder record = new Welder();
        record.setSurname("Захаров");
        record.setName("Василий");
        record.setSecname("Николаевич");
        record.setBirthday(new Date());
        record.setDateBegin(new Date());
        record.setDocNumber("14-108");
        record.setEducation(eduServ.get(1L));
        record.setOrganization(orgServ.get(1L));
        record.setQualification(qualifServ.get(1L));
        record.setJob(jobServ.get(1L));
        record.getWeldMethods().add(weldMethodServ.get(1L));
        record.getWeldMethods().add(weldMethodServ.get(2L));

        return record;
    }
}
