package com.lvg.weldercenter.se.models


enum Evaluation implements Serializable{
    E('годен'),
    NE('не годен')

    String value

    Evaluation(String value){
        this.value = value
    }

    @Override
    String toString() {
        return "$value"
    }
}