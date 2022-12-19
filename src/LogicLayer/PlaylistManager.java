package LogicLayer;

import Datalayer.PlaylistDAO;
import entities.Playlist;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistManager {

    private final ObservableList<Playlist> playlists;
    private ObservableList<Song> playlistSongs;
    PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistManager() {

        playlists = FXCollections.observableArrayList();
        playlistSongs = FXCollections.observableArrayList();
    }

    /**
     * First it clears the list as it is a final list, we have to in order to make changes to the list.
     * It then adds all the playlists from the playlistDAO method to the observable list playlists.
     */
    public void fetchAllPlaylists() {
        playlists.clear();
        playlists.addAll(playlistDAO.getAllPlaylists());
    }

    public ObservableList<Playlist> getPlaylists()
    {
        fetchAllPlaylists();
        return playlists;
    }

    /**
     * Creates a playlist by user input (String name)
     * Constructs the playlist in playlistDAO and adds it to the observable list.
     *
     * @param name
     * @return
     */
    public void createPlaylist(String name) {

        playlistDAO.addPlaylistToDatabase(name);
        int id = playlistDAO.getNewestIdPlaylist();
        Playlist playlist = new Playlist(id, name);
        playlists.add(playlist);

    }

    /**
     * When adding a song to a playlist, we need the song_id and playlist_id.
     * We add the ids to our table Playlists_Songs in the database.
     * We get the id that was selected from playlistDAO and then add it to our playlistSongs list.
     *
     * @param playlist_id
     * @param song_id
     */
    public void addSongToPlaylist(int playlist_id, int song_id) {
        playlistDAO.addSongToPlaylist(playlist_id, song_id);
        playlistSongs.add(playlistDAO.getSong(song_id));
    }

    /**
     * @return the new list of songs that are in playlists.
     */
    public ObservableList<Song> getSelectedPlaylistSongs() {
        return playlistSongs;
    }

    /**
     * We select a playlist and gets its id and check which song ids belong to the playlist id in the playlistDAO method.
     * We get a list of song ids from the playlistDAO. We add them to the observable list.
     *
     * @param id
     */
    public void selectPlaylist(int id) {
        playlistSongs = FXCollections.observableArrayList(playlistDAO.getSongsFromPlaylist(id));
    }
    private int playlistId;

    public void playlistRename(String playlistName){
        int id = this.playlistId;
        Playlist playlist = new Playlist(id, playlistName);
        playlistDAO.editPlaylistName(playlist);
    }

    public void getPlaylistName(String playlistName){
        this.playlistId = playlistDAO.getPlaylistID(playlistName);

    }

    /**
     * We get the selected playlist from the controller.
     * First we remove the playlist from the observable list.
     * Then we pass the playlist to playlistDAO to remove it from the database.
     *
     * @param playlist
     */
    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        playlistDAO.removePlaylistFromPlaylistSongDatabase(playlist);
        playlistDAO.removePlaylistFromDatabase(playlist);
    }

    /**
     * Deletes the selected playlist from the Playlist and Playlists_Songs table in the database.
     * First it removes the playlist from the Playlist table.
     * Then it deletes one of rows that has the selected playlist_id in Playlists_Songs in playlistDAO.
     *
     * @param selected
     */
    public void removeOneSongFromPlaylist(Song selected) {
        playlistSongs.remove(selected);
        playlistDAO.removeOneSongFromPlaylist(selected);
    }

    /**
     * Deletes all the selected song_ids in Playlists_Songs.
     * If the user deletes a song from theMistressList, we have to delete all the ids in the Playlist_Songs table too.
     * @param selected
     */
    public void removeSongsFromPlaylist(Song selected) {
        playlistSongs.remove(selected);
        playlistDAO.removeSongsFromPlaylist(selected);
    }
}
