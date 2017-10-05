package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Welder
import org.junit.Assert
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction
import java.time.LocalDate

class WelderTest extends GenericModelTest{


    @Test
    void insertItemTest(){
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Welder welder = getWelder()
        em.persist(welder)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select w from Welder w').getResultList()
        tx.commit()

        Assert.assertEquals(1, list.size())
        welder = list.get(0)
        def org = welder.getOrganization()

        assert welder.id != null
        assert welder.name == 'Иван'
        assert welder.surname == 'Иванов'
        assert welder.secondName == 'Иванович'
        assert welder.birthday == LocalDate.of(1984,10,28)
        assert welder.dateBegin == LocalDate.of(2000,10,28)
        assert welder.documentNumber == '17-033/17'
        assert welder.address == 'Michigan City 12066'
        assert welder.education == 'среднее-специальное'
        assert welder.qualification == 'электросварщик'
        assert welder.job == 'элекросварщик'
        assert org.id != null
        assert org.name == 'IBM'
    }

    @Test
    void updateItemTest(){
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Welder welder = getWelder()
        em.persist(welder)
        tx.commit()

        assert welder.id != null
        def WELDER_ID = welder.id
        tx.begin()
        em = EMF.createEntityManager()
        Welder welderUpd = em.find(Welder.class, WELDER_ID)
        welderUpd.surname = 'Петров'
        em.persist(welderUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Welder chkWelder = em.find(Welder.class, WELDER_ID)
        assert chkWelder.surname == 'Петров'
        tx.commit()


    }


    @Test
    void deleteItemTest(){
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Welder welder = getWelder()
        em.persist(welder)
        tx.commit()

        assert welder.id != null
        def WELDER_ID = welder.id

        tx.begin()
        em = EMF.createEntityManager()
        Welder welderUpd = em.find(Welder.class, WELDER_ID)
        em.remove(welderUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Welder chkWelder = em.find(Welder.class, WELDER_ID)
        assert chkWelder == null
        tx.commit()


    }

    @Test
    void equalsHashCodeTest(){
        def welder1 = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        def welder2 = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')

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

    @Test
    void toStringTest(){
        def welder = new Welder(name: 'Иван', surname: 'Иванов', secondName: 'Иванович')
        assert welder.toString() == "Иванов Иван Иванович"
    }
}
