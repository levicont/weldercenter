package com.lvg.weldercenter.se.utils

import bitronix.tm.resource.jdbc.PoolingDataSource
import org.hibernate.dialect.HSQLDialect
import org.hibernate.dialect.MySQL57InnoDBDialect
import org.hibernate.dialect.Oracle10gDialect
import org.hibernate.dialect.PostgreSQL82Dialect

enum DatabaseProduct {



    HSQLDB(
            new DataSourceConfiguration() {

                void configure(PoolingDataSource ds, String connectionURL) {
                    ds.setClassName("org.hsqldb.jdbc.pool.JDBCXADataSource")

                    // External instance: jdbc:h2:tcp://localhost/mem:test;USER=sa
                    ds.getDriverProperties().put(
                            "URL",
                            connectionURL != null
                                    ? connectionURL :
                                    "jdbc:hsqldb:hsql://localhost"
                    )

                    //ds.getDriverProperties().put("driverClassName", "org.hsqldb.jdbcDriver");
                    ds.getDriverProperties().put("user", "sa")


                }
            },
            HSQLDialect.class.getName()
    ),

    ORACLE(
            new DataSourceConfiguration() {

                void configure(PoolingDataSource ds, String connectionURL) {
                    ds.setClassName("oracle.jdbc.xa.client.OracleXADataSource")
                    ds.getDriverProperties().put(
                            "URL",
                            connectionURL != null
                                    ? connectionURL :
                                    "jdbc:oracle:thin:test/test@192.168.56.101:1521:xe"
                    )

                    // Required for reading VARBINARY/LONG RAW columns easily, see
                    // http://stackoverflow.com/questions/10174951
                    Properties connectionProperties = new Properties()
                    connectionProperties.put("useFetchSizeWithLongColumn", "true")
                    ds.getDriverProperties().put("connectionProperties", connectionProperties)
                }
            },
            Oracle10gDialect.class.getName()
    ),

    POSTGRESQL(
            new DataSourceConfiguration() {
                void configure(PoolingDataSource ds, String connectionURL) {
                    ds.setClassName("org.postgresql.xa.PGXADataSource")
                    if (connectionURL != null) {
                        throw new IllegalArgumentException(
                                "PostgreSQL XADataSource doesn't support connection URLs"
                        )
                    }
                    ds.getDriverProperties().put("serverName", "10.0.0.2")
                    ds.getDriverProperties().put("databaseName", "test")
                    ds.getDriverProperties().put("user", "test")
                    ds.getDriverProperties().put("password", "test")
                }
            },
            PostgreSQL82Dialect.class.getName()
    ),

    MYSQL(
            new DataSourceConfiguration() {
                void configure(PoolingDataSource ds, String connectionURL) {
                    // TODO: MySQL XA support is completely broken, we use the BTM XA wrapper
                    //ds.setClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
                    ds.setClassName("bitronix.tm.resource.jdbc.lrc.LrcXADataSource")
                    ds.getDriverProperties().put(
                            "url",
                            connectionURL != null
                                    ? connectionURL :
                                    "jdbc:mysql://localhost/test?sessionVariables=sql_mode='PIPES_AS_CONCAT'"
                    )
                    ds.getDriverProperties().put("user", "test")
                    ds.getDriverProperties().put("password", "test")
                    ds.getDriverProperties().put("driverClassName", "com.mysql.jdbc.Driver")
                }
            },
            // Yes, this should work with 5.6, no idea why Gail named it 5.7
            MySQL57InnoDBDialect.class.getName()
    )

    public DataSourceConfiguration configuration
    public String hibernateDialect

    private DatabaseProduct(DataSourceConfiguration configuration,
                            String hibernateDialect) {
        this.configuration = configuration
        this.hibernateDialect = hibernateDialect
    }

    interface DataSourceConfiguration {

        void configure(PoolingDataSource ds, String connectionURL)
    }

}