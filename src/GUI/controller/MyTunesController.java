package GUI.controller;

import LogicLayer.MusicManager;
import entities.Song;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
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
    private Button btnPlay, btnPause, btnReset, btnPrevious, btnNext, btnCreateNewSong;

    MusicManager musicManager = new MusicManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
            double volume = volumeSlider.getValue()/100;
            musicManager.volumeIncrement(volume);
            }
        });

        txtNowPlaying.setText(musicManager.getCurrentSongTitle());

        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        songListTable.setItems(musicManager.getMistressListAgain());

    }

    private void setProgressBar() {
        musicManager.getSongProgress().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                progressBar.setProgress(newValue.toMillis() / musicManager.getSongLength());
            }
        });
    }


    @FXML
    private void clickPlay(ActionEvent actionEvent) {
        Song selectedSong = songListTable.getSelectionModel().getSelectedItem();
        String selectedSongPath = "";
        if (selectedSong == null){
        musicManager.playFirstSong();
        setProgressBar();
        }
        else
        {
            selectedSongPath = selectedSong.getPath();
            System.out.println(selectedSongPath);
            musicManager.playSelectedSong(selectedSongPath);
            setProgressBar();
        }
    }

    @FXML
    private void clickPause(ActionEvent actionEvent) {
        musicManager.pauseSong();
    }

    @FXML
    private void clickReset(ActionEvent actionEvent) {
        musicManager.stopSong();
        setProgressBar();
    }

    @FXML
    private void clickPrevious(ActionEvent actionEvent) {
        musicManager.previousSong();
        setProgressBar();
    }

    @FXML
    private void clickNext(ActionEvent actionEvent) {
        musicManager.nextSong();
        setProgressBar();
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
}
