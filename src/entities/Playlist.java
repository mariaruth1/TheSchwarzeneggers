package entities;

public class Playlist {
    private final int id;

    private String title;

    private int songs;

    private int songLenth;


    public Playlist(int id, String title, int songs, int songLenth) {
        this.id = id;
        this.title = title;
        this.songLenth = songLenth;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSongLenth() {
        return songLenth;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSongLenth(int songLenth) {
        this.songLenth = songLenth;
    }


    @Override
    public String toString() {
        return  title + " " + songs + " " + songLenth;
    }
}

