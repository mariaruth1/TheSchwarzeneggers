package GUI.controller;
import LogicLayer.Player;
import entities.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {

    public ProgressBar progressBar;
    public Label txtNowPlaying;
    public Slider volumeSlider;
    public TextField searchBar;
    public Button btnPlay;
    public Button btnPause;
    public Button btnPrevious;
    public Button btnNext;
    private ListView<Song> lstSongs;


    Player player = new Player();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Call to database here?
    }


    @FXML
    private void clickPlay(ActionEvent actionEvent) {
        player.playSong();
        progressBar.setProgress(player.songProgress());
    }

    @FXML
    private void clickPause(ActionEvent actionEvent) {
        player.pauseSong();
    }

    @FXML
    private void clickStop(ActionEvent actionEvent) {
        player.stopSong();
    }

    @FXML
    private void clickPrevious(ActionEvent actionEvent) {
        player.previousSong();
    }

    @FXML
    private void clickNext(ActionEvent actionEvent) {
        player.nextSong();
    }

    public void dragVolumeSlider(MouseEvent mouseEvent) {
        //TODO implement method
    }
}
