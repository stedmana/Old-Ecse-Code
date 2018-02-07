/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.has.desktop.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../HomeAudioSystem.ump"
// line 39 "../../../../../HomeAudioSystem.ump"
public class Has
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Has theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Has Associations
  private List<Location> location;
  private List<PlayList> playlist;
  private List<Song> song;
  private List<Album> album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private Has()
  {
    location = new ArrayList<Location>();
    playlist = new ArrayList<PlayList>();
    song = new ArrayList<Song>();
    album = new ArrayList<Album>();
  }

  public static Has getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new Has();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Location getLocation(int index)
  {
    Location aLocation = location.get(index);
    return aLocation;
  }

  public List<Location> getLocation()
  {
    List<Location> newLocation = Collections.unmodifiableList(location);
    return newLocation;
  }

  public int numberOfLocation()
  {
    int number = location.size();
    return number;
  }

  public boolean hasLocation()
  {
    boolean has = location.size() > 0;
    return has;
  }

  public int indexOfLocation(Location aLocation)
  {
    int index = location.indexOf(aLocation);
    return index;
  }

  public PlayList getPlaylist(int index)
  {
    PlayList aPlaylist = playlist.get(index);
    return aPlaylist;
  }

  public List<PlayList> getPlaylist()
  {
    List<PlayList> newPlaylist = Collections.unmodifiableList(playlist);
    return newPlaylist;
  }

  public int numberOfPlaylist()
  {
    int number = playlist.size();
    return number;
  }

  public boolean hasPlaylist()
  {
    boolean has = playlist.size() > 0;
    return has;
  }

  public int indexOfPlaylist(PlayList aPlaylist)
  {
    int index = playlist.indexOf(aPlaylist);
    return index;
  }

  public Song getSong(int index)
  {
    Song aSong = song.get(index);
    return aSong;
  }

  public List<Song> getSong()
  {
    List<Song> newSong = Collections.unmodifiableList(song);
    return newSong;
  }

  public int numberOfSong()
  {
    int number = song.size();
    return number;
  }

  public boolean hasSong()
  {
    boolean has = song.size() > 0;
    return has;
  }

  public int indexOfSong(Song aSong)
  {
    int index = song.indexOf(aSong);
    return index;
  }

  public Album getAlbum(int index)
  {
    Album aAlbum = album.get(index);
    return aAlbum;
  }

  public List<Album> getAlbum()
  {
    List<Album> newAlbum = Collections.unmodifiableList(album);
    return newAlbum;
  }

  public int numberOfAlbum()
  {
    int number = album.size();
    return number;
  }

  public boolean hasAlbum()
  {
    boolean has = album.size() > 0;
    return has;
  }

  public int indexOfAlbum(Album aAlbum)
  {
    int index = album.indexOf(aAlbum);
    return index;
  }

  public static int minimumNumberOfLocation()
  {
    return 0;
  }

  public boolean addLocation(Location aLocation)
  {
    boolean wasAdded = false;
    if (location.contains(aLocation)) { return false; }
    location.add(aLocation);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLocation(Location aLocation)
  {
    boolean wasRemoved = false;
    if (location.contains(aLocation))
    {
      location.remove(aLocation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addLocationAt(Location aLocation, int index)
  {  
    boolean wasAdded = false;
    if(addLocation(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocation()) { index = numberOfLocation() - 1; }
      location.remove(aLocation);
      location.add(index, aLocation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLocationAt(Location aLocation, int index)
  {
    boolean wasAdded = false;
    if(location.contains(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocation()) { index = numberOfLocation() - 1; }
      location.remove(aLocation);
      location.add(index, aLocation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLocationAt(aLocation, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPlaylist()
  {
    return 0;
  }

  public boolean addPlaylist(PlayList aPlaylist)
  {
    boolean wasAdded = false;
    if (playlist.contains(aPlaylist)) { return false; }
    playlist.add(aPlaylist);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlaylist(PlayList aPlaylist)
  {
    boolean wasRemoved = false;
    if (playlist.contains(aPlaylist))
    {
      playlist.remove(aPlaylist);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPlaylistAt(PlayList aPlaylist, int index)
  {  
    boolean wasAdded = false;
    if(addPlaylist(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylist()) { index = numberOfPlaylist() - 1; }
      playlist.remove(aPlaylist);
      playlist.add(index, aPlaylist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlaylistAt(PlayList aPlaylist, int index)
  {
    boolean wasAdded = false;
    if(playlist.contains(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylist()) { index = numberOfPlaylist() - 1; }
      playlist.remove(aPlaylist);
      playlist.add(index, aPlaylist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlaylistAt(aPlaylist, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSong()
  {
    return 0;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (song.contains(aSong)) { return false; }
    song.add(aSong);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    if (song.contains(aSong))
    {
      song.remove(aSong);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSongAt(Song aSong, int index)
  {  
    boolean wasAdded = false;
    if(addSong(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSong()) { index = numberOfSong() - 1; }
      song.remove(aSong);
      song.add(index, aSong);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSongAt(Song aSong, int index)
  {
    boolean wasAdded = false;
    if(song.contains(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSong()) { index = numberOfSong() - 1; }
      song.remove(aSong);
      song.add(index, aSong);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSongAt(aSong, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAlbum()
  {
    return 0;
  }

  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (album.contains(aAlbum)) { return false; }
    album.add(aAlbum);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    if (album.contains(aAlbum))
    {
      album.remove(aAlbum);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAlbumAt(Album aAlbum, int index)
  {  
    boolean wasAdded = false;
    if(addAlbum(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbum()) { index = numberOfAlbum() - 1; }
      album.remove(aAlbum);
      album.add(index, aAlbum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAlbumAt(Album aAlbum, int index)
  {
    boolean wasAdded = false;
    if(album.contains(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbum()) { index = numberOfAlbum() - 1; }
      album.remove(aAlbum);
      album.add(index, aAlbum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAlbumAt(aAlbum, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    location.clear();
    playlist.clear();
    song.clear();
    album.clear();
  }

}