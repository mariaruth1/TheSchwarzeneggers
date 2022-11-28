package LogicLayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Player {

    String fileName = "songs/Kirk Osamayo - Wander To The Moon.mp3";
    Media media = new Media(new File(fileName).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    public void playSong(){
        try {
            mediaPlayer.setAutoPlay(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
