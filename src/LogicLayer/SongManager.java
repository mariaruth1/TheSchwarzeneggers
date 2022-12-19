package LogicLayer;


import Datalayer.SongDAO;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SongManager {
    private static SongManager instance=null;
    private SongDAO songDAO = new SongDAO();

    private final ObservableList<Song> mistressSongList;

    /**
     * Turns the SongManager class into a singleton class.
     * @return
     */
    public static SongManager getInstance(){
        if(instance == null){
            instance = new SongManager();
        } return instance;
    }

    /**
     * Makes the mistress song list observable.
     */
    public SongManager() {
        mistressSongList = FXCollections.observableArrayList();
    }

    /**
     * Adds all the songs to the observable list.
     */
    public void getAllSongs(){
        mistressSongList.clear();
        mistressSongList.addAll(songDAO.getAllSongs());
    }

    /**
     * @return an observable list with all songs added.
     */
    public ObservableList<Song> getMistressSongList(){
        getAllSongs();
        return mistressSongList;
    }

    /**
     * Moves the selected file into the project folder "songs".
     * @param inputPath
     * @return
     */
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

    /**
     * Adds a song to the database and mistress song list, and moves the song to the project folder "songs".
     * @param title
     * @param year
     * @param artist
     * @param genre
     * @param filePath
     */
    public void addSong(String title, int year, String artist, String genre, String filePath) {
        String newpath = moveFile(filePath);
        mistressSongList.add(new Song(title, year, artist,genre,newpath));
        songDAO.addSongToDataBase(title, year,artist,genre,newpath);
    }

    /**
     * Removes a song from the database and the mistress song list.
     * @param song
     */
    public void removeSong(Song song){
    songDAO.removeFromDataBase(song);
    mistressSongList.remove(song);
    }

    /**
     * Updates song details based on user input.
     * @param song
     */
    public void updateSong(Song song){
        songDAO.updateSong(song);
    }
}




