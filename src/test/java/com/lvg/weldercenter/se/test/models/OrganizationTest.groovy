package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.services.OrganizationService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class OrganizationTest extends GenericModelTest {

    @Autowired
    OrganizationService service

    @Test
    void insertItemTest() {
        def ORGANIZATION_ID
        Organization organization = getOrganization()
        service.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null


        Organization organization1 = service.get(ORGANIZATION_ID)
        assert organization1 != null
        assert organization1.id != null
        assert organization1.name == 'IBM'
        assert organization1.address == 'New-York'
        assert organization1.phone == '(0595)466-15-59'


    }

    @Test
    void updateItemTest() {
        def ORGANIZATION_ID
        Organization organization = getOrganization()
        service.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        Organization organizationUpd = service.get(ORGANIZATION_ID)
        organizationUpd.name = 'Microsoft'
        service.save(organizationUpd)

        Organization chkOrganization = service.get(ORGANIZATION_ID)
        assert chkOrganization.name == 'Microsoft'
    }


    @Test
    void deleteItemTest() {
        def ORGANIZATION_ID
        Organization organization = getOrganization()
        service.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        Organization organizationUpd = service.get(ORGANIZATION_ID)
        service.delete(organizationUpd)

        Organization chkOrganization = service.get(ORGANIZATION_ID)
        assert chkOrganization == null
    }

    @Test
    void equalsHashCodeTest() {
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
    void toStringTest() {
        def org = getOrganization()
        assert org.toString() == 'IBM, New-York, (0595)466-15-59'
    }


}
