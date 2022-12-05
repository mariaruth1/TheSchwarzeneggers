package entities;

public class Playlist {

    private final int id;

    private String title;

    private int songCount;


    public Playlist(int id, String title) {
        this.id = id;
        this.title = title;
        this.songCount = songCount;
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
        return  "id" +id+ "name" +title;
    }
}

