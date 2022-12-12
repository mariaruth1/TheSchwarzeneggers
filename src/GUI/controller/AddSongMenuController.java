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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.awt.*;


import java.io.File;
import java.net.URL;
import java.util.Optional;
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

            SongManager sm = SongManager.getInstance();
            String title = txtTitle.getText();
            int year = checkImput(txtYear.getText());
            String artist = txtArtist.getText();
            String genre = choiceBox.getValue();

            String path = txtFile.getText();

            sm.addSong(title, year, artist, genre, path);
            MyTunesController mtc = new MyTunesController();

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
        this.txtErrorMessage.setText(" year must be a number");
    }

    //lets you select a file on your pc when you click on the chooser button,
    public void clickChoose(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select song");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio", "*.mp3"));

        //the default folder you start in the is your default music folder.
        String userprofile = System.getenv("USERPROFILE");
        chooser.setInitialDirectory(new File(userprofile +"\\music"));

        Node n = (Node) actionEvent.getSource();
        Window stage = n.getScene().getWindow();

        //this will put the full path into the path text field.
        File selectedFile = chooser.showOpenDialog(stage);
        if (selectedFile != null) {
            txtFile.setText(selectedFile.getAbsolutePath().toString());
            txtFile.setEditable(false);
            autoInput(selectedFile.getAbsolutePath().toString());
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
