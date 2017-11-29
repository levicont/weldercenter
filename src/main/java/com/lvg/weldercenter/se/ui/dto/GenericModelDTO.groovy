package com.lvg.weldercenter.se.ui.dto

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import org.apache.log4j.Logger

abstract class GenericModelDTO<T extends Serializable> {
    protected static final Logger LOGGER = Logger.getLogger(" DTO ")
    protected final String NULL_ERROR_MESSAGE = "Model in constructor cannot be null"
    protected final ObjectProperty<Long> idProperty = new SimpleObjectProperty<>(DTOConstants.NULL_ID_FIELD_DEFAULT)
    abstract Long getId()

    void validateModel(T model){
        if (model == null){
            LOGGER.error(NULL_ERROR_MESSAGE)
            throw new IllegalArgumentException(NULL_ERROR_MESSAGE)
        }
    }

}