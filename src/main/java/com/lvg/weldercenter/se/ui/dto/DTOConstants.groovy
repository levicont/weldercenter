package com.lvg.weldercenter.se.ui.dto

import java.time.LocalDate

interface DTOConstants {
    String DATE_FORMAT_PATTERN = 'dd.MM.yyyy'
    String NULL_FIELD_PLACEHOLDER = ''
    Long NULL_ID_FIELD_DEFAULT = 0l
    LocalDate DEFAULT_BIRTHDAY_PLACEHOLDER = LocalDate.of(1990,01,01)
    LocalDate DEFAULT_DATE_PLACEHOLDER = LocalDate.of(2010,01,01)
}