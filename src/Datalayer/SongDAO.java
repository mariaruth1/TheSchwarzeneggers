package Datalayer;


import entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    DatabaseConnection dbc = new DatabaseConnection();
    List<Song> mistressSonglist;

    public List<Song> getAllSongs()
    {
        mistressSonglist = new ArrayList<>();

        try(Connection con = dbc.getConnection();){
            ResultSet rs = con.createStatement().executeQuery("SELECT iD, Title, ReleaseYear, Artist, Genre, SongPath FROM Songs");
            while (rs.next()){
                int id = rs.getInt("iD");
                String title = rs.getString("Title");
                int year = rs.getInt("ReleaseYear");
                String artist = rs.getString("Artist");
                String genre = rs.getString("Genre");
                String path = rs.getString("SongPath");
                mistressSonglist.add(new Song(id, title,year, artist, genre, path));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mistressSonglist;
    }

    public void addSongToDataBase(String title,int year,String artist,String genre,String path) {

        // makes a connection to the database and makes a prepared statement, that adds a song to database
        String sql = "INSERT INTO Songs (Title, ReleaseYear, Artist, Genre, SongPath) VALUES (?,?,?,?,?)";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,title);
            ps.setInt(2,year);
            ps.setString(3,artist);
            ps.setString(4,genre);
            ps.setString(5,path);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeFromDataBase(String title){

        String sql ="DELETE FROM Songs WHERE Title='" + title + "';";

        try(Connection con = dbc.getConnection();) {
            con.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateSong()
    {
        throw new RuntimeException();
    }
}
