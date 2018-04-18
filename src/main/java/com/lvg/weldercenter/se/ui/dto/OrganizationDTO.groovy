package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.ModelsFactory
import com.lvg.weldercenter.se.models.Organization
import javafx.beans.property.BooleanProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.beans.value.ChangeListener

import static com.lvg.weldercenter.se.ui.dto.DTOConstants.NULL_FIELD_PLACEHOLDER
import static com.lvg.weldercenter.se.ui.dto.DTOConstants.NULL_ID_FIELD_DEFAULT

class OrganizationDTO extends GenericModelDTO<Organization> implements ModelDTO{

    private final Organization organization

    private final StringProperty nameProperty = new SimpleStringProperty()
    private final StringProperty addressProperty = new SimpleStringProperty()
    private final StringProperty phoneProperty = new SimpleStringProperty()
    private final BooleanProperty isOrganizationDTOChanged = new SimpleBooleanProperty()
    private final ObjectProperty<Organization> originalOrganizationProperty = new SimpleObjectProperty<>()

    OrganizationDTO(Organization organization){
        validateModel(organization)
        originalOrganizationProperty.set(organization)
        idProperty.set(organization.id == null ? NULL_ID_FIELD_DEFAULT: organization.id)
        nameProperty.set(organization.name)
        addressProperty.set(organization.address == null ? NULL_FIELD_PLACEHOLDER : organization.address)
        phoneProperty.set(organization.phone == null ? NULL_FIELD_PLACEHOLDER : organization.phone)
        this.organization = organization
        addListeners()
    }

    Organization getOrganization() {
        organization.name = getName()
        organization.address = getAddress()
        organization.phone = getPhone()
        return organization
    }

    Long getId(){
        def id = getOrganization().id
        return (id == null ? NULL_ID_FIELD_DEFAULT : id)
    }

    String getName(){
        return nameProperty.get()
    }

    String getAddress(){
        return addressProperty.get()
    }

    String getPhone(){
        return phoneProperty.get()
    }

    void setName(String name){
        nameProperty.set(name)
    }

    void setAddress(String address){
        addressProperty.set(address)
    }

    void setPhone(String phone){
        phoneProperty.set(phone)
    }

    StringProperty nameProperty(){
        nameProperty
    }

    StringProperty addressProperty(){
        addressProperty
    }

    StringProperty phoneProperty(){
        phoneProperty
    }

    BooleanProperty isOrganizationDTOChanged(){
        boolean result = !(nameProperty.get() == originalOrganizationProperty.get().name &&
                            addressProperty.get() == originalOrganizationProperty.get().address &&
                            phoneProperty.get() == originalOrganizationProperty.get().phone)
        isOrganizationDTOChanged.set(result)
        return isOrganizationDTOChanged
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null) return false
        if (getClass() != o.class) return false

        OrganizationDTO that = (OrganizationDTO) o

        if (idProperty.value != that.idProperty.value) return false
        if (addressProperty.value != that.addressProperty.value) return false
        if (nameProperty.value != that.nameProperty.value) return false
        if (phoneProperty.value != that.phoneProperty.value) return false

        return true
    }

    int hashCode() {
        int result
        result = nameProperty.value.hashCode()
        result = 31 * result + addressProperty.value.hashCode()
        result = 31 * result + phoneProperty.value.hashCode()
        result = 31 * result + idProperty.value.hashCode()
        return result
    }

    @Override
    String toString() {
        return "OrganizationDTO: idProperty: ${idProperty.get()}, nameProperty: ${nameProperty.get()}, " +
                "addressProperty: ${addressProperty.get()}"
    }

    private void addListeners(){
        nameProperty.addListener((ChangeListener<String>){observable, oldValue, newValue ->
            update()
        })
        addressProperty.addListener((ChangeListener<String>){observable, oldValue, newValue ->
            update()
        })
        phoneProperty.addListener((ChangeListener<String>){observable, oldValue, newValue ->
            update()
        })
    }

    private void update(){
        isOrganizationDTOChanged()
    }

    static OrganizationDTO getDefaultOrganizationDTO(){
        OrganizationDTO result = new OrganizationDTO(ModelsFactory.getDefaultOrganization())
        result.idProperty.set(NULL_ID_FIELD_DEFAULT)
        return result
    }
}
