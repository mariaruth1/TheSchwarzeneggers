package LogicLayer;

import entities.Playlist;
import entities.Song;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MusicManager {

    SongManager songManager = SongManager.getInstance();
    PlaylistManager playlistManager = new PlaylistManager();

    Media media;
    MediaPlayer mediaPlayer;
    private int songIndex;
    private int playlistId;

    //static Song currentSong = new Song();

    /**
     * @return observable list of all the songs in the database.
     */

    public ObservableList<Song> getMistressListAgain() {
        return songManager.getMistressSongList();
    }

    public boolean isMediaPlayerNull() {
        return mediaPlayer == null;
    }


    public ObservableList<Playlist> getPlaylistAgain()
    {
        return playlistManager.getPlaylists();
    }

    public void addSongToPlaylistAgain(int playlist_id, int song_id)
    {
        playlistManager.addSongToPlaylist(playlist_id, song_id);
    }

    /**
     * Creates a list of strings with the path of a given song.
     * @return songPath of the relevant song.
     */
    public List<String> getAllSongPaths() {
        List<Song> songs = getMistressListAgain();
        List<String> songPaths = new ArrayList<>();

        for(Song s : songs)
        {
            songPaths.add(s.getPath());
        }
        return songPaths;
    }

    public List<String> getPlaylistSongPaths(){
        List<Song> songs = getPlaylistSongs();
        List<String> playlistSongPaths = new ArrayList<>();

        for(Song s : songs)
        {
            playlistSongPaths.add(s.getPath());
        }
        return playlistSongPaths;
    }



    /**
     * Measures the current Duration of a song, as an observable property.
     */
    public ReadOnlyObjectProperty<Duration> getSongProgress(){
        return mediaPlayer.currentTimeProperty();
    }

    /**
     * @return Total length of a song in milliseconds.
     */
    public double getSongLength(){
        return mediaPlayer.getTotalDuration().toMillis();
    }

    /**
     * First checks if there is currently a MediaPlayer instantiated as this is necessary to play a song.
     * Then if there is a song already playing,
     * and calls the stop method, in order to prevent multiple songs playing simultaneously.
     * Then checks if there is a MediaPlayer instantiated and the song is paused, the play method can now be called.
     * Finally, if there is no MediaPlayer a new instance is created, in order to play the selected song.
     * It also called the next song method when the song has finished playing,
     * as well as keeping track of the current song's index in the list.
     ** @param song to be played.
     */
    public void playSelectedSong(String song){
        try {
            mediaPlayerStatus();
            media = new Media(new File(song).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(this::nextSong);
            mediaPlayer.play();
            songIndex = getSongIndex(song);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void playSelectedPlaylistSong(String song){
        try {
            mediaPlayerStatus();
            media = new Media(new File(song).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(this::nextPlaylistSong);
            mediaPlayer.play();
            songIndex = getPlaylistSongIndex(song);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mediaPlayerStatus(){
        if (mediaPlayer != null && mediaPlayer.getStatus()==MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.stop();
        }
        else if (mediaPlayer != null && mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED)
        {
            mediaPlayer.play();
        }
    }



    /**
     * If no song is selected,
     * calls the play selected song method on the first index.
     */
    public void playFirstSong(){
        int currentSongIndex = 0;
        String currentSongPath = getAllSongPaths().get(currentSongIndex);
        try {
            playSelectedSong(currentSongPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * If no song is selected,
     * calls the play selected playlist song method on the first index of the selected playlist.
     */
    public void playFirstSongInPlaylist(){
        int currentSongIndex = 0;
        String currentSongPath = getPlaylistSongPaths().get(currentSongIndex);
        try {
            playSelectedPlaylistSong(currentSongPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return filename of the song currently playing, with folder name and filetype trimmed off.
     */
    public String getCurrentSongTitle() {
        if (mediaPlayer != null) {
            String songTitle = getAllSongPaths().get(songIndex);
            return songTitle.substring(6,songTitle.length()-4);
        }
        return "No song selected";
    }

    /**
     * @return filename of the playlist song currently playing, with folder name and filetype trimmed off.
     */
    public String getCurrentPlaylistSongTitle() {
        if (mediaPlayer != null) {
            String playlistSongTitle = getPlaylistSongPaths().get(songIndex);
            return playlistSongTitle.substring(6, playlistSongTitle.length()-4);
        }
        return "No song selected";
    }

    /**
     * @param path of song.
     * @return the index of the song in the list.
     */
    public int getSongIndex(String path) {
        return getAllSongPaths().indexOf(path);
    }

    /**
     * @param path of song in playlist.
     * @return the index of the song in the playlist.
     */
    public int getPlaylistSongIndex(String path){
        return getPlaylistSongPaths().indexOf(path);
    }

    /**
     * If there is currently a MediaPlayer instantiated, pauses the song that is playing.
     */
    public void pauseSong() {
        try {
            if(mediaPlayer != null)
                mediaPlayer.pause();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * If there is currently a MediaPlayer instantiated, resets the program to the start.
     */
    public void stopSong() {
        try {
            if(mediaPlayer!=null) mediaPlayer.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Based on the current song index it goes back to the previous index and plays that song.
     * If there is no previous song to play it restarts the first song.
     */
    public void previousSong() {
        try {
            if (mediaPlayer != null) {

                if (songIndex > 0)
                    songIndex--;

                playSelectedSong(getAllSongPaths().get(songIndex));

            }
            } catch(Exception e){
                throw new RuntimeException(e);
            }
    }

    /**
     * Based on the current playlist song index it goes back to the previous index and plays that song.
     * If there is no previous song to play it restarts the first song in the chosen playlist.
     */
    public void previousPlaylistSong() {
        try {
            if (mediaPlayer != null) {

                if (songIndex > 0) {
                    songIndex--;
                }
                playSelectedPlaylistSong(getPlaylistSongPaths().get(songIndex));
            }

            } catch(Exception e){
                throw new RuntimeException(e);
            }
        }

    /**
     * Based on the current song index it goes to the next index and plays that song.
     * If there is no next song to play it starts playing the list from the first song again.
     */
    public void nextSong() {
        try {
            if (mediaPlayer != null) {

                if (songIndex < getAllSongPaths().size()-1) {
                    songIndex++;
                }
                else{
                    songIndex = 0;
                }
                playSelectedSong(getAllSongPaths().get(songIndex));
            }

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void nextPlaylistSong() {
        try {
            if (mediaPlayer != null) {

                if (songIndex < getPlaylistSongPaths().size()-1) {
                    songIndex++;
                }
                else{
                    songIndex = 0;
                }
                playSelectedPlaylistSong(getPlaylistSongPaths().get(songIndex));
            }

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Enables the volume to be set to a specific value,
     * but only if there is a MediaPlayer instantiated.
     * @param volume to be set from volume slider.
     */
    public void volumeIncrement(double volume){
        if(mediaPlayer!=null)
            mediaPlayer.setVolume(volume);
    }
    public void removePlaylist(Playlist selected) {
        playlistManager.removePlaylist(selected);
    }
    public void removeSongPassThrough(Song selected) {
        SongManager.getInstance().removeSong(selected);
    }

    /**
     *
     * @return the list of song ids from playlistManager.
     */
    public ObservableList<Song> getPlaylistSongs() {
        return playlistManager.getSelectedPlaylistSongs();
    }

    public void selectPlaylist(int id) {
        playlistManager.selectPlaylist(id);
    }

    public int getPlaylistId()
    {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
    public void removeOneSongFromPlaylist(Song selected) {
        playlistManager.removeOneSongFromPlaylist(selected);
    }

    public ObservableList<Song> searchListSongs(String query)
    {
            ObservableList<Song> songs = getMistressListAgain();
            List<Song> filtered = new ArrayList<>();

            for(Song s: songs){
                if(s.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                (s.getArtist().toLowerCase().contains(query.toLowerCase()) ||
                (s.getGenre().toLowerCase().contains(query.toLowerCase()))))
                    filtered.add(s);
            }
            return FXCollections.observableArrayList(filtered);
        }

    public void updateSongPassThrough(Song newSong) {
        SongManager.getInstance().updateSong(newSong);
    }
    public void createPlaylistPassThrough(String playlistName){

        playlistManager.createPlaylist(playlistName);

    }
    public void renamePlaylistPassThrough(String playlistName){
        playlistManager.playlistRename(playlistName);
    }

    public  void oldSongpassthrough(String oldName){
        playlistManager.getPlaylistName(oldName);
    }

}
