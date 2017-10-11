package com.lvg.weldercenter.se.models

enum AttestType {
    PRIMARY('Первичная'),
    ADDITIONAL('Дополнительная'),
    PEREODICAL('Переодическая'),
    EXTRAORDINARY('Внеочередная')

    String value

    AttestType(String value){
        this.value = value
    }

    String toString(){
        return value
    }

}