package com.lvg.weldercenter.se.test.models
import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.models.Section
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
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Curriculum curriculum = getCurriculum()
        em.persist(curriculum)
        tx.commit()

        assert curriculum.id != null
        def CURRICULUM_ID = curriculum.id
        tx.begin()
        em = EMF.createEntityManager()
        Curriculum curriculumUpd = em.find(Curriculum.class, CURRICULUM_ID)
        curriculumUpd.title = 'Предаттестационная подготовка - 48 часов'

        def section = curriculumUpd.sections.getAt(0)
        section.title = 'Дефекты аргонодуговой сварки'

        def topic = section.topics.getAt(0)
        topic.title = 'Аргон и его свойства'

        em.persist(curriculumUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Curriculum chkCurriculum = em.find(Curriculum.class, CURRICULUM_ID)
        assert chkCurriculum.sections.getAt(0).title == 'Дефекты аргонодуговой сварки'
        assert chkCurriculum.sections.getAt(0).topics.getAt(0).title == 'Аргон и его свойства'
        assert chkCurriculum.title == 'Предаттестационная подготовка - 48 часов'
        tx.commit()
    }

    @Override
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Curriculum curriculum = getCurriculum()
        em.persist(curriculum)
        tx.commit()

        assert curriculum.id != null
        def CURRICULUM_ID = curriculum.id

        tx.begin()
        em = EMF.createEntityManager()
        Curriculum curriculumUpd = em.find(Curriculum.class, CURRICULUM_ID)

        def section = curriculumUpd.sections.getAt(0)
        def SECTION_ID = section.id

        em.remove(curriculumUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Curriculum chkCurriculum = em.find(Curriculum.class, CURRICULUM_ID)
        assert chkCurriculum == null
        Section chkSection = em.find(Section.class, SECTION_ID)
        assert chkSection == null

        tx.commit()

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def curriculum2 = getCurriculum()
        def curriculum1 = getCurriculum()

        assert curriculum2 == curriculum1

        curriculum2.id = 100
        curriculum1.id = 101

        assert curriculum2 != curriculum1

        def list = new HashSet<Curriculum>()
        list.add(curriculum2)
        curriculum1.id = 100
        list.add(curriculum1)

        assert list.size() == 1

    }

    @Override
    @Test
    void toStringTest() {
        def curriculum = getCurriculum()

        assert curriculum.toString() == "Подготовка 20 часов"
    }
}
