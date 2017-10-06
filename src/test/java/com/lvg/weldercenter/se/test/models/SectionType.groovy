package com.lvg.weldercenter.se.test.models

enum SectionType{

    WELDING ( Integer.valueOf(1) ),
    HEALTH ( Integer.valueOf(2) ),
    DEFECTS ( Integer.valueOf(3) )

    Integer value

    SectionType(Integer value){
        this.value = value
    }

    String toString(){
        return "$value"
    }
}