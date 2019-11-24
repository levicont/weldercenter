package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.models.Section
import com.lvg.weldercenter.se.services.CurriculumService
import com.lvg.weldercenter.se.services.SectionService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class CurriculumTest extends GenericModelTest{
    @Autowired
    CurriculumService curriculumService
    @Autowired
    SectionService sectionService

    @Override
    @Test
    void insertItemTest() {


        def CURRICULUM_ID

            Curriculum curriculum = getCurriculumWithoutSections()
            curriculumService.save(curriculum)
            def sections = getSections(curriculum)
            curriculum.sections.addAll(sections)
            curriculum.sections.each {sectionService.save(it)}
            CURRICULUM_ID = curriculum.id



            Curriculum chkCurriculum = curriculumService.get(CURRICULUM_ID)
            assert chkCurriculum.id != null
            assert chkCurriculum.title == 'Подготовка 20 часов'
            assert chkCurriculum.description == 'Программа подготовки сварщиков перед аттестацией - 20 часов'
            assert chkCurriculum.sections.size() == 3
            assert chkCurriculum.sections[0].orderIndex == 0
            assert chkCurriculum.sections[2].orderIndex == 2
            def topicOfFirstSection = chkCurriculum.sections[0].topics
            assert topicOfFirstSection.size() == 3
            assert topicOfFirstSection[0].toString() == 'Введение в дефекты'
            assert topicOfFirstSection[0].orderIndex == 0
            assert topicOfFirstSection[2].orderIndex == 2



    }

    @Override
    @Test
    void updateItemTest() {
        def CURRICULUM_ID


            Curriculum curriculum = getCurriculumWithoutSections()
            curriculumService.save(curriculum)
            def sections = getSections(curriculum)
            curriculum.sections.addAll(sections)
            curriculum.sections.each {sectionService.save(it)}
            CURRICULUM_ID = curriculum.id

        assert CURRICULUM_ID != null


            Curriculum curriculumUpd = curriculumService.get(CURRICULUM_ID)
            curriculumUpd.title = 'Предаттестационная подготовка - 48 часов'

            def section = curriculumUpd.sections[0]
            section.title = 'Дефекты аргонодуговой сварки'

            def topic = section.topics[0]
            topic.title = 'Аргон и его свойства'
            curriculumUpd.sections.each {sectionService.save(it)}
            curriculumService.save(curriculumUpd)


            Curriculum chkCurriculum = curriculumService.get(CURRICULUM_ID)
            assert chkCurriculum.sections[0].title == 'Дефекты аргонодуговой сварки'
            assert chkCurriculum.sections[0].topics[0].title == 'Аргон и его свойства'
            assert chkCurriculum.title == 'Предаттестационная подготовка - 48 часов'

    }

    @Override
    void deleteItemTest() {
        def CURRICULUM_ID

            Curriculum curriculum = getCurriculumWithoutSections()
            curriculumService.save(curriculum)
            def sections = getSections(curriculum)
            curriculum.sections.addAll(sections)
            curriculum.sections.each {sectionService.save(it)}
            CURRICULUM_ID = curriculum.id

        assert CURRICULUM_ID != null

        def SECTION_ID

            Curriculum curriculumUpd = curriculumService.get(CURRICULUM_ID)
            def section = curriculumUpd.sections[0]
            SECTION_ID = section.id
            curriculumService.delete(curriculumUpd)



            Curriculum chkCurriculum = curriculumService.get(CURRICULUM_ID)
            assert chkCurriculum == null
            Section chkSection = sectionService.get(SECTION_ID)
            assert chkSection == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def curriculum2 = getCurriculumWithoutSections()
        def curriculum1 = getCurriculumWithoutSections()

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
        def curriculum = getCurriculumWithoutSections()
        assert curriculum.toString() == "Подготовка 20 часов"
    }
}
