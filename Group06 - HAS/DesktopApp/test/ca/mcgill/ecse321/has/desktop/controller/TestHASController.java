package ca.mcgill.ecse321.has.desktop.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;
import ca.mcgill.ecse321.has.desktop.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.has.desktop.controller.InvalidInputException;
import ca.mcgill.ecse321.has.model.*;

public class TestHASController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+
				"eventregistration"+File.separator+"controller"+File.separator+"date.xml");
		
		//The following could be moved to the controller's constructor
		PersistenceXStream.setFilename("HAS_data.xml");
		PersistenceXStream.setAlias("album",Album.class);
		PersistenceXStream.setAlias("has",Has.class);
		PersistenceXStream.setAlias("location",Location.class);
		PersistenceXStream.setAlias("playlist",PlayList.class);
		PersistenceXStream.setAlias("song",Song.class);
	}

	@After
	public void tearDown() throws Exception {
		Has has = Has.getInstance();
		has.delete();
	}

	@Test
	public void testCreateAlbum() {
		
		Has has = Has.getInstance();
		assertEquals(0, has.getAlbums().size());
		
		String	albumName 	= "The best album ever",
				artistName 	= "A god amongst men",
				genre 		= "Revolutionary";
		//For some reason, the test only works when the deprecated constructor is used.
		//If I use the Date(long) constructor link in assignment 1, I get an AsserstionError
		Date	releaseDate = new Date(2013, 04, 25);
		
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			//I know this is not how you called your method,
			//but that's how you should have called it.
			hasc.createAlbum(albumName, artistName, genre, releaseDate);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultAlbum(albumName, artistName, genre, releaseDate, has);
		
		//This works if you import PersistenceXStream from assignment 1 as a jar.
		Has has2 = (Has) PersistenceXStream.loadFromXMLwithXStream();
		
		// check file contents
		checkResultAlbum(albumName, artistName, genre, releaseDate, has2);
	}
	
	private void checkResultAlbum(String albumName, String artistName, String genre,
			Date releaseDate, Has has) {
		//For this to work, Has must be associated to Album. Change the umple file!
		assertEquals(1, has.getAlbums().size());
		assertEquals(albumName, has.getAlbum(0).getName());
		assertEquals(artistName, has.getAlbum(0).getArtist());
		assertEquals(genre, has.getAlbum(0).getGenre());
		assertEquals(releaseDate, has.getAlbum(0).getReleaseDate());
		assertEquals(0, has.getPlaylist().size());
		assertEquals(0, has.getLocation().size());
	}
}
