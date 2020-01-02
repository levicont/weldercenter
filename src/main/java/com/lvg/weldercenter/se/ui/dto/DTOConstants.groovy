package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.OrganizationEmbedded

import java.time.LocalDate

interface DTOConstants {
    String DATE_FORMAT_PATTERN = 'dd.MM.yyyy'
    String NULL_FIELD_PLACEHOLDER = ''
    Long NULL_ID_FIELD_DEFAULT = 0l
    LocalDate DEFAULT_BIRTHDAY_PLACEHOLDER = LocalDate.of(1990,01,01)
    LocalDate DEFAULT_DATE_PLACEHOLDER = LocalDate.of(2010,01,01)
    OrganizationEmbedded DEFAULT_ORGANIZATION_PLACEHOLDER = new OrganizationEmbedded(name: '',phone: '', address: '')

    interface WelderDTOPropertiesNames{
        String ID_PROP_NAME = 'id'
        String NAME__PROP_NAME = 'name'
        String SURNAME_PROP_NAME = 'surname'
        String SECOND_NAME_PROP_NAME = 'secondName'
        String BIRTHDAY_PROP_NAME = 'birthday'
        String DATE_BEGIN_PROP_NAME = 'dateBegin'
        String DOCUMENT_NUMBER_PROP_NAME = 'documentNumber'
        String ADDRESS_PROP_NAME = 'address'
        String JOB_PROP_NAME = 'job'
        String EDUCATION_PROP_NAME = 'education'
        String QUALIFICATION_PROP_NAME = 'qualification'
        String ORG_NAME_PROP_NAME = 'organizationName'
        String ORG_ADDRESS_PROP_NAME = 'organizationAddress'
        String ORG_PHONE_PROP_NAME = 'organizationPhone'
        String BIRTHDAY_FORMAT_PROP_NAME = 'birthdayFormat'

    }
}