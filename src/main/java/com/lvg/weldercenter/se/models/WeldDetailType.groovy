package com.lvg.weldercenter.se.models

enum WeldDetailType {
    T(new WeldDetail(code:'T', description: 'труба')),
    P(new WeldDetail(code:'P', description: 'пластина'))

    WeldDetail value

    WeldDetailType(WeldDetail weldDetail){
        this.value = weldDetail
    }


    @Override
    String toString() {
        return "$value.code"
    }
}
