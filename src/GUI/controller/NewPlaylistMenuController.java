package GUI.controller;

import LogicLayer.MusicManager;

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

    @FXML
    private TextField txtPlaylist;

    static String playlistname;
    MusicManager mm = new MusicManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPlaylist.clear();
        getPlaylistname(playlistname);
    }

    /**
     * Retrieves the current name of the playlist.
     */
    public void getOldPlaylistName(){
        mm.getPlaylistName(playlistname);
    }

    /**
     * Saves the changes the user makes to the playlist. Either when adding new playlists or editing the name of the playlist.
     * @param actionEvent
     */
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

    /**
     * Automatically enters the current playlist name to the edit box.
     * @param text
     */
    public void getPlaylistname(String text){
        if(isEditingPlaylist ==true){
            txtPlaylist.setText(text);
        } else{
            txtPlaylist.clear();
        }
    }

    /**
     * Enables the user to change their mind and cancels the action.
     * @param actionEvent
     */
    @FXML
    private void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
