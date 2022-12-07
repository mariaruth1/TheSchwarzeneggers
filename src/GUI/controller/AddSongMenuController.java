package GUI.controller;

import LogicLayer.InputChecker;
import LogicLayer.SongManager;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
        if(checkImput()==true){
            SongManager sm = new SongManager();
            String title = txtTitle.getText();
            String year = txtYear.getText();
            int numberYear = Integer.parseInt(year);
            String artist = txtArtist.getText();
            String genre = choiceBox.toString();
            String path = txtFile.getText();

            sm.addSong(title, numberYear, artist, genre, path);

            txtTitle.clear();
            txtArtist.clear();
            txtYear.clear();

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }

   private boolean checkImput(){
       InputChecker iC = new InputChecker();
       boolean titleTest = iC.checkTitle(txtTitle.getText());
       boolean yearTest = iC.checkYear(txtYear.getText());
       boolean artistTest = iC.checkArtist(txtArtist.getText());

       if(titleTest == true && (artistTest == true && yearTest == true)){
           return true;
       }
       return false;
   }
    @FXML
    private Label lbTitleError;
    @FXML
    private Label lbArtistError;
    @FXML
    private Label lbYearError;

    @FXML
    private TextField txtErrorMessage;
    public void titleError() {
    lbTitleError.setOpacity(1);
        txtErrorMessage.setOpacity(1);
    txtErrorMessage.setText("invaild title");
    }
    public void artistError() {
    lbArtistError.setOpacity(1);
        txtErrorMessage.setOpacity(1);
        txtErrorMessage.setText("invaild artist");
    }
    public void yearError() {
    lbYearError.setOpacity(1);
        txtErrorMessage.setOpacity(1);
        txtErrorMessage.setText("invaild year, must be a vaild number");
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
