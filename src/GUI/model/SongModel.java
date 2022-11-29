package GUI.model;

import LogicLayer.LogicManager;
import entities.Playlist;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongModel {
    private final ObservableList<Song> songs;
    private final ObservableList<Playlist> playlists;
    private LogicManager bll = new LogicManager();

    public SongModel(){
    songs = FXCollections.observableArrayList();
    playlists = FXCollections.observableArrayList();
    }

    public void search(String text) {
        songs.clear();
        songs.addAll(bll.searchSongs(text));
    }

    public Song createSong(String title, String artist, String genre) {
        Song song = bll.createSong(title, artist);
        songs.add(song);
        return song;
    }

    public Playlist createPlaylist(String name)
    {
        Playlist playlist = bll.createPlaylist(name);
        playlists.add(playlist);
        return playlist;
    }
}
