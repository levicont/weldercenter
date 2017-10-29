package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.test.utils.ConnectionManager
import com.lvg.weldercenter.se.utils.TransactionManagerSetup

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.transaction.Status
import javax.transaction.UserTransaction

abstract class GenericModelTest extends ModelsGenerator{
    protected static final TransactionManagerSetup TMS = ConnectionManager.transactionManagerSetup()
    protected static final EntityManagerFactory EMF = ConnectionManager.entityManagerFactory()

    abstract void insertItemTest()
    abstract void updateItemTest()
    abstract void deleteItemTest()
    abstract void equalsHashCodeTest()
    abstract void toStringTest()


    private static closeEntityManager(EntityManager em){
        if (em != null && em.isOpen())
            em.close()
    }

    private static rollbackTransaction(UserTransaction tx){
        try {
            if (tx.getStatus() == Status.STATUS_ACTIVE ||
                    tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
                tx.rollback()
        }catch (Exception rbEx){
            System.err.println 'Exception during rollback'
            rbEx.printStackTrace(System.err)
        }
    }

    private static rollbackTransactionWithException(UserTransaction tx, Exception ex){
        rollbackTransaction(tx)
        throw new RuntimeException(ex)
    }

    protected static void callInTransaction(Closure<EntityManager> closure) {
        UserTransaction tx = TMS.getUserTransaction()
        EntityManager em = null
        try {
            tx.begin()
            em = closure.call()
            tx.commit()

        } catch (Exception ex) {
            rollbackTransactionWithException(tx, ex)
        } finally {
            closeEntityManager(em)
        }
    }


}
