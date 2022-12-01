package Datalayer;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


public class DatabaseConnection {

     public void addSongToDataBase(String title,int year,String artist,String album,String genre,String path, int iD) {
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe22B_17_MyTunes"); // make this unique as names are shared on server
        ds.setUser("CSe22B_17"); // Use your own username
        ds.setPassword("CSe22B_17"); // Use your own password
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true); // Newer JDBC has this on by default, our db doesn't work with real TLS certificate

        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        }
        //above code makes a connection to the database
        // below code runs the SQL code to add a song to the database based onthe imput the meteod got
        String sql = "INSERT INTO Songs (Title, Year, Artist, Album, Genre, Path, iD)" +
                "VALUES" + "('" + title + "', '" + year + "', '" + artist + "', '" + album + "', '" + genre + "', '" + path + "', '" + iD + "')";

        try {
            con.createStatement().execute(sql);
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getNextSongID(){
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe22B_17_MyTunes"); // make this unique as names are shared on server
        ds.setUser("CSe22B_17"); // Use your own username
        ds.setPassword("CSe22B_17"); // Use your own password
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true); // Newer JDBC has this on by default, our db doesn't work with real TLS certificate

        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        }
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT TOP 1 * FROM Songs ORDER BY iD DESC;");
            rs.next();
            int id = rs.getInt("iD");
            int nextID = id+1;
            return nextID;
        } catch (SQLException e) {
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


