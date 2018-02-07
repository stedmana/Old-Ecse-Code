package ca.mcgill.ecse321.has.desktop.application;

import ca.mcgill.ecse321.has.desktop.persistence.PersistenceHomeAudioSystem;
import ca.mcgill.ecse321.has.desktop.view.HomeAudioSystemPage;

public class HomeAudioSystem {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PersistenceHomeAudioSystem.loadHomeAudioSystemModel();
		
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new HomeAudioSystemPage().setVisible(true);
			}
		});

	}

}
