package GUI.controller;
import LogicLayer.MusicManager;
import LogicLayer.SongManager;
import entities.Song;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {


    @FXML
    private TableView<Song> songListTable;
    @FXML
    private TableColumn<Song, String> columnTitle, columnArtist, columnGenre;
    @FXML
    private TableColumn<Song, Integer> columnYear;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label txtNowPlaying;
    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btnPlay, btnPause, btnStop, btnPrevious, btnNext;


    MusicManager musicManager = new MusicManager();


    private double songProgress = musicManager.getSongProgress();
    private double songLength = musicManager.getSongLength();
    private double currentPercentProgress = songProgress/songLength;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
            double volume = volumeSlider.getValue()/100;
            musicManager.volumeIncrement(volume);
            }
        });
        txtNowPlaying.setText(musicManager.getCurrentSong());

        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        songListTable.setItems(musicManager.getMistressListAgain());
    }


    @FXML
    private void clickPlay(ActionEvent actionEvent) {
        musicManager.playSong();
    }

    @FXML
    private void clickPause(ActionEvent actionEvent) {
        musicManager.pauseSong();
    }

    @FXML
    private void clickStop(ActionEvent actionEvent) {
        musicManager.stopSong();
    }

    @FXML
    private void clickPrevious(ActionEvent actionEvent) {
        musicManager.previousSong();
    }

    @FXML
    private void clickNext(ActionEvent actionEvent) {
        musicManager.nextSong();
    }

    public void createNewSong(ActionEvent actionEvent) {
    }

    public void clickCreateNewSong(ActionEvent actionEvent) {
    }

    public void clickDelteSong(ActionEvent actionEvent) {
    }

    public void clickEditSongDetails(ActionEvent actionEvent) {
    }

    public void clickCreatePlaylist(ActionEvent actionEvent) {
    }

    public void clickDeletePlaylist(ActionEvent actionEvent) {
    }

    public void clickReset(ActionEvent actionEvent) {
    }
}
