package com.lvg.weldercenter.se.test.models
import com.lvg.weldercenter.se.models.Section
import org.junit.Test

class SectionTest extends GenericModelTest{

    @Override
    void insertItemTest() {
        throw new UnsupportedOperationException()
    }

    @Override
    void updateItemTest() {
        throw new UnsupportedOperationException()
    }

    @Override
    void deleteItemTest() {
        throw new UnsupportedOperationException()
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def curriculum = getCurriculumWithoutSections()
        curriculum.id = 100
        def section2 = getSection(curriculum)
        def section1 = getSection(curriculum)

        assert section2 == section1

        section1.title = 'one'

        assert section2 != section1

        def list = new HashSet<Section>()
        list.add(section1)
        section2.title = 'one'
        list.add(section2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def curriculum = getCurriculumWithoutSections()
        curriculum.id = 100
        def section = getSection(curriculum)
        assert section.toString() == "Дефекты металлопродукции"
    }
}
