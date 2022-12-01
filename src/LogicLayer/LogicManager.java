package LogicLayer;

import dal.PlaylistDAO;
import dal.SongDAO;
import entities.Playlist;
import entities.Song;

import java.util.ArrayList;
import java.util.List;

public class LogicManager {

    private SongDAO songDAO = new SongDAO();
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public List<Song> searchSongs(String query)
    {
        throw new RuntimeException();
    }

    public Song createSong(String title, String artist) {
        return songDAO.addSong(title, artist);
    }

    public Playlist createPlaylist(String name) {
        return playlistDAO.addPlaylist(name);
    }
}
