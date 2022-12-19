package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Playlist {
    private int id;
    private StringProperty title = new SimpleStringProperty();


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
        return title.get();
    }
}

