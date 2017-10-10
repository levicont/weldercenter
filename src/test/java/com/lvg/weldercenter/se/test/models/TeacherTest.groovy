package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Teacher
import org.junit.Assert
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

class TeacherTest extends GenericModelTest{
    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Teacher teacher = getTeacher()
        em.persist(teacher)
        def TEACHER_ID = teacher.id
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Teacher teacherChk = em.find(Teacher.class, TEACHER_ID)
        tx.commit()


        assert teacherChk.id != null
        assert teacherChk.name == 'Амвросий'
        assert teacherChk.surname == 'Кац'
        assert teacherChk.secondName == 'Федорович'

    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Teacher teacher = getTeacher()
        em.persist(teacher)
        tx.commit()

        assert teacher.id != null
        def TEACHER_ID = teacher.id
        tx.begin()
        em = EMF.createEntityManager()
        Teacher teacherUpd = em.find(Teacher.class, TEACHER_ID)
        teacherUpd.surname = 'Петров'
        em.persist(teacherUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Teacher chkTeacher = em.find(Teacher.class, TEACHER_ID)
        assert chkTeacher.surname == 'Петров'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Teacher teacher = getTeacher()
        em.persist(teacher)
        tx.commit()

        assert teacher.id != null
        def TEACHER_ID = teacher.id

        tx.begin()
        em = EMF.createEntityManager()
        Teacher teacherUpd = em.find(Teacher.class, TEACHER_ID)
        em.remove(teacherUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Teacher chkTeacher = em.find(Teacher.class, TEACHER_ID)
        assert chkTeacher == null
        tx.commit()
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
