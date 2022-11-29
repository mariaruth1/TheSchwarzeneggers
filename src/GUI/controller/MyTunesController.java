package GUI.controller;
import LogicLayer.Player;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    @FXML
    private void dragVolumeSlider(MouseEvent mouseEvent) {

    }

}
