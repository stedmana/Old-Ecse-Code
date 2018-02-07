package ca.mcgill.ecse223.tileo.application;


import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.tileo.view.design.DesignPage;
import ca.mcgill.ecse223.tileo.view.game.GamePage;

import javax.swing.*;

public class TileOApplication {


	private static ca.mcgill.ecse223.tileo.model.TileO tileO = new ca.mcgill.ecse223.tileo.model.TileO();

	private static String filename = "data.tileO";
	private static String visiblePage = "Design";
	private static JFrame designPage = null;
	private static JFrame gamePage = null;
	
	
	public static ca.mcgill.ecse223.tileo.model.TileO getTileO() {
		if (tileO == null) {
			// load model
			tileO = load();
		}
		return tileO;
	}

	public static void save() {
		PersistenceObjectStream.serialize(tileO);
	}

	public static TileO load() {
		PersistenceObjectStream.setFilename(filename);
		tileO = (TileO) PersistenceObjectStream.deserialize();
		if (tileO == null){
			tileO = new TileO();
		}
		return tileO;
	}


	public static void main(String[] args) {

		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				designPage = new DesignPage();
				designPage.setVisible(true);
			}
		});

	}

	public static void changeMode(){
		if(visiblePage == "Design"){
			if (gamePage == null){
				gamePage = new GamePage();
			}
			designPage.setVisible(false);
			gamePage.setVisible(true);
			((GamePage) gamePage).refreshData();
			visiblePage = "Game";
		}
		else{
			designPage.setVisible(true);
			gamePage.setVisible(false);
			((DesignPage) designPage).refreshData();
			visiblePage = "Design";
		}

	}

}



