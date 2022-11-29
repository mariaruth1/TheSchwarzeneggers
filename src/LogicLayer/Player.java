package LogicLayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Player {
    String fileName = "songs/Kirk Osamayo - Wander To The Moon.mp3";
    Media media = new Media(new File(fileName).toURI().toString());
    //media = new Media(songs.get(songNumber).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);




    public void playSong(){
        try {
            mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

}

