package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistMenuController {
    @FXML
    private TextField txtPlaylist;
    @FXML
    private void clickSave(ActionEvent actionEvent) {
    }

    @FXML
    private void clickCancel(ActionEvent actionEvent) {

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
