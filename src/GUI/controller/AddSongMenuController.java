package GUI.controller;

import LogicLayer.InputChecker;
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
    private TextField txtYear;
    @FXML
    private TextField txtFile;
    @FXML
    ChoiceBox<String> choiceBox;
    ObservableList<String> genres = FXCollections.observableArrayList("Pop", "Rock", "Disco", "Metal", "Country", "Classical", "Country", "Jazz", "Blues", "Hip hop", "Techno", "Folk");


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

            SongManager sm = new SongManager();
            String title = txtTitle.getText();
            int year = checkImput(txtYear.getText());
            String artist = txtArtist.getText();
            String genre = choiceBox.getValue();

            String path = txtFile.getText();

            sm.addSong(title, year, artist, genre, path);

            txtTitle.clear();
            txtArtist.clear();
            txtYear.clear();

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();

    }

   private int checkImput(String year){
       InputChecker iC = new InputChecker();
       if(iC.checkYear(year)==true){
           return Integer.parseInt(year);
       }
       return 0;
   }
    @FXML
    TextField txtErrorMessage = new TextField();

    public void yearError() {

        this.txtErrorMessage.setOpacity(100);
        this.txtErrorMessage.setText("invaild year, must be a vaild number");
    }

    // looks through files only mp3 works, should really be changed to non jfilechooser and make the chooser window be the child of the main window
    public void clickChoose(ActionEvent actionEvent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MP3 FILES", "mp3");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            txtFile.setText(chooser.getSelectedFile().toString());
            txtFile.setEditable(false);
            autoInput(chooser.getSelectedFile().toString());
        }

    }
    //trys to read the selected file and input the results into the add new song UI
    public void autoInput(String filepath){
        InputChecker iC = new InputChecker();
        txtArtist.setText(iC.getSongArtist(filepath));
        txtYear.setText(iC.getSongReleaseYear(filepath));
        txtTitle.setText(iC.getSongTitle(filepath));
        choiceBox.setValue(iC.getSongGenre(filepath)); // needs work
    }
}
