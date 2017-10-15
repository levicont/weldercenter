package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class WeldDetail implements Serializable{

    @Column(name = 'WELD_PATTERN_CODE')
    String code
    @Column(name = 'WELD_PATTERN_DESCRIPTION')
    String description

    protected  WeldDetail(){}

    WeldDetail(String code, String description){
        this.code = code
        this.description = description
    }

    String toString(){
        return "$code ($description)"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WeldDetail that = (WeldDetail) o

        if (code != that.code) return false

        return true
    }

    int hashCode() {
        return (code != null ? code.hashCode() : 0)
    }
}