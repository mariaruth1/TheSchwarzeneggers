package entities;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Playlist {
    private int id;

    private StringProperty title = new SimpleStringProperty();

    private List<Song> songsInPlaylist;


    public Playlist(int id, String title) {
        this.id = id;
        setTitle(title);
    }

    public int getId() {
        return id;
    }

    public Playlist(String title) {
        setTitle(title);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }


    @Override
    public String toString() {
        //return  title + " " + songCount;
        return title.get();
    }
}

