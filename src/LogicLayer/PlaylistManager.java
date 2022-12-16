package LogicLayer;

import Datalayer.PlaylistDAO;
import Datalayer.SongDAO;
import entities.Playlist;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistManager {

    private final ObservableList<Playlist> playlists;
    private ObservableList<Song> playlistSongs;
    PlaylistDAO playlistDAO = new PlaylistDAO();
    SongDAO songDAO = new SongDAO();

    public PlaylistManager() {

        playlists = FXCollections.observableArrayList();
        playlistSongs = FXCollections.observableArrayList();
    }

    public void fetchAllPlaylists() {
        playlists.clear();
        playlists.addAll(playlistDAO.getAllPlaylists());
    }

    public ObservableList<Playlist> getPlaylists()
    {
        fetchAllPlaylists();
        return playlists;
    }

    public Playlist createPlaylist(String name) {
        Playlist playlist = playlistDAO.addPlaylist(name);
        playlists.add(playlist);
        return playlist;
    }

    public void addSongToPlaylist(int playlist_id, int song_id)
    {
        playlistDAO.addSongToPlaylist(playlist_id, song_id);
        playlistSongs.add(songDAO.getSong(song_id));
    }

    public ObservableList<Song> getSelectedPlaylistSongs() {
        return playlistSongs;
    }

    public void selectPlaylist(int id) {
        playlistSongs = FXCollections.observableArrayList(playlistDAO.getSongsFromPlaylist(id));
    }

    public void removePlaylist(Playlist playlist)
    {
        playlists.remove(playlist);
        playlistDAO.removePlaylistFromPlaylistSongDatabase(playlist);
        playlistDAO.removePlaylistFromDatabase(playlist);
    }

    public void removeSongFromPlaylist(Song selected) {
        playlistSongs.remove(selected);
        playlistDAO.removeSongFromPlaylist(selected);
    }
}
