package com.lvg.weldercenter.se.models


enum WeldDetail{

    T(code:'T', description: 'труба'),
    P(code:'P', description: 'пластина')

    String code
    String description


    WeldDetail(String code, String description){
        this.code = code
        this.description = description
    }

    String toString(){
        return "$code"
    }

}