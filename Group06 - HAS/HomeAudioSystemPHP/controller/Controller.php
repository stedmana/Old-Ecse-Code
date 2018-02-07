<?php
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/controller/InputValidator.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/persistence/PersistenceHAS.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/model/Album.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/model/Location.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem//model/PlayList.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/model/Song.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/model/Has.php';

class Controller
{
	public function __construct(){
	
	}
	
	public function createAlbum($album_name,$artist_name, $genre, $album_date ){
		//$error="";
		//1. Validate input
		$date=date('Y-m-d', strtotime($album_date));
		
		$name = InputValidator::validate_input($album_name);
		$date = InputValidator::validate_input($album_date);
		$artist = InputValidator::validate_input($artist_name);
		$type = InputValidator::validate_input($genre);
	
		if ($name==null||strlen($name)==0){
			throw new Exception("Album name cannot be empty!");
			//$error=$error +"Event name cannot be empty!";
		}
	
	
		if ($artist==null||strlen($artist)==0){
			throw new Exception("Artist name cannot be empty!");
			//$error=$error +"Event start time must be specified correctly (HH:MM)!";
		}
		
		
		if ($type==null||strlen($type)==0){
			throw new Exception("Genre cannot be empty!");
			//$error=$error +"Event end time must be specified correctly (HH:MM)!";
		}
		
		if ($date==null||strlen($date)==0||strtotime($date) == false){
			throw new Exception("Album date must be specified correctly (YYYY-MM-DD)!");
			//$error=$error +"Event date must be specified correctly (YYYY-MM-DD)!";
		}
	
	
		
		/*	if (strlen($error)>0){
		 throw new Exception($error);
			}   */
	
		else{
			//2. Load all of the data
			$pm=new PersistenceHAS();
			$rm=$pm->loadDataFromStore();
	
			//3. add the new event
			$album=new Album($album_name, $artist_name, $genre, $album_date);
			$rm->addAlbum($album);
	
			//4. write all of the data
			$pm->writeDataToStore($rm);
		}
	
	}
	public function register($anAlbum){
		//1. load all of the participant
		$pm=new PersistenceHAS();
		$rm=$pm->loadDataFromStore();
	
		
	
		//3. Find the Event
		$myalbum =Null;
		foreach($rm->getAlbum() as $album){
			if (strcmp($album->getName(), $analbum)==0){
				$myalbum=$album;
				break;
			}
		}
	
		//4. register for the event
		$error="";
		if ( $myalbum!=NULL){
			$myregistration = new registration( $myalbum);
			$rm->addRegistration($myregistration);
			$pm->writeDataToStore($rm);
		} else{
			
			if($myalbum==NULL){
				$error .= "@2Event ";
				if ($analbum != NULL){
					$error .= $analbum;
				}
				$error .= " not found! ";
			}
			throw new Exception(trim($error));
		}
	}
}