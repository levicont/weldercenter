package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Teacher
import com.lvg.weldercenter.se.services.TeacherService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class TeacherTest extends GenericModelTest{
    @Autowired
    TeacherService service

    @Override
    @Test
    void insertItemTest() {
        def TEACHER_ID

            Teacher teacher = getTeacher()
            service.save(teacher)
            TEACHER_ID = teacher.id

        assert TEACHER_ID != null

            Teacher teacherChk = service.get(TEACHER_ID)
            assert teacherChk.id != null
            assert teacherChk.name == 'Амвросий'
            assert teacherChk.surname == 'Кац'
            assert teacherChk.secondName == 'Федорович'

    }

    @Override
    @Test
    void updateItemTest() {
        def TEACHER_ID
            Teacher teacher = getTeacher()
        service.save(teacher)
            TEACHER_ID = teacher.id

        assert TEACHER_ID != null

            Teacher teacherUpd = service.get(TEACHER_ID)
            teacherUpd.surname = 'Петров'
            service.save(teacherUpd)

            Teacher chkTeacher = service.get(TEACHER_ID)
            assert chkTeacher.surname == 'Петров'

    }

    @Override
    @Test
    void deleteItemTest() {
        def TEACHER_ID
            Teacher teacher = getTeacher()
            service.save(teacher)
            TEACHER_ID = teacher.id

        assert TEACHER_ID != null

            Teacher teacherUpd = service.get(TEACHER_ID)
            service.delete(teacherUpd)

            Teacher chkTeacher = service.get(TEACHER_ID)
            assert chkTeacher == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def teacher2 = new Teacher(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        def teacher1 = new Teacher(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')

        assert teacher2 == teacher1

        teacher2.id = 100
        teacher1.id = 101

        assert teacher2 != teacher1

        def list = new HashSet<Teacher>()
        list.add(teacher2)
        teacher1.id = 100
        list.add(teacher1)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def teacher = new Teacher(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        assert teacher.toString() == "Иванов Иван Иванович"
    }
}
