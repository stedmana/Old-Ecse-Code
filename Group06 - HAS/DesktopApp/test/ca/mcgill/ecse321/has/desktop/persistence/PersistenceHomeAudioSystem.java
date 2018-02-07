package ca.mcgill.ecse321.has.desktop.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.has.desktop.model.Album;
import ca.mcgill.ecse321.has.desktop.model.Has;
import ca.mcgill.ecse321.has.desktop.model.Location;
import ca.mcgill.ecse321.has.desktop.model.PlayList;
import ca.mcgill.ecse321.has.desktop.model.Song;


public class PersistenceHomeAudioSystem {

	private static String fileName = "homeaudiosystem.xml";
	
	private static void initializeXStream(){
		PersistenceXStream.setFilename(fileName);
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("Has", Has.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("playlist", PlayList.class);
		PersistenceXStream.setAlias("song", Song.class);
	}
	
	public static void loadHomeAudioSystemModel(){
		Has has = Has.getInstance();
		PersistenceHomeAudioSystem.initializeXStream();
		Has has2 = (Has) PersistenceXStream.loadFromXMLwithXStream();
		if(has2 != null){
			Iterator<Location> lIt = has2.getLocation().iterator();
			while (lIt.hasNext())
				has.addLocation(lIt.next());
			Iterator<PlayList> pIt = has2.getPlaylist().iterator();
			while (pIt.hasNext())
				has.addPlaylist(pIt.next());
			Iterator<Album> aIt = has2.getAlbum().iterator();
			while (aIt.hasNext())
				has.addAlbum(aIt.next());
			Iterator<Song> sIt = has2.getSong().iterator();
			while (sIt.hasNext())
				has.addSong(sIt.next());
			
		}
	}
	
	public static void setFileName(String name) {
		fileName = name;
	}

}