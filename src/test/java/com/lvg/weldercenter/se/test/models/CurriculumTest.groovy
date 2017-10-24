package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.models.Section
import org.junit.Test

class CurriculumTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {

        def CURRICULUM_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum curriculum = getCurriculum()
            em.persist(curriculum)
            CURRICULUM_ID = curriculum.id
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum chkCurriculum = em.find(Curriculum.class, CURRICULUM_ID)
            assert chkCurriculum.id != null
            assert chkCurriculum.title == 'Подготовка 20 часов'
            assert chkCurriculum.description == 'Программа подготовки сварщиков перед аттестацией - 20 часов'
            assert chkCurriculum.sections.size() == 3
            assert chkCurriculum.sections[0].orderIndex == 0
            assert chkCurriculum.sections[2].orderIndex == 2
            def topicOfFirstSection = curriculum.sections[0].topics
            assert topicOfFirstSection.size() == 3
            assert topicOfFirstSection[0].toString() == 'Введение в дефекты'
            assert topicOfFirstSection[0].orderIndex == 0
            assert topicOfFirstSection[2].orderIndex == 2
            return em
        }


    }

    @Override
    @Test
    void updateItemTest() {
        def CURRICULUM_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum curriculum = getCurriculum()
            em.persist(curriculum)
            CURRICULUM_ID = curriculum.id
            return em
        }
        assert CURRICULUM_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum curriculumUpd = em.find(Curriculum.class, CURRICULUM_ID)
            curriculumUpd.title = 'Предаттестационная подготовка - 48 часов'

            def section = curriculumUpd.sections[0]
            section.title = 'Дефекты аргонодуговой сварки'

            def topic = section.topics[0]
            topic.title = 'Аргон и его свойства'

            em.persist(curriculumUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum chkCurriculum = em.find(Curriculum.class, CURRICULUM_ID)
            assert chkCurriculum.sections[0].title == 'Дефекты аргонодуговой сварки'
            assert chkCurriculum.sections[0].topics[0].title == 'Аргон и его свойства'
            assert chkCurriculum.title == 'Предаттестационная подготовка - 48 часов'
            return em
        }

    }

    @Override
    void deleteItemTest() {
        def CURRICULUM_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum curriculum = getCurriculum()
            em.persist(curriculum)
            CURRICULUM_ID = curriculum.id
            return em
        }
        assert CURRICULUM_ID != null

        def SECTION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum curriculumUpd = em.find(Curriculum.class, CURRICULUM_ID)
            def section = curriculumUpd.sections[0]
            SECTION_ID = section.id
            em.remove(curriculumUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Curriculum chkCurriculum = em.find(Curriculum.class, CURRICULUM_ID)
            assert chkCurriculum == null
            Section chkSection = em.find(Section.class, SECTION_ID)
            assert chkSection == null
            return em
        }
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
