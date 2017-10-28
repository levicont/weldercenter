package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class WelderServiceTest extends GenericServiceTest{

    @Autowired
    WelderService welderService

    @Override
    void saveTest() {
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        Welder updWelder = welderService.get(WELDER_ID)
        assert updWelder != null
        assert updWelder instanceof Welder
        updWelder.name = 'Константин'
        welderService.save(updWelder)

        Welder chkWelder = welderService.get(WELDER_ID)
        assert chkWelder != null
        assert chkWelder instanceof Welder
        assert chkWelder.name == 'Константин'

    }

    @Override
    void deleteTest() {
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        Welder delWelder = welderService.get(WELDER_ID)
        assert delWelder != null
        assert delWelder instanceof Welder
        welderService.delete(welder)

        Welder chkWelder = welderService.get(WELDER_ID)
        assert chkWelder == null
    }

    @Override
    void getTest() {
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        Welder chkWelder = welderService.get(WELDER_ID)
        assert chkWelder != null
        assert chkWelder instanceof Welder
    }

    @Override
    void getAllTest() {
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        def list = welderService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
