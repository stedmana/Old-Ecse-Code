/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

import java.util.List;
import java.util.Random;

// line 120 "../../../../../Tile-O.ump"
public class RandomlyMovePlayersActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RandomlyMovePlayersActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean play() throws InvalidInputException {

      boolean success = false;

      Game currentGame = TileOApplication.getTileO().getCurrentGame();

      List<Player> players = currentGame.getPlayers();

      for (Player player : players) {
          moveRandomly(player);
      }

      return success;

  }

  void moveRandomly(Player player) {
      Game currentGame = TileOApplication.getTileO().getCurrentGame();
      List<Tile> tiles = currentGame.getTiles();

      Random randomGenerator = new Random();
      int index = randomGenerator.nextInt(tiles.size());

      Tile randomTile = tiles.get(index);

      if (randomTile instanceof ActionTile || randomTile instanceof WinTile){
          moveRandomly(player);
          return;
      }

      player.setCurrentTile(randomTile);
  }


  public void delete()
  {
    super.delete();
  }

  @Override
  public Game.Mode getActionCardGameMode() {
    return Game.Mode.GAME_RANDOMMOVEPLAYERSACTIONCARD;
  }

}