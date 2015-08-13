package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.models.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 03.11.14.
 */
public class CommissionCertificationServiceHiberImplTest extends GenericServiceHibernateTest {


    @Autowired
    private CommissionCertificationService service;
    @Autowired
    private TeacherService teacherService;

    @Override
    public void testGet() {
        CommissionCertification record = service.get(1L);
        assertNotNull(record);
    }

    @Override
    public void testGetAll() {
        List<CommissionCertification> list = service.getAll();
        assertNotNull(list);

    }

    @Override
    public void testInsert() {
        CommissionCertification record = new CommissionCertification();
        record.setHead(teacherService.get(2l));
        record.setNdtSpecialist(teacherService.get(3l));
        record.setSafetySpecialist(teacherService.get(4l));
        record.setWeldSpecialist(teacherService.get(1l));

        Long id = service.insert(record);
        assertNotNull(id);

        record = service.get(id);
        assertEquals("Ашукин", record.getWeldSpecialist().getSurname());

    }

    @Override
    public void testDelete() {
        CommissionCertification record = service.get(1L);
        service.delete(record);

        record = service.get(1L);
        assertNull(record);

    }

    @Override
    public void testUpdate() {
        CommissionCertification record = service.get(1L);
        record.setHead(teacherService.get(1l));

        service.update(record);

        record = service.get(1L);
        assertEquals("Ашукин", record.getHead().getSurname());

    }

    @Test
    @Transactional
    public void testCascade(){
        CommissionCertification comm = getCommission();

        Long id = service.insert(comm);
        assertNotNull(service.get(id));

        Teacher teacher = teacherService.get(2l);
        teacherService.delete(teacher);
        teacher = teacherService.get(2l);
        assertNull(teacher);

        // This assert passed if method will be not @Transactional
        //comm = service.get(id);
        //assertNull(comm.getHead());

    }

    private CommissionCertification getCommission(){
        CommissionCertification commission = new CommissionCertification();
        commission.setHead(teacherService.get(2l));
        commission.setNdtSpecialist(teacherService.get(3l));
        commission.setSafetySpecialist(teacherService.get(4l));
        commission.setWeldSpecialist(teacherService.get(1l));
        return commission;
    }

}
