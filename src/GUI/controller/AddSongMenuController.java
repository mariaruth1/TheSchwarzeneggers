package GUI.controller;

import LogicLayer.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSongMenuController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtFile;
    @FXML
    ChoiceBox<String> choiceBox;
    ObservableList<String> genres = FXCollections.observableArrayList("Pop", "Rock", "Disco", "Metal", "Country", "Classical", "Country", "Jazz", "Blues", "Hip hop", "Techno", "Folk");
   SongManager songManager = new SongManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.getItems().addAll(genres);
    }

    @FXML
    private void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void clickSave(ActionEvent actionEvent) {
        songManager.addSong(txtTitle.getText(), txtArtist.getText(), choiceBox.getSelectionModel().getSelectedItem());
        txtTitle.clear();
        txtArtist.clear();
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    // looks through files only mp3 works
    public void clickChoose(ActionEvent actionEvent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MP3 FILES", "mp3");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            txtFile.setText(chooser.getSelectedFile().toString());
            txtFile.setEditable(false);
        }
    }
}
