package com.lvg.weldercenter.se.ui.models

import com.lvg.weldercenter.se.models.Organization
import javafx.beans.property.LongProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

import static com.lvg.weldercenter.se.ui.models.ModelsConstants.*

class OrganizationUI {

    private final Organization organization

    final LongProperty id = new SimpleLongProperty()
    final StringProperty name = new SimpleStringProperty()
    final StringProperty address = new SimpleStringProperty()
    final StringProperty phone = new SimpleStringProperty()

    OrganizationUI(Organization organization){
        this.organization = organization
        this.id.set(organization.id == null ? NULL_ID_FIELD_DEFAULT : organization.id)
        this.name.set(organization.name)
        this.address.set(organization.address)
        this.phone.set(organization.phone)
    }

    Organization getOrganization() {
        organization.name = this.name
        organization.address = this.address
        organization.phone = this.phone

        return organization
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        OrganizationUI that = (OrganizationUI) o

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
