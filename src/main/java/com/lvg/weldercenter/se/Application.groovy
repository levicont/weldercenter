package com.lvg.weldercenter.se

import com.lvg.weldercenter.se.ui.ApplicationFX
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = 'com.lvg.weldercenter.se, com.lvg.weldercenter.se.test')
class Application implements CommandLineRunner{

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Override
    void run(String... strings) throws Exception {
        javafx.application.Application.launch(ApplicationFX.class, strings)
    }
}
