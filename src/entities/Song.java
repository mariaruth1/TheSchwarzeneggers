package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song {
    private IntegerProperty id = new SimpleIntegerProperty();

    private StringProperty title = new SimpleStringProperty();

    private IntegerProperty year= new SimpleIntegerProperty();

    private StringProperty artist = new SimpleStringProperty();

    private StringProperty genre = new SimpleStringProperty();

    private StringProperty path = new SimpleStringProperty();

    public Song(int id, String title, int year, String artist, String genre, String path) {
        setId(id);
        setTitle(title);
        setYear(year);
        setArtist(artist);
        setGenre(genre);
        setPath(path);

    }
    public Song(String title, int year, String artist, String genre, String path) {
        setTitle(title);
        setYear(year);
        setArtist(artist);
        setGenre(genre);
        setPath(path);
    }

    public Song() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    @Override
    public String toString() {
        return  title + " " + year + " " + artist + " " + genre + " " + path;
    }
}
