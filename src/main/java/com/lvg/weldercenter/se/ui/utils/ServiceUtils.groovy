package com.lvg.weldercenter.se.ui.utils

import com.lvg.weldercenter.se.ui.services.OnceStartedService
import com.lvg.weldercenter.se.ui.services.ParameterUIService
import javafx.concurrent.Service

class ServiceUtils {

    static void startService(OnceStartedService service){
        def s = (Service)service
        if(service.isStartedOnce()){
            s.restart()
        }else {
            service.setStartedOnceFlag(true)
            s.start()
        }
    }

   static <T> void startParameterService(ParameterUIService service, T parameter){
            service.initParameter(parameter)
            startService(service)
   }


}
