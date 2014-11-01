package com.lvg.weldercenter;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context-db-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class GenericServiceHibernateTest {

    @Test
    public abstract void testGet();

    @Test
    public abstract void testGetAll();

    @Test
    @Transactional
    public abstract void testInsert();

    @Test
    @Transactional
    public abstract void testDelete();

    @Test
    @Transactional
    public abstract void testUpdate();




}
