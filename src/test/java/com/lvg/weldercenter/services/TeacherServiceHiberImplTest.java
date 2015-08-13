package com.lvg.weldercenter.services;

import com.lvg.weldercenter.GenericServiceHibernateTest;
import com.lvg.weldercenter.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
public class TeacherServiceHiberImplTest extends GenericServiceHibernateTest {

    @Autowired
    private TeacherService teacherService;

    @Override
    public void testGet(){
        Teacher teacher = teacherService.get(1L);
        assertNotNull(teacher);
    }

    @Override
    public void testGetAll(){
        List<Teacher> teachers = teacherService.getAll();
        assertNotNull(teachers);
    }

    @Override
    public void testInsert(){
        Teacher teacher = new Teacher();
        teacher.setSurname("Солонецкий");
        teacher.setName("Николай");
        teacher.setSecname("Алексеевич");

        Long id = teacherService.insert(teacher);
        assertNotNull(id);

        teacher = teacherService.get(id);
        assertEquals("Солонецкий", teacher.getSurname());

    }

    @Override
    public void testDelete() {
        Teacher teacher = teacherService.get(1L);
        assertNotNull(teacher);

        teacherService.delete(teacher);

        teacher = teacherService.get(1L);
        assertNull(teacher);

    }

    @Override
    public void testUpdate() {
        Teacher teacher = teacherService.get(1L);
        assertNotNull(teacher);
        teacher.setName("Олександр");
        teacherService.update(teacher);

        teacher = teacherService.get(1L);
        assertEquals("Олександр", teacher.getName());
    }
}
