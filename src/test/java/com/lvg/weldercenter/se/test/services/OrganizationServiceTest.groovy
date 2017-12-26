package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.services.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional


@Transactional
class OrganizationServiceTest extends GenericServiceTest {

    @Autowired
    OrganizationService organizationService

    @Override
    void saveTest() {
        def ORGANIZATION_ID
        def organization = getOrganization()
        organization = organizationService.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        Organization updOrganization = organizationService.get(ORGANIZATION_ID)
        assert updOrganization != null
        assert updOrganization instanceof Organization
        updOrganization.name = 'Zipper'
        organizationService.save(updOrganization)

        Organization chkOrganization = organizationService.get(ORGANIZATION_ID)
        assert chkOrganization != null
        assert chkOrganization instanceof Organization
        assert chkOrganization.name == 'Zipper'

    }

    @Override
    void deleteTest() {
        def ORGANIZATION_ID
        def organization = getOrganization()
        organization = organizationService.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        Organization delOrganization = organizationService.get(ORGANIZATION_ID)
        assert delOrganization != null
        assert delOrganization instanceof Organization
        organizationService.delete(delOrganization)

        Organization chkOrganization = organizationService.get(ORGANIZATION_ID)
        assert chkOrganization == null
    }

    @Override
    void getTest() {
        def ORGANIZATION_ID
        def organization = getOrganization()
        organization = organizationService.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        Organization delOrganization = organizationService.get(ORGANIZATION_ID)
        assert delOrganization != null
        assert delOrganization instanceof Organization
    }

    @Override
    void getAllTest() {
        def ORGANIZATION_ID
        def organization = getOrganization()
        organization = organizationService.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        def list = organizationService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 3

    }

    @Override
    void countTest() {
        def ORGANIZATION_ID
        def organization = getOrganization()
        organization = organizationService.save(organization)
        ORGANIZATION_ID = organization.id
        assert ORGANIZATION_ID != null

        def count = organizationService.count()
        assert count != null
        assert count instanceof Long
        assert count == 3
    }
}
