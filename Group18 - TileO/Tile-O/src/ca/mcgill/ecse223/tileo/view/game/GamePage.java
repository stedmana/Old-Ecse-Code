package ca.mcgill.ecse223.tileo.view.game;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.view.BoardVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePage extends JFrame{
	
    PlayController myPlayController = new PlayController();

    
    //Take Turn Panel
    private JPanel takeTurnPanelContainer = new JPanel();
    private JPanel takeTurnPanel = new JPanel();

    //Randomly move players Action card Panel
    private JPanel randomlyMovePlayersActionCardPanelContainer = new JPanel();
    private JPanel randomlyMovePlayersActionCardPanel = new JPanel();

    //rollDieAC Panel
    private JPanel rollDieActionCardPanelContainer = new JPanel();
    private JPanel rollDieActionCardPanel = new JPanel();

    //connectTileAC Panel
    private JPanel connectTileActionCardPanelContainer = new JPanel();
    private JPanel connectTileActionCardPanel = new JPanel();

    //rollDieAC Panel
    private JPanel loseTurnActionCardPanelContainer = new JPanel();
    private JPanel loseTurnActionCardPanel = new JPanel();
    
    //removeConnectionAC Panel
    private JPanel removeConnectionActionCardPanelContainer = new JPanel();
    private JPanel removeConnectionActionCardPanel = new JPanel();

    //Get Win Tile Hint Panel
    private JPanel getWinTileHintActionCardPanelContainer = new JPanel();
    private JPanel getWinTileHintActionCardPanel = new JPanel();

    //Todo: Board Visualizer declaration

    //error message box
    private JLabel errorMessage = new JLabel();

    //teleportAC Panel
    private JPanel teleportActionCardPanelContainer = new JPanel();
    private JPanel teleportActionCardPanel = new JPanel();

    //win panel
	private JPanel winPanelContainer= new JPanel();
	private JPanel winPanel= new JPanel();
	
	//nextPlayerRollsOneAC Panel
	private JPanel nextPlayerRollsOneActionCardPanelContainer = new JPanel();
	private JPanel nextPlayerRollsOneActionCardPanel = new JPanel();


    public GamePage() {
        setTitle("Tile-O");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
		try {
			myPlayController.startGame();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        initComponents();
        refreshData();
    }

    public void refreshData() {
    	reinitialize();
        Game.Mode gameMode = myPlayController.getCurrentMode();
        System.out.println("GAMEMODE: "+gameMode);
        if (gameMode == Game.Mode.GAME) {
            setContentPane(takeTurnPanelContainer);
        } else if (gameMode == Game.Mode.GAME_ROLLDIEACTIONCARD) {
            setContentPane(rollDieActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_CONNECTTILESACTIONCARD) {
            setContentPane(connectTileActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_LOSETURNACTIONCARD) {
            setContentPane(loseTurnActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_REMOVECONNECTIONACTIONCARD) {
            setContentPane(removeConnectionActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_TELEPORTACTIONCARD) {
            setContentPane(teleportActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_WON) {
	        setContentPane(winPanelContainer);
	    } else if (gameMode == Game.Mode.GAME_WINTILEHINTACTIONCARD) {
            setContentPane(getWinTileHintActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_RANDOMMOVEPLAYERSACTIONCARD) {
            setContentPane(randomlyMovePlayersActionCardPanelContainer);
        } else if (gameMode == Game.Mode.GAME_NEXTPLAYERROLLSONEACTIONCARD) {
            setContentPane(randomlyMovePlayersActionCardPanelContainer); //Todo
        }else if (gameMode == Game.Mode.GAME_MOVEWINTILEACTIONCARD) {
            setContentPane(randomlyMovePlayersActionCardPanelContainer); //Todo
        }else if (gameMode == Game.Mode.GAME_REVEALTILEACTIONCARD) {
            setContentPane(randomlyMovePlayersActionCardPanelContainer);  //Todo
        }else if (gameMode == Game.Mode.GAME_RANDOMLYASSIGNNEWINACTIVITYPERIODSTOALLACTIONTILESACTIONCARD) {
            setContentPane(randomlyMovePlayersActionCardPanelContainer); //Todo
        }


        invalidate();
        validate();
    }


    /** By Jackson Li
     * Updates view according to the model. First removes existing containers and
     * then calls initComponents to restore with updated info
     * UPDATE: FIXED BUG BY restoring tilesPanel.removeAll()
     * and others
     * */
    private void reinitialize(){

        myPlayController.save();
        //Todo: Add back game board and call removeAll() here
        takeTurnPanelContainer.removeAll();
        takeTurnPanel.removeAll();
        rollDieActionCardPanelContainer.removeAll();
        rollDieActionCardPanel.removeAll();
        loseTurnActionCardPanelContainer.removeAll();
        loseTurnActionCardPanel.removeAll();
        connectTileActionCardPanelContainer.removeAll();
        connectTileActionCardPanel.removeAll();
        getWinTileHintActionCardPanelContainer.removeAll();
        getWinTileHintActionCardPanel.removeAll();
        randomlyMovePlayersActionCardPanelContainer.removeAll();
        randomlyMovePlayersActionCardPanel.removeAll();

        initComponents();
    }

    private void initComponents() {
        board();
        saveAndLoadBar();
        playTakeATurn();
        playRollDieActionCard();
        playConnectTilesActionCard();
        playLoseTurnActionCard();
        playRemoveConnectionActionCard();
        playTeleportActionCard();
        playWinTileHintActionCard();
        playRandomlyMovePlayersActionCard();
        playNextPlayerRollsOneActionCard();
        win();
    }



    // JPanel Layouts
    private JPanel saveAndLoadBar(){
        JPanel saveAndLoadBar = new JPanel();

        saveAndLoadBar.setPreferredSize(new Dimension(500,40));
        saveAndLoadBar.setBackground(Color.orange);

        // Game selector by index
        JComboBox<String> gameList = new JComboBox<>();
        gameList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!"- Select Game -".equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });

        gameList.addItem("- Select Game -");
        for(int i = 0; i < myPlayController.getNumberOfGames(); i++){
        	if (TileOApplication.getTileO().getGame(i).getMode() != Game.Mode.DESIGN)
        		gameList.addItem(Integer.toString(i));
        }
        gameList.setSelectedIndex(0);
        gameList.setSize(20, gameList.getPreferredSize().height);

        // Load Game Button
        JButton loadGameButton = new JButton();
        loadGameButton.setText("Load");
        loadGameButton.addActionListener(evt -> {
			try {
				loadGameActionPerformed(evt, gameList.getSelectedItem().toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        //Save Game Button

        JButton saveGameButton = new JButton ("Save Game "+ myPlayController.getCurrentGameIndex());
        saveGameButton.addActionListener(this::saveGameButtonPerformed);


        //Setup bar
        saveAndLoadBar.add(saveGameButton);
        saveAndLoadBar.add(loadGameButton);
        saveAndLoadBar.add(gameList);

        return saveAndLoadBar;
    }





    private JPanel board() {
        JPanel boardContainer = new JPanel();

        boardContainer.setSize(new Dimension(300,400));
        boardContainer.setPreferredSize(new Dimension(300,400));
        boardContainer.setLayout(new FlowLayout());

        /** BOARD CODE GOES HERE **/

        //Set up board view
        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        BoardVisualizer boardPanel = new BoardVisualizer(currentGame);
        boardPanel.setPreferredSize(new Dimension(300,310));



        /*************************/

        boardContainer.add(boardPanel);

        boardContainer.setBackground(Color.BLUE);

        return boardContainer;
    }

    private void moveButtonActionPerformed(ActionEvent evt, String selectedTileString) {

        String delims = ",";
        String chosenTileString =  selectedTileString;
        String [] position = chosenTileString.split(delims);
        String xPos = position[0];
        String yPos = position[1];

        Tile tilePicked = myPlayController.findTileByPos(Integer.parseInt(xPos),Integer.parseInt(yPos));

        try {
            myPlayController.land(tilePicked);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
   
        refreshData();

    }



    private void playTakeATurn(){
        //Set layout of container
        takeTurnPanelContainer.setLayout(new BoxLayout(takeTurnPanelContainer, BoxLayout.X_AXIS));

        //Connection Options
        JPanel turnPanel = new JPanel();
        turnPanel.setMinimumSize(new Dimension(500, 360));
        turnPanel.setPreferredSize(new Dimension(500, 360));
        turnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        turnPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in turnPanel JPanel

        //TODO: Setup take a turn options
        JLabel turns = new JLabel("Take a turn page");
        turnPanel.add(turns);

        //current action panel
        JPanel currentActionPanel = new JPanel();
        currentActionPanel.setBackground(Color.yellow);
        JLabel currentActionPanelLabel = new JLabel("Current Action");
        JLabel playerLabel = new JLabel ("Player Turn: " + ((int)PlayController.getCurrentPlayerIndex()+1));
        JLabel instructionLabel = new JLabel ("Play Turn: Roll Die");

        currentActionPanel.setMinimumSize(new Dimension(400,300));
        currentActionPanel.setPreferredSize(new Dimension(400, 300));
        currentActionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        currentActionPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        //roll die button
        JButton rollDieButton = new JButton("Roll Die");
        rollDieButton.addActionListener(this :: rollDieButtonActionPerformed);





        // Play move to tile  selector
        JComboBox<String> PlayMoveList = new JComboBox<>();
        PlayMoveList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 8L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!"- Select Tile by coordinates -".equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });


        PlayMoveList.addItem("- Select Tile by coordinates -");

        //position separated by comma x=2 y=3 (2,3)
        for(Tile tile : myPlayController.getPossibleMoves()){
            PlayMoveList.addItem(Integer.toString(tile.getX()) + "," + Integer.toString( tile.getY()));

        }

        PlayMoveList.setSelectedIndex(0);
        PlayMoveList.setSize(20, PlayMoveList.getPreferredSize().height);



        //move to new position button
        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(evt -> moveButtonActionPerformed(evt, PlayMoveList.getSelectedItem().toString()));

        //Group layout
        GroupLayout layout = new GroupLayout(currentActionPanel);
        currentActionPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        //Horizontal Layout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        		.addComponent(playerLabel)
                                .addComponent(currentActionPanelLabel)
                                .addComponent(instructionLabel)
                                .addComponent(rollDieButton)
                                .addComponent(PlayMoveList)
                                .addComponent(moveButton)
                        )


        );


        //Vertical Layout

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                		.addComponent(playerLabel)
                        .addComponent(currentActionPanelLabel)
                        .addComponent(instructionLabel)
                        .addComponent(rollDieButton)
                        .addComponent(PlayMoveList)
                        .addComponent(moveButton)

        );



        turnPanel.add(currentActionPanel);

        turnPanel.setBackground(Color.CYAN);

        /*************************/

        //Add Tab bar
        takeTurnPanel.setLayout(new FlowLayout());
        takeTurnPanel.setPreferredSize(new Dimension(500,400));
        takeTurnPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        takeTurnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        takeTurnPanel.add(saveAndLoadBar());
        takeTurnPanel.add(turnPanel);

        //Add panels
        takeTurnPanelContainer.add(board());
        takeTurnPanelContainer.add(takeTurnPanel);
        //takeTurnPanelContainer.add(currentActionPanel);

    }


    private void playRandomlyMovePlayersActionCard() {

        //Set layout of container
        randomlyMovePlayersActionCardPanelContainer.setLayout(new BoxLayout(randomlyMovePlayersActionCardPanelContainer, BoxLayout.X_AXIS));

        //Tiles Options
        JPanel actionCardPanel = new JPanel();
        actionCardPanel.setMinimumSize(new Dimension(500, 360));
        actionCardPanel.setPreferredSize(new Dimension(500, 360));
        actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in tilesOptionsPanel JPanel

        JLabel cardInfo = new JLabel("Current Action: " + myPlayController.getActionCardInstructions());

        //button
        JButton actionButton = new JButton("Randomize Players");
        actionButton.addActionListener(e -> {
            try {
                randomizePlayersActionCardButtonPerformed(e);
            } catch (InvalidInputException e1) {
                e1.printStackTrace();
            }
        });


        //action card UI
        actionCardPanel.add(cardInfo);
        actionCardPanel.add(actionButton);
        /*************************/

        //Add save and load bar
        randomlyMovePlayersActionCardPanel.setPreferredSize(new Dimension(500, 360));
        randomlyMovePlayersActionCardPanel.setLayout(new BoxLayout(randomlyMovePlayersActionCardPanel, BoxLayout.Y_AXIS));
        randomlyMovePlayersActionCardPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        randomlyMovePlayersActionCardPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        randomlyMovePlayersActionCardPanel.add(saveAndLoadBar());
        randomlyMovePlayersActionCardPanel.add(actionCardPanel);

        //Add to Container
        randomlyMovePlayersActionCardPanelContainer.add(board());
        randomlyMovePlayersActionCardPanelContainer.add(randomlyMovePlayersActionCardPanel);

    }







    private void win() {

        //Set layout of container
        winPanelContainer.setLayout(new BoxLayout(winPanelContainer, BoxLayout.X_AXIS));

        //Tiles Options
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.GREEN);
        messagePanel.setMinimumSize(new Dimension(500, 360));
        messagePanel.setPreferredSize(new Dimension(500, 360));
        messagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        messagePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in tilesOptionsPanel JPanel

        //info
        JLabel PlayInfo = new JLabel("WINNER: " + ((int)myPlayController.getCurrentPlayerIndex()+1));


        //action card UI
        messagePanel.add(PlayInfo);
        /*************************/

        //Add save and load bar
        winPanel.setPreferredSize(new Dimension(500, 360));
        winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
        winPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        winPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        winPanel.add(messagePanel);

        //Add to Container
        winPanelContainer.add(winPanel);

    }
    





        private void playRollDieActionCard() {

        //Set layout of container
        rollDieActionCardPanelContainer.setLayout(new BoxLayout(rollDieActionCardPanelContainer, BoxLayout.X_AXIS));

        //Tiles Options
        JPanel actionCardPanel = new JPanel();
        actionCardPanel.setMinimumSize(new Dimension(500, 360));
        actionCardPanel.setPreferredSize(new Dimension(500, 360));
        actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in tilesOptionsPanel JPanel

        //info
        JLabel PlayInfo = new JLabel("Current Turn: Play " + myPlayController.getCurrentPlayerIndex());
        JLabel cardInfo = new JLabel("Current Action: " + myPlayController.getActionCardInstructions());

        //button
        JButton actionButton = new JButton("Roll Die Again");
        actionButton.addActionListener(e -> {
            try {
                rollDieActionCardButtonPerformed(e);
            } catch (InvalidInputException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        //action card UI
        actionCardPanel.add(PlayInfo);
        actionCardPanel.add(cardInfo);
        actionCardPanel.add(actionButton);
        /*************************/

        //Add save and load bar
        rollDieActionCardPanel.setPreferredSize(new Dimension(500, 360));
        rollDieActionCardPanel.setLayout(new BoxLayout(rollDieActionCardPanel, BoxLayout.Y_AXIS));
        rollDieActionCardPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        rollDieActionCardPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        rollDieActionCardPanel.add(saveAndLoadBar());
        rollDieActionCardPanel.add(actionCardPanel);



        //Add to Container
        rollDieActionCardPanelContainer.add(board());
        rollDieActionCardPanelContainer.add(rollDieActionCardPanel);

    }
    
    private void playConnectTilesActionCard(){
		//Set layout of container
		connectTileActionCardPanelContainer.setLayout(new BoxLayout(connectTileActionCardPanelContainer, BoxLayout.X_AXIS));

		//Connection Options
		JPanel actionCardPanel = new JPanel();
		actionCardPanel.setLayout(new BoxLayout(actionCardPanel, BoxLayout.PAGE_AXIS));
		actionCardPanel.setMinimumSize(new Dimension(500, 360));
		actionCardPanel.setPreferredSize(new Dimension(500, 360));
		actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		/** YOUR CODE GOES HERE **/
		//x position field for tile 1
		JTextField xPosTile1 = new JTextField(2);
		xPosTile1.setMaximumSize(new Dimension(200,24));
		JLabel xPosTile1Label = new JLabel("First Tile X Position");
		xPosTile1Label.setLabelFor(xPosTile1);

		//y position field for tile 1
		JTextField yPosTile1 = new JTextField(2);
		yPosTile1.setMaximumSize(new Dimension(200,24));
		JLabel yPosTile1Label = new JLabel("First Tile Y Position");
		yPosTile1Label.setLabelFor(yPosTile1);

		//x position field for tile 2
		JTextField xPosTile2 = new JTextField(2);
		xPosTile1.setMaximumSize(new Dimension(200,2));
		JLabel xPosTile2Label = new JLabel("Second Tile X Position");
		xPosTile2Label.setLabelFor(xPosTile2);

		//y position field for tile 2
		JTextField yPosTile2 = new JTextField(2);
		yPosTile1.setMaximumSize(new Dimension(200,2));
		JLabel yPosTile2Label = new JLabel("Second Tile Y Position");
		yPosTile2Label.setLabelFor(yPosTile2);        

		//setting up buttons for add and remove connection
		JButton addConnection = new JButton("Add Connection");
		addConnection.addActionListener(evt -> {
			try {
				connectionTilesActionCardActionPerformed(evt, xPosTile1.getText(), yPosTile1.getText(),xPosTile2.getText(),yPosTile2.getText());
			} catch (NumberFormatException | InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		JButton passButton = new JButton("Pass");
		passButton.addActionListener(this::passActionPerformed);

		//TODO: Setup connection options
		JLabel connections = new JLabel("Connections page");

		actionCardPanel.add(connections);
		actionCardPanel.add(xPosTile1Label);
		actionCardPanel.add(xPosTile1);
		actionCardPanel.add(yPosTile1Label);
		actionCardPanel.add(yPosTile1);
		actionCardPanel.add(xPosTile2Label);
		actionCardPanel.add(xPosTile2);
		actionCardPanel.add(yPosTile2Label);
		actionCardPanel.add(yPosTile2);
		actionCardPanel.add(addConnection);
		actionCardPanel.add(passButton);


		/*************************/

		//Add Save and Load Bar
		//connectTileActionCardPanel.setLayout(new BoxLayout(connectTileActionCardPanel, BoxLayout.Y_AXIS));
		connectTileActionCardPanel.setPreferredSize(new Dimension(500,360));
		connectTileActionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		connectTileActionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		connectTileActionCardPanel.add(saveAndLoadBar());
		connectTileActionCardPanel.add(actionCardPanel);

		//Add panels
		connectTileActionCardPanelContainer.add(board());
		connectTileActionCardPanelContainer.add(connectTileActionCardPanel);
		
	}
    
    private void playRemoveConnectionActionCard(){
    	removeConnectionActionCardPanelContainer.setLayout(new BoxLayout(removeConnectionActionCardPanelContainer, BoxLayout.X_AXIS));
    	
    	JPanel actionCardPanel = new JPanel();
		actionCardPanel.setLayout(new BoxLayout(actionCardPanel, BoxLayout.PAGE_AXIS));
		actionCardPanel.setMinimumSize(new Dimension(500, 360));
		actionCardPanel.setPreferredSize(new Dimension(500, 360));
		actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//x position field for tile 1
		JTextField xPosTile1 = new JTextField(2);
		xPosTile1.setMaximumSize(new Dimension(200,24));
		JLabel xPosTile1Label = new JLabel("First Tile X Position");
		xPosTile1Label.setLabelFor(xPosTile1);

		//y position field for tile 1
		JTextField yPosTile1 = new JTextField(2);
		yPosTile1.setMaximumSize(new Dimension(200,24));
		JLabel yPosTile1Label = new JLabel("First Tile Y Position");
		yPosTile1Label.setLabelFor(yPosTile1);

		//x position field for tile 2
		JTextField xPosTile2 = new JTextField(2);
		xPosTile1.setMaximumSize(new Dimension(200,2));
		JLabel xPosTile2Label = new JLabel("Second Tile X Position");
		xPosTile2Label.setLabelFor(xPosTile2);

		//y position field for tile 2
		JTextField yPosTile2 = new JTextField(2);
		yPosTile1.setMaximumSize(new Dimension(200,2));
		JLabel yPosTile2Label = new JLabel("Second Tile Y Position");
		yPosTile2Label.setLabelFor(yPosTile2);
		
		JButton removeConnection = new JButton("Remove Connection");
		removeConnection.addActionListener(evt -> {
			try {
				removeConnectionActionPerformed(evt, xPosTile1.getText().toString(), yPosTile1.getText().toString(),xPosTile2.getText().toString(),yPosTile2.getText().toString());
			} catch (NumberFormatException | InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		JButton passButton = new JButton("Pass");
		passButton.addActionListener(this::passActionPerformed);
		
		actionCardPanel.add(xPosTile1Label);
		actionCardPanel.add(xPosTile1);
		actionCardPanel.add(yPosTile1Label);
		actionCardPanel.add(yPosTile1);
		actionCardPanel.add(xPosTile2Label);
		actionCardPanel.add(xPosTile2);
		actionCardPanel.add(yPosTile2Label);
		actionCardPanel.add(yPosTile2);
		actionCardPanel.add(removeConnection);
		actionCardPanel.add(passButton);
		
		removeConnectionActionCardPanel.setPreferredSize(new Dimension(500,360));
		removeConnectionActionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		removeConnectionActionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		removeConnectionActionCardPanel.add(saveAndLoadBar());
		removeConnectionActionCardPanel.add(actionCardPanel);
		
		removeConnectionActionCardPanelContainer.add(board());
		removeConnectionActionCardPanelContainer.add(removeConnectionActionCardPanel);
    }
    
    private void playTeleportActionCard(){
    	teleportActionCardPanelContainer.setLayout(new BoxLayout(teleportActionCardPanelContainer, BoxLayout.X_AXIS));
    	
    	JPanel actionCardPanel = new JPanel();
		actionCardPanel.setLayout(new BoxLayout(actionCardPanel, BoxLayout.PAGE_AXIS));
		actionCardPanel.setMinimumSize(new Dimension(500, 360));
		actionCardPanel.setPreferredSize(new Dimension(500, 360));
		actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//x position field for new tile
		JTextField xPos = new JTextField(2);
		xPos.setMaximumSize(new Dimension(200,24));
		JLabel xPosLabel = new JLabel("X Position");
		xPosLabel.setLabelFor(xPos);

		//y position field for new tile
		JTextField yPos = new JTextField(2);
		yPos.setMaximumSize(new Dimension(200,24));
		JLabel yPosLabel = new JLabel("Y Position");
		yPosLabel.setLabelFor(yPos);
		
		JButton teleport = new JButton("Move");
		teleport.addActionListener(evt -> {
			try {
				teleportActionPerformed(evt, xPos.getText().toString(), yPos.getText());
			} catch (NumberFormatException | InvalidInputException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		JButton passButton = new JButton("Pass");
		passButton.addActionListener(this::passActionPerformed);
		
		actionCardPanel.add(xPosLabel);
		actionCardPanel.add(xPos);
		actionCardPanel.add(yPosLabel);
		actionCardPanel.add(yPos);
		actionCardPanel.add(teleport);
		actionCardPanel.add(passButton);
		
		teleportActionCardPanel.setPreferredSize(new Dimension(500,360));
		teleportActionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		teleportActionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		teleportActionCardPanel.add(saveAndLoadBar());
		teleportActionCardPanel.add(actionCardPanel);
		
		teleportActionCardPanelContainer.add(board());
		teleportActionCardPanelContainer.add(teleportActionCardPanel);
    }
    
    private void playLoseTurnActionCard() {

        //Set layout of container
        loseTurnActionCardPanelContainer.setLayout(new BoxLayout(loseTurnActionCardPanelContainer, BoxLayout.X_AXIS));

        //Tiles Options
        JPanel actionCardPanel = new JPanel();
        actionCardPanel.setMinimumSize(new Dimension(500, 360));
        actionCardPanel.setPreferredSize(new Dimension(500, 360));
        actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in tilesOptionsPanel JPanel

        //info
        JLabel PlayInfo = new JLabel("Current Turn: Play " + myPlayController.getCurrentPlayerIndex());
        JLabel cardInfo = new JLabel("Current Action: " + myPlayController.getActionCardInstructions());

        //button
        JButton actionButton = new JButton("Continue");
        actionButton.addActionListener(arg0 -> {
			try {
				loseTurnActionCardButtonPerformed(arg0);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        //action card UI
        actionCardPanel.add(PlayInfo);
        actionCardPanel.add(cardInfo);
        actionCardPanel.add(actionButton);
        /*************************/

        //Add save and load bar
        loseTurnActionCardPanel.setPreferredSize(new Dimension(500, 360));
        loseTurnActionCardPanel.setLayout(new BoxLayout(loseTurnActionCardPanel, BoxLayout.Y_AXIS));
        loseTurnActionCardPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        loseTurnActionCardPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        loseTurnActionCardPanel.add(saveAndLoadBar());
        loseTurnActionCardPanel.add(actionCardPanel);



        //Add to Container
        loseTurnActionCardPanelContainer.add(board());
        loseTurnActionCardPanelContainer.add(loseTurnActionCardPanel);

    }
    
    private void playNextPlayerRollsOneActionCard(){
        //Set layout of container
        loseTurnActionCardPanelContainer.setLayout(new BoxLayout(loseTurnActionCardPanelContainer, BoxLayout.X_AXIS));

        //Tiles Options
        JPanel actionCardPanel = new JPanel();
        actionCardPanel.setMinimumSize(new Dimension(500, 360));
        actionCardPanel.setPreferredSize(new Dimension(500, 360));
        actionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
      //info
        JLabel PlayInfo = new JLabel("Current Turn: Play " + myPlayController.getCurrentPlayerIndex());
        JLabel cardInfo = new JLabel("Current Action: " + myPlayController.getActionCardInstructions());

        //button
        JButton actionButton = new JButton("Continue");
        actionButton.addActionListener(arg0 -> {
			try {
				nextPlayerRollsOneActionCardButtonPerformed(arg0);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
      //Add save and load bar
        nextPlayerRollsOneActionCardPanel.setPreferredSize(new Dimension(500, 360));
        nextPlayerRollsOneActionCardPanel.setLayout(new BoxLayout(nextPlayerRollsOneActionCardPanel, BoxLayout.Y_AXIS));
        nextPlayerRollsOneActionCardPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        nextPlayerRollsOneActionCardPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        nextPlayerRollsOneActionCardPanel.add(saveAndLoadBar());
        nextPlayerRollsOneActionCardPanel.add(actionCardPanel);



        //Add to Container
        nextPlayerRollsOneActionCardPanelContainer.add(board());
        nextPlayerRollsOneActionCardPanelContainer.add(nextPlayerRollsOneActionCardPanel);
    }

    private void playWinTileHintActionCard() {
        //Set layout of container
        getWinTileHintActionCardPanelContainer.setLayout(new BoxLayout(getWinTileHintActionCardPanelContainer, BoxLayout.X_AXIS));
        //Action card options
        getWinTileHintActionCardPanel.setLayout(new BoxLayout(getWinTileHintActionCardPanel, BoxLayout.PAGE_AXIS));
        getWinTileHintActionCardPanel.setMinimumSize(new Dimension(500, 360));
        getWinTileHintActionCardPanel.setPreferredSize(new Dimension(500, 360));
        getWinTileHintActionCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        getWinTileHintActionCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        //Page title
        JLabel playWinTileHintAC = new JLabel("Select a Tile");
        JLabel description = new JLabel("If the selected tile is adjacent to the win tile you will be notified.");

        //Input area
        JTextField xPosition = new JTextField(2);
        JTextField yPosition = new JTextField( 2);

        JLabel xPosLabel = new JLabel("Input tile x position: ");
        JLabel yPosLabel = new JLabel("Input tile y position: ");

        xPosLabel.setLabelFor(xPosition);
        yPosLabel.setLabelFor(yPosition);

        //output text field
        JLabel outputText = new JLabel(" ");

        //Button to input coords
        JButton inputTile = new JButton("Guess");



        inputTile.addActionListener(evt -> {
                boolean isThere = getWinTileHintActionPerformed(evt,xPosition.getText(),yPosition.getText());
                if (isThere) {
                    outputText.setText("The selected tile is or is adjacent to the win tile");
                }
                else {
                    outputText.setText("The selected tile is not adjacent to the win tile");
                }
        });

        getWinTileHintActionCardPanel.add(playWinTileHintAC);
        getWinTileHintActionCardPanel.add(description);
        getWinTileHintActionCardPanel.add(xPosLabel);
        getWinTileHintActionCardPanel.add(xPosition);
        getWinTileHintActionCardPanel.add(yPosLabel);
        getWinTileHintActionCardPanel.add(yPosition);
        getWinTileHintActionCardPanel.add(inputTile);
        getWinTileHintActionCardPanel.add(outputText);

        //add Panels
        getWinTileHintActionCardPanelContainer.add(board());
        getWinTileHintActionCardPanelContainer.add(getWinTileHintActionCardPanel);
    }

    //Action Listeners

    private void loadGameActionPerformed(java.awt.event.ActionEvent evt, String game) throws NumberFormatException, InvalidInputException {

        //Must select a game
        if (game.equals("- Select Game -")) {
            //TODO: Error message
            refreshData();
            return;
        }

        //Change currentGame to selected one

        myPlayController.load(Integer.valueOf(game));

        // update visuals
        refreshData();
    }

    private void saveGameButtonPerformed(java.awt.event.ActionEvent evt){
        refreshData();
    }

    //Listeners


    //Yaniv Bronshtein + Kartik Misra
    private void rollDieButtonActionPerformed(java.awt.event.ActionEvent evt)  {
        myPlayController.rollDie();
        //gameMode = Game.Mode.GAME;
        refreshData();
    }

    private void rollDieActionCardButtonPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
        myPlayController.playRollDieActionCard();
        //gameMode = Game.Mode.GAME;
        refreshData();
    }

    private void loseTurnActionCardButtonPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
        myPlayController.playLoseTurnActionCard();
        //gameMode = Game.Mode.GAME;
        refreshData();
    }
    
    private void nextPlayerRollsOneActionCardButtonPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
    	myPlayController.playNextPlayerRollsOneActionCard();
    	refreshData();
    }
    
    private void connectionTilesActionCardActionPerformed(ActionEvent evt, String tile1x, String tile1y ,String tile2x ,String tile2y) throws NumberFormatException, InvalidInputException {

		try{
			Integer.parseInt(tile1x);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		try{
			Integer.parseInt(tile1y);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		try{
			Integer.parseInt(tile2x);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		try{
			Integer.parseInt(tile2y);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		myPlayController.playConnectTilesActionCard(Integer.parseInt(tile1x), Integer.parseInt(tile1y), Integer.parseInt(tile2x), Integer.parseInt(tile2y));

		//gameMode = Game.Mode.GAME;
		refreshData();
	}
    
    private void removeConnectionActionPerformed(ActionEvent evt, String tile1x, String tile1y ,String tile2x ,String tile2y) throws NumberFormatException, InvalidInputException {
    	try{
			Integer.parseInt(tile1x);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		try{
			Integer.parseInt(tile1y);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		try{
			Integer.parseInt(tile2x);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		try{
			Integer.parseInt(tile2y);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
		
		myPlayController.playRemoveConnectionActionCard(Integer.parseInt(tile1x), Integer.parseInt(tile1y), Integer.parseInt(tile2x), Integer.parseInt(tile2y));
		//gameMode = Mode.GAME;
		refreshData();
    }

    private void randomizePlayersActionCardButtonPerformed(ActionEvent evt) throws InvalidInputException {
        myPlayController.playRandomlyMovePlayersActionCard();
        refreshData();
    }
    
    private void teleportActionPerformed(ActionEvent evt, String tilex, String tiley) throws NumberFormatException, InvalidInputException{
    	try{
			Integer.parseInt(tilex);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
    	
    	try{
			Integer.parseInt(tiley);
		}catch(NumberFormatException e){
			//TODO: Error message
			refreshData();
			return;
		}
    	myPlayController.playTeleportActionCard(Integer.parseInt(tilex), Integer.parseInt(tiley));
    	//gameMode = Mode.GAME;
    	refreshData();
    }
    
    private void passActionPerformed(ActionEvent evt){
    	PlayController.getCurrentGame().setMode(Game.Mode.GAME);
    	refreshData();
    }

    private boolean getWinTileHintActionPerformed(ActionEvent evt, String tilex, String tiley) {
        boolean success = false;
        try {
            Integer.parseInt(tilex);
        } catch (NumberFormatException e) {
            //TODO: Error message
            refreshData();
            return false;
        }

        try {
            Integer.parseInt(tiley);
        } catch (NumberFormatException e) {
            //TODO: Error message
            refreshData();
            return false;
        }
        try {
            success = myPlayController.playWinTileHintActionCard(Integer.parseInt(tilex), Integer.parseInt(tiley));
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return false;
        }
        refreshData();
        return success;

    }



}
