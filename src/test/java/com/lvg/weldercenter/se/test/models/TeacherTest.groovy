package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Teacher
import org.junit.Assert
import org.junit.Test

class TeacherTest extends GenericModelTest{
    @Override
    @Test
    void insertItemTest() {
        def TEACHER_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher teacher = getTeacher()
            em.persist(teacher)
            TEACHER_ID = teacher.id
            return em
        }
        assert TEACHER_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher teacherChk = em.find(Teacher.class, TEACHER_ID)
            assert teacherChk.id != null
            assert teacherChk.name == 'Амвросий'
            assert teacherChk.surname == 'Кац'
            assert teacherChk.secondName == 'Федорович'
            return em
        }

    }

    @Override
    @Test
    void updateItemTest() {
        def TEACHER_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher teacher = getTeacher()
            em.persist(teacher)
            TEACHER_ID = teacher.id
            return em
        }
        assert TEACHER_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher teacherUpd = em.find(Teacher.class, TEACHER_ID)
            teacherUpd.surname = 'Петров'
            em.persist(teacherUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher chkTeacher = em.find(Teacher.class, TEACHER_ID)
            assert chkTeacher.surname == 'Петров'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def TEACHER_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher teacher = getTeacher()
            em.persist(teacher)
            TEACHER_ID = teacher.id
            return em
        }
        assert TEACHER_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher teacherUpd = em.find(Teacher.class, TEACHER_ID)
            em.remove(teacherUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Teacher chkTeacher = em.find(Teacher.class, TEACHER_ID)
            assert chkTeacher == null
            return em
        }
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
