package Datalayer;

import entities.Playlist;
import entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaylistDAO {
    DatabaseConnection dbc = new DatabaseConnection();

    private SongDAO songDao = new SongDAO();
    private HashMap<Playlist, Song> songAndPlaylistId = new HashMap<>();


    public List<Playlist> getAllPlaylists()
    {
        List <Playlist> getAllPlaylists = new ArrayList<>();
        try(Connection con = dbc.getConnection();)
        {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement("Select * from Playlist");
            ResultSet rst = pS.executeQuery();
            while (rst.next())
            {
                getAllPlaylists.add(new Playlist(rst.getInt("playlist_id"), rst.getString("title_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getAllPlaylists;
    }


    public Playlist addPlaylist(String title_name)
    {
        int id = getNextPlaylistID();
        try(Connection con = dbc.getConnection();) {
            addPlaylistToDatabase(id, title_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Playlist(id, title_name);
    }
    public void deletePlaylist()
    {
        throw new RuntimeException();
    }

    public int getNextPlaylistID(){
        try(Connection con = dbc.getConnection();){
            ResultSet rs = con.createStatement().executeQuery("SELECT TOP 1 * FROM Playlist ORDER BY playlist_id DESC;");
            rs.next();
            int id = rs.getInt("playlist_id");
            int nextID = id+1;
            return nextID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist getPlaylist(int id){
        List<Playlist> playlists = getAllPlaylists();

        for(Playlist p : playlists)
        {
            if (p.getId()==id)
                return p;
        }
        return null;
    }

    public List<Song> getSongsFromPlaylist(int playlist_id)
    {
        List<Integer> songIds = getSongIdsFromPlaylist(playlist_id);
        List<Song> songsInPlaylist = new ArrayList<>();

        for (int id : songIds) {
            songsInPlaylist.add(songDao.getSong(id));
        }
        return songsInPlaylist;
    }

        public void getPlaylistID(String name) {

        try (Connection con = dbc.getConnection();) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Playlist");
            rs.next();
            int playlist_name_id = rs.getInt("playlist_id");
            String playlist_name = rs.getString("title_name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlaylistToDatabase(int playlist_id, String title_name)
    {
        int id = getNextPlaylistID();
        String sql = "INSERT INTO Playlist (playlist_id, title_name) VALUES (?,?)";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,playlist_id);
            ps.setString(2,title_name);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getSongIdsFromPlaylist(int playlist_id)
    {
        List<Integer> songIds = new ArrayList<>();
        String sql = "SELECT song_id FROM Playlists_Songs WHERE playlist_id =?";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,playlist_id);
            ResultSet rst  = ps.executeQuery();
            while(rst.next())
            {
                songIds.add(rst.getInt("song_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songIds;
    }

    public void addSongToPlaylist(int playlist_id, int song_id)
    {
        String sql = "INSERT INTO Playlists_Songs (playlist_id, song_id) VALUES (?,?)";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,playlist_id);
            ps.setInt(2,song_id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSongAndPlaylistIdToHashmap(int playlist_id, int song_id)
    {
        songAndPlaylistId.put(getPlaylist(playlist_id), songDao.getSong(song_id));
    }

    public Playlist createPlaylist(String name) {
        throw new RuntimeException();
    }

    //Update name of playlist
}