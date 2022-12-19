package GUI.controller;

import LogicLayer.MusicManager;
import entities.Song;
import entities.Playlist;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static GUI.controller.NewPlaylistMenuController.playlistname;


public class MyTunesController implements Initializable {

    @FXML
    private TableView<Song> songListTable;
    @FXML
    private TableView<Song> playListTable;
    @FXML
    private TableColumn<Song, String> columnTitle, columnArtist, columnGenre;
    @FXML
    private TableColumn<Song, Integer> columnYear;
    @FXML
    private TableColumn<Song, String> columnPlaylistSongTitle, columnPlaylistSongArtist, columnPlaylistSongGenre;
    @FXML
    private TableColumn<Song, Integer> columnPlaylistSongYear;
    @FXML
    private ProgressBar progressBar;
    @FXML
    ChoiceBox<Playlist> playlistChoicebox;
    @FXML
    private Label txtNowPlaying;
    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btnCreateNewSong;
    @FXML
    private ImageView btnPlay, btnPause, btnReset, btnPrevious, btnNext, btnAddSongToPlaylist;

    MusicManager musicManager = new MusicManager();

    static boolean isPlaylistSelected = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songListTable.setEditable(true);
        playListTable.setEditable(true);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
            double volume = volumeSlider.getValue()/100;
            musicManager.volumeIncrement(volume);
            }
        });

        //add back text here

        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                songListTable.getItems().clear();
                songListTable.setItems(musicManager.searchListSongs(newValue));
            }
        });

        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        songListTable.setItems(musicManager.getMistressListAgain());


        playlistChoicebox.setItems(musicManager.getPlaylistAgain());
        playlistChoicebox.getSelectionModel().selectedItemProperty().addListener((options,oldValue, newValue)->
        {
            musicManager.setPlaylistId(newValue.getId());
            musicManager.selectPlaylist(newValue.getId());
            playListTable.refresh();
            playListTable.setItems(musicManager.getPlaylistSongs());
        });

        columnPlaylistSongTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnPlaylistSongArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        columnPlaylistSongYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnPlaylistSongGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        playListTable.setItems(musicManager.getPlaylistSongs());
    }

    private void setProgressBar() {
        if (musicManager.isMediaPlayerNull()) {
            return;
        }
        musicManager.getSongProgress().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                if (musicManager.isMediaPlayerNull()) {
                    return;
                }
                progressBar.setProgress(newValue.toMillis() / musicManager.getSongLength());
            }
        });
    }


    @FXML
    private void clickPlay(MouseEvent mouseEvent) {
        Song selectedSong = songListTable.getSelectionModel().getSelectedItem();
        Song selectedSongInPlaylist = playListTable.getSelectionModel().getSelectedItem();
        String selectedSongPath;
        if (!isPlaylistSelected) {
            if (selectedSong == null) {
                musicManager.playFirstSong();
            }
            else {
                selectedSongPath = selectedSong.getPath();
                musicManager.stopSong();
                musicManager.playSelectedSong(selectedSongPath);
            }
            txtNowPlaying.setText(musicManager.getCurrentSongTitle());
        }
        else {
            if (selectedSongInPlaylist == null) {
                musicManager.stopSong();
                musicManager.playFirstSongInPlaylist();
            } else {
                selectedSongPath = selectedSongInPlaylist.getPath();
                musicManager.stopSong();
                musicManager.playSelectedPlaylistSong(selectedSongPath);
            }
            txtNowPlaying.setText(musicManager.getCurrentPlaylistSongTitle());
        }
        setProgressBar();
    }


    @FXML
    private void clickPause(MouseEvent mouseEvent) {
        musicManager.pauseSong();
    }

    @FXML
    private void clickReset(MouseEvent mouseEvent) {
        musicManager.stopSong();
        txtNowPlaying.setText("No song selected");
        setProgressBar();
    }

    @FXML
    private void clickPrevious(MouseEvent mouseEvent) {
        if (!isPlaylistSelected) {
            musicManager.previousSong();
            txtNowPlaying.setText(musicManager.getCurrentSongTitle());
        }
        else {
            musicManager.previousPlaylistSong();
            txtNowPlaying.setText(musicManager.getCurrentPlaylistSongTitle());
        }
        setProgressBar();
    }

    @FXML
    private void clickNext(MouseEvent mouseEvent) {
        if (!isPlaylistSelected) {
            musicManager.nextSong();
            txtNowPlaying.setText(musicManager.getCurrentSongTitle());
        }
        else {
            musicManager.nextPlaylistSong();
            txtNowPlaying.setText(musicManager.getCurrentPlaylistSongTitle());
        }
        setProgressBar();
    }

    @FXML
    private void clickShuffle(ActionEvent actionEvent) {
        //TODO
        throw new RuntimeException();
    }

    public void clickCreateNewSong(ActionEvent actionEvent){

            Node n = (Node) actionEvent.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/AddSongMenu.fxml"));
                Stage addSongWindow = new Stage();
                addSongWindow.setScene(new Scene(root));
                addSongWindow.setTitle("Add Songs");
                addSongWindow.initOwner(stage);
                addSongWindow.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void clickDeleteSong(ActionEvent actionEvent) {
        if(songListTable.getSelectionModel().getSelectedItem()!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this song from the program? The file will still be on the computer", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Song selected = songListTable.getSelectionModel().getSelectedItem();
                musicManager.removeSongPassThrough(selected);
            }
        }
    }


    public void clickEditSongDetails(ActionEvent actionEvent) {
        if(songListTable.getSelectionModel().getSelectedItem()!=null) {
            Node n = (Node) actionEvent.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                EditSongMenuController.selected = songListTable.getSelectionModel().getSelectedItem();
                root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/EditSongMenu.fxml"));
                Stage addSongWindow = new Stage();
                addSongWindow.setScene(new Scene(root));
                addSongWindow.setTitle("Edit Song");
                addSongWindow.initOwner(stage);
                addSongWindow.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static boolean isEditingPlaylist = false;
    public void clickCreatePlaylist(ActionEvent actionEvent) {
        isEditingPlaylist=false;
            Node n = (Node) actionEvent.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                playlistname = "";
                root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/NewPlaylistMenu.fxml"));
                Stage addPlaylistWindow = new Stage();
                addPlaylistWindow.setScene(new Scene(root));
                addPlaylistWindow.setTitle("Name your new playlist");
                addPlaylistWindow.initOwner(stage);
                addPlaylistWindow.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void clickEditPlaylistName(ActionEvent actionEvent) {
        if(playlistChoicebox.getValue()!=null) {
            isEditingPlaylist = true;
            Node n = (Node) actionEvent.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                playlistname = playlistChoicebox.getValue().toString();
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/NewPlaylistMenu.fxml"));
                    Stage editPlaylistWindow = new Stage();
                    editPlaylistWindow.setScene(new Scene(root));
                    editPlaylistWindow.setTitle("Rename playlist");
                    editPlaylistWindow.initOwner(stage);
                    editPlaylistWindow.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    /** First checks if the playlistChoicebox is not null.
     * If not the code under will execute.
     * Here we need the information of the selected playlist, we pass through our code.
     */
    public void clickDeletePlaylist(ActionEvent actionEvent) {
        if(playlistChoicebox.getSelectionModel().getSelectedItem()!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this playlist?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Playlist selected = playlistChoicebox.getSelectionModel().getSelectedItem();
                musicManager.removePlaylist(selected);
            }
        }
    }
    /**
     * Checks if an item on songListTable and playlistChoicebox has been selected.
     * If so executes the code under. Gets the id from the selected items and passes them through musicManager and so on.
     */
    public void clickAddSongToPlaylist(MouseEvent mouseEvent) {
        Song selectedSong = songListTable.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = playlistChoicebox.getSelectionModel().getSelectedItem();
        int song_id;
        int playlist_id;
        if (selectedSong!=null && selectedPlaylist!=null)
        {
            song_id = selectedSong.getId();
            playlist_id = selectedPlaylist.getId();
            musicManager.addSongToPlaylistAgain(playlist_id, song_id);
        }
    }


    @FXML
    private void clickSongTable(MouseEvent mouseEvent) {
        isPlaylistSelected = false;
    }

    @FXML
    private void clickPlaylistTable(MouseEvent mouseEvent) {
        isPlaylistSelected = true;
    }

    @FXML
    private void clickChoicebox(MouseEvent mouseEvent) {
        isPlaylistSelected = true;
    }
    /**
     * Checks if playlistChoicebox and a selected item from the playlistTable is not null.
     * Then executes the code under.
     * Here we need the information of the selected song, we pass through our code.
     */
    public void clickRemoveSongFromPlaylist(ActionEvent actionEvent) {
        if(playlistChoicebox.getSelectionModel().getSelectedItem()!=null && playListTable.getSelectionModel().getSelectedItem()!=null) {
            Song selected = playListTable.getSelectionModel().getSelectedItem();
            musicManager.removeOneSongFromPlaylist(selected);
        }
    }


}
