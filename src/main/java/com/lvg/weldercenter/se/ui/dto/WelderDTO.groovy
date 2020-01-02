package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.models.Welder
import javafx.beans.binding.StringBinding
import javafx.beans.binding.When
import javafx.beans.property.*
import javafx.beans.value.ChangeListener

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static com.lvg.weldercenter.se.ui.dto.DTOConstants.*
import static com.lvg.weldercenter.se.ui.dto.DTOConstants.WelderDTOPropertiesNames.*

class WelderDTO extends GenericModelDTO<Welder> implements ModelDTO {

    private Map<String, Object> originalWelderProperties = new HashMap<String, Object>()

    private final LongProperty idProperty = new SimpleLongProperty(super.idProperty.get())
    private final StringProperty nameProperty = new SimpleStringProperty(this, NAME__PROP_NAME)
    private final StringProperty surnameProperty = new SimpleStringProperty(this, SURNAME_PROP_NAME)
    private final StringProperty secondNameProperty = new SimpleStringProperty(this, SECOND_NAME_PROP_NAME)
    private final StringProperty documentNumberProperty = new SimpleStringProperty(this, DOCUMENT_NUMBER_PROP_NAME)
    private final StringProperty addressProperty = new SimpleStringProperty(this, ADDRESS_PROP_NAME)
    private final ObjectProperty<LocalDate> birthdayProperty = new SimpleObjectProperty<>(this, BIRTHDAY_PROP_NAME)
    private final ObjectProperty<LocalDate> dateBeginProperty = new SimpleObjectProperty<>(this, DATE_BEGIN_PROP_NAME)
    private final StringProperty educationProperty = new SimpleStringProperty(this, EDUCATION_PROP_NAME)
    private final StringProperty qualificationProperty = new SimpleStringProperty(this, QUALIFICATION_PROP_NAME)
    private final StringProperty jobProperty = new SimpleStringProperty(this, JOB_PROP_NAME)
    private final StringProperty birthdayFormatProperty = new SimpleStringProperty(this,BIRTHDAY_FORMAT_PROP_NAME)


    private final StringProperty organizationNameProperty = new SimpleStringProperty(this, ORG_NAME_PROP_NAME)
    private final StringProperty organizationAddressProperty = new SimpleStringProperty(this, ORG_ADDRESS_PROP_NAME)
    private final StringProperty organizationPhoneProperty = new SimpleStringProperty(this, ORG_PHONE_PROP_NAME)
    private final BooleanProperty isWelderDTOChangedProperty = new SimpleBooleanProperty(this, 'isWelderDTOChanged')



    WelderDTO(final Welder welder) {
        validateModel(welder)
        initProperties(welder)
        initOriginalWelderProperties(welder)
        StringBinding birthdayBinding = new When(birthdayProperty.isNull())
                .then('')
                .otherwise(birthdayProperty.get().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)))
        birthdayFormatProperty.bind(birthdayBinding)
        addListeners()
    }

    void updateWelderDTO(Welder welder) {
        initOriginalWelderProperties(welder)
    }


    private void initProperties(Welder welder){
        idProperty.set(welder.id == null ? NULL_ID_FIELD_DEFAULT : welder.id)
        nameProperty.set(welder.name)
        surnameProperty.set(welder.surname)
        secondNameProperty.set(welder.secondName)
        documentNumberProperty.set(welder.documentNumber)
        addressProperty.set(welder.address)
        birthdayProperty.set(welder.birthday)
        dateBeginProperty.set(welder.dateBegin)
        educationProperty.set(welder.education)
        qualificationProperty.set(welder.qualification)
        jobProperty.set(welder.job)
        OrganizationEmbedded org = welder.organization
        organizationNameProperty.set(org == null ? DEFAULT_ORGANIZATION_PLACEHOLDER.name :
                org.name)
        organizationAddressProperty.set(org == null ? DEFAULT_ORGANIZATION_PLACEHOLDER.address :
                org.address)
        organizationPhoneProperty.set(org == null ? DEFAULT_ORGANIZATION_PLACEHOLDER.phone :
                org.phone)
    }

    private void initOriginalWelderProperties(Welder welder){
        originalWelderProperties.put(ID_PROP_NAME, welder.id)
        originalWelderProperties.put(NAME__PROP_NAME, welder.name)
        originalWelderProperties.put(SURNAME_PROP_NAME, welder.surname)
        originalWelderProperties.put(SECOND_NAME_PROP_NAME, welder.secondName)
        originalWelderProperties.put(BIRTHDAY_PROP_NAME, welder.birthday)
        originalWelderProperties.put(DATE_BEGIN_PROP_NAME, welder.dateBegin)
        originalWelderProperties.put(DOCUMENT_NUMBER_PROP_NAME, welder.documentNumber)
        originalWelderProperties.put(EDUCATION_PROP_NAME, welder.education)
        originalWelderProperties.put(JOB_PROP_NAME, welder.job)
        originalWelderProperties.put(QUALIFICATION_PROP_NAME, welder.qualification)
        originalWelderProperties.put(ADDRESS_PROP_NAME, welder.address)
        originalWelderProperties.put(ORG_NAME_PROP_NAME, welder.organization != null ? welder.organization.name : null)
        originalWelderProperties.put(ORG_ADDRESS_PROP_NAME, welder.organization != null ? welder.organization.address : null)
        originalWelderProperties.put(ORG_PHONE_PROP_NAME, welder.organization != null ? welder.organization.phone : null)

    }

    BooleanProperty isWelderDTOChangedProperty(){
        boolean result =
                !(isEqualValuesProperties(nameProperty, originalWelderProperties.get(NAME__PROP_NAME)) &&
                isEqualValuesProperties(secondNameProperty, originalWelderProperties.get(SECOND_NAME_PROP_NAME)) &&
                isEqualValuesProperties(surnameProperty, originalWelderProperties.get(SURNAME_PROP_NAME)) &&
                isEqualValuesProperties(birthdayProperty, originalWelderProperties.get(BIRTHDAY_PROP_NAME)) &&
                isEqualValuesProperties(documentNumberProperty, originalWelderProperties.get(DOCUMENT_NUMBER_PROP_NAME)) &&
                isEqualValuesProperties(dateBeginProperty, originalWelderProperties.get(DATE_BEGIN_PROP_NAME)) &&
                isEqualValuesProperties(addressProperty, originalWelderProperties.get(ADDRESS_PROP_NAME)) &&
                isEqualValuesProperties(educationProperty, originalWelderProperties.get(EDUCATION_PROP_NAME)) &&
                isEqualValuesProperties(qualificationProperty, originalWelderProperties.get(QUALIFICATION_PROP_NAME)) &&
                isEqualValuesProperties(jobProperty, originalWelderProperties.get(JOB_PROP_NAME)) &&
                isEqualValuesProperties(organizationNameProperty,  originalWelderProperties.get(ORG_NAME_PROP_NAME)) &&
                isEqualValuesProperties(organizationAddressProperty, originalWelderProperties.get(ORG_ADDRESS_PROP_NAME)) &&
                isEqualValuesProperties(organizationPhoneProperty, originalWelderProperties.get(ORG_PHONE_PROP_NAME)) )
        isWelderDTOChangedProperty.set(result)
        return isWelderDTOChangedProperty
    }

    private static boolean isEqualValuesProperties(Property newProperty, Object oldValue){
        if (newProperty == null)
            throw new NullPointerException('Property must be not null')
        if (newProperty instanceof StringProperty && oldValue instanceof String)
            return newProperty.get() != null ? newProperty.get().trim() == oldValue : newProperty.get() == oldValue
        if (newProperty instanceof ObjectProperty<LocalDate> && oldValue instanceof LocalDate)
            return newProperty.get() == oldValue
        throw new IllegalArgumentException("Method does not support arguments of such classes newProperty: " +
                "${newProperty.class.getName()} and oldValue: ${oldValue.class.getName()}")
    }

    private void addListeners(){
        nameProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        secondNameProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        surnameProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        birthdayProperty.addListener((ChangeListener<LocalDate>){observable, oldName, newName ->
            update()
        })
        dateBeginProperty.addListener((ChangeListener<LocalDate>){observable, oldName, newName ->
            update()
        })
        documentNumberProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        addressProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        educationProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        qualificationProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        jobProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        organizationNameProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        organizationAddressProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
        organizationPhoneProperty.addListener((ChangeListener<String>){observable, oldName, newName ->
            update()
        })
    }

    private void update(){
        isWelderDTOChangedProperty().get()
    }



    Welder getWelder() {
        Welder result = new Welder()
        result.id = originalWelderProperties.get(ID_PROP_NAME) != null ? (Long)originalWelderProperties.get(ID_PROP_NAME):null
        result.organization = getOrganization()
        result.name = getName()
        result.surname = getSurname()
        result.secondName = getSecondName()
        result.birthday = getBirthday()
        result.dateBegin = getDateBegin()
        result.documentNumber = getDocumentNumber()
        result.address = getAddress()
        result.qualification = getQualification()
        result.education = getEducation()
        result.job = getJob()
        return result
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WelderDTO welderDTO = (WelderDTO) o

        if (getAddress() != welderDTO.getAddress()) return false
        if (getBirthday() != welderDTO.getBirthday()) return false
        if (getDateBegin() != welderDTO.getDateBegin()) return false
        if (getDocumentNumber() != welderDTO.getDocumentNumber()) return false
        if (getEducation() != welderDTO.getEducation()) return false
        if (getJob() != welderDTO.getJob()) return false
        if (getName() != welderDTO.getName()) return false
        if (getQualification() != welderDTO.getQualification()) return false
        if (getSecondName() != welderDTO.getSecondName()) return false
        if (getSurname() != welderDTO.getSurname()) return false

        return true
    }

    int hashCode() {
        int result
        result = (organizationNameProperty.get() != null ? organizationNameProperty.hashCode() : 0)
        result = 31 * result + (getName() != null ? getName().hashCode() : 0)
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0)
        result = 31 * result + (getSecondName() != null ? getSecondName().hashCode() : 0)
        result = 31 * result + (getDocumentNumber() != null ? getDocumentNumber().hashCode() : 0)
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0)
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0)
        result = 31 * result + (getDateBegin() != null ? getDateBegin().hashCode() : 0)
        result = 31 * result + (getEducation() != null ? getEducation().hashCode() : 0)
        result = 31 * result + (getQualification() != null ? getQualification().hashCode() : 0)
        result = 31 * result + (getJob() != null ? getJob().hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return getWelder().toString()
    }

    @Override
    Object clone() throws CloneNotSupportedException {
        return new WelderDTO(welder)
    }

    @Override
    Long getId() {
        def id = originalWelderProperties.get(ID_PROP_NAME)
        return id == null ? NULL_ID_FIELD_DEFAULT : (Long)id
    }

    String getName() {
        return nameProperty.get()
    }

    String getSurname() {
        return surnameProperty.get()
    }

    String getSecondName() {
        return secondNameProperty.get()
    }

    LocalDate getBirthday() {
        return birthdayProperty.get()
    }

    LocalDate getDateBegin() {
        return dateBeginProperty.get()
    }

    String getDocumentNumber() {
        return documentNumberProperty.get()
    }

    String getAddress() {
        return addressProperty.get()
    }

    String getEducation() {
        return educationProperty.get()
    }

    String getQualification() {
        return qualificationProperty.get()
    }

    String getJob() {
        return jobProperty.get()
    }

    OrganizationEmbedded getOrganization() {
        return organizationNameProperty.get().trim().isEmpty()? null:
                new OrganizationEmbedded(name: organizationNameProperty.get().trim(),
                        address: organizationAddressProperty.get().trim(),
                        phone: organizationPhoneProperty.get().trim())
    }

    String getBirthdayFormat() {
        def birthday = getBirthday()
        return birthday == null ? NULL_FIELD_PLACEHOLDER :
                birthday.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getDateBeginFormat() {
        def dateBegin = getDateBegin()
        return dateBegin == null ? NULL_FIELD_PLACEHOLDER :
                dateBegin.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    void setName(String name) {
        nameProperty.set(name)
    }

    void setSurname(String surname) {
        surnameProperty.set(surname)
    }


    void setSecondName(String secondName) {
        secondNameProperty.set(secondName)
    }

    void setBirthday(LocalDate birthday) {
        birthdayProperty.set(birthday)
    }

    void setDateBegin(LocalDate dateBegin) {
        dateBeginProperty.set(dateBegin)
    }

    void setDocumentNumber(String documentNumber) {
        documentNumberProperty.set(documentNumber)
    }

    void setAddress(String address) {
        addressProperty.set(address)
    }

    void setEducation(String education) {
        educationProperty.set(education)
    }

    void setQualification(String qualification) {
        qualificationProperty.set(qualification)
    }

    void setJob(String job) {
        jobProperty.set(job)
    }

    StringProperty getNameProperty() {
        return nameProperty
    }

    StringProperty getSurnameProperty() {
        return surnameProperty
    }

    StringProperty getSecondNameProperty() {
        return secondNameProperty
    }

    StringProperty getDocumentNumberProperty() {
        return documentNumberProperty
    }

    StringProperty getAddressProperty() {
        return addressProperty
    }

    ObjectProperty<LocalDate> getBirthdayProperty() {
        return birthdayProperty
    }

    ObjectProperty<LocalDate> getDateBeginProperty() {
        return dateBeginProperty
    }

    StringProperty getEducationProperty() {
        return educationProperty
    }

    StringProperty getQualificationProperty() {
        return qualificationProperty
    }

    StringProperty getJobProperty() {
        return jobProperty
    }

    StringProperty getOrganizationNameProperty(){
        return organizationNameProperty
    }

    StringProperty getOrganizationAddressProperty(){
        return organizationAddressProperty
    }

    StringProperty getOrganizationPhoneProperty(){
        return organizationPhoneProperty
    }

    static WelderDTO defaultWelderDTO() {
        WelderDTO result = new WelderDTO(getDefaultWelder())
        return result
    }


    private static Welder getDefaultWelder() {
        def welder = new Welder()
        welder.name = NULL_FIELD_PLACEHOLDER
        welder.surname = NULL_FIELD_PLACEHOLDER
        welder.secondName = NULL_FIELD_PLACEHOLDER
        welder.birthday = DEFAULT_BIRTHDAY_PLACEHOLDER
        welder.documentNumber = NULL_FIELD_PLACEHOLDER
        welder.dateBegin = DEFAULT_DATE_PLACEHOLDER
        welder.address = NULL_FIELD_PLACEHOLDER
        return welder
    }



}
