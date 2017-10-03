package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.utils.DatabaseProduct
import com.lvg.weldercenter.se.utils.TransactionManagerSetup
import org.junit.Assert
import org.junit.Test

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.transaction.UserTransaction
import java.time.LocalDate


class WelderTest {

    private static final TransactionManagerSetup TMS = getTransactionManagerSetup(DatabaseProduct.MYSQL);
    private static final EntityManagerFactory EMF = getEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);


    private static TransactionManagerSetup getTransactionManagerSetup(DatabaseProduct dbProduct){
        TransactionManagerSetup result = null;
        try{
            result =  new TransactionManagerSetup(dbProduct);
        }catch(Exception ex){
            System.out.println("We have exception: " + ex.getMessage());
            System.exit(0);
        }
        return result;
    }

    private static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName){
        EntityManagerFactory result = null;
        try{
            result =  Persistence.createEntityManagerFactory(persistenceUnitName);
        }catch(Exception ex){
            System.out.println("We have exception: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(0);
        }
        return result;
    }

    @Test
    public void WelderSaveTest(){
        UserTransaction tx = TMS.getUserTransaction()

        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Welder welder = new Welder(name: 'Иван', surname: 'Иванов', secname: 'Иванович')
        welder.birthday = LocalDate.of(1984,10,28)
        welder.dateBegin = LocalDate.of(2000,10,28)
        welder.docNumber = '17-033/17'
        em.persist(welder)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select w from Welder w').getResultList()
        tx.commit()

        Assert.assertEquals(1, list.size())
        welder = list.get(0)

        assert welder.name == 'Иван'
        assert welder.surname == 'Иванов'
        assert welder.secname == 'Иванович'
        assert welder.birthday == LocalDate.of(1984,10,28)
        assert welder.dateBegin == LocalDate.of(2000,10,28)
        assert welder.docNumber == '17-033/17'

        Serializable s = welder
    }

    @Test
    public void WelderEqualsHashTest(){
        def welder1 = new Welder(name: 'Иван', surname: 'Иванов', secname: 'Иванович')
        def welder2 = new Welder(name: 'Иван', surname: 'Иванов', secname: 'Иванович')

        assert welder1 == welder2

        welder1.id = 100
        welder2.id = 101

        assert welder1 != welder2

        def list = new HashSet<Welder>()
        list.add(welder1)
        welder2.id = 100
        list.add(welder2)

        assert list.size() == 1

    }
}
