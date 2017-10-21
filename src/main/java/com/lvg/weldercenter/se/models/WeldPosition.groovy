package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class WeldPosition implements Serializable{

    @Column(name = 'WELD_POSITION_CODE')
    String code

    @Column(name = 'WELD_POSITION_DESCRIPTION')
    String description

    protected WeldPosition(){}

    WeldPosition(String code, String description){
        this.code = code
        this.description = description
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WeldPosition that = (WeldPosition) o

        if (code != that.code) return false

        return true
    }

    int hashCode() {
        return (code != null ? code.hashCode() : 0)
    }

    String toString(){
        return "$code"
    }


}