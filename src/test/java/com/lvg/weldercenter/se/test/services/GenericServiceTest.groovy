package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.test.models.ModelsGenerator
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner)
@TestPropertySource("classpath:test.properties")
@ActiveProfiles("test")
@SpringBootTest
abstract class GenericServiceTest extends ModelsGenerator{

    @Test
    abstract void saveTest()
    @Test
    abstract void deleteTest()
    @Test
    abstract void getTest()
    @Test
    abstract void getAllTest()


}
