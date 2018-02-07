<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class PlayList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayList Attributes
  private $name;
  private $listSize;

  //PlayList Associations
  private $album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aListSize, $aAlbum)
  {
    $this->name = $aName;
    $this->listSize = $aListSize;
    if (!$this->setAlbum($aAlbum))
    {
      throw new Exception("Unable to create PlayList due to aAlbum");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setListSize($aListSize)
  {
    $wasSet = false;
    $this->listSize = $aListSize;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getListSize()
  {
    return $this->listSize;
  }

  public function getAlbum()
  {
    return $this->album;
  }

  public function setAlbum($aNewAlbum)
  {
    $wasSet = false;
    if ($aNewAlbum != null)
    {
      $this->album = $aNewAlbum;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->album = null;
  }

}
?>