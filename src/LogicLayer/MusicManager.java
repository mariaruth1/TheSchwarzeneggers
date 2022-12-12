package LogicLayer;

import entities.Song;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.List;


public class MusicManager {

    SongManager songManager = SongManager.getInstance();

    /*String fileName = "songs/Kirk Osamayo - Wander To The Moon.mp3";
    Media media = new Media(new File(fileName).toURI().toString());

    Media media = new Media(getMistressListAgain().get(songManager.getPath().toURI().toString());*/

    String filename = getAllMP3Files();
    Media media = new Media(new File(filename).toURI().toString());

    MediaPlayer mediaPlayer = new MediaPlayer(media);
    public String getAllMP3Files()
    {
        List<Song> songs = songManager.getMistressSongList();
        String songPath = "";

        for(Song s : songs)
        {
            songPath = s.getPath();
        }
        return songPath;
    }


    public double getSongProgress(){
        return mediaPlayer.getCurrentTime().toMillis();
    }

    public double getSongLength(){
        return mediaPlayer.getStopTime().toMillis();
    }

    public void playCurrentSong(String song){
        try {
            mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        return media.getSource(); //placeholder implementation
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