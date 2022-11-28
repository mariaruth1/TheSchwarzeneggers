package GUI.controller;
import entities.Song;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Call to database here?
    }


    public void clickPlay(ActionEvent actionEvent) {
        //Media and media-player class - play media
    }
}
