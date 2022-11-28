package entities;

public class Playlist {
    private final int id;

    private String title;

    private int songs;

    private int songLength;


    public Playlist(int id, String title, int songs, int songLength) {
        this.id = id;
        this.title = title;
        this.songLength = songLength;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSongLenth(int songLenth) {
        this.songLength = songLenth;
    }


    @Override
    public String toString() {
        return  title + " " + songs + " " + songLength;
    }
}

