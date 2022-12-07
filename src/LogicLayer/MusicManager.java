package LogicLayer;

import entities.Song;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class MusicManager {
    Song song = new Song();
    SongManager songManager = new SongManager();


    String fileName = "songs/Jenna Jay - Someone Real - Jenna Jay.mp3";
    Media media = new Media(new File(fileName).toURI().toString());
    //Media media = new Media(getMistressListAgain().get(song.getPath()).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    public double getSongProgress(){
        return mediaPlayer.getCurrentTime().toMillis();
    }

    public double getSongLength(){
        return mediaPlayer.getStopTime().toMillis();
    }

    public void playSong(){
        try {
            mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentSong(){
        return fileName; //placeholder implementation
    }

    public ObservableList<Song> getMistressListAgain(){
        return songManager.getMistressSongList();
    }

    public void pauseSong() {
        try {
            mediaPlayer.pause();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void stopSong() {
        try {
            mediaPlayer.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void previousSong() {
        try {
            mediaPlayer.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void nextSong() {
        try {
            mediaPlayer.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void volumeIncrement(double volume){
        mediaPlayer.setVolume(volume);
    }
}