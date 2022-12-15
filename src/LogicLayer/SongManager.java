package LogicLayer;


import Datalayer.SongDAO;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SongManager {
    private static SongManager instance=null;
    private SongDAO songDAO = new SongDAO();

    private final ObservableList<Song> mistressSongList;

    // Makes it so it is only possible to have one or zero instance of this class at all time
    public static SongManager getInstance(){
        if(instance == null){
            instance = new SongManager();
        } return instance;
    }

    private SongManager() {
        mistressSongList = FXCollections.observableArrayList();
    }



    public void getAllSongs(){
        mistressSongList.clear();
        mistressSongList.addAll(songDAO.getAllSongs());
    }

    public ObservableList<Song> getMistressSongList(){
        getAllSongs();
        return mistressSongList;
    }


    public List<Song> searchSongs(String query) {

        throw new RuntimeException();
    }


    private String moveFile(String inputPath) {
        File f = new File(inputPath);
        String songName = f.getName();
        String outputPath = ("songs/" + songName);
        try {
            Files.move(Path.of(inputPath), Path.of(outputPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputPath;
    }

    public void addSong(String title, int year, String artist, String genre, String filePath) {
        String newpath = moveFile(filePath);
        mistressSongList.add(new Song(title, year, artist,genre,newpath));
        songDAO.addSongToDataBase(title, year,artist,genre,newpath);

    }
    public void removeSong(Song song){
    songDAO.removeFromDataBase(song);
    mistressSongList.remove(song);
    }
    public void updateSong(Song song){
        songDAO.updateSong(song);

    }
}




