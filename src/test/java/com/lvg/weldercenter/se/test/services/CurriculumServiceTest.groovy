package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Curriculum
import com.lvg.weldercenter.se.services.CurriculumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class CurriculumServiceTest extends GenericServiceTest{

    @Autowired
    CurriculumService curriculumService

    @Override
    void saveTest() {
        def CURRICULUM_ID
        def curriculum = getCurriculumWithoutSections()
        curriculum = curriculumService.save(curriculum)
        CURRICULUM_ID = curriculum.id
        assert CURRICULUM_ID != null

        Curriculum updCurriculum = curriculumService.get(CURRICULUM_ID)
        assert updCurriculum != null
        assert updCurriculum instanceof Curriculum
        updCurriculum.title = 'Программа подготовки 25'
        curriculumService.save(updCurriculum)

        Curriculum chkCurriculum = curriculumService.get(CURRICULUM_ID)
        assert chkCurriculum != null
        assert chkCurriculum instanceof Curriculum
        assert chkCurriculum.title == 'Программа подготовки 25'
    }

    @Override
    void deleteTest() {
        def CURRICULUM_ID
        def curriculum = getCurriculumWithoutSections()
        curriculum = curriculumService.save(curriculum)
        CURRICULUM_ID = curriculum.id
        assert CURRICULUM_ID != null

        Curriculum delCurriculum = curriculumService.get(CURRICULUM_ID)
        assert delCurriculum != null
        assert delCurriculum instanceof Curriculum
        curriculumService.delete(delCurriculum)

        Curriculum chkCurriculum = curriculumService.get(CURRICULUM_ID)
        assert chkCurriculum == null
    }

    @Override
    void getTest() {
        def CURRICULUM_ID
        def curriculum = getCurriculumWithoutSections()
        curriculum = curriculumService.save(curriculum)
        CURRICULUM_ID = curriculum.id
        assert CURRICULUM_ID != null

        Curriculum updCurriculum = curriculumService.get(CURRICULUM_ID)
        assert updCurriculum != null
        assert updCurriculum instanceof Curriculum
    }

    @Override
    void getAllTest() {
        def CURRICULUM_ID
        def curriculum = getCurriculumWithoutSections()
        curriculum = curriculumService.save(curriculum)
        CURRICULUM_ID = curriculum.id
        assert CURRICULUM_ID != null

        def list = curriculumService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }

    @Override
    void countTest() {
        def CURRICULUM_ID
        def curriculum = getCurriculumWithoutSections()
        curriculum = curriculumService.save(curriculum)
        CURRICULUM_ID = curriculum.id
        assert CURRICULUM_ID != null

        def count = curriculumService.count()
        assert count != null
        assert count instanceof Long
        assert count == 1
    }
}
