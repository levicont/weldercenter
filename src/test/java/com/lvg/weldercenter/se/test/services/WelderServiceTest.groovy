package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import java.time.format.DateTimeFormatter

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
        welderService.delete(delWelder)

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
        assert list.size() == 5
    }

    @Test
    void countTest() {
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        def count = welderService.count()
        assert count != null
        assert count instanceof Long
        assert count == 5
    }

    @Test
    void getWeldersForTableViewTest(){
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        List list = welderService.getWeldersForTableView()
        assert list != null
        assert list.size() == 5
        def chkWelderList
        list.forEach({ Object[] weldersDTO ->
            if (weldersDTO[0] == WELDER_ID){
                chkWelderList = weldersDTO
            }
        })
        assert chkWelderList != null
        assert chkWelderList[0] != null && chkWelderList[0] == WELDER_ID
        assert chkWelderList[1] != null && chkWelderList[1] == welder.name
        assert chkWelderList[2] != null && chkWelderList[2] == welder.surname
        assert chkWelderList[3] != null && chkWelderList[3] == welder.secondName
        assert chkWelderList[4] != null && chkWelderList[4] == welder.birthday
        assert chkWelderList[5] != null && chkWelderList[5] == welder.organization.name

    }

    @Test
    void getAllWeldersTableViewDTOTest(){
        def WELDER_ID
        def welder = getWelder()
        welder = welderService.save(welder)
        WELDER_ID = welder.id
        assert WELDER_ID != null

        List list = welderService.getAllWeldersTableViewDTO()
        assert list != null
        assert list.size() == 5
        def chkWelderList
        list.forEach({ WelderTableViewDTO welderDTO ->
            if (welderDTO.id == WELDER_ID){
                chkWelderList = welderDTO
            }
        })
        assert chkWelderList != null
        assert chkWelderList.id == WELDER_ID
        assert chkWelderList.name == welder.name
        assert chkWelderList.surname  == welder.surname
        assert chkWelderList.secondName == welder.secondName
        assert chkWelderList.birthday == welder.birthday.format(DateTimeFormatter.ofPattern(DTOConstants.DATE_FORMAT_PATTERN))
        assert chkWelderList.organization == welder.organization.name

    }

    @Test
    void getAllOrganizationTest(){
        Set<OrganizationEmbedded> organizations = welderService.getAllOrganization()
        assert organizations != null
        assert organizations.size() == 2
        assert organizations.first() != null
        assert organizations.first().name == 'IBM'
    }
}
