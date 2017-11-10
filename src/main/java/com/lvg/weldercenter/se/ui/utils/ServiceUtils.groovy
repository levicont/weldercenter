package com.lvg.weldercenter.se.ui.utils

import com.lvg.weldercenter.se.ui.services.OnceStartedFlag
import javafx.concurrent.Service

class ServiceUtils {

    static void startService(OnceStartedFlag service){
        def s = (Service)service
        if(service.isStartedOnce()){
            s.restart()
        }else {
            service.setStartedOnceFlag(true)
            s.start()
        }
    }


}
