package LogicLayer;

import entities.Song;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MusicManager {

    SongManager songManager = SongManager.getInstance();

    Media media;
    MediaPlayer mediaPlayer;
    private int songIndex;
    static Song currentSong = new Song();
    private String songTitle;


    /**
     * @return observable list of all the songs in the database.
     */
    public ObservableList<Song> getMistressListAgain(){
        return songManager.getMistressSongList();
    }

    public boolean isMediaPlayerNull(){
        return mediaPlayer == null;
    }


    /**
     * Creates a list of strings with the path of a given song.
     * @return songPath of the relevant song.
     */
    public List<String> getAllMP3Files()
    {
        List<Song> songs = getMistressListAgain();
        List<String> songPaths = new ArrayList<>();

        for(Song s : songs)
        {
            songPaths.add(s.getPath());
        }
        return songPaths;
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
            if (mediaPlayer != null && mediaPlayer.getStatus()==MediaPlayer.Status.PLAYING)
            {
                mediaPlayer.stop();
            }
            else if (mediaPlayer != null && mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED)
            {
                mediaPlayer.play();
                return;
            }
            media = new Media(new File(song).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(this::nextSong);
            mediaPlayer.play();
            songIndex = getSongIndex(song);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * If no song is selected,
     * calls the play selected song method on the default first index.
     */
    public void playFirstSong(){
        int currentSongIndex = 0;
        String currentSongPath = getAllMP3Files().get(currentSongIndex);
        try {
            playSelectedSong(currentSongPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param path
     * @return the index of the song in the list.
     */
    public int getSongIndex(String path)
    {
        return getAllMP3Files().indexOf(path);
    }

    /**
     *
     * @param query
     * @return
     */
    public ObservableList<Song> searchListSongs(String query){
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
            if(mediaPlayer!=null)
                mediaPlayer.stop();
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

                if (songIndex > 0) {
                    songIndex--;
                }
                playSelectedSong(getAllMP3Files().get(songIndex));
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

                if (songIndex < getAllMP3Files().size()-1) {
                    songIndex++;
                }
                else{
                    songIndex = 0;
                }
                playSelectedSong(getAllMP3Files().get(songIndex));
            }

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Enables the volume to be set to a specific value,
     * but only if there is a MediaPlayer instantiated.
     * @param volume
     */
    public void volumeIncrement(double volume){
        if(mediaPlayer!=null)
            mediaPlayer.setVolume(volume);
    }

    public void removeSongPassThrough(Song selected){
        SongManager.getInstance().removeSong(selected);
    }
    public void updateSongPassThrough(Song selected){
        SongManager.getInstance().updateSong(selected);
    }
}