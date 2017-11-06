package com.lvg.weldercenter.se.test.ui.services

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner)
@TestPropertySource("classpath:test.properties")
@ActiveProfiles("test")
@SpringBootTest
abstract class GenericUIServicesTest {
}
