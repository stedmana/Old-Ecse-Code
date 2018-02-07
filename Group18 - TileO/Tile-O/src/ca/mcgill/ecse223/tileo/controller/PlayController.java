/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-1dcc48b modeling language!*/

package ca.mcgill.ecse223.tileo.controller;

import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 3 "../../../../../../../../ump/tmp601798/model.ump"
// line 115 "../../../../../../../../ump/tmp601798/model.ump"
public class PlayController
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //PlayController State Machines
    public enum Mode { Ready, Roll, Move, ActionCard, Won }
    private Mode mode;
    private List<Tile> possibleMoves;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public PlayController()
    {
        setMode(Mode.Ready);
    }

    //------------------------
    // INTERFACE
    //------------------------



    public String getModeFullName()
    {
        String answer = mode.toString();
        return answer;
    }

    public Mode getMode()
    {
        return mode;
    }

    public boolean startGame() throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case Ready:
                // line 6 "../../../../../../../../ump/tmp601798/model.ump"
                doStartGame(getCurrentGame());
                setMode(Mode.Roll);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean load(int game) throws InvalidInputException
    {
        Game selectedGame = TileOApplication.getTileO().getGame(game);

        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case Ready:
                if (isInGameMode(selectedGame))
                {
                    // line 9 "../../../../../../../../ump/tmp601798/model.ump"
                    doLoad(getGameIndex(selectedGame));
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                if (isInWonMode(selectedGame))
                {
                    // line 12 "../../../../../../../../ump/tmp601798/model.ump"
                    doLoad(getGameIndex(selectedGame));
                    setMode(Mode.Won);
                    wasEventProcessed = true;
                    break;
                }
                if (isNotInGameOrWonMode(selectedGame))
                {
                    // line 15 "../../../../../../../../ump/tmp601798/model.ump"
                    doLoad(getGameIndex(selectedGame));
                    setMode(Mode.ActionCard);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }



    public boolean rollDie()
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case Roll:
                // line 20 "../../../../../../../../ump/tmp601798/model.ump"
                possibleMoves = doRollDie();
                setMode(Mode.Move);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean land(Tile tile) throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case Move:
                if (isNormalTile(tile))
                {
                    // line 25 "../../../../../../../../ump/tmp601798/model.ump"
                    doLand(tile);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                if (isWinTile(tile))
                {
                    // line 28 "../../../../../../../../ump/tmp601798/model.ump"
                    doLand(tile);
                    setMode(Mode.Won);
                    wasEventProcessed = true;
                    break;
                }
                if (isActionTile(tile))
                {
                    // line 31 "../../../../../../../../ump/tmp601798/model.ump"
                    doLand(tile);
                    setMode(Mode.ActionCard);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean playRollDieActionCard() throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:

                System.out.println(isRollDieActionCard());
                if (isRollDieActionCard())
                {
                    // line 36 "../../../../../../../../ump/tmp601798/model.ump"

                    possibleMoves = doPlayRollDieActionCard();
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
                System.out.println("Default case bro");
        }

        return wasEventProcessed;
    }

    public boolean playConnectTilesActionCard(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isConnectTilesActionCard())
                {
                    // line 40 "../../../../../../../../ump/tmp601798/model.ump"
                    doPlayConnectTilesActionCard(tile1x, tile1y, tile2x, tile2y);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }
    public boolean playRemoveConnectionActionCard(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isRemoveConnectionActionCard())
                {
                    // line 44 "../../../../../../../../ump/tmp601798/model.ump"
                    doPlayRemoveConnectionActionCard(tile1x, tile1y, tile2x, tile2y);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean playTeleportActionCard(int tilex, int tiley) throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Tile tile = findTileByPos(tilex, tiley);

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isTeleportAndNormalTile(tile))
                {
                    // line 47 "../../../../../../../../ump/tmp601798/model.ump"
                    doPlayTeleportActionCard(tilex, tiley);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                if (isTeleportAndWinTile(tile))
                {
                    // line 50 "../../../../../../../../ump/tmp601798/model.ump"
                    doPlayTeleportActionCard(tilex, tiley);
                    setMode(Mode.Won);
                    wasEventProcessed = true;
                    break;
                }
                if (isTeleportAndActionTile(tile))
                {
                    // line 53 "../../../../../../../../ump/tmp601798/model.ump"
                    doPlayTeleportActionCard(tilex, tiley);
                    setMode(Mode.ActionCard);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean playLoseTurnActionCard() throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isLoseTurnActionCard())
                {
                    // line 56 "../../../../../../../../ump/tmp601798/model.ump"
                    doPlayLoseTurnActionCard();
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

//

    public boolean playRandomlyMovePlayersActionCard() throws InvalidInputException{
        boolean wasEventProcessed = false;
        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isRandomlyMovePlayersActionCard()){
                    doRandomlyMovePlayersActionCard();
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;

        }


        return wasEventProcessed;
    }



    public boolean playRandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard(int inactivePeriod)
    {

        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isRandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard())
                {
                    // line 137 "../../../../../../../../ump/tmp231798/model.ump"
                    doPlayRandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard(inactivePeriod);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event

        }


        return wasEventProcessed;
    }



    public boolean playWinTileHintActionCard(int x,int y) throws InvalidInputException
    {

        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isWinTileHintActionCard())
                {
                    // line 144 "../../../../../../../../ump/tmp231798/model.ump"
                    doPlayWinTileHintActionCard(x, y);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event

        }


        return wasEventProcessed;
    }

    public boolean playMoveWinTile(int x,int y) throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isMoveWinTileActionCard())
                {
                    // line 153 "../../../../../../../../ump/tmp231798/model.ump"
                    doPlayMoveWinTileActionCard(x, y);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;

                }

                break;
            default:
                // Other states do respond to this event

        }


        return wasEventProcessed;







    }

    public boolean playRevealTile(int x,int y) throws InvalidInputException
    {
        boolean wasEventProcessed = false;

        Mode aMode = mode;
        switch (aMode)
        {
            case ActionCard:
                if (isRevealTileActionCard())
                {
                    // line 161 "../../../../../../../../ump/tmp231798/model.ump"
                    doPlayRevealTileActionCard(x, y);
                    setMode(Mode.Roll);
                    wasEventProcessed = true;
                    break;
                }
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;

    }
    
    public boolean playNextPlayerRollsOneActionCard() throws InvalidInputException {
  	  boolean wasEventProcessed = false;
  	    
  	    Mode aMode = mode;
  	    switch (aMode)
  	    {
  	      case ActionCard:
  	        if (isNextPlayerRollsOneActionCard())
  	        {
  	        // line 36 "../../../../../../../../ump/tmp601798/model.ump"
  	          possibleMoves = doPlayNextPlayerRollsOneActionCard();
  	          setMode(Mode.Roll);
  	          wasEventProcessed = true;
  	          break;
  	        }
  	        break;
  	      default:
  	        // Other states do respond to this event
  	    }

  	    return wasEventProcessed;
  	  }



    private void setMode(Mode aMode) {
        mode = aMode;
    }

    public void delete()
    {}


    /**
     *
     * Gets total number of games
     *
     * @apiNote Author : Jackson
     */
    public static int getNumberOfGames() {
        save();
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

    public static int getGameIndex(Game selectedGame){
        return TileOApplication.getTileO().indexOfGame(selectedGame);
    }
    /**By Jackson +Yaniv
     * method retrieves current mode */

    public static Game.Mode getCurrentMode() { //Todo for testing
        Game.Mode currentMode = getCurrentGame().getMode();
        if (currentMode == Game.Mode.DESIGN){
            getCurrentGame().setMode(Game.Mode.GAME);
            currentMode = getCurrentGame().getMode();
        }
        return currentMode;
    }

    /**
     *
     * Gets current player from model
     *
     * @apiNote Author : Jackson
     */
    public static Player getCurrentPlayer() {
        return getCurrentGame().getCurrentPlayer();
    }


    /**
     *
     * Gets index of current player from model
     *
     * @apiNote Author : Jackson
     */
    public static int getCurrentPlayerIndex() {
        return getCurrentGame().indexOfPlayer(getCurrentPlayer());
    }

    /**
     *
     * Gets instructions from action card
     *
     * @apiNote Author : Jackson
     */
    public static String getActionCardInstructions() {
        try{
            return getCurrentGame().getDeck().getCurrentCard().getInstructions();
        }
        catch (Exception e) {
            return "Instructions not found";
        }

    }

    public List<Tile> getPossibleMoves(){
        if (possibleMoves == null)
            return new LinkedList<Tile>();
        return possibleMoves;
    }

    public static ActionCard getCurrentActionCard() {
        return getCurrentGame().getDeck().getCurrentCard();
    }

    private boolean isInGameMode(Game selectedGame) {
        if(selectedGame.getMode() == Game.Mode.GAME)
            return true;
        return false;
    }

    private boolean isInWonMode(Game selectedGame){
        if(selectedGame.getMode() == Game.Mode.GAME_WON)
            return true;
        return false;
    }

    private boolean isNotInGameOrWonMode(Game selectedGame){
        if(!isInGameMode(selectedGame)&&!isInWonMode(selectedGame))
            return true;
        return false;
    }

    private boolean isNormalTile(Tile tile){
        if(tile instanceof NormalTile)
            return true;
        return false;
    }

    private boolean isActionTile(Tile tile){
        if(tile instanceof ActionTile)
            return true;
        return false;
    }

    private boolean isWinTile(Tile tile){
        if(tile instanceof WinTile)
            return true;
        return false;
    }

    private boolean isRollDieActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if (currentCard instanceof RollDieActionCard)
            return true;
        return false;
    }

    private boolean isRandomlyMovePlayersActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if (currentCard instanceof RandomlyMovePlayersActionCard)
            return true;
        return false;
    }

    private boolean isConnectTilesActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if (currentCard instanceof ConnectTilesActionCard)
            return true;
        return false;
    }

    private boolean isRemoveConnectionActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if (currentCard instanceof RemoveConnectionActionCard)
            return true;
        return false;
    }

    private boolean isLoseTurnActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if (currentCard instanceof LoseTurnActionCard)
            return true;
        return false;
    }

    private boolean isTeleportActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if (currentCard instanceof TeleportActionCard)
            return true;
        return false;
    }

    private boolean isTeleportAndNormalTile(Tile tile){
        if(isTeleportActionCard()&&isNormalTile(tile))
            return true;
        return false;
    }

    private boolean isTeleportAndWinTile(Tile tile){
        if(isTeleportActionCard()&&isWinTile(tile))
            return true;
        return false;
    }

    private boolean isTeleportAndActionTile(Tile tile){
        if(isTeleportActionCard()&&isActionTile(tile))
            return true;
        return false;
    }


    // line 285 "../../../../../../../../ump/tmp402435/model.ump"
    private boolean isRevealTileActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if( currentCard instanceof RevealTileActionCard)
            return true;
        return false;
    }

    // line 292 "../../../../../../../../ump/tmp402435/model.ump"
    private boolean isNextPlayerRollsOneActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if( currentCard instanceof NextPlayerRollsOneActionCard)
            return true;
        return false;
    }

    // line 322 "../../../../../../../../ump/tmp231798/model.ump"
    private boolean isWinTileHintActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if( currentCard instanceof WinTileHintActionCard)
            return true;
        return false;
    }

    // line 339 "../../../../../../../../ump/tmp231798/model.ump"
    private boolean isTurnTileToActionTileActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if( currentCard instanceof TurnTileToActionTileActionCard)
            return true;
        return false;
    }


    // line 299 "../../../../../../../../ump/tmp402435/model.ump"
    private boolean isRandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if( currentCard instanceof RandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard)
            return true;
        return false;
    }


    /**
     * added for new card
     */
    // line 309 "../../../../../../../../ump/tmp402435/model.ump"

    //added for new card //Todo fix by changing model
    private boolean isMoveWinTileActionCard(){
        ActionCard currentCard = getCurrentActionCard();
        if( currentCard instanceof MoveWinTileActionCard)
            return true;
        return false;
    }

 // jackson li 260681801
    private boolean setGameModeToActionCard(){
    	Game game = getCurrentGame();
        if (isRollDieActionCard()){
            return game.setMode(Game.Mode.GAME_ROLLDIEACTIONCARD);
        }
        else if(isRandomlyMovePlayersActionCard()){
            return game.setMode(Game.Mode.GAME_RANDOMMOVEPLAYERSACTIONCARD);
        }
        else if (isConnectTilesActionCard()){
            return game.setMode(Game.Mode.GAME_CONNECTTILESACTIONCARD);
        }
        else if (isRemoveConnectionActionCard()){
            return game.setMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD);
        }
        else if (isLoseTurnActionCard()){
            return game.setMode(Game.Mode.GAME_LOSETURNACTIONCARD);
        }
        else if (isTeleportActionCard()){
            return game.setMode(Game.Mode.GAME_TELEPORTACTIONCARD);
        }
        //new ones
        else if (isRevealTileActionCard()){
            return game.setMode(Game.Mode.GAME_REVEALTILEACTIONCARD);
        }
        else if (isNextPlayerRollsOneActionCard()){
            return game.setMode(Game.Mode.GAME_NEXTPLAYERROLLSONEACTIONCARD);
        }
        else if (isWinTileHintActionCard()){
            return game.setMode(Game.Mode.GAME_WINTILEHINTACTIONCARD);
        }        
        else if (isTurnTileToActionTileActionCard()){
            return game.setMode(Game.Mode.GAME_TURNTILETOACTIONTILEACTIONCARD);
        }
        else if (isRandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard()){
            return game.setMode(Game.Mode.GAME_RANDOMLYASSIGNNEWINACTIVITYPERIODSTOALLACTIONTILESACTIONCARD);
        }
        else if (isMoveWinTileActionCard()){
            return game.setMode(Game.Mode.GAME_MOVEWINTILEACTIONCARD);
        }
        return false;
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
     * Rolls a die and returns a list of possible tiles the current player can move to.
     *
     * @param ()
     * @return tiles - list of tiles
     *
     * Rolls a die and returns a list of possible tiles the current player can move to.
     *
     * @param ()
     * @return tiles - list of tiles
     * @throws InvalidInputException
     * @apiNote Author : Jackson Li 260681801
     */
    private List<Tile> doPlayRollDieActionCard() throws InvalidInputException{

        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        //checking for right action card
        if(!(currentCard instanceof RollDieActionCard)){
            //throw new InvalidInputException("Wrong action card");
        }

        List<Tile> tiles = ((RollDieActionCard) currentCard).play();

        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

        else{
            deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard)+1));
        }

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

        //turn until active needs to be adapted since same player goes twice
        for(Tile t : getCurrentGame().getTiles()) {
            if(t instanceof ActionTile && ((ActionTile) t).getTurnsUntilActive() > 0){
                ((ActionTile) t).setTurnsUntilActive(((ActionTile) t).getTurnsUntilActive()+1);
            }
        }
        getCurrentGame().setMode(Game.Mode.GAME);

        return tiles;
    }

    /**
     *
     *  Adds a connector piece between the two selected tiles.
     *
     * @param tile1x - x position for first tile
     * @param tile1y - y position for first tile
     * @param tile2x - x position for second tile
     * @param tile2y - y position for second tile
     * @return success
     * @throws InvalidInputException
     * @apiNote Author : Jackson Li 260681801
     */
    private void doPlayConnectTilesActionCard(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException{

        Tile tile1 = findTileByPos(tile1x,tile1y);
        Tile tile2 = findTileByPos(tile2x, tile2y);

        Game currentGame = getCurrentGame();

        //validation check //////////////////////
        if (!currentGame.getTiles().contains(tile1)){
            throw new InvalidInputException("First tile invalid");
        }
        else if (!currentGame.getTiles().contains(tile2)){
            throw new InvalidInputException("Second tile invalid");
        }
        else if (!(Math.abs(tile1x - tile2x) == 1 || Math.abs(tile1y - tile2y) == 1)){
            throw new InvalidInputException("Tiles not adjacent");
        }
        else if (currentGame.getCurrentConnectionPieces() <= 0 ){
            throw new InvalidInputException("No more connection pieces");
        }
        // end of validation check ///////////////

        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        //checking for right action card
        if(!(currentCard instanceof ConnectTilesActionCard)){
            throw new InvalidInputException("Wrong action card");
        }

        ((ConnectTilesActionCard) currentCard).play(tile1, tile2);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

        getCurrentGame().setMode(Game.Mode.GAME);

        return;

    }

    /**
     *
     * @throws InvalidInputException
     * @apiNote Author Connor Fowlie 260687955
     */
    private void doRandomlyMovePlayersActionCard() throws  InvalidInputException {
        Game currentGame = getCurrentGame();
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard = currentDeck.getCurrentCard();

        ((RandomlyMovePlayersActionCard) currentCard).play();

        currentGame.determineNextPlayer();

        //setting up cards for next turn
        if(currentDeck.indexOfCard(currentCard) == currentDeck.numberOfCards()-1){
            currentDeck.shuffle();
            currentDeck.setCurrentCard(currentDeck.getCard(0));
        }
        //setting mode for next turn



        getCurrentGame().setMode(Game.Mode.GAME);
    }

    /**
     * @author Jackson
     *
     */
    private void doPlayLoseTurnActionCard() throws InvalidInputException{
        Game currentGame = getCurrentGame();
        Deck currentDeck = currentGame.getDeck(); //to reshuffle if empty
        ActionCard currentCard = currentDeck.getCurrentCard(); //calls play()
        Player currentPlayer = getCurrentPlayer(); //argument for play()


        //checking for right action card
        if(!(currentCard instanceof LoseTurnActionCard)){
            throw new NullPointerException("Wrong action card"); //Todo figure out expections?

        }

        ((LoseTurnActionCard) currentCard).play(currentPlayer); //run play method

        currentGame.determineNextPlayer();

        //setting up cards for next turn
        if(currentDeck.indexOfCard(currentCard) == currentDeck.numberOfCards()-1){
            currentDeck.shuffle();
            currentDeck.setCurrentCard(currentDeck.getCard(0));
        }

        getCurrentGame().setMode(Game.Mode.GAME);


    }

    /**
     * Implements RemoveConnectionActionCard within play method, validates connection re-shuffles deck
     *
     *
     * @param
     * @return void
     * @apiNote Author : Yaniv Bronshtein 260618099 and Kartik Misra 260663577 and Ananya Chandra 260682532
     */
    private void doPlayRemoveConnectionActionCard(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException {

        Tile tile1 = findTileByPos(tile1x,tile1y);
        Tile tile2 = findTileByPos(tile2x,tile2y);

        Connection connection = null;
        for(Connection connect: tile1.getConnections()){
            if(tile2.getConnections().contains(connect)){
                connection = connect;
                break;
            }
        }

        Game currentGame = TileOApplication.getTileO().getCurrentGame();

        //validation check //////////////////////
        if(connection == null){
            throw new InvalidInputException("Error: Connection is invalid");
        }
        if (!currentGame.getConnections().contains(connection)){
            throw new InvalidInputException("Error: Connection is invalid ");
        }

        // end of validation check ///////////////

        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        //checking for right action card
        if(!(currentCard instanceof RemoveConnectionActionCard)){
            throw new InvalidInputException("Error:Wrong action card");
        }

        ((RemoveConnectionActionCard)currentCard).play(connection); //run play method

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }


        getCurrentGame().setMode(Game.Mode.GAME);

    }



    /**
     * Validates existence of tile argument and proceeds to call abstract Tile model method land()
     *
     *
     * @param Tile tile
     * @return void
     * @apiNote Author : Yaniv Bronshtein 260618099
     */


    private void doLand(Tile tile) throws InvalidInputException {

        Game currentGame = TileOApplication.getTileO().getCurrentGame();

        //Validation Check
        if(!currentGame.getTiles().contains(tile)) {
            throw new InvalidInputException("Error: The tile landed on does not exist in this game! \n");
        }

        else {
            tile.land(); //call model method

        }

        getCurrentGame().updateTileStatus();
        
        if(tile instanceof ActionTile){
        	setGameModeToActionCard();
        }
        
        else if(tile instanceof WinTile) {
            currentGame.setMode(Game.Mode.GAME_WON);
        }
        
    }




    /**
     * Starts game from selected Game instance. Does validation check on the number of cards
     * in deck and on valid starting positions for player.
     *  Shuffles deck, Instantiates starting tiles of players,
     *  sets those tiles as visited, sets current player, and sets the number of currentConnectionPieces
     *
     * @param (Game selectedGame)
     * @return void
     * @apiNote Author : Yaniv Bronshtein 260618099
     */

    private void doStartGame(Game selectedGame) throws InvalidInputException {

        int numCardsInDeck = selectedGame.getDeck().numberOfCards();
        if(numCardsInDeck < 32)
            throw new InvalidInputException("Error: The selectedGame has less than 32 Action Cards \n ");


        if(!selectedGame.hasWinTile())
            throw new InvalidInputException("Error: WinTile is missing \n ");

        for(Player player : selectedGame.getPlayers()) {
            if(player.getStartingTile() == null) {
                throw new InvalidInputException("Error: Player  " + player.getNumber()
                        + "  is missing a starting tile \n");
            }
        }
        //initialize game
        TileO tileO = new TileO();
        tileO.setCurrentGame(selectedGame);
        Deck currentDeck = selectedGame.getDeck();
        currentDeck.shuffle();

        for(Tile tile : selectedGame.getTiles()) {
            tile.setHasBeenVisited(false);
        }
        for(Player player : selectedGame.getPlayers()) {
            Tile startingTile = player.getStartingTile();
            if(player.getCurrentTile() == null){
                player.setCurrentTile(startingTile);
                startingTile.setHasBeenVisited(true);
            }
        }
        if (selectedGame.getCurrentPlayer() == null){
            selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
        }
        if(selectedGame.getDeck().getCurrentCard() == null){
            selectedGame.getDeck().setCurrentCard(selectedGame.getDeck().getCard(0));
        }


        if(selectedGame.getMode() == Game.Mode.DESIGN){
            selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
            selectedGame.setMode(Game.Mode.GAME);
        }
    }



    /**
     * Calls die object from game to roll the die and then calls the player method getPossibleMoves()
     *  to return a list of potential tile objects to move to based on on the outcome
     *  of the die roll.
     *
     * @param
     * @return List<Tile>
     * @apiNote Author : Yaniv Bronshtein 260618099
     */

    private List<Tile> doRollDie() {
        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        Die currentDie = currentGame.getDie();
        int number = currentDie.roll();
        Player currentPlayer = currentGame.getCurrentPlayer();
        List<Tile> tiles = currentPlayer.getPossibleMoves(number);

        return tiles;

    }


    /**
     * sets the player location to the specified tile
     *
     * @param (Tile tile)
     * @return void
     * @apiNote Author : Ananya Chandra 260682532
     */
    private void doPlayTeleportActionCard(int x, int y) throws InvalidInputException{
        Tile tile = findTileByPos(x,y);
        Game currentGame = getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        ((TeleportActionCard) currentCard).play(tile);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }
        if(tile instanceof ActionTile){
        	setGameModeToActionCard();
        }
        
        else if(tile instanceof WinTile) {
            currentGame.setMode(Game.Mode.GAME_WON);
        }
    }

    /**
     *changes the location of the win tile to the one chosen by the user
     * @param (int x, int y)
     * @apiNote Author : Yaniv Bronshtein 260618099
     */

    private void doPlayMoveWinTileActionCard(int x, int y) throws InvalidInputException {
        Tile tilePicked = findTileByPos(x,y);

        Game currentGame = getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        ((MoveWinTileActionCard) currentCard).play(tilePicked);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

    }


    /**
     *Reveals the selected tile to the players
     * @param (int x, int y)
     * @apiNote Author : Kartik Misra 260663577
     */
    private String doPlayRevealTileActionCard(int x, int y) throws InvalidInputException {
        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        String message;
        Tile selectedTile = findTileByPos(x,y);

        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        message = ((RevealTileActionCard) currentCard).play(selectedTile);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

        return message;
    }

    /**
     *Has the next player roll exactly 1.
     * @param (int x, int y)
     * @apiNote Author : Ananya Chandra 260682532
     */


    private List<Tile> doPlayNextPlayerRollsOneActionCard() throws InvalidInputException {

        Game currentGame = getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        List<Tile> tiles = ((NextPlayerRollsOneActionCard) currentCard).play();

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

        return tiles;
    }

    /**
     *Has the next player roll exactly 1.
     * @param (int x, int y)
     * @apiNote Author : Jackson Li 260681801
     */

    private void doPlayTurnTileToActionTileActionCard(int x, int y, int inactivePeriod) {

        Tile tilePicked = findTileByPos(x,y);

        Game currentGame = getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        ((TurnTileToActionTileActionCard) currentCard).play(tilePicked, inactivePeriod);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

    }



    /**
     *
     * Saves the game
     *
     * @param
     * @return
     * @apiNote Author :  Ananya Chandra 260682532
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
     * @apiNote Author : Ananya Chandra 260682532
     */

    private void doLoad(int index) throws InvalidInputException{
        Game selectedGame = TileOApplication.getTileO().getGame(index);

        TileOApplication.getTileO().setCurrentGame(selectedGame);

    }

    /**
     * When a player draws the card, the player picks a tile on the board.
     * If the selected tile or one of its neighbours is the WinTile,
     * the game would return a message confirming or denying.
     *
     * @param (int x, int y) - coordinates of selected tile
     * @return boolean - returns true if the input tile is the win tile, or one of its neighbours
     * By Alex Stedman
     * @throws InvalidInputException
     */
    private boolean doPlayWinTileHintActionCard(int x, int y) throws InvalidInputException {
        Game currentGame = getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();
        Tile selectedTile = findTileByPos(x,y);

        boolean returnValue = ((WinTileHintActionCard) currentCard).play(selectedTile, x, y);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }
        return returnValue;
    }

    private void doPlayRandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard(int inactivePeriod) {
        Game currentGame = getCurrentGame();
        Deck deck = currentGame.getDeck();
        ActionCard currentCard = deck.getCurrentCard();

        ((RandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard) currentCard).play(inactivePeriod);

        //setting up players for next turn
        getCurrentGame().determineNextPlayer();
        //setting up cards for next turn
        if(deck.indexOfCard(currentCard) == deck.numberOfCards()-1){
            deck.shuffle();
            deck.setCurrentCard(deck.getCard(0));
        }

    }


}