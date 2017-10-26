package com.lvg.weldercenter.se.test.config

import com.lvg.weldercenter.se.services.EducationService
import com.lvg.weldercenter.se.services.impl.EducationServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = 'com.lvg.weldercenter.se')
class SpringJpaHibernateDBConfig {

    @Bean
    EducationService educationService(){
        return new EducationServiceImpl()
    }
}
