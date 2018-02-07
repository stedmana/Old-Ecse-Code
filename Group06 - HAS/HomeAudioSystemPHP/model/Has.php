<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Has
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Has Associations
  private $location;
  private $playlist;
  private $song;
  private $album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->location = array();
    $this->playlist = array();
    $this->song = array();
    $this->album = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new Has();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getLocation_index($index)
  {
    $aLocation = $this->location[$index];
    return $aLocation;
  }

  public function getLocation()
  {
    $newLocation = $this->location;
    return $newLocation;
  }

  public function numberOfLocation()
  {
    $number = count($this->location);
    return $number;
  }

  public function hasLocation()
  {
    $has = $this->numberOfLocation() > 0;
    return $has;
  }

  public function indexOfLocation($aLocation)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->location as $location)
    {
      if ($location->equals($aLocation))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getPlaylist_index($index)
  {
    $aPlaylist = $this->playlist[$index];
    return $aPlaylist;
  }

  public function getPlaylist()
  {
    $newPlaylist = $this->playlist;
    return $newPlaylist;
  }

  public function numberOfPlaylist()
  {
    $number = count($this->playlist);
    return $number;
  }

  public function hasPlaylist()
  {
    $has = $this->numberOfPlaylist() > 0;
    return $has;
  }

  public function indexOfPlaylist($aPlaylist)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->playlist as $playlist)
    {
      if ($playlist->equals($aPlaylist))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getSong_index($index)
  {
    $aSong = $this->song[$index];
    return $aSong;
  }

  public function getSong()
  {
    $newSong = $this->song;
    return $newSong;
  }

  public function numberOfSong()
  {
    $number = count($this->song);
    return $number;
  }

  public function hasSong()
  {
    $has = $this->numberOfSong() > 0;
    return $has;
  }

  public function indexOfSong($aSong)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->song as $song)
    {
      if ($song->equals($aSong))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAlbum_index($index)
  {
    $aAlbum = $this->album[$index];
    return $aAlbum;
  }

  public function getAlbum()
  {
    $newAlbum = $this->album;
    return $newAlbum;
  }

  public function numberOfAlbum()
  {
    $number = count($this->album);
    return $number;
  }

  public function hasAlbum()
  {
    $has = $this->numberOfAlbum() > 0;
    return $has;
  }

  public function indexOfAlbum($aAlbum)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->album as $album)
    {
      if ($album->equals($aAlbum))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfLocation()
  {
    return 0;
  }

  public function addLocation($aLocation)
  {
    $wasAdded = false;
    if ($this->indexOfLocation($aLocation) !== -1) { return false; }
    $this->location[] = $aLocation;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeLocation($aLocation)
  {
    $wasRemoved = false;
    if ($this->indexOfLocation($aLocation) != -1)
    {
      unset($this->location[$this->indexOfLocation($aLocation)]);
      $this->location = array_values($this->location);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addLocationAt($aLocation, $index)
  {  
    $wasAdded = false;
    if($this->addLocation($aLocation))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLocation()) { $index = $this->numberOfLocation() - 1; }
      array_splice($this->location, $this->indexOfLocation($aLocation), 1);
      array_splice($this->location, $index, 0, array($aLocation));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveLocationAt($aLocation, $index)
  {
    $wasAdded = false;
    if($this->indexOfLocation($aLocation) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLocation()) { $index = $this->numberOfLocation() - 1; }
      array_splice($this->location, $this->indexOfLocation($aLocation), 1);
      array_splice($this->location, $index, 0, array($aLocation));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addLocationAt($aLocation, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfPlaylist()
  {
    return 0;
  }

  public function addPlaylist($aPlaylist)
  {
    $wasAdded = false;
    if ($this->indexOfPlaylist($aPlaylist) !== -1) { return false; }
    $this->playlist[] = $aPlaylist;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removePlaylist($aPlaylist)
  {
    $wasRemoved = false;
    if ($this->indexOfPlaylist($aPlaylist) != -1)
    {
      unset($this->playlist[$this->indexOfPlaylist($aPlaylist)]);
      $this->playlist = array_values($this->playlist);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addPlaylistAt($aPlaylist, $index)
  {  
    $wasAdded = false;
    if($this->addPlaylist($aPlaylist))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPlaylist()) { $index = $this->numberOfPlaylist() - 1; }
      array_splice($this->playlist, $this->indexOfPlaylist($aPlaylist), 1);
      array_splice($this->playlist, $index, 0, array($aPlaylist));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMovePlaylistAt($aPlaylist, $index)
  {
    $wasAdded = false;
    if($this->indexOfPlaylist($aPlaylist) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPlaylist()) { $index = $this->numberOfPlaylist() - 1; }
      array_splice($this->playlist, $this->indexOfPlaylist($aPlaylist), 1);
      array_splice($this->playlist, $index, 0, array($aPlaylist));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addPlaylistAt($aPlaylist, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfSong()
  {
    return 0;
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $this->song[] = $aSong;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSong($aSong)
  {
    $wasRemoved = false;
    if ($this->indexOfSong($aSong) != -1)
    {
      unset($this->song[$this->indexOfSong($aSong)]);
      $this->song = array_values($this->song);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSongAt($aSong, $index)
  {  
    $wasAdded = false;
    if($this->addSong($aSong))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSong()) { $index = $this->numberOfSong() - 1; }
      array_splice($this->song, $this->indexOfSong($aSong), 1);
      array_splice($this->song, $index, 0, array($aSong));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSongAt($aSong, $index)
  {
    $wasAdded = false;
    if($this->indexOfSong($aSong) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSong()) { $index = $this->numberOfSong() - 1; }
      array_splice($this->song, $this->indexOfSong($aSong), 1);
      array_splice($this->song, $index, 0, array($aSong));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSongAt($aSong, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAlbum()
  {
    return 0;
  }

  public function addAlbum($aAlbum)
  {
    $wasAdded = false;
    if ($this->indexOfAlbum($aAlbum) !== -1) { return false; }
    $this->album[] = $aAlbum;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAlbum($aAlbum)
  {
    $wasRemoved = false;
    if ($this->indexOfAlbum($aAlbum) != -1)
    {
      unset($this->album[$this->indexOfAlbum($aAlbum)]);
      $this->album = array_values($this->album);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAlbumAt($aAlbum, $index)
  {  
    $wasAdded = false;
    if($this->addAlbum($aAlbum))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbum()) { $index = $this->numberOfAlbum() - 1; }
      array_splice($this->album, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->album, $index, 0, array($aAlbum));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAlbumAt($aAlbum, $index)
  {
    $wasAdded = false;
    if($this->indexOfAlbum($aAlbum) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbum()) { $index = $this->numberOfAlbum() - 1; }
      array_splice($this->album, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->album, $index, 0, array($aAlbum));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAlbumAt($aAlbum, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->location = array();
    $this->playlist = array();
    $this->song = array();
    $this->album = array();
  }

}
?>