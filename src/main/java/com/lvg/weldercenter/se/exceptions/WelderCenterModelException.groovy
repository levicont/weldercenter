package com.lvg.weldercenter.se.exceptions

class WelderCenterModelException extends Exception {

        WelderCenterModelException(){
            super()
        }

    WelderCenterModelException(String msg, Exception ex){
            super(msg, ex)
    }

    WelderCenterModelException(String msg){
            super(msg)
    }
    }
