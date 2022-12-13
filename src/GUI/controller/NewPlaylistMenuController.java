package GUI.controller;

import LogicLayer.PlaylistManager;
import entities.Playlist;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistMenuController {
    private ObservableList<Playlist> playlists;
    PlaylistManager pm = new PlaylistManager(playlists);
    @FXML
    private TextField txtPlaylist;
    @FXML
    private void clickSave(ActionEvent actionEvent) {
        {
            pm.createPlaylist(txtPlaylist.getText());
            txtPlaylist.clear();
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void clickCancel(ActionEvent actionEvent) {

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
