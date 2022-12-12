package LogicLayer;

import entities.Song;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MusicManager {

    SongManager songManager = SongManager.getInstance();

    Media media;

    /*Media media = new Media(getMistressListAgain().get(songManager.getPath().toURI().toString());

    String filename = getAllMP3Files();
    Media media = new Media(new File(filename).toURI().toString()); */

    MediaPlayer mediaPlayer;
    int songIndex;

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


    public ReadOnlyObjectProperty<Duration> getSongProgress(){
        return mediaPlayer.currentTimeProperty();
    }

    public double getSongLength(){
        return mediaPlayer.getTotalDuration().toMillis();
    }

    public void playSelectedSong(String song){
        try {
            if (mediaPlayer != null && mediaPlayer.getStatus()==MediaPlayer.Status.PLAYING)
            {mediaPlayer.stop();}
            else if (mediaPlayer != null && mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED)
            {
                mediaPlayer.play();
                return;
            }

            media = new Media(new File(song).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() ->
            {
                nextSong();
            });
            mediaPlayer.play();
            songIndex = getSongIndex(song);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void playFirstSong(){
        int currentSongIndex = 0;
        String currentSongPath = getAllMP3Files().get(currentSongIndex);
        try {
            playSelectedSong(currentSongPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public String getCurrentSong(){
        return media.getSource(); //placeholder implementation
    }*/

    public int getSongIndex(String path)
    {
        return getAllMP3Files().indexOf(path);
    }

    public ObservableList<Song> getMistressListAgain(){
        return songManager.getMistressSongList();
    }

    public void pauseSong() {
        try {
            if(mediaPlayer != null) mediaPlayer.pause();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void stopSong() {
        try {
            if(mediaPlayer!=null) mediaPlayer.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void previousSong() {
        int nextSongIndex = songIndex-1;
        String previousSongPath = getAllMP3Files().get(nextSongIndex);
        try {
            playSelectedSong(previousSongPath);
            System.out.println(nextSongIndex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void nextSong() {
        int nextSongIndex = songIndex+1;
        String nextSongPath = getAllMP3Files().get(nextSongIndex);
        try {
            playSelectedSong(nextSongPath);
            System.out.println(nextSongIndex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void volumeIncrement(double volume){
        mediaPlayer.setVolume(volume);
    }
}