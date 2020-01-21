package com.lvg.weldercenter.se.test.properties

import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections

class ListPropertiesTest {

    static ListProperty<String> listProperty = new SimpleListProperty<>()

    static void main(String[] args) {
        println("listProperty isEmpty: ${listProperty.isEmpty()}")
        listProperty.setValue(FXCollections.observableArrayList("one", 'two', "three"))
        println("listProperty values: ${listProperty.get()}")
        listProperty.clear()
        println("listProperty after clear: ${listProperty.get()}")
        listProperty.addAll(["four", "five", "six"])
        println("listProperty after addAll: ${listProperty.get()}")
    }
}
