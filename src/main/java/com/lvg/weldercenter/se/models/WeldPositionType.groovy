package com.lvg.weldercenter.se.models


enum WeldPositionType implements Serializable{
    PA(new WeldPosition(code: 'PA', description: 'нижнее для угловых и стыковых швов')),
    PB(new WeldPosition(code: 'PB', description: 'горизонтальное нижнее для угловых швов')),
    PC(new WeldPosition(code: 'PC', description: 'горизонтальное на вертикальной плоскости')),
    PD(new WeldPosition(code: 'PD', description: 'горизонтальное потолочное')),
    PE(new WeldPosition(code: 'PE', description: 'потолочное')),
    PF(new WeldPosition(code: 'PF', description: 'снизу вверх')),
    PG(new WeldPosition(code: 'PG', description: 'сверху вниз')),
    H_LO45(new WeldPosition(code: 'H-LO45', description: 'ось переменная'))

    WeldPosition value

    WeldPositionType(WeldPosition value){
        this.value = value
    }

    @Override
    String toString() {
        return "$value"
    }
}