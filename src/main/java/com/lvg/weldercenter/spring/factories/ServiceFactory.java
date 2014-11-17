package com.lvg.weldercenter.spring.factories;

import com.lvg.weldercenter.models.WeldMethod;
import com.lvg.weldercenter.services.WeldMethodService;
import com.lvg.weldercenter.services.WelderService;
import com.lvg.weldercenter.spring.ContextFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Victor Levchenko LVG Corp. on 14.11.14.
 */
public class ServiceFactory {

    private static ApplicationContext context = ContextFactory.getApplicationContext();
    private static WelderService welderService;
    private static WeldMethodService weldMethodService;

    private ServiceFactory(){

    }

    public static WelderService getWelderService(){
        if(null != welderService){
            return welderService;
        }
        else {
            welderService = (WelderService)context.getBean("welderService");
        }
        return welderService;
    }

    public static WeldMethodService getWeldMethodService(){
        if(null != weldMethodService){
            return weldMethodService;
        }
        else{
            weldMethodService = (WeldMethodService)context.getBean("weldMethodService");
        }
        return weldMethodService;
    }
}
