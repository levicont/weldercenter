package com.lvg.weldercenter.se.test.ui.services

import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class LoadingWeldersServiceTest extends GenericUIServicesTest{

    @Autowired
    LoadingWeldersService service

    @Test
    void getAllWeldersServiceTest(){
        assert service != null
    }
}
