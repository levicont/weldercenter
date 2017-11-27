package com.lvg.weldercenter.se.exceptions

class NoItemSelectedException extends RuntimeException {

    NoItemSelectedException(){super()}

    NoItemSelectedException(String msg){super(msg)}

    NoItemSelectedException(String msg, Exception ex){super(msg, ex)}
}
