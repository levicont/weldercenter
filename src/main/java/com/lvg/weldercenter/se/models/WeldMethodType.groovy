package com.lvg.weldercenter.se.models

enum WeldMethodType {

    MMA(new WeldMethod(name: 'РДЭ', code: '111')),
    MIG(new WeldMethod(name: 'МИГ', code: '131')),
    MAG(new WeldMethod(name: 'МАГ', code: '135')),
    WIG(new WeldMethod(name: 'ВИГ', code: '141')),
    GW(new WeldMethod(name: 'ГС', code: '311'))

    WeldMethod weldMethod

    WeldMethodType(WeldMethod weldMethod){
        this.weldMethod = weldMethod
    }

    String toString(){
        return "$weldMethod"
    }


}