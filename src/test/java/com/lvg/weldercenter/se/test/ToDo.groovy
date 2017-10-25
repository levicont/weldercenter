package com.lvg.weldercenter.se.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootApplication
class ToDo {

    @PersistenceContext
    private static EntityManager entityManager

    static void main(String[] args) {
        def context = SpringApplication.run(getClass(), args)
        context.getAutowireCapableBeanFactory().autowireBean(this)
        print entityManager
    }
}
