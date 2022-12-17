package GUI.controller;

import LogicLayer.MusicManager;
import LogicLayer.PlaylistManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static GUI.controller.MyTunesController.isEditingPlaylist;


public class NewPlaylistMenuController implements Initializable {

    static String playlistname;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPlaylist.clear();
        getPlaylistname(playlistname);
    }
    public void getOldPlaylistName(){
        mm.oldSongpassthrough(playlistname);
    }

    MusicManager mm = new MusicManager();

    @FXML
    private TextField txtPlaylist;

    @FXML
    private void clickSave(ActionEvent actionEvent) {

        if (txtPlaylist.getText() != null) {
            if (isEditingPlaylist == false) {
                mm.createPlaylistPassThrough(txtPlaylist.getText());
                txtPlaylist.clear();
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            } else {
                getOldPlaylistName();
                mm.renamePlaylistPassThrough(txtPlaylist.getText());
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void getPlaylistname(String text){
        if(isEditingPlaylist ==true){
            txtPlaylist.setText(text);
        } else{
            txtPlaylist.clear();
        }
    }

    @FXML
    private void clickCancel(ActionEvent actionEvent) {

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


}
