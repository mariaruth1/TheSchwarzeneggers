package Datalayer;

import entities.Playlist;
import entities.Song;

import java.util.List;

public class PlaylistDAO {

    public List<Song> getAllPlaylists()
    {
        throw new RuntimeException();
    }

    public Playlist addPlaylist(String name)
    {
        throw new RuntimeException();
    }
    public void deletePlaylist()
    {
        throw new RuntimeException();
    }

    private int getNextId()
    {
                List<Song> songs = getAllPlaylists();
                int biggestId = 0;
                for(Song s : songs)
                {
                    if (biggestId<s.getId())
                        biggestId = s.getId();
                }
                return biggestId+1;
            }
        }
