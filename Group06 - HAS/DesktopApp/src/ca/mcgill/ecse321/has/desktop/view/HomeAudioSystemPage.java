package ca.mcgill.ecse321.has.desktop.view;

import java.awt.Color;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.has.desktop.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.has.desktop.controller.InvalidInputException;
import ca.mcgill.ecse321.has.desktop.model.Album;
import ca.mcgill.ecse321.has.desktop.model.Has;
import ca.mcgill.ecse321.has.desktop.model.Location;
import ca.mcgill.ecse321.has.desktop.model.PlayList;
import ca.mcgill.ecse321.has.desktop.model.Song;

public class HomeAudioSystemPage extends JFrame {

	/**
	 * 
	 */
private static final long serialVersionUID = -8062635784771606869L;
	
	//UI elements
	private JLabel errorMessage;
	/*
	private JComboBox<String> participantList;
	private JLabel participantLabel;
	*/
	private JComboBox<String> albumList;
	private JLabel albumLabel;
	
	/*
	private JButton registerButton;
	private JTextField participantNameTextField;
	private JLabel participantNameLabel;
	private JButton addParticipantButton;
	*/
	
	private JTextField albumNameTextField;
	private JLabel albumNameLabel;
	private JDatePickerImpl releaseDatePicker;
	private JLabel releaseDateLabel;
	private JTextField artistNameTextField;
	private JLabel artistNameLabel;
	private JTextField genreNameTextField;
	private JLabel genreNameLabel;
	
	/*
	private JSpinner startTimeSpinner;
	private JLabel startTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel endTimeLabel;
	*/
	
	private JButton addAlbumButton;
	
	private String error = null;
	/*
	private Integer selectedParticipant=-1;
	private HashMap<Integer, Participant> participants;
	*/
	private Integer selectedAlbum = -1;
	private HashMap<Integer,Album> album;
	
	
	public HomeAudioSystemPage(){
		initComponents();
		refreshData();
	}
	
	private void initComponents(){
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		//elements for registration
		/*
		participantList =new JComboBox<String>(new String[0]);
		participantList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>) evt.getSource();
				selectedParticipant=cb.getSelectedIndex();
			}
		});
		participantLabel = new JLabel();
		*/
		
		albumList =new JComboBox<String>(new String[0]);
		albumList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>) evt.getSource();
				selectedAlbum=cb.getSelectedIndex();
			}
		});
		albumLabel = new JLabel();
		
		/*
		registerButton =new JButton();
		
		
		//element for participant
		participantNameTextField = new JTextField();
		participantNameLabel = new JLabel();
		addParticipantButton = new JButton();
		*/
		
		// elements for album
		albumNameTextField = new JTextField();
		albumNameLabel = new JLabel();
		
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		releaseDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());	
		
		releaseDateLabel = new JLabel();
		/*
		startTimeSpinner = new JSpinner (new SpinnerDateModel());
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(startTimeEditor);
		startTimeLabel = new JLabel();
		endTimeSpinner = new JSpinner (new SpinnerDateModel());
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(endTimeEditor);
		endTimeLabel= new JLabel();
		*/
		addAlbumButton = new JButton();
		
		//global setting and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Home Audio System");
		
		//participantLabel.setText("Select Participant:");
		albumLabel.setText("Select Album");
		//registerButton.setText("Register");
//		registerButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt){
//				registerButtonActionPerformed(evt);
//			}
//		}
//		);
		
		
//		participantNameLabel.setText("Name:");
//		addParticipantButton.setText("Add Participant");
//		addParticipantButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt){
//				addParticipantButtonActionPerformed(evt);
//			}
//		});
		
		albumNameLabel.setText("Album Name:");
		releaseDateLabel.setText("Release Date:");
		artistNameLabel.setText("Artist Name:");
		genreNameLabel.setText("Genre:");
		addAlbumButton.setText("Add Album");
		addAlbumButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addEventButtonActionPerformed(evt);
			}
		});
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
//						.addGroup(layout.createParallelGroup()
//						       .addComponent(participantLabel)
//						        .addComponent(registerButton)
//				                .addComponent(participantNameLabel))
//				        .addGroup(layout.createParallelGroup()
//				        		.addComponent(participantList)
//						        .addComponent(participantNameTextField, 200, 200, 400)
//						        .addComponent(addParticipantButton))
				        .addGroup(layout.createParallelGroup()
								.addComponent(albumLabel)
								.addComponent(albumNameLabel)
						        .addComponent(releaseDateLabel)
						        .addComponent(artistNameLabel)
						        .addComponent(genreNameLabel))
				        .addGroup(layout.createParallelGroup()
				        		.addComponent(albumList)
				        		.addComponent(albumNameTextField, 200, 200, 400)
				        		.addComponent(releaseDatePicker)
//				        		.addComponent(startTimeSpinner)
//				        		.addComponent(endTimeSpinner)
				        		.addComponent(addAlbumButton)))
						);
//		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{registerButton,participantLabel});
//		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addParticipantButton,participantNameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addAlbumButton,albumNameTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
//						.addComponent(participantLabel)
//		        		.addComponent(participantList)
				        .addComponent(albumLabel)
				        .addComponent(albumList))
//				.addComponent(registerButton)
				.addGroup(layout.createParallelGroup()
//						.addComponent(participantNameLabel)
//						.addComponent(participantNameTextField)
						.addComponent(albumNameLabel)
						.addComponent(albumNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(releaseDateLabel)
						.addComponent(releaseDatePicker))
				.addGroup(layout.createParallelGroup()
						.addComponent(artistNameLabel)
						.addComponent(artistNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(genreNameLabel)
						.addComponent(genreNameTextField))
				.addGroup(layout.createParallelGroup()
//						.addComponent(addParticipantButton)
						.addComponent(addAlbumButton))
						
				);
		
		pack();
	}	
	
	private void refreshData(){
		Has has =Has.getInstance();
		
		errorMessage.setText(error);
		if(error==null||error.length()==0){
//			participants = new HashMap<Integer,Participant>();
//			participantList.removeAllItems();
//			Iterator<Participant> pIt=rm.getParticipants().iterator();
			Integer index = 0;
//			while(pIt.hasNext()){
//				Participant p =pIt.next();
//				participants.put(index, p);
//				participantList.addItem(p.getName());
//				index++;
//			}
//			selectedParticipant=-1;
//			participantList.setSelectedIndex(selectedParticipant);
			
			//event List
			album = new HashMap<Integer, Album>();
			albumList.removeAllItems();
			Iterator<Album> aIt=has.getAlbum().iterator();
			index = 0;
			while(aIt.hasNext()){
				Album a =aIt.next();
				album.put(index, a);
				albumList.addItem(a.getName());
				index++;
			}
			selectedAlbum=-1;
			albumList.setSelectedIndex(selectedAlbum);
			//participant
//		    participantNameTextField.setText("");
		    //event
		    albumNameTextField.setText("");
		    releaseDatePicker.getModel().setValue(null);
		    artistNameTextField.setText("");
		    genreNameTextField.setText("");
//		    startTimeSpinner.setValue(new Date());
//		    endTimeSpinner.setValue(new Date());
		}
		pack();
		
		
	}
	
//	private void addParticipantButtonActionPerformed(java.awt.event.ActionEvent evt){
//
//		HomeAudioSystemController erc = new HomeAudioSystemController();
//		error=null;
//		try{
//			erc.createParticipant(participantNameTextField.getText());
//		} catch (InvalidInputException e){
//			error=e.getMessage();
//		}
//		refreshData();
//
//
//	}
	

	private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt){

		HomeAudioSystemController erc = new HomeAudioSystemController();
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime((Date) startTimeSpinner.getValue());
//		calendar.set(2000, 1, 1);
//		Time startTime = new Time(calendar.getTime().getTime());
//		calendar.setTime((Date) endTimeSpinner.getValue());
//		calendar.set(2000, 1, 1);
//		Time endTime = new Time(calendar.getTime().getTime());
		error=null;
		try{
			erc.createAlbum(albumNameTextField.getText(),artistNameTextField.getText(), genreNameTextField.getText(), (java.sql.Date) releaseDatePicker.getModel().getValue());
		} catch (InvalidInputException e){
			error=e.getMessage();
		}
		refreshData();


	}
	
//	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt){
//		error="";
//		if(selectedParticipant<0)
//			error=error+"Participant needs to be selected for registration!";
//		if(selectedEvent<0)
//			error=error+"Event needs to be selected for registeration!";
//		error=error.trim();
//		if(error.length()==0){
//			EventRegistrationController erc = new EventRegistrationController();
//			try{
//				erc.register(participants.get(selectedParticipant),events.get(selectedEvent));
//			}catch (InvalidInputException e){
//				error=e.getMessage();
//			}
//		}
//	    refreshData();
//	}
}
