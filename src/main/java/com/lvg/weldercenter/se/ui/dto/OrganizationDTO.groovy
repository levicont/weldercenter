package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.Organization
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty

import static com.lvg.weldercenter.se.ui.dto.DTOConstants.NULL_ID_FIELD_DEFAULT

class OrganizationDTO extends GenericModelDTO<Organization>{
    private final Organization organization
    private final ObjectProperty<Organization> organizationProperty = new SimpleObjectProperty<>()

    OrganizationDTO(Organization organization){
        this.organization = organization
        this.organizationProperty.set(organization)
        validateModel(organization)
    }

    Organization getOrganization() {
        return organization
    }

    Long getId(){
        return organization.id == null ? NULL_ID_FIELD_DEFAULT : organization.id
    }

    String getName(){
        return organization.name
    }

    String getAddress(){
        return organization.address
    }

    String getPhone(){
        return organization.phone
    }

    void setName(String name){
        this.organization.name = name
    }

    void setAddress(String address){
        this.organization.address = address
    }

    void setPhone(String phone){
        this.organization.phone = phone
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        OrganizationDTO that = (OrganizationDTO) o

        if (organization != that.organization) return false

        return true
    }

    int hashCode() {
        return organization.hashCode()
    }

    @Override
    String toString() {
        return getOrganization().toString()
    }
}
