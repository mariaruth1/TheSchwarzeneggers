package GUI.controller;
import LogicLayer.MusicManager;
import entities.Song;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
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
        Song selectedSong = songListTable.getSelectionModel().getSelectedItem();
        String selectedSongPath = "";
        if (selectedSong == null){
        musicManager.playSong();
        }
        else
        {
            selectedSongPath = selectedSong.getPath();
            musicManager.playCurrentSong(selectedSongPath);
        }
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
    Button btnCreateNewSong;

    @FXML
    private void clickNext(ActionEvent actionEvent) {
        musicManager.nextSong();
    }

    public void createNewSong(ActionEvent actionEvent) {

    }

    public void clickCreateNewSong(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Window stage = n.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/AddSongMenu.fxml"));
            Stage addSongWindow = new Stage();
            addSongWindow.setScene(new Scene(root));
            addSongWindow.setTitle("Add Songs");
            addSongWindow.initOwner(stage);
            addSongWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickDeleteSong(ActionEvent actionEvent) {
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
