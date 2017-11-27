package com.lvg.weldercenter.se.exceptions

class WelderCenterException extends Exception{

    WelderCenterException(){super()}

    WelderCenterException(String msg){super(msg)}

    WelderCenterException(String msg, Exception ex) {super(msg, ex)}
}
