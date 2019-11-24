package com.lvg.weldercenter.se.test.models

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional


@RunWith(SpringRunner.class)
@SpringBootTest
abstract class GenericModelTest extends ModelsGenerator{




    abstract void insertItemTest()
    abstract void updateItemTest()
    abstract void deleteItemTest()
    abstract void equalsHashCodeTest()
    abstract void toStringTest()


    private static closeEntityManager(EntityManager em){
        if (em != null && em.isOpen())
            em.close()
    }

    @Transactional
    protected static void callInTransaction(Closure<EntityManager> closure) {
        EntityManager em = null
        try {
            em = closure.call()
        } catch (Exception ex) {
            ex.printStackTrace()
        } finally {
            closeEntityManager(em)
        }
    }

    private class EntityManagerFactory{
        private javax.persistence.EntityManagerFactory entityManagerFactoryLocal

        EntityManagerFactory(javax.persistence.EntityManagerFactory entityManagerFactoryLocal) {
            this.entityManagerFactoryLocal = entityManagerFactoryLocal
        }

        public javax.persistence.EntityManager createEntityManager(){
            throw new UnsupportedOperationException('NOT Supported yet')
        }
    }


}
