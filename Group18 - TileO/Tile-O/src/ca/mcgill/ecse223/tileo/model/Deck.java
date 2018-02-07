/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 57 "../../../../../Tile-O.ump"
public class Deck implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deck Associations
  private List<ActionCard> cards;
  private ActionCard currentCard;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deck(Game aGame)
  {
    cards = new ArrayList<ActionCard>();
    if (aGame == null || aGame.getDeck() != null)
    {
      throw new RuntimeException("Unable to create Deck due to aGame");
    }
    game = aGame;
  }

  public Deck(int aCurrentConnectionPiecesForGame, Die aDieForGame, TileO aTileOForGame)
  {
    cards = new ArrayList<ActionCard>();
    game = new Game(aCurrentConnectionPiecesForGame, this, aDieForGame, aTileOForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public ActionCard getCard(int index)
  {
    ActionCard aCard = cards.get(index);
    return aCard;
  }

  public List<ActionCard> getCards()
  {
    List<ActionCard> newCards = Collections.unmodifiableList(cards);
    return newCards;
  }

  public int numberOfCards()
  {
    int number = cards.size();
    return number;
  }

  public boolean hasCards()
  {
    boolean has = cards.size() > 0;
    return has;
  }

  public int indexOfCard(ActionCard aCard)
  {
    int index = cards.indexOf(aCard);
    return index;
  }

  public ActionCard getCurrentCard()
  {
    return currentCard;
  }

  public boolean hasCurrentCard()
  {
    boolean has = currentCard != null;
    return has;
  }

  public Game getGame()
  {
    return game;
  }

  public static int minimumNumberOfCards()
  {
    return 0;
  }

  public static int maximumNumberOfCards()
  {
    return 32;
  }

  //  public ActionCard addCard(String aInstructions)
  //  {
  //    if (numberOfCards() >= maximumNumberOfCards())
  //    {
  //      return null;
  //    }
  //    else
  //    {
  //      return new ActionCard(aInstructions, this);
  //    }
  //  }

  public boolean addCard(ActionCard aCard)
  {
    boolean wasAdded = false;
    if (cards.contains(aCard)) { return false; }
    if (numberOfCards() >= maximumNumberOfCards())
    {
      return wasAdded;
    }

    Deck existingDeck = aCard.getDeck();
    boolean isNewDeck = existingDeck != null && !this.equals(existingDeck);
    if (isNewDeck)
    {
      aCard.setDeck(this);
    }
    else
    {
      cards.add(aCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCard(ActionCard aCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aCard, as it must always have a deck
    if (!this.equals(aCard.getDeck()))
    {
      cards.remove(aCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCardAt(ActionCard aCard, int index)
  {
    boolean wasAdded = false;
    if(addCard(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardAt(ActionCard aCard, int index)
  {
    boolean wasAdded = false;
    if(cards.contains(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    }
    else
    {
      wasAdded = addCardAt(aCard, index);
    }
    return wasAdded;
  }

  public boolean setCurrentCard(ActionCard aNewCurrentCard)
  {
    boolean wasSet = false;
    currentCard = aNewCurrentCard;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (cards.size() > 0)
    {
      ActionCard aCard = cards.get(cards.size() - 1);
      aCard.delete();
      cards.remove(aCard);
    }

    currentCard = null;
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }





  //for each i in [n .. 1] do
  //  j â†� random integer in [ 0 .. i ]
  //  exchange a[j] and a[i]

  public void shuffle(){

    for(int i = this.numberOfCards()-1; i >= 0 ; i--) {
      int j = (int) ((Math.random() * 31) +1);

      //swap
      ActionCard temp = this.getCard(i);
      cards.set(i, cards.get(j));
      cards.set(j, temp);
    }

  }

  /**
   * Selects and adds 32 action cards to the deck
   *
   *
   * @param String aInstructions
   * @return deck completeDeck
   * @apiNote Author : Kartik Misra 260663577
   */


  //enum of action cards
  public enum ActionCardType {
    ConnectTilesActionCard, LoseTurnActionCard, RemoveConnectionActionCard, RollDieActionCard,
    TeleportActionCard, WinTileHintActionCard, RandomlyMovePlayersActionCard, RevealTileActionCard,
    NextPlayerRollsOneActionCard
  }

  public Deck select32Card(String aInstructions){
    //creating deck to be filled with 32 card
    Deck completeDeck = new Deck(this.game);
    if(completeDeck.numberOfCards() == 32){
      return completeDeck;
    }
    int i = completeDeck.numberOfCards();
    while(i < 32) {
      //each possible case of action cards that can be added, looped 32 times
      switch (ActionCardType) {
        case ConnectTilesActionCard:
          completeDeck.addCardAt( new ConnectTilesActionCard(aInstructions,completeDeck), i);
          break;

        case LoseTurnActionCard:
          completeDeck.addCardAt( new LoseTurnActionCard(aInstructions, completeDeck), i);
          break;

        case RemoveConnectionActionCard:
          completeDeck.addCardAt( new RemoveConnectionActionCard(aInstructions, completeDeck), i);
          break;

        case RollDieActionCard:
          completeDeck.addCardAt( new RollDieActionCard (aInstructions, completeDeck), i);
          break;

        case WinTileHintActionCard:
          completeDeck.addCardAt( new WinTileHintActionCard (aInstructions, completeDeck), i);
          break;

        case RandomlyMovePlayersActionCard:
          completeDeck.addCardAt( new RandomlyMovePlayersActionCard (aInstructions, completeDeck), i);
          break;
          
        case RevealTileActionCard:
            completeDeck.addCardAt( new RevealTileActionCard (aInstructions, completeDeck), i);
            break;
            
        case NextPlayerRollsOneActionCard:
        	completeDeck.addCardAt(new NextPlayerRollsOneActionCard (aInstructions, completeDeck), i);

        default:
          completeDeck.addCardAt( new TeleportActionCard(aInstructions,completeDeck), i);
          break;
      }
    }
    return completeDeck;
  }

  private static final long serialVersionUID = 5555406856025012133L;
  private ActionCardType ActionCardType;



}