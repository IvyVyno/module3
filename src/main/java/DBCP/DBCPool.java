package DBCP;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPool {
    private static DBCPool instance;
    private static BasicDataSource ds = new BasicDataSource();
    private static MySQLProperties mySqlProps = MySQLProperties.getInstance();

    static {
        ds.setUrl(mySqlProps.getUrl());
        ds.setUsername(mySqlProps.getUser());
        ds.setPassword(mySqlProps.getPassword());
        ds.setMinIdle(Configs.MIN_IDLE);
        ds.setMaxIdle(Configs.MAX_IDLE);
        ds.setMaxOpenPreparedStatements(Configs.MAX_OPEN_STATEMENTS);
    }

    public static DBCPool getInstance() {
        if (instance == null) {
            instance = new DBCPool();
        }
        return instance;
    }

    private DBCPool() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
