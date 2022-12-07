package LogicLayer;

import Datalayer.PlaylistDAO;
import entities.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistManager {
    /**
    private final ObservableList<Playlist> playlists;
    PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistModel() {

        playlists = FXCollections.observableArrayList();
    }

    public Playlist createPlaylist(String name) {
        Playlist playlist = playlistDAO.createPlaylist(name);
        playlists.add(playlist);
        return playlist;
    }

    public void fetchAllPlaylists() {
        playlists.clear();
        playlists.addAll(playlistDAO.getAllPlaylists());
    }
    */
}
