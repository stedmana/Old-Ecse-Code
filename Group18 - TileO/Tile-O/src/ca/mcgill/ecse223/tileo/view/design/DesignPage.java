package ca.mcgill.ecse223.tileo.view.design;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.view.BoardVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DesignPage extends JFrame {

	DesignController myDesignController = new DesignController();
	
    //Design Mode Enum
    private DesignMode designMode = DesignMode.NEW;

    private String error = "";

    //added counter for Cards
    private JLabel cardCounterLabel = new JLabel("0");

    //New Game Panel
    private JPanel newGamePanel = new JPanel();

    //Tiles Panel
    private JPanel tilesPanelContainer = new JPanel();
    private JPanel tilesPanel = new JPanel();

    //Connection Panel
    private JPanel connectionsPanelContainer = new JPanel();
    private JPanel connectionsPanel = new JPanel();

    //Players Panel
    private JPanel playersPanelContainer = new JPanel();
    private JPanel playersPanel = new JPanel();

    //Action Cards Panel
    private JPanel actionCardsPanelContainer = new JPanel();
    private JPanel actionCardsPanel = new JPanel();
    

    public DesignPage() {
        setTitle("Tile-O");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);

        initComponents();
        refreshData();
    }

    private void initComponents() {
        newGame();
        designConnections();
        designTiles();
        designPlayers();
        designActionCards();
    }


    /**
     * By: Jackson Li
     * Method switches tabs  by first checking the current mode, and then calls invalidate() and validate() to redraw */
    public void refreshData() {

        reinitialize();

        if (designMode == DesignMode.NEW) {
            setContentPane(newGamePanel);
        } else if (designMode == DesignMode.TILES) {
            setContentPane(tilesPanelContainer);
        } else if (designMode == DesignMode.CONNECTOIONS){
            setContentPane(connectionsPanelContainer);
        } else if (designMode == DesignMode.ACTIONS){
            setContentPane(actionCardsPanelContainer);
        } else if (designMode == DesignMode.PLAYERS){
            setContentPane(playersPanelContainer);
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

        DesignController.save();
        //Todo: Add back design board and call removeAll() here
        tilesPanelContainer.removeAll();
        tilesPanel.removeAll();

        connectionsPanelContainer.removeAll();
        connectionsPanel.removeAll();

        actionCardsPanelContainer.removeAll();
        actionCardsPanel.removeAll();

        playersPanelContainer.removeAll();
        playersPanel.removeAll();

        initComponents();
    }


    // JPanel Layouts
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
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel boardOptionsPanel = new JPanel();
        boardOptionsPanel.setPreferredSize(new Dimension(300,200));
        //boardOptionsPanel.setBackground(Color.ORANGE);
        JButton switchToPlay = new JButton("Switch to Play");
        switchToPlay.addActionListener(this:: switchToPlayActionPerformed);
        boardOptionsPanel.add(switchToPlay);
        
        //setup selected tile output
        //Tile selected = boardPanel.getSelected();
        //JLabel selectedTile = new JLabel("The selected tile is at: x: " + selected.getX() + " y: " + selected.getY());

        /**
         * experimental selection display
         */

        /*************************/

        JLabel label = new JLabel("Game Board");
        label.setPreferredSize(new Dimension(300, 40));

        boardContainer.add(label);
        boardContainer.add(boardPanel);

        boardContainer.add(boardOptionsPanel);
        //boardContainer.setBackground(Color.BLUE);
        //boardContainer.add(selectedTile);

        return boardContainer;
    }

    private void switchToPlayActionPerformed(ActionEvent actionEvent) {
        TileOApplication.changeMode();
        DesignController.getCurrentGame().setMode(Game.Mode.GAME);
    }

    private JPanel tabBar() {
        JPanel tabBar = new JPanel();

        tabBar.setMaximumSize(new Dimension(500,40));
        tabBar.setPreferredSize(new Dimension(500,40));

        //Create Tab bar buttons
        JButton tilesButton = new JButton ("Tiles");
        JButton playersButton = new JButton ("Players");
        JButton actionCardsButton = new JButton ("Action Cards");
        JButton connectionsButton = new JButton ("Connections");

        connectionsButton.addActionListener(this::connectionsTabBarButtonPerformed);
        tilesButton.addActionListener(this::tilesTabBarButtonPerformed);
        playersButton.addActionListener(this::playersTabBarButtonPerformed);
        actionCardsButton.addActionListener(this::actionCardsTabBarButtonPerformed);

        //Setup Tab bar
        tabBar.add(tilesButton);
        tabBar.add(connectionsButton);
        tabBar.add(actionCardsButton);
        tabBar.add(playersButton);

        //tabBar.setBackground(Color.RED);

        return tabBar;
    }

    private JPanel saveAndLoadBar(){
        JPanel saveAndLoadBar = new JPanel();

        saveAndLoadBar.setPreferredSize(new Dimension(500,40));
        //saveAndLoadBar.setBackground(Color.orange);

        // Game selector by index
        JComboBox<String> designGameList = new JComboBox<>();
        designGameList.setModel(new DefaultComboBoxModel<String>() {
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

        designGameList.addItem("- Select Design -");
        for(int i = 0; i < DesignController.getNumberOfGames(); i++){
            if (TileOApplication.getTileO().getGame(i).getMode() == Game.Mode.DESIGN)
        		designGameList.addItem(Integer.toString(i));
        }
        designGameList.setSelectedIndex(0);
        designGameList.setSize(20, designGameList.getPreferredSize().height);

        // Load Game Button
        JButton loadGameButton = new JButton();
        loadGameButton.setText("Load");
        loadGameButton.addActionListener(evt -> {
			try {
				loadGameActionPerformed(evt, designGameList.getSelectedItem().toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        //Save Game Button

        JButton saveGameButton = new JButton ("Save Game "+ DesignController.getCurrentGameIndex());
        saveGameButton.addActionListener(this::saveGameButtonPerformed);


        //Setup bar

        saveAndLoadBar.add(saveGameButton);
        saveAndLoadBar.add(loadGameButton);
        saveAndLoadBar.add(designGameList);

        return saveAndLoadBar;
    }

    private void newGame() {

        // Create Panel
        newGamePanel = new JPanel();

        // Top Label
        JLabel topLabel = new JLabel();
        topLabel.setText("<html><b>Create and design a new game</b></html");

        // Load label
        JLabel loadGameLabel = new JLabel();
        loadGameLabel.setText("Load a Previous Design");

        // Load label
        JLabel loadDesignLabel = new JLabel();
        loadGameLabel.setText("Load a Previous Game");
        
        // game selector by index
        JComboBox<String> gameList = new JComboBox<>();
        gameList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 2L;
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

        // design selector by index
        JComboBox<String> designList = new JComboBox<>();
        designList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 2L;
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
        designList.addItem("- Select Design -");
        for( int i = 0; i < DesignController.getNumberOfGames(); i++){
        	if (TileOApplication.getTileO().getGame(i).getMode() == Game.Mode.DESIGN)
        		designList.addItem(Integer.toString(i));
        	else
        		gameList.addItem(Integer.toString(i));
        }

        gameList.setSelectedIndex(0);
        gameList.setSize(40, gameList.getPreferredSize().height);

        designList.setSelectedIndex(0);
        designList.setSize(40, gameList.getPreferredSize().height);
        
        // Load Game Button

        JButton loadGameButton = new JButton();
        loadGameButton.setText("Load Game");
        loadGameButton.addActionListener(evt -> {
			try {
				loadGameActionPerformed(evt, gameList.getSelectedItem().toString());
			} catch (NumberFormatException | InvalidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        JButton loadDesignButton = new JButton();
        loadDesignButton.setText("Load Design");
        loadDesignButton.addActionListener(evt -> {
			try {
				loadGameActionPerformed(evt, designList.getSelectedItem().toString());
			} catch (NumberFormatException | InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
        //New Game label
        JLabel newGameLabel = new JLabel();
        newGameLabel.setText("Start a New Design");

        // Number of players selector
        JComboBox<String> playerList = new JComboBox<>();
        playerList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 3L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!"- Select Players -".equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });

        playerList.addItem("- Select Players -");
        playerList.addItem("2");
        playerList.addItem("3");
        playerList.addItem("4");
        playerList.setSelectedIndex(0);
        playerList.setSize(40, playerList.getPreferredSize().height);

        // New Game Button
        JButton newGameButton = new JButton();
        newGameButton.setText("New");
        newGameButton.addActionListener(evt -> newGameActionPerformed(evt, playerList.getSelectedItem().toString()));

        //Separator
        JSeparator jSeparatorLoad = new JSeparator();
        JSeparator jSeparatorNew = new JSeparator();

        //Layout
        GroupLayout layout = new GroupLayout(newGamePanel);
        newGamePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        //Horizontal Layout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(topLabel)
                                .addComponent(jSeparatorLoad)
                                .addComponent(loadGameLabel)
                                .addComponent(gameList)
                                .addComponent(loadGameButton)
                                .addComponent(loadDesignLabel)
                                .addComponent(designList)
                                .addComponent(loadDesignButton)
                                .addComponent(jSeparatorNew)
                                .addComponent(newGameLabel)
                                .addComponent(playerList)
                                .addComponent(newGameButton))
        );

        //Vertical Layout
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(topLabel)
                        .addComponent(jSeparatorLoad)
                        .addComponent(loadGameLabel)
                        .addComponent(gameList)
                        .addComponent(loadGameButton)
                        .addComponent(loadDesignLabel)
                        .addComponent(designList)
                        .addComponent(loadDesignButton)
                        .addComponent(jSeparatorNew)
                        .addComponent(newGameLabel)
                        .addComponent(playerList)
                        .addComponent(newGameButton)
        );
    }

    private void designTiles() {
        //Set layout of container
        tilesPanelContainer.setLayout(new BoxLayout(tilesPanelContainer, BoxLayout.X_AXIS));

        //Tiles Panel
        tilesPanel.setPreferredSize(new Dimension(500, 400));
        tilesPanel.setLayout(new FlowLayout());
        //tilesPanel.setLayout(new BoxLayout(tilesPanel, BoxLayout.Y_AXIS));
        tilesPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        tilesPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        //Tiles Options
        JPanel tilesOptionsPanel = new JPanel();
        tilesOptionsPanel.setMinimumSize(new Dimension(500, 360));
        tilesOptionsPanel.setPreferredSize(new Dimension(500, 360));
        tilesOptionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tilesOptionsPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in tilesOptionsPanel JPanel

        //xPos Field
        JTextField xPos = new JTextField(2);
        xPos.setMaximumSize(new Dimension(200,24));
        JLabel xPosLabel = new JLabel("Tile X Position:");
        xPosLabel.setLabelFor(xPos);

        //yPos Field
        JTextField yPos = new JTextField(2);
        yPos.setMaximumSize(new Dimension(200,24));
        JLabel yPosLabel = new JLabel("Tile Y Position:");
        yPosLabel.setLabelFor(yPos);

        //Tile type box
        JComboBox<String> tileType = new JComboBox<>();
        tileType.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 4L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!"- Select Tile Type -".equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });

        tileType.addItem("- Select Tile Type -");
        tileType.addItem("Regular Tile");
        tileType.addItem("Action Tile");
        tileType.addItem("Win Tile");
        tileType.setSelectedIndex(0);
        tileType.setPreferredSize(new Dimension(260, tileType.getPreferredSize().height));

        //Create actionReset
        JTextField actionReset = new JTextField(2);
        actionReset.setMaximumSize(new Dimension(200,24));
        JLabel actionResetLabel = new JLabel("Inactivity Period:");
        actionResetLabel.setLabelFor(actionReset);
        actionReset.setVisible(false);
        actionResetLabel.setVisible(false);

        tileType.addActionListener (evt -> {
            if (tileType.getSelectedIndex() == 2){
                //Show action reset
                actionReset.setVisible(true);
                actionResetLabel.setVisible(true);
            }
            else {
                //Hide action reset
                actionReset.setVisible(false);
                actionResetLabel.setVisible(false);
            }
        });

        //Add Tile button
        JButton addTile = new JButton("Add Tile");
        addTile.addActionListener(evt -> addTileActionPerformed(evt, actionReset.getText(), xPos.getText(), yPos.getText(),
                tileType.getSelectedItem().toString()));

        //Remove Tile button
        JButton removeTile = new JButton("Remove Tile");
        removeTile.addActionListener(evt -> removeTileActionPerformed(evt, xPos.getText(), yPos.getText()));

        tilesOptionsPanel.add(xPosLabel);
        tilesOptionsPanel.add(xPos);
        tilesOptionsPanel.add(yPosLabel);
        tilesOptionsPanel.add(yPos);
        tilesOptionsPanel.add(actionResetLabel);
        tilesOptionsPanel.add(actionReset);
        tilesOptionsPanel.add(tileType);
        tilesOptionsPanel.add(addTile);
        tilesOptionsPanel.add(removeTile);

        /*************************/

        //Add Tab bar
        tilesPanel.add(saveAndLoadBar());
        tilesPanel.add(tabBar());

        //tilesPanel.add(save...

        //Add Options Panel
        tilesPanel.add(tilesOptionsPanel);

        //Color panels for debugging
        //tilesOptionsPanel.setBackground(Color.GREEN);
        //tilesPanel.setBackground(Color.PINK);

        //Add to Container
        tilesPanelContainer.add(board());
        tilesPanelContainer.add(tilesPanel);
    }

    private void designConnections(){
        //Set layout of container
        connectionsPanelContainer.setLayout(new BoxLayout(connectionsPanelContainer, BoxLayout.X_AXIS));

        //Connection Options
        JPanel connectionsOptionsPanel = new JPanel();
        connectionsOptionsPanel.setMinimumSize(new Dimension(500, 360));
        connectionsOptionsPanel.setPreferredSize(new Dimension(500, 360));
        connectionsOptionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        connectionsOptionsPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in connectionOptionsPanel JPanel

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
        addConnection.addActionListener(evt -> addConnectionActionPerformed(evt, xPosTile1.getText(), yPosTile1.getText(),xPosTile2.getText(),yPosTile2.getText()));

        JButton removeConnection = new JButton("Remove Connection");
        removeConnection.addActionListener(evt -> removeConnectionActionPerformed(evt, xPosTile1.getText(), yPosTile1.getText(),xPosTile2.getText(),yPosTile2.getText()));
        //TODO: Setup connection options
        JLabel connections = new JLabel("Connections page");


        connectionsOptionsPanel.add(connections);
        connectionsOptionsPanel.add(xPosTile1Label);
        connectionsOptionsPanel.add(xPosTile1);
        connectionsOptionsPanel.add(yPosTile1Label);
        connectionsOptionsPanel.add(yPosTile1);
        connectionsOptionsPanel.add(xPosTile2Label);
        connectionsOptionsPanel.add(xPosTile2);
        connectionsOptionsPanel.add(yPosTile2Label);
        connectionsOptionsPanel.add(yPosTile2);
        connectionsOptionsPanel.add(addConnection);
        connectionsOptionsPanel.add(removeConnection);

        /*************************/

        //Add Tab bar
        connectionsPanel.setLayout(new FlowLayout());
        connectionsPanel.setPreferredSize(new Dimension(500,400));
        connectionsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        connectionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        connectionsPanel.add(saveAndLoadBar());
        connectionsPanel.add(tabBar());
        connectionsPanel.add(connectionsOptionsPanel);

        //Add panels
        connectionsPanelContainer.add(board());
        connectionsPanelContainer.add(connectionsPanel);

    }

    private void designPlayers(){
        //Set layout of container
        playersPanelContainer.setLayout(new BoxLayout(playersPanelContainer, BoxLayout.X_AXIS));

        //Connection Options
        JPanel playersOptionsPanel = new JPanel();
        playersOptionsPanel.setMinimumSize(new Dimension(500, 360));
        playersOptionsPanel.setPreferredSize(new Dimension(500, 360));
        playersOptionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        playersOptionsPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in playersOptionsPanel JPanel

        //TODO: Setup player options
        JLabel players = new JLabel("Players page");
        playersOptionsPanel.add(players);

        JComboBox<String> playerList = new JComboBox<>();
        playerList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!"- Select Player Type -".equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });

        playerList.addItem("- Select Players _");

        Game currentGame = TileOApplication.getTileO().getCurrentGame();

        if (currentGame != null)
            for(int i = 1; i <= currentGame.numberOfPlayers(); i++) {
                playerList.addItem(Integer.toString(i));
            }
        playerList.setSelectedIndex(0);
        playerList.setSize(20, playerList.getPreferredSize().height);

        //xPos Field
        JTextField xPos = new JTextField(2);
        xPos.setMaximumSize(new Dimension(200,24));
        JLabel xPosLabel = new JLabel("Tile X Position");
        xPosLabel.setLabelFor(xPos);

        //yPos Field
        JTextField yPos = new JTextField(2);
        yPos.setMaximumSize(new Dimension(200,24));
        JLabel yPosLabel = new JLabel("Tile Y Position");
        yPosLabel.setLabelFor(yPos);

        JButton selectPlayerButton = new JButton();
        selectPlayerButton.setText("Identify Player");
        selectPlayerButton.addActionListener(evt -> addPlayerActionPerformed(evt, xPos.getText(), yPos.getText(),
                playerList.getSelectedItem().toString()));

        //added counter for Cards
//        JLabel cardCounterLabel = new JLabel();


        playersOptionsPanel.add(xPosLabel);
        playersOptionsPanel.add(xPos);
        playersOptionsPanel.add(yPosLabel);
        playersOptionsPanel.add(yPos);
        playersOptionsPanel.add(playerList);
        playersOptionsPanel.add(selectPlayerButton);




        /*************************/

        //Add Tab bar
        playersPanel.setLayout(new FlowLayout());
        playersPanel.setPreferredSize(new Dimension(500,400));
        playersPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        playersPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        playersPanel.add(saveAndLoadBar());
        playersPanel.add(tabBar());
        playersPanel.add(playersOptionsPanel);

        //Add panels
        playersPanelContainer.add(board());
        playersPanelContainer.add(playersPanel);

    }

    private void designActionCards(){
        //Set layout of container
        actionCardsPanelContainer.setLayout(new BoxLayout(actionCardsPanelContainer, BoxLayout.X_AXIS));

        //Connection Options
        JPanel actionCardsOptionsPanel = new JPanel();
        actionCardsOptionsPanel.setMinimumSize(new Dimension(500, 360));
        actionCardsOptionsPanel.setPreferredSize(new Dimension(500, 360));
        actionCardsOptionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionCardsOptionsPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /** YOUR CODE GOES HERE **/
        //Draw things in actionCardOptionsPanel JPanel

        //TODO: Setup actionCards options
        JLabel actionCards = new JLabel("Action Cards page");
        actionCardsOptionsPanel.add(actionCards);

        /*************************/

        JComboBox<String> actionCardList = new JComboBox<>();
        actionCardList.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!"- Select Action Card -".equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });

        
        
        actionCardList.addItem("- Select Action Card -");
        actionCardList.addItem("Remove Connection Action Card");
        actionCardList.addItem("Add Connection Action Card");
        actionCardList.addItem("Lose Turn Action Card");
        actionCardList.addItem("Teleport Action Card");
        actionCardList.addItem("Roll Die Action Card");
        actionCardList.addItem("Randomly Move Players Action Card");
        actionCardList.addItem("Win Tile Hint Action Card");
        actionCardList.addItem("Next Player Rolls One");

        actionCardList.setSelectedIndex(0);
        actionCardList.setSize(20, actionCardList.getPreferredSize().height);

        JTextField numberField = new JTextField(2);
        numberField.setMaximumSize(new Dimension(200,24));
        JLabel numberFieldLabel = new JLabel("Number of cards");
        numberFieldLabel.setLabelFor(numberField);

        JButton selectCardButton = new JButton();
        selectCardButton.setText("Add Card");
        selectCardButton.addActionListener(evt -> addCardActionPerformed(evt, numberField.getText(),
                actionCardList.getSelectedItem().toString()));

    
        
        actionCardsOptionsPanel.add(actionCardList);
        actionCardsOptionsPanel.add(numberField);
        actionCardsOptionsPanel.add(selectCardButton);
        actionCardsOptionsPanel.add(cardCounterLabel);


        //Add Tab bar
        actionCardsPanel.setLayout(new FlowLayout());
        actionCardsPanel.setPreferredSize(new Dimension(500,400));
        actionCardsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        actionCardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionCardsPanel.add(saveAndLoadBar());
        actionCardsPanel.add(tabBar());
        actionCardsPanel.add(actionCardsOptionsPanel);

        //Add panels
        actionCardsPanelContainer.add(board());
        actionCardsPanelContainer.add(actionCardsPanel);

    }

    // Action Listeners
    private void newGameActionPerformed(java.awt.event.ActionEvent evt, String players) {

        //Must select number of players
        if (players.equals("- Select Players -")) {
            //TODO: Error message
            refreshData();
            return;
        }

        //Create game with players
        DesignController.createGame(Integer.parseInt(players));

        //Set new game mode
        designMode = DesignMode.TILES;

        // update visuals
        refreshData();
    }

    private void loadGameActionPerformed(java.awt.event.ActionEvent evt, String game) throws NumberFormatException, InvalidInputException {

        //Must select a game
        if (game.equals("- Select Game -")) {
            //TODO: Error message
            refreshData();
            return;
        }

        //Change currentGame to selected one

        myDesignController.load(Integer.valueOf(game));

        designMode = DesignMode.TILES;

        // update visuals
        refreshData();
    }

    private void saveGameButtonPerformed(java.awt.event.ActionEvent evt){
        refreshData();
    }

    private void addTileActionPerformed(java.awt.event.ActionEvent evt, String actionReset, String x, String y, String type){

        //Must select type of tile
        if (type.equals("- Select Tile Type -")) {

            try{
                Integer.parseInt(x);
            }catch(NumberFormatException e){
                //TODO: Error message
                refreshData();
                return;
            }

            try{
                Integer.parseInt(y);
            }catch(NumberFormatException e){
                //TODO: Error message
                refreshData();
                return;
            }

            try{
                Integer.parseInt(actionReset);
            }catch(NumberFormatException e){
                //TODO: Error message
                refreshData();
                return;
            }

            //TODO: Error message
            refreshData();
            return;
        }

        if (type.equals("Regular Tile")) {
            DesignController.addTile(Integer.parseInt(x), Integer.parseInt(y), DesignController.TileType.NORMAL, 0);
        }

        if (type.equals("Action Tile")) {
            DesignController.addTile(Integer.parseInt(x), Integer.parseInt(y), DesignController.TileType.ACTION, Integer.parseInt(actionReset));
        }

        if (type.equals("Win Tile")) {
            DesignController.addTile(Integer.parseInt(x), Integer.parseInt(y), DesignController.TileType.WIN, 0);
        }

        refreshData();
    }

    private void removeTileActionPerformed(java.awt.event.ActionEvent evt, String x, String y){
        try{
            Integer.parseInt(x);
        }catch(NumberFormatException e){
            //TODO: Error message
            refreshData();
            return;
        }

        try{
            Integer.parseInt(y);
        }catch(NumberFormatException e){
            //TODO: Error message
            refreshData();
            return;
        }

        DesignController.removeTile(Integer.parseInt(x), Integer.parseInt(y));

        refreshData();
    }

    //Tab Bar Listeners
    private void connectionsTabBarButtonPerformed(java.awt.event.ActionEvent evt){
        designMode = DesignMode.CONNECTOIONS;
        
        refreshData();
    }

    private void playersTabBarButtonPerformed(java.awt.event.ActionEvent evt){
        designMode = DesignMode.PLAYERS;
        refreshData();
    }

    private void actionCardsTabBarButtonPerformed(java.awt.event.ActionEvent evt){
        designMode = DesignMode.ACTIONS;
        refreshData();
    }

    private void addPlayerActionPerformed (java.awt.event.ActionEvent evt, String x, String y, String player){
        int xInt = Integer.parseInt(x);
        int yInt = Integer.parseInt(y);
        int playerInt = Integer.parseInt(player) - 1;
        DesignController.setPlayerStartTile(playerInt, xInt, yInt);

        refreshData(); //added line
    }

    private void addConnectionActionPerformed(java.awt.event.ActionEvent evt, String tile1x, String tile1y ,String tile2x ,String tile2y) {

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
        try {        
        	DesignController.addConnection(Integer.parseInt(tile1x), Integer.parseInt(tile1y), Integer.parseInt(tile2x), Integer.parseInt(tile2y));
        }
        catch (NullPointerException e) {
        	error = error + e.getMessage();
        }
        refreshData();
    }

    private void removeConnectionActionPerformed(java.awt.event.ActionEvent evt, String tile1x, String tile1y ,String tile2x ,String tile2y) {
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
        try {
        	DesignController.removeConnection(Integer.parseInt(tile1x), Integer.parseInt(tile1y), Integer.parseInt(tile2x), Integer.parseInt(tile2y));
        }
        catch (NullPointerException e){
        	error = error + e.getMessage();
        }
        refreshData();
    }

    private void addCardActionPerformed(java.awt.event.ActionEvent evt, String number, String cardType ){

    	int intNumber;
        //Must select type of tile to add
        if (cardType.equals("- Select Tile Type -")) {
            //TODO: Error message
            refreshData();
            return;
        }
        else {
        	try{
        		intNumber = Integer.parseInt(number) ;
        	}
        	catch (Exception e) {
				intNumber = 1;
			}
        	for(int i=0; i<intNumber; i++)
        		DesignController.addCard(cardType);
        }


 //       int currentCount = Integer.parseInt(cardCounterLabel.getText());
 //       int newCount = currentCount + 1; //update count
        cardCounterLabel.setText(Integer.toString(DesignController.getNumberofCards())); //update
        refreshData();

        System.out.println(TileOApplication.getTileO().getCurrentGame().getDeck().numberOfCards());
    }

    private void tilesTabBarButtonPerformed(java.awt.event.ActionEvent evt){
        designMode = DesignMode.TILES;
        refreshData();
    }

    //Enum for different design modes
    public enum DesignMode {
        NEW, LOAD, TILES, CONNECTOIONS, PLAYERS, ACTIONS
    }

}



