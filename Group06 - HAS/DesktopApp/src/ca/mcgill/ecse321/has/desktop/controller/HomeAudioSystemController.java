package ca.mcgill.ecse321.has.desktop.controller;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.has.desktop.controller.InvalidInputException;
import ca.mcgill.ecse321.has.desktop.model.Album;
import ca.mcgill.ecse321.has.desktop.model.Has;
import ca.mcgill.ecse321.has.desktop.model.Location;
import ca.mcgill.ecse321.has.desktop.model.PlayList;
import ca.mcgill.ecse321.has.desktop.model.Song;
import ca.mcgill.ecse321.has.desktop.persistence.PersistenceXStream;

public class HomeAudioSystemController {


	public HomeAudioSystemController(){
		
	}
	
	public void createLocation(String name, boolean muted, int volume) throws InvalidInputException{
		if(name == null || name.trim().length() == 0)
			throw new InvalidInputException("Location name cannot be empty!");
		
		
		Location l = new Location(name, muted, volume);
		Has has = Has.getInstance();
		has.addLocation(l);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createPlayList(String name, int listSize, Album album) throws InvalidInputException{
		String error = "";
		if(name == null || name.trim().length() == 0)
			error = error + "Playlist name cannot be empty! ";
		if(listSize == 0)
			error = error + "List size cannot be empty! ";
		if(album == null)
			error = error + "Album cannot be empty! ";
	
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		PlayList p = new PlayList(name, listSize, album);
		Has has = Has.getInstance();
		has.addPlaylist(p);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createAlbum(String name, String artist, String genre, Date releaseDate) throws InvalidInputException{
		String error = "";
		if(name == null || name.trim().length() == 0)
			error = error + "ALbum name cannot be empty! ";
		if(artist == null)
			error = error + "Artist name cannot be empty! ";
		if(genre == null)
			error = error + "Genre cannot be empty! ";
		if(releaseDate == null)
			error = error + "Event date cannot be empty! ";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		Album a = new Album(name, artist, genre, releaseDate);
		Has has = Has.getInstance();
		has.addAlbum(a);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	/*public void register(Participant participant, Event event) throws InvalidInputException{
		
		RegistrationManager rm = RegistrationManager.getInstance();
		
		String error = "";
		if(participant == null)
			error = error + "Participant needs to be selected for registration! ";
		else if (!rm.getParticipants().contains(participant))
			error = error + "Participant does not exist! ";
		if(event == null)
			error = error + "Event needs to be selected for registration!";
		else if (!rm.getEvents().contains(event))
			error = error + "Event does not exist!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		Registration r = new Registration(participant, event);
		rm.addRegistration(r);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	*/
	
}
