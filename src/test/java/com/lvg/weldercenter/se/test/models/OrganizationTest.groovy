package com.lvg.weldercenter.se.test.models
import com.lvg.weldercenter.se.models.Organization
import org.junit.Assert
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction
/**
 * Created by Victor on 05.10.2017.
 */
class OrganizationTest extends GenericModelTest{

    @Test
    void insertItemTest(){
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Organization organization = getOragnization()
        em.persist(organization)
        def ORGANIZATION_ID = organization.id
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Organization organization1 = em.find(Organization.class, ORGANIZATION_ID)
        tx.commit()

        assert organization1 != null
        assert organization1.id != null
        assert organization1.name == 'IBM'
        assert organization1.address == 'New-York'
        assert organization1.phone == '(0595)466-15-59'

    }

    @Test
    void updateItemTest(){
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Organization organization = getOragnization()
        em.persist(organization)
        tx.commit()

        assert organization.id != null
        def ORGANIZATION_ID = organization.id
        tx.begin()
        em = EMF.createEntityManager()
        Organization organizationUpd = em.find(Organization.class, ORGANIZATION_ID)
        organizationUpd.name = 'Microsoft'
        em.persist(organizationUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Organization chkOrganization = em.find(Organization.class, ORGANIZATION_ID)
        assert chkOrganization.name == 'Microsoft'
        tx.commit()


    }


    @Test
    void deleteItemTest(){
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Organization organization = getOragnization()
        em.persist(organization)
        tx.commit()

        assert organization.id != null
        def ORGANIZATION_ID = organization.id

        tx.begin()
        em = EMF.createEntityManager()
        Organization organizationUpd = em.find(Organization.class, ORGANIZATION_ID)
        em.remove(organizationUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Organization chkOrganization = em.find(Organization.class, ORGANIZATION_ID)
        assert chkOrganization == null
        tx.commit()


    }

    @Test
    void equalsHashCodeTest(){
        def org1 = getOragnization()
        def org2 = getOragnization()

        assert org1 == org2

        org1.id = 100
        org2.id = 101

        assert org1 != org2

        def list = new HashSet<Organization>()
        list.add(org1)
        org2.id = 100
        list.add(org2)

        assert list.size() == 1

    }

    @Test
    void toStringTest(){
        def org = getOragnization()
        assert org.toString() == 'IBM, New-York, (0595)466-15-59'
    }


}
