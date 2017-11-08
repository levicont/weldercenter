package com.lvg.weldercenter.se.ui.models

import javafx.beans.property.LongProperty
import javafx.beans.property.SimpleLongProperty

abstract class GenericModelUI<T extends Serializable> {
    protected final LongProperty id = new SimpleLongProperty(ModelsConstants.NULL_ID_FIELD_DEFAULT)
    abstract Long getId()
}
