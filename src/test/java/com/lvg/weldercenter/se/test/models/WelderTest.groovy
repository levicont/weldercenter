package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Welder
import org.junit.Test

import java.time.LocalDate

class WelderTest extends GenericModelTest{


    @Test
    void insertItemTest(){
        def WELDER_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Welder welder = getWelder()
            em.persist(welder)
            WELDER_ID = welder.id
            return em
        }
        assert WELDER_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Welder chkWelder = em.find(Welder.class, WELDER_ID)
            def org = chkWelder.getOrganization()
            assert chkWelder.id != null
            assert chkWelder.name == 'Иван'
            assert chkWelder.surname == 'Иванов'
            assert chkWelder.secondName == 'Иванович'
            assert chkWelder.birthday == LocalDate.of(1984,10,28)
            assert chkWelder.dateBegin == LocalDate.of(2000,10,28)
            assert chkWelder.documentNumber == '17-033/17'
            assert chkWelder.address == 'Michigan City 12066'
            assert chkWelder.education == 'среднее-специальное'
            assert chkWelder.qualification == 'электросварщик'
            assert chkWelder.job == 'элекросварщик'
            assert org.id != null
            assert org.name == 'IBM'
            return em
        }
    }

    @Test
    void updateItemTest(){
        def WELDER_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Welder welder = getWelder()
            em.persist(welder)
            WELDER_ID = welder.id
            return em
        }
        assert WELDER_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Welder welderUpd = em.find(Welder.class, WELDER_ID)
            welderUpd.surname = 'Петров'
            em.persist(welderUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Welder chkWelder = em.find(Welder.class, WELDER_ID)
            assert chkWelder.surname == 'Петров'
            return em
        }
    }


    @Test
    void deleteItemTest(){
        def WELDER_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Welder welder = getWelder()
            em.persist(welder)
            WELDER_ID = welder.id
            return em
        }
        assert WELDER_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Welder welderUpd = em.find(Welder.class, WELDER_ID)
            em.remove(welderUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Welder chkWelder = em.find(Welder.class, WELDER_ID)
            assert chkWelder == null
            return em
        }
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
