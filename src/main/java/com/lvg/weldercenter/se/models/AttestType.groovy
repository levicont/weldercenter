package com.lvg.weldercenter.se.models

enum AttestType {
    PRIMARY('Первичная'),
    ADDITIONAL('Дополнительная'),
    PERIODICAL('Переодическая'),
    EXTRAORDINARY('Внеочередная')

    String value

    AttestType(String value){
        this.value = value
    }

    String toString(){
        return value
    }

}