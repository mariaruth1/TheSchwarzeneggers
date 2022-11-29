package Datalayer;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


public class DatabaseConnection {
   //public static void main(String[] args) {
     //   DatabaseConnection DBC = new DatabaseConnection();
       //DBC.addSongToDataBase( "navn",1999,"John", "songs","jazz",1.20,"pathy");
    //}


    public void addSongToDataBase(String title,int year,String artist,String album,String genre,double songLength,String path) {
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
        String sql = "INSERT INTO Songs (Title, Year, Artist, Album, Genre, SongLength, Path)" +
                "VALUES" + "('" + title + "', '" + year + "', '" + artist + "', '" + album + "', '" + genre + "', '" + songLength + "', '" + path + "')";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con.createStatement().execute(sql);
            con.close();
            System.out.println("Song added" + sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }


