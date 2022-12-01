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


    private double songProgress = player.getSongProgress();
    private double songLength = player.getSongLength();
    private double currentPercentProgress = songProgress/songLength;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    /**
     *
     * @FXML
    private void bindProgressBar(){
        progressBar.progressProperty().bind(
                Bindings.createDoubleBinding(() -> player.getSongProgress() / player.getSongLength()
        ));
    }

    @FXML
    private void setValueProgressBar(){
        DoubleProperty num1 = new SimpleDoubleProperty(songProgress);
        DoubleProperty num2 = new SimpleDoubleProperty(songLength);
        NumberBinding result = Bindings.divide(num1,num2);
        num1.setValue(num2);
    }
     */

    @FXML
    private void dragVolumeSlider(MouseEvent mouseEvent) {
        player.volumeIncrement(volumeSlider.getBlockIncrement());
        //do i need a listener?
    }
}
