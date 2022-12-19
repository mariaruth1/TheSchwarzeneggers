package Datalayer;

import entities.Playlist;
import entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    DatabaseConnection dbc = new DatabaseConnection();

    private SongDAO songDao = new SongDAO();

    /**
     * Gets all the playlists from our database and adds them to a list.
     * @return the list of playlists.
     */
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
    /**
     * Adds a playlist by the name the user inputs.
     * The id will always be the last id+1.
     * @param title_name
     * @return new Playlist with id and title name.
     */
    public Playlist addPlaylist(String title_name)
    {

        try(Connection con = dbc.getConnection();) {
            addPlaylistToDatabase(title_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Playlist(title_name);
    }
    /**
     * A loop that goes through all the songs to find the id of the song, that has been selected.
     * @param id
     * @return a song.
     */
    public Song getSong(int id){
        List<Song> songs = songDao.getAllSongs();
        for (Song s : songs) {
            if(s.getId() == id) {
                return s;
            }
        }
        return null;
    }


    /**
     * We make a list to collect the song ids, that belong to the playlist, we have selected.
     * The loop checks if the song id belongs to the playlist id and then adds it to a list.
     * @param playlist_id
     * @return list of song ids.
     */
    public List<Song> getSongsFromPlaylist(int playlist_id)
    {
        List<Integer> songIds = getSongIdsFromPlaylist(playlist_id);
        List<Song> songsInPlaylist = new ArrayList<>();

        for (int id : songIds) {
            songsInPlaylist.add(getSong(id));
        }
        return songsInPlaylist;
    }
    public int getPlaylistID(String name) {

        try (Connection con = dbc.getConnection();) {
            ResultSet rs = con.createStatement().executeQuery("SELECT playlist_id FROM Playlist WHERE title_name='" + name + "'");
            rs.next();
            return rs.getInt("playlist_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adds new playlist id and title name into the database.
     * @param title_name
     */
    public void addPlaylistToDatabase(String title_name) {
        String sql = "INSERT INTO Playlist (title_name) VALUES (?);";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title_name);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNewestIdPlaylist() {

        try (Connection con = dbc.getConnection();) {
            ResultSet rs = con.createStatement().executeQuery("SELECT TOP 1 * FROM Playlist ORDER BY playlist_id DESC;");
            rs.next();
            int id = rs.getInt("playlist_id");
            int nextID = id;
            return nextID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Here we get the song ids and adds them to a list of integers.
     * @param playlist_id
     * @return
     */
    public List<Integer> getSongIdsFromPlaylist(int playlist_id) {
        List<Integer> songIds = new ArrayList<>();
        String sql = "SELECT song_id FROM Playlists_Songs WHERE playlist_id =?";
        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlist_id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                songIds.add(rst.getInt("song_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songIds;
    }
    /**
     * Adds playlist_id and song_id to Playlists_Songs table.
     * With this table, we can now see which playlist_id has what song_id.
     * @param playlist_id
     * @param song_id
     */
    public void addSongToPlaylist(int playlist_id, int song_id) {
        String sql = "INSERT INTO Playlists_Songs (playlist_id, song_id) VALUES (?,?)";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlist_id);
            ps.setInt(2, song_id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPlaylistName(Playlist playlist) {
        String title = playlist.getTitle();
        int id = playlist.getId();

        System.out.println(title +" " + id);
        String sql = "UPDATE PlayList SET title_name = ? WHERE playlist_id = ? ;";

        //I have no idea why this isn't working

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * We get the id from the playlist that was selected.
     * We then delete the rows depending on the id in Playlists_Songs in our database.
     */
    public void removePlaylistFromPlaylistSongDatabase(Playlist playlist) {
        int id = playlist.getId();
        String sql = "DELETE FROM Playlists_Songs WHERE playlist_id='" + id + "';";

        try(Connection con = dbc.getConnection();) {
            con.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * First we get the id of the playlist that was selected.
     * We then delete the row depending on the id in Playlist in the database.
     */
    public void removePlaylistFromDatabase(Playlist playlist) {
        int id = playlist.getId();
        String sql ="DELETE FROM Playlist WHERE playlist_id='" + id + "';";

        try(Connection con = dbc.getConnection();) {
            con.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * First we get the id of the selected song.
     * We then delete a row depending on the song_id in Playlist_Songs.
     * Deletes one row as we can have duplicate songs.
     */
    public void removeOneSongFromPlaylist(Song song) {
        int id = song.getId();
        String sql = "SET ROWCOUNT 1 DELETE FROM Playlists_Songs WHERE song_id='" +id+ "' SET ROWCOUNT 0;";
        try(Connection con = dbc.getConnection();) {
            con.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeSongsFromPlaylist(Song song) {
        int id = song.getId();
        String sql = "DELETE FROM Playlists_Songs WHERE song_id='" +id+ "';";
        try(Connection con = dbc.getConnection();) {
            con.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}