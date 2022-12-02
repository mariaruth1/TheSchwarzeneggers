package GUI.controller;
import LogicLayer.Player;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {

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

    Player player = new Player();


    private double songProgress = player.getSongProgress();
    private double songLength = player.getSongLength();
    private double currentPercentProgress = songProgress/songLength;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
            double volume = volumeSlider.getValue()/100;
            player.volumeIncrement(volume);
            }
        });
        txtNowPlaying.setText(player.getCurrentSong());

    }


    @FXML
    private void clickPlay(ActionEvent actionEvent) {
        player.playSong();
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
}
