import GUI.controller.MyTunesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import java.util.Objects;

public class MyTunes extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GUI/view/NewPlaylistMenu.fxml")));
        Scene scene = new Scene(root);
        //ProgressBar progressBar = new ProgressBar();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MyTunes");
        stage.centerOnScreen();
        stage.show();
    }


    public static void main (String[] args) {
        launch();
    }
}