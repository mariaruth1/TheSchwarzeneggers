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
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
    private Button btnPlay, btnPause, btnReset, btnPrevious, btnNext, btnCreateNewSong, btnAddSongToPlaylist, btnRemoveSongFromPlaylist;


    MusicManager musicManager = new MusicManager();

    static boolean isPlaylistSelected = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songListTable.setEditable(true);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
            double volume = volumeSlider.getValue()/100;
            musicManager.volumeIncrement(volume);
            }
        });

        //txtNowPlaying.setText(musicManager.getCurrentSongTitle());

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
            playListTable.setItems(musicManager.getSelectedPlaylistSongs());
        });

        columnPlaylistSongTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnPlaylistSongArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        columnPlaylistSongYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnPlaylistSongGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        playListTable.setItems(musicManager.getSelectedPlaylistSongs());
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
    private void clickPlay(ActionEvent actionEvent) {
        if (isPlaylistSelected == false) {
            Song selectedSong = songListTable.getSelectionModel().getSelectedItem();
            String selectedSongPath = "";
            if (selectedSong == null) {
                musicManager.playFirstSong();
                setProgressBar();
            } else {
                selectedSongPath = selectedSong.getPath();
                System.out.println(selectedSongPath);
                musicManager.playSelectedSong(selectedSongPath);
                setProgressBar();
            }
        }
        else
        {
            System.out.println("It doesn't work!!!!!!!");
        }
    }


    @FXML
    private void clickPause(ActionEvent actionEvent) {
        musicManager.pauseSong();
    }

    @FXML
    private void clickReset(ActionEvent actionEvent) {
        musicManager.stopSong();
        setProgressBar();
    }

    @FXML
    private void clickPrevious(ActionEvent actionEvent) {
        musicManager.previousSong();
        setProgressBar();
    }

    @FXML
    private void clickNext(ActionEvent actionEvent) {
        musicManager.nextSong();
        setProgressBar();
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
                playListTable.refresh();
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
                addSongWindow.setTitle("Add Songs");
                addSongWindow.initOwner(stage);
                addSongWindow.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clickCreatePlaylist(ActionEvent actionEvent) {
    }

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

    public void clickAddSongToPlaylist(ActionEvent actionEvent) {
        Song selectedSong = songListTable.getSelectionModel().getSelectedItem();
        int song_id;
        int playlist_id;
        if (selectedSong!=null)
        {
            song_id = selectedSong.getId();
            playlist_id = playlistChoicebox.getSelectionModel().getSelectedItem().getId();
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

    public void clickRemoveSongFromPlaylist(ActionEvent actionEvent) {
        if(playlistChoicebox.getSelectionModel().getSelectedItem()!=null && playListTable.getSelectionModel().getSelectedItem()!=null) {
                Song selected = playListTable.getSelectionModel().getSelectedItem();
                musicManager.removeSongFromPlaylist(selected);
            }
        }

    }


