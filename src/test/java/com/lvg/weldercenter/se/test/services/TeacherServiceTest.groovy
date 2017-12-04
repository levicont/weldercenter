package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Teacher
import com.lvg.weldercenter.se.services.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional


@Transactional
class TeacherServiceTest extends GenericServiceTest{

    @Autowired
    TeacherService teacherService

    @Override
    void saveTest() {
        def TEACHER_ID
        def teacher = getTeacher()
        teacher = teacherService.save(teacher)
        TEACHER_ID = teacher.id
        assert TEACHER_ID != null

        Teacher updTeacher = teacherService.get(TEACHER_ID)
        assert updTeacher != null
        assert updTeacher instanceof Teacher
        updTeacher.name = 'Петро'
        teacherService.save(updTeacher)

        Teacher chkTeacher = teacherService.get(TEACHER_ID)
        assert chkTeacher != null
        assert chkTeacher instanceof Teacher
        assert chkTeacher.name == 'Петро'

    }

    @Override
    void deleteTest() {
        def TEACHER_ID
        def teacher = getTeacher()
        teacher = teacherService.save(teacher)
        TEACHER_ID = teacher.id
        assert TEACHER_ID != null

        Teacher delTeacher = teacherService.get(TEACHER_ID)
        assert delTeacher != null
        assert delTeacher instanceof Teacher
        teacherService.delete(delTeacher)

        Teacher chkTeacher = teacherService.get(TEACHER_ID)
        assert chkTeacher == null

    }

    @Override
    void getTest() {
        def TEACHER_ID
        def teacher = getTeacher()
        teacher = teacherService.save(teacher)
        TEACHER_ID = teacher.id
        assert TEACHER_ID != null

        Teacher updTeacher = teacherService.get(TEACHER_ID)
        assert updTeacher != null
        assert updTeacher instanceof Teacher
    }

    @Override
    void getAllTest() {
        def TEACHER_ID
        def teacher = getTeacher()
        teacher = teacherService.save(teacher)
        TEACHER_ID = teacher.id
        assert TEACHER_ID != null

        def list = teacherService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }

    @Override
    void countTest() {
        def TEACHER_ID
        def teacher = getTeacher()
        teacher = teacherService.save(teacher)
        TEACHER_ID = teacher.id
        assert TEACHER_ID != null

        def count = teacherService.count()
        assert count != null
        assert count instanceof Long
        assert count == 1
    }
}
