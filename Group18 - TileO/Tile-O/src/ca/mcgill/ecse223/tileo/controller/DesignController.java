package ca.mcgill.ecse223.tileo.controller;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.util.ArrayList;
import java.util.List;

public final class DesignController {

    PlayController myPlayController = new PlayController();

    public enum TileType {NORMAL, ACTION, WIN}

    public enum CardType {REMOVECONNECTION, ADDCONNECTION, LOSETURN, TELEPORT, ROLLDIE}

    /**
     *
     * Gets total number of games
     *
     * @apiNote Author : Jackson
     */
    public static int getNumberOfGames() {
        return 	TileOApplication.load().getGames().size();
    }


    /**
     *
     * Gets current game from model
     *
     * @apiNote Author : Jackson
     */
    public static Game getCurrentGame() {
        return TileOApplication.getTileO().getCurrentGame();
    }


    /**
     *
     * Gets index of current game from model
     *
     * @apiNote Author : Jackson
     */
    public static int getCurrentGameIndex() {
        return TileOApplication.getTileO().indexOfGame(getCurrentGame());
    }


    /**
     *
     * Gets current player from model
     *
     * @apiNote Author : Jackson
     */
    public static Player getCurrentPlayer() {
        return TileOApplication.getTileO().getCurrentGame().getCurrentPlayer();
    }


    /**
     *
     * Gets index of current player from model
     *
     * @apiNote Author : Jackson
     */
    public static int getCurrentPlayerIndex() {
        return TileOApplication.getTileO().getCurrentGame().indexOfPlayer(getCurrentPlayer());
    }


    public static int getNumberofCards(){
    	return TileOApplication.getTileO().getCurrentGame().getDeck().numberOfCards();
    }

    /**
     *
     * Creates a new game designer with specified number of players and sets it as current game in TileO
     *
     * @param playerNumber number of players for the game
     * @return wasCreated
     * @apiNote Author : Connor Fowlie
     */
    public static boolean createGame(int playerNumber){

        boolean wasCreated = false;

        TileO tileO = TileOApplication.getTileO();

        //Create game instance
        Game game = new Game(0, tileO);

        tileO.addGame(game);
        tileO.setCurrentGame(game);
        if(tileO.getCurrentGame() == game){
            wasCreated = true;
        }

        //Set to design mode
        game.setMode(Game.Mode.DESIGN);

        //Add players for game
        for (int i = 1; i < playerNumber+1; i++) {
            Player player = new Player(i,game);
        }

        return wasCreated;
    }

    /**
     *
     * Creates a new tile with specified type at position (x,y) for a the current game
     *
     * @param x x-position of the tile
     * @param y y-position of the tile
     * @param tileType type of the tile
     * @return wasCreated
     * @apiNote Author : Connor Fowlie
     */
    public static boolean addTile(int x, int y, TileType tileType, int resetTime) {

        boolean wasCreated = false;

        Game game = TileOApplication.getTileO().getCurrentGame();

        //Must not be tile already at pos
        if(DesignController.findTileByPos(x,y) != null){
            return false;
        }

        //Create Tile by type
        if (tileType == TileType.ACTION){
            ActionTile actionTile = new ActionTile(x, y, game, resetTime);
            game.addTile(actionTile);
            wasCreated = true;
        }

        if (tileType == TileType.WIN){
            //Create Win Tile
            WinTile winTile = new WinTile(x,y, game);
            game.addTile(winTile);
            game.setWinTile(winTile);
            wasCreated = true;
        }

        if (tileType == TileType.NORMAL){
            //Create Normal Tile
            NormalTile normalTile = new NormalTile(x,y,game);
            game.addTile(normalTile);
            wasCreated = true;
        }

        return wasCreated;
    }

    /**
     *
     * Removes a tile at a position (x,y) for a the current game.
     *
     * @param x x-position of the tile
     * @param y y-position of the tile
     * @return wasRemoved
     * @apiNote Author : Connor Fowlie
     */
    public static boolean removeTile(int x, int y) {

        Game game = TileOApplication.getTileO().getCurrentGame();

        boolean wasRemoved = false;

        Tile tile = findTileByPos(x,y);

        if (tile != null){
            game.removeTile(tile);
            wasRemoved = true;
        }

        return wasRemoved;
    }

    /**
     *
     * Finds tile by position and return it for the current game. If tile not found at position, returns null
     *
     * @param x x-position for tile
     * @param y y-position for tile
     * @return tile
     * @apiNote Author : Connor Fowlie
     */
    public static Tile findTileByPos(int x, int y) {
        Game game = TileOApplication.getTileO().getCurrentGame();

        //Find tile by pos
        List<Tile> tiles = game.getTiles();

        for (Tile tile : tiles) {
            if (tile.getX() == x && tile.getY() == y) {
                return tile;
            }
        }

        return null;
    }


    /**
     *
     * Creates a connection between two tiles based on their x and y coordinates. If this connection cannot be created it returns false
     *
     * @param tile1x first tile selected x position
     * @param tile1y first tile y position
     * @param tile2x second tile x position selected to add conection to
     * @param tile2y second tile y
     * @return success
     * @apiNote Author : Alex Stedman
     */
    public static boolean addConnection(int tile1x, int tile1y, int tile2x, int tile2y) {
        boolean success = false;
        Game game = TileOApplication.getTileO().getCurrentGame();
		/*tile1x++;
        tile1y++;
        tile2x++;
        tile2y++;
		 */
        if ((Math.abs(tile1x-tile2x) + Math.abs(tile1y-tile2y)) != 1) {
            throw new NullPointerException("The tiles selected must be adjacent");
        }
        //creates tiles based on their x and y positions
        Tile tile1 = findTileByPos(tile1x,tile1y);
        Tile tile2 = findTileByPos(tile2x, tile2y);
        if (tile1 == null || tile2 == null) {
            throw new NullPointerException("There is no tile at one of the selected coordinates");
        }

        //creates connection object using the current game adding the connections and tiles involved to the necessary places
        Connection newConnection = new Connection(game);
        newConnection.addTile(tile1);
        newConnection.addTile(tile2);
        //System.out.println("this is in controller" + game.getTiles().size());
        //System.out.println("was successful: " + success);

        return success;



    }

    /**
     *
     * Removes a connection between two tiles. If this connection cannot be removed it returns false
     *
     * @param tile1x first tile selected x position
     * @param tile1y first tile y position
     * @param tile2x second tile x position selected to add conection to
     * @param tile2y second tile y
     * @return success
     * @apiNote Author : Alex Stedman
     */
    public static boolean removeConnection(int tile1x, int tile1y, int tile2x, int tile2y) {
        Game game = TileOApplication.getTileO().getCurrentGame();

        //creates tiles based on their x and y positions
        Tile tile1 = findTileByPos(tile1x,tile1y);
        Tile tile2 = findTileByPos(tile2x, tile2y);
        if ((Math.abs(tile1x +tile2x) + Math.abs(tile1y + tile2y)) != 1) {
            throw new NullPointerException("The tiles selected must be adjacent");
        }
        if ((tile1 == null) || (tile2 == null)) {
            return false;
        }
        //checks if connection exists, if it does not false is returned
        if (!doesConnectionExist(tile1, tile2)) {
            return false;
        }
        //finds connection based on tiles
        Connection currentConnection = findConnectionByTiles(tile1,tile2);
        //removes connection from tiles and game, then deletes connection
        tile1.removeConnection(currentConnection);
        tile2.removeConnection(currentConnection);
        game.removeConnection(currentConnection);
        currentConnection.delete();
        return true;

    }

    /**
     *
     *  Takes input of two tiles and returns if there is currently a connection between them
     *
     * @param tile1
     * @param tile2
     * @return boolean indicating if connection between two tiles exists (true if it does)
     * @apiNote Author : Alex Stedman
     */
    private static boolean doesConnectionExist(Tile tile1, Tile tile2) {
        Game game = TileOApplication.getTileO().getCurrentGame();

        List<Connection> connections = game.getConnections();
        for (int i = 0; i < connections.size(); i++) {
            List<Tile> currentConnection = connections.get(i).getTiles();
            if (currentConnection.contains(tile1) && currentConnection.contains(tile2)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Finds the connection associated with the two pieces
     *
     * @param tile1
     * @param tile2
     * @return returnConnection
     * @apiNote Author : Alex Stedman
     */
    private static Connection findConnectionByTiles(Tile tile1, Tile tile2) {
        Game game = TileOApplication.getTileO().getCurrentGame();
        Connection returnConnection = null;
        List<Connection> connections = game.getConnections();
        for (int i = 0; i < connections.size(); i++) {
            List<Tile> currentConnection = connections.get(i).getTiles();
            if (currentConnection.contains(tile1) && currentConnection.contains(tile2)) {
                returnConnection = connections.get(i);
            }
        }
        return returnConnection;

    }

    /**
     *
     * Returns the win Tile
     *
     * @return
     * @apiNote Author : Alex Stedman
     */
    public static WinTile getWinTile() {
        Game game = TileOApplication.getTileO().getCurrentGame();

        return game.getWinTile();

    }


    /**
     *
     * Saves the design
     *
     * @param
     * @return
     * @apiNote Author : Jackson Li 260681801
     */

    public static void save(){

        TileOApplication.save();
    }

    /**
     *
     * Loads a game
     *
     * @param index - index of the game in TileO's game list
     * @return currentGame - returns desired game
     * @apiNote Author : Jackson Li 260681801
     */

    public void load(int index) throws InvalidInputException {
        Game selectedGame = TileOApplication.getTileO().getGame(index);
        if (selectedGame.getMode() == Mode.DESIGN){
            TileOApplication.getTileO().setCurrentGame(selectedGame);
        }
        else{
            myPlayController.load(index);
            TileOApplication.changeMode();

        }
    }

    /**
     *
     * Creates a new tile with specified type at position (x,y) for a the current game
     *
     * @param x x-position of the tile
     * @param y y-position of the tile
     * @param tileType type of the tile
     * @return wasCreated
     * @apiNote Author : Kartik Misra 260663577
     */
    public static boolean addCard( String cardType) {

        boolean wasCreated = false;

        Deck currentDeck = TileOApplication.getTileO().getCurrentGame().getDeck();



       
        if(cardType.equals("Remove Connection Action Card")) {
            currentDeck.addCard(new RemoveConnectionActionCard("Remove a Connection by selecting two tiles", currentDeck));

        }
        if(cardType.equals("Add Connection Action Card")) {
            currentDeck.addCard(new ConnectTilesActionCard("Please add a connection between two tiles", currentDeck));

        }
        if(cardType.equals("Lose Turn Action Card")) {
            currentDeck.addCard(new LoseTurnActionCard("You have lost 1 turn", currentDeck));

        }
        if(cardType.equals("Teleport Action Card")) {
            currentDeck.addCard(new TeleportActionCard("Please choose a tile to teleport to", currentDeck));

        }
        if(cardType.equals("Roll Die Action Card")) {
            currentDeck.addCard(new RollDieActionCard("Please roll the die", currentDeck));

        }

        if(cardType.equals("Randomly Move Players Action Card")) {
            currentDeck.addCard(new RandomlyMovePlayersActionCard("Players have been randomly moved", currentDeck));
        }

        if(cardType.equals("Win Tile Hint Action Card")) {
            currentDeck.addCard((new WinTileHintActionCard("Please select a tile to guess if its adjacent to Win Tile", currentDeck)));

        }



        return wasCreated;
    }

    /**
     *
     * This feature sets the starting tile of individual player
     *
     * @param x x-position of the tile
     * @param y y-position of the tile
     * @return wasRemoved
     * @apiNote Author : Kartik Misra and Yaniv Bronshtein
     */
    public static void setPlayerStartTile(int intSelectedPlayer, int x, int y) {

        Tile tile = findTileByPos(x, y);
        if(tileTaken(tile) == false){

            Player selectedPlayer = getCurrentGame().getPlayer(intSelectedPlayer);
            selectedPlayer.setCurrentTile(tile);
            selectedPlayer.setStartingTile(tile);
            System.out.println(selectedPlayer);
        }
    }

    public static boolean tileTaken (Tile tile){
        boolean isThere = true;
        for (int i=0; i<getCurrentGame().getPlayers().size();i++){
            if(getCurrentGame().getPlayer(i).getStartingTile() == tile){
            }
            else{
                isThere = false;
            }
        }
        return isThere;
    }


}