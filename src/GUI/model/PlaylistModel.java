package GUI.model;

import LogicLayer.LogicManager;
import entities.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistModel {

    private final ObservableList<Playlist> playlists;
    private LogicManager bll = new LogicManager();

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