package com.lvg.weldercenter.se.test.utils

import com.lvg.weldercenter.se.test.models.Constants
import com.lvg.weldercenter.se.utils.DatabaseProduct
import com.lvg.weldercenter.se.utils.TransactionManagerSetup
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const

import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

/**
 * Created by Victor on 05.10.2017.
 */
class ConnectionManager {
    private static final TransactionManagerSetup TMS = getTransactionManagerSetup(DatabaseProduct.MYSQL);
    private static final EntityManagerFactory EMF = getEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);


    private static TransactionManagerSetup getTransactionManagerSetup(DatabaseProduct dbProduct){
        TransactionManagerSetup result = null;
        try{
            result =  new TransactionManagerSetup(dbProduct);
        }catch(Exception ex){
            System.out.println("We have exception: " + ex.getMessage());
            System.exit(0);
        }
        return result;
    }

    private static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName){
        EntityManagerFactory result = null;
        try{
            result =  Persistence.createEntityManagerFactory(persistenceUnitName);
        }catch(Exception ex){
            System.out.println("We have exception: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(0);
        }
        return result;
    }

    public static TransactionManagerSetup transactionManagerSetup(){
        return TMS == null ? getTransactionManagerSetup(DatabaseProduct.MYSQL):TMS
    }

    public static EntityManagerFactory entityManagerFactory(){
        return EMF == null ? getEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME) : EMF
    }

}
