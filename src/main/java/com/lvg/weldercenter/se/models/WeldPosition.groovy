package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class WeldPosition implements Serializable{

    @NotNull
    @Column(name = 'CODE', nullable = false)
    String code

    @Column(name = 'DESCRIPTION')
    String description

    protected WeldPosition(){}

    WeldPosition(WeldPositionType weldPositionType){
        this.code = weldPositionType.value.code
        this.description = weldPositionType.value.description
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