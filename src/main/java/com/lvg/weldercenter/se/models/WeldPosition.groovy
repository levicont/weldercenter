package com.lvg.weldercenter.se.models

/**
 * According EN 247
 */
enum WeldPosition implements Serializable{
    PA(code: 'PA', description: 'нижнее для угловых и стыковых швов'),
    PB(code: 'PB', description: 'горизонтальное нижнее для угловых швов'),
    PC(code: 'PC', description: 'горизонтальное на вертикальной плоскости'),
    PD(code: 'PD', description: 'горизонтальное потолочное'),
    PE(code: 'PE', description: 'потолочное'),
    PF(code: 'PF', description: 'снизу вверх'),
    PG(code: 'PG', description: 'сверху вниз'),
    H_LO45(code: 'H-LO45', description: 'ось переменная')

    String code
    String description

    WeldPosition(String code, String description){
        this.code = code
        this.description = description
    }

    String toString(){
        return "$code"
    }


}