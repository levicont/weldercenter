package com.lvg.weldercenter.se.test.launch

import com.lvg.weldercenter.se.repositories.EducationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.sql.DataSource


@SpringBootApplication
class Application implements CommandLineRunner{

    @Autowired
    DataSource dataSource

    @Autowired
    EducationRepository repository


    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Transactional(readOnly = true)
    @Override
    void run(String... strings) throws Exception {
        print "Data source: $dataSource"
        exit(0)
    }
}
