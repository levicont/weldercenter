package com.lvg.weldercenter.model

import com.lvg.weldercenter.models.Welder
import com.lvg.weldercenter.services.WelderService
import org.apache.log4j.Logger
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import static org.junit.Assert.assertNotNull

/**
 * Created by Victor on 06.07.2017.
 */

class WelderTest extends GenericModelTest{

    private final Logger LOGGER = Logger.getLogger(WelderTest.class)

    @Autowired
    private WelderService welderService

    @Test
    @Transactional
    void testWelderFields(){
        print('\n')
        LOGGER.info("Starting testWelderFields")
        def welders = welderService.getAll()

        assertNotNull(welders)
        LOGGER.info("Finded weldersList with size: $welders.size")

        Welder welder = welders[0]
        assertNotNull(welder)
        LOGGER.info("Finded Welder: $welder")

        assertNotNull(welder.education)
        LOGGER.info("Education of welder: $welder.education")

        assertNotNull(welder.qualification)
        LOGGER.info("Qualification of welder: $welder.qualification")

        assertNotNull(welder.organization)
        LOGGER.info("Organization of welder: $welder.organization")

        assertNotNull(welder.job)
        LOGGER.info("Job of welder: $welder.job")

        LOGGER.info("End testWelderFields")
        print('\n')
    }

    @Test
    @Transactional
    void testWelderManyToManyFields(){
        print('\n')
        LOGGER.info("Starting testWelderManyToManyFields")

        Welder welder = welderService.get(12l)
        assertNotNull(welder)
        LOGGER.info("Finded Welder: $welder")


        def weldMethods = welder.weldMethods
        assertNotNull(weldMethods)
        assert weldMethods.size() > 0
        LOGGER.info("Weld methods of welder is: $weldMethods")

        def personalProtocols = welder.personalProtocols
        assertNotNull(personalProtocols)
        assert personalProtocols.size() > 0
        LOGGER.info("Personal protocols of welder is: $personalProtocols")

        def journals = welder.journals
        assertNotNull(journals)
        assert journals.size() > 0
        LOGGER.info("Journals of welder is: $journals")



        LOGGER.info("End testWelderManyToManyFields")
        print('\n')
    }
}
