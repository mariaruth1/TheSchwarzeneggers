package Datalayer;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import entities.Playlist;
import entities.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    public List<Song> getAllPlaylists()
    {
        throw new RuntimeException();
    }

    public Playlist addPlaylist(String name)
    {
        throw new RuntimeException();
    }
    public void deletePlaylist()
    {
        throw new RuntimeException();
    }

    private int getNextId()
    {
                List<Song> songs = getAllPlaylists();
                int biggestId = 0;
                for(Song s : songs)
                {
                    if (biggestId<s.getId())
                        biggestId = s.getId();
                }
                return biggestId+1;
            }

    public void getPlaylists(String name) {

        try (Connection con = dbc.getConnection();) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Playlist");
            rs.next();
            int playlist_name_id = rs.getInt("playlist_id");
            String playlist_name = rs.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addSongToPlaylist(int playlist_id, int song_id)
    {
        try(Connection con = dbc.getConnection();) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Playlist_Songs");
            rs.next();
            int id = rs.getInt("id");
            int id_playlist = rs.getInt("playlist_id");
            int id_song = rs.getInt("song_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}