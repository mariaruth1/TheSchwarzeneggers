package LogicLayer;
import Datalayer.SongDAO;
import entities.Song;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.List;


public class Player {
    Song song = new Song();
    SongDAO songDAO = new SongDAO();

    String fileName = "songs/Jenna Jay - Someone Real - Jenna Jay.mp3";
    Media media = new Media(new File(fileName).toURI().toString());
    //Media media = new Media(songs.get(songNumber).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    public List<Song> getAllSongs(){
        return songDAO.getAllSongs();
    }

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
        mediaPlayer.setVolume(volume);
    }
}