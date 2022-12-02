package Datalayer;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


public class DatabaseConnection {

    public Connection getConnection() {
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe22B_17_MyTunes"); // make this unique as names are shared on server
        ds.setUser("CSe22B_17"); // Use your own username
        ds.setPassword("CSe22B_17"); // Use your own password
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true); // Newer JDBC has this on by default, our db doesn't work with real TLS certificate
        try {
            return ds.getConnection();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFromDataBase(String title){
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe22B_17_MyTunes"); // make this unique as names are shared on server
        ds.setUser("CSe22B_17"); // Use your own username
        ds.setPassword("CSe22B_17"); // Use your own password
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true); // Newer JDBC has this on by default, our db doesn't work with real TLS certificate

        String sql ="DELETE FROM Songs WHERE Title='" + title + "';";
        Connection con = null;
        try {
            con.createStatement().execute(sql);
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void editFromDataBase(String title){
     //to do

     }
}


