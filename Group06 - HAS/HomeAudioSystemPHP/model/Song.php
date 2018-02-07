<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private $title;
  private $listPosition;
  private $duration;
  private $albumPosition;

  //Song Associations
  private $albums;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aListPosition, $aDuration, $aAlbumPosition)
  {
    $this->title = $aTitle;
    $this->listPosition = $aListPosition;
    $this->duration = $aDuration;
    $this->albumPosition = $aAlbumPosition;
    $this->albums = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTitle($aTitle)
  {
    $wasSet = false;
    $this->title = $aTitle;
    $wasSet = true;
    return $wasSet;
  }

  public function setListPosition($aListPosition)
  {
    $wasSet = false;
    $this->listPosition = $aListPosition;
    $wasSet = true;
    return $wasSet;
  }

  public function setDuration($aDuration)
  {
    $wasSet = false;
    $this->duration = $aDuration;
    $wasSet = true;
    return $wasSet;
  }

  public function setAlbumPosition($aAlbumPosition)
  {
    $wasSet = false;
    $this->albumPosition = $aAlbumPosition;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getListPosition()
  {
    return $this->listPosition;
  }

  public function getDuration()
  {
    return $this->duration;
  }

  public function getAlbumPosition()
  {
    return $this->albumPosition;
  }

  public function getAlbum_index($index)
  {
    $aAlbum = $this->albums[$index];
    return $aAlbum;
  }

  public function getAlbums()
  {
    $newAlbums = $this->albums;
    return $newAlbums;
  }

  public function numberOfAlbums()
  {
    $number = count($this->albums);
    return $number;
  }

  public function hasAlbums()
  {
    $has = $this->numberOfAlbums() > 0;
    return $has;
  }

  public function indexOfAlbum($aAlbum)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->albums as $album)
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

  public static function minimumNumberOfAlbums()
  {
    return 0;
  }

  public function addAlbum($aAlbum)
  {
    $wasAdded = false;
    if ($this->indexOfAlbum($aAlbum) !== -1) { return false; }
    $this->albums[] = $aAlbum;
    if ($aAlbum->indexOfSong($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aAlbum->addSong($this);
      if (!$wasAdded)
      {
        array_pop($this->albums);
      }
    }
    return $wasAdded;
  }

  public function removeAlbum($aAlbum)
  {
    $wasRemoved = false;
    if ($this->indexOfAlbum($aAlbum) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfAlbum($aAlbum);
    unset($this->albums[$oldIndex]);
    if ($aAlbum->indexOfSong($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aAlbum->removeSong($this);
      if (!$wasRemoved)
      {
        $this->albums[$oldIndex] = $aAlbum;
        ksort($this->albums);
      }
    }
    $this->albums = array_values($this->albums);
    return $wasRemoved;
  }

  public function addAlbumAt($aAlbum, $index)
  {  
    $wasAdded = false;
    if($this->addAlbum($aAlbum))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbums()) { $index = $this->numberOfAlbums() - 1; }
      array_splice($this->albums, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->albums, $index, 0, array($aAlbum));
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
      if($index > $this->numberOfAlbums()) { $index = $this->numberOfAlbums() - 1; }
      array_splice($this->albums, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->albums, $index, 0, array($aAlbum));
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
    $copyOfAlbums = $this->albums;
    $this->albums = array();
    foreach ($copyOfAlbums as $aAlbum)
    {
      $aAlbum->removeSong($this);
    }
  }

}
?>