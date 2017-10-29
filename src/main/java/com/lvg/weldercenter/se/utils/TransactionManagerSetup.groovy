package com.lvg.weldercenter.se.utils

import bitronix.tm.TransactionManagerServices
import bitronix.tm.resource.jdbc.PoolingDataSource
import org.apache.log4j.Logger

import javax.naming.Context
import javax.naming.InitialContext
import javax.sql.DataSource
import javax.transaction.UserTransaction


/**
 * Provides a database connection pool with the Bitronix JTA transaction
 * manager (http://docs.codehaus.org/display/BTM/Home).
 * <p>
 * Hibernate will look up the datasource and <code>UserTransaction</code> through
 * JNDI, that's why you also need a <code>jndi.properties</code> file. A minimal
 * JNDI context is bundled with and started by Bitronix.
 * </p>
 */
@SuppressWarnings("SpellCheckingInspection")
class TransactionManagerSetup {

    public static final String DATASOURCE_NAME = "wcDS"

    private static final Logger LOGGER =
            Logger.getLogger(TransactionManagerSetup.class.getName())

    protected final Context context = new InitialContext()
    protected final PoolingDataSource datasource
    public final DatabaseProduct databaseProduct

    TransactionManagerSetup(DatabaseProduct databaseProduct) throws Exception {
        this(databaseProduct, null)
    }

    TransactionManagerSetup(DatabaseProduct databaseProduct,
                            String connectionURL) throws Exception {

        LOGGER.info("Starting database connection pool")

        LOGGER.info("Setting stable unique identifier for transaction recovery")
        TransactionManagerServices.getConfiguration().setServerId("myServer1234")

        LOGGER.info("Disabling JMX binding of manager in unit tests")
        TransactionManagerServices.getConfiguration().setDisableJmx(true)

        LOGGER.info("Disabling transaction logging for unit tests")
        TransactionManagerServices.getConfiguration().setJournal("null")

        LOGGER.info("Disabling warnings when the database isn't accessed in a transaction")
        TransactionManagerServices.getConfiguration().setWarnAboutZeroResourceTransaction(false)

        LOGGER.info("Creating connection pool")
        datasource = new PoolingDataSource()
        datasource.setUniqueName(DATASOURCE_NAME)
        datasource.setMinPoolSize(1)
        datasource.setMaxPoolSize(5)
        datasource.setPreparedStatementCacheSize(10)

        // Our locking/versioning tests assume READ COMMITTED transaction
        // isolation. This is not the default on MySQL InnoDB, so we set
        // it here explicitly.
        datasource.setIsolationLevel("READ_COMMITTED")

        // Hibernate's SQL schema generator calls connection.setAutoCommit(true)
        // and we use auto-commit mode when the EntityManager is in suspended
        // mode and not joined with a transaction.
        datasource.setAllowLocalTransactions(true)

        LOGGER.info("Setting up database connection: " + databaseProduct)
        this.databaseProduct = databaseProduct
        databaseProduct.configuration.configure(datasource, connectionURL)

        LOGGER.info("Initializing transaction and resource management")
        datasource.init()
    }

    Context getNamingContext() {
        return context
    }

    UserTransaction getUserTransaction() {
        try {
            return (UserTransaction) getNamingContext()
                    .lookup("java:comp/UserTransaction")
        } catch (Exception ex) {
            throw new RuntimeException(ex)
        }
    }

    DataSource getDataSource() {
        try {
            return (DataSource) getNamingContext().lookup(DATASOURCE_NAME)
        } catch (Exception ex) {
            throw new RuntimeException(ex)
        }
    }

    void rollback() {
        UserTransaction tx = getUserTransaction()
        try {
            if (tx.getStatus() == Status.STATUS_ACTIVE ||
                    tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
                tx.rollback()
        } catch (Exception ex) {
            System.err.println("Rollback of transaction failed, trace follows!")
            ex.printStackTrace(System.err)
        }
    }

    void stop() throws Exception {
        LOGGER.info("Stopping database connection pool")
        datasource.close()
        TransactionManagerServices.getTransactionManager().shutdown()
    }

}
