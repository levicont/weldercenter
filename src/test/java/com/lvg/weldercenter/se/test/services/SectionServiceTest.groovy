package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Section
import com.lvg.weldercenter.se.services.CurriculumService
import com.lvg.weldercenter.se.services.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class SectionServiceTest extends GenericServiceTest{

    @Autowired
    SectionService sectionService
    @Autowired
    CurriculumService curriculumService

    @Override
    void saveTest() {
        def SECTION_ID
        def curriculum = curriculumService.save(getCurriculumWithoutSections())
        def section = getSection(curriculum)
        section = sectionService.save(section)
        SECTION_ID = section.id
        assert SECTION_ID != null

        Section updSection = sectionService.get(SECTION_ID)
        assert updSection != null
        assert updSection instanceof Section
        updSection.title = 'Общие вопросы'
        sectionService.save(updSection)

        Section chkSection = sectionService.get(SECTION_ID)
        assert chkSection != null
        assert chkSection instanceof Section
        assert chkSection.title == 'Общие вопросы'
    }

    @Override
    void deleteTest() {
        def SECTION_ID
        def curriculum = curriculumService.save(getCurriculumWithoutSections())
        def section = getSection(curriculum)
        section = sectionService.save(section)
        SECTION_ID = section.id
        assert SECTION_ID != null

        Section delSection = sectionService.get(SECTION_ID)
        assert delSection != null
        assert delSection instanceof Section
        sectionService.delete(delSection)

        Section chkSection = sectionService.get(SECTION_ID)
        assert chkSection == null
    }

    @Override
    void getTest() {
        def SECTION_ID
        def curriculum = curriculumService.save(getCurriculumWithoutSections())
        def section = getSection(curriculum)
        section = sectionService.save(section)
        SECTION_ID = section.id
        assert SECTION_ID != null

        Section chkSection = sectionService.get(SECTION_ID)
        assert chkSection != null
        assert chkSection instanceof Section
    }

    @Override
    void getAllTest() {
        def SECTION_ID
        def curriculum = curriculumService.save(getCurriculumWithoutSections())
        def section = getSection(curriculum)
        section = sectionService.save(section)
        SECTION_ID = section.id
        assert SECTION_ID != null

        def list = sectionService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
