package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Organization
import org.junit.Test


class OrganizationTest extends GenericModelTest{

    @Test
    void insertItemTest(){
        def ORGANIZATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Organization organization = getOrganization()
            em.persist(organization)
            ORGANIZATION_ID = organization.id
            return em
        }
        assert ORGANIZATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Organization organization1 = em.find(Organization.class, ORGANIZATION_ID)
            assert organization1 != null
            assert organization1.id != null
            assert organization1.name == 'IBM'
            assert organization1.address == 'New-York'
            assert organization1.phone == '(0595)466-15-59'
            return em
        }


    }

    @Test
    void updateItemTest(){
        def ORGANIZATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Organization organization = getOrganization()
            em.persist(organization)
            ORGANIZATION_ID = organization.id
            return em
        }
        assert ORGANIZATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Organization organizationUpd = em.find(Organization.class, ORGANIZATION_ID)
            organizationUpd.name = 'Microsoft'
            em.persist(organizationUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Organization chkOrganization = em.find(Organization.class, ORGANIZATION_ID)
            assert chkOrganization.name == 'Microsoft'
            return em
        }
    }


    @Test
    void deleteItemTest(){
        def ORGANIZATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Organization organization = getOrganization()
            em.persist(organization)
            ORGANIZATION_ID = organization.id
            return em
        }
        assert ORGANIZATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Organization organizationUpd = em.find(Organization.class, ORGANIZATION_ID)
            em.remove(organizationUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Organization chkOrganization = em.find(Organization.class, ORGANIZATION_ID)
            assert chkOrganization == null
            return em
        }
    }

    @Test
    void equalsHashCodeTest(){
        def org1 = getOrganization()
        def org2 = getOrganization()

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
        def org = getOrganization()
        assert org.toString() == 'IBM, New-York, (0595)466-15-59'
    }


}
