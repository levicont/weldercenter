package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.models.Topic
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

/**
 * Created by Victor on 09.10.2017.
 */
class CurriculumTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Curriculum curriculum = getCurriculum()
        em.persist(curriculum)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select c from Curriculum c').getResultList()
        tx.commit()

        assert list.size() == 1
        curriculum = list.get(0)


        assert curriculum.id != null
        assert curriculum.title == 'Подготовка 20 часов'
        assert curriculum.description == 'Программа подготовки сварщиков перед аттестацией - 20 часов'
        assert curriculum.sections.size() == 3
        assert curriculum.sections.getAt(0).orderIndex == 0
        assert curriculum.sections.getAt(2).orderIndex == 2

        def topicOfFirstSection = curriculum.sections.getAt(0).topics
        assert topicOfFirstSection.size() == 3
        assert topicOfFirstSection.getAt(0).toString() == 'Введение в дефекты'
        assert topicOfFirstSection.getAt(0).orderIndex == 0
        assert topicOfFirstSection.getAt(2).orderIndex == 2

    }

    @Override
    void updateItemTest() {

    }

    @Override
    void deleteItemTest() {

    }

    @Override
    void equalsHashCodeTest() {

    }

    @Override
    void toStringTest() {

    }
}
