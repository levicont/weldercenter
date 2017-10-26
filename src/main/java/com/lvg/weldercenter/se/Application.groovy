package com.lvg.weldercenter.se

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@ComponentScan(basePackages = 'com.lvg.weldercenter.se, com.lvg.weldercenter.se.test')
class Application implements CommandLineRunner{

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Transactional
    @Override
    void run(String... strings) throws Exception {
    }
}
