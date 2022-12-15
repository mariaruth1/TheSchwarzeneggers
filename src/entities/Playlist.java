package entities;


import java.util.List;

public class Playlist {
    private final int id;

    private String title;


    public Playlist(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        //return  title + " " + songCount;
        return title;
    }
}

