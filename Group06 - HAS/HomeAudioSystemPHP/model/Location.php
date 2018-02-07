<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private $name;
  private $muted;
  private $volume;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aMuted, $aVolume)
  {
    $this->name = $aName;
    $this->muted = $aMuted;
    $this->volume = $aVolume;
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

  public function setMuted($aMuted)
  {
    $wasSet = false;
    $this->muted = $aMuted;
    $wasSet = true;
    return $wasSet;
  }

  public function setVolume($aVolume)
  {
    $wasSet = false;
    $this->volume = $aVolume;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getMuted()
  {
    return $this->muted;
  }

  public function getVolume()
  {
    return $this->volume;
  }

  public function isMuted()
  {
    return $this->muted;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>