package GUI.controller;

import GUI.model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistMenuController {
    SongModel model = new SongModel();
    @FXML
    private TextField txtPlaylist;
    @FXML
    private void clickSave(ActionEvent actionEvent) {
        {
            model.createPlaylist(txtPlaylist.getText());
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
