package LogicLayer;
import Datalayer.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.xml.datatype.Duration;
import java.io.File;


public class Player {

    String fileName ="songs/Kirk Osamayo - Wander To The Moon.mp3";
    Media media = new Media(new File(fileName).toURI().toString());
    //Media media = new Media(songs.get(songNumber).toURI().toString());
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
        if (volume == 0)
            mediaPlayer.setVolume(0);
        if(volume == 25)
            mediaPlayer.setVolume(0.25);
        if(volume == 50)
            mediaPlayer.setVolume(0.5);
        if(volume == 75)
            mediaPlayer.setVolume(0.75);
        if(volume == 100)
            mediaPlayer.setVolume(1.0);
    }
}

