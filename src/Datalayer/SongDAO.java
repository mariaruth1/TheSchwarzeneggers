package Datalayer;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import entities.Song;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    DatabaseConnection dbc = new DatabaseConnection();

    public List<Song> getAllSongs()
    {
        return null;
    }

    public Song addSong(String title,int year,String artist,String album,String genre,String path, int iD)
    {


        return null;
    }

    public void deleteSong(Song song)
    {
        throw new RuntimeException();
    }

    public void updateSong()
    {
        throw new RuntimeException();
    }

    public int getNextSongID(){
        try(Connection con = dbc.getConnection();){
            ResultSet rs = con.createStatement().executeQuery("SELECT TOP 1 * FROM Songs ORDER BY iD DESC;");
            rs.next();
            int id = rs.getInt("iD");
            int nextID = id+1;
            return nextID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Song getSong(int id)
    {

        throw new RuntimeException();
    }
    public void addSongToDataBase(String title,int year,String artist,String album,String genre,String path, int iD) {

        // makes a connection to the database and makes a prepared statement, that adds a song to database
        String sql = "INSERT INTO Songs (Title, Year, Artist, Album, Genre, Path, iD) VALUES (?,?,?,?,?,?,?)";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,title);
            ps.setInt(2,year);
            ps.setString(3,artist);
            ps.setString(4,album);
            ps.setString(5,genre);
            ps.setString(6,path);
            ps.setInt(7,iD);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
