package GUI.controller;

import LogicLayer.InputManager;
import LogicLayer.MusicManager;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class EditSongMenuController implements Initializable {
    @FXML
    private TextField txtSongEditYear, txtSongEditTitle, txtSongEditArtist;
    @FXML
    private ChoiceBox choiceEditGenre;

    static Song selected = new Song();

    ObservableList<String> genres = FXCollections.observableArrayList("Pop", "Rock", "Disco", "Metal", "Country", "Classical", "Country", "Jazz", "Blues", "Hip hop", "Techno", "Folk");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

     choiceEditGenre.getItems().addAll(genres);
        autofill(selected);
    }

    /**
     * Looks at what song is selected to input the values that song already has.
     * @param song
     */
    public void autofill(Song song){
     txtSongEditTitle.setText(song.getTitle());
     txtSongEditYear.setText(String.valueOf(song.getYear()));
     txtSongEditArtist.setText(song.getArtist());
     choiceEditGenre.setValue(song.getGenre());
    }

    /**
     * Updates the selected song with values from the text fields
     * @return
     */
    private Song upddateSong(){InputManager ipc = new InputManager();
       String newTitle = txtSongEditTitle.getText();
       String newArtist = txtSongEditArtist.getText();
       int year = ipc.checkInput(txtSongEditYear.getText());
       String newGenre = choiceEditGenre.getValue().toString();

        selected.setTitle(newTitle);
        selected.setYear(year);
        selected.setArtist(newArtist);
        selected.setGenre(newGenre);
        return selected;
    }

    /**
     * Passes the updated song down the system and closes the window.
     * @param actionEvent
     */
    public void clickSaveEdit(ActionEvent actionEvent) {
        MusicManager musicManager = new MusicManager();
        Song newSong = upddateSong();
        musicManager.updateSongPassThrough(newSong);

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Enables the user to change their mind and cancels the action.
     * @param actionEvent
     */
    public void clickCancelEdit(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}

