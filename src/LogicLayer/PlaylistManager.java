package LogicLayer;

import entities.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistManager {
    private final ObservableList<Playlist> playlists;
    private SongManager bll = new SongManager();

    public PlaylistModel() {
        playlists = FXCollections.observableArrayList();
    }

    public Playlist createPlaylist(String name) {
        Playlist playlist = bll.createPlaylist(name);
        playlists.add(playlist);
        return playlist;
    }

    public void fetchAllPlaylists() {
        playlists.clear();
        playlists.addAll(bll.getAllPlaylists());
    }
}
