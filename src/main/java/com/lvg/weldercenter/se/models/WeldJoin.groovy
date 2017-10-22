package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class WeldJoin implements Serializable{


    @Column(name = 'CODE',nullable = false)
    String code
    @Column(name = 'DESCRIPTION')
    String description

    protected WeldJoin(){}

    WeldJoin(String code, String description){
        this.code = code
        this.description = description
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WeldJoin weldJoin = (WeldJoin) o

        if (code != weldJoin.code) return false
        if (description != weldJoin.description) return false

        return true
    }

    int hashCode() {
        int result
        result = (code != null ? code.hashCode() : 0)
        result = 31 * result + (description != null ? description.hashCode() : 0)
        return result
    }

    String toString(){
        "$code"
    }
}
