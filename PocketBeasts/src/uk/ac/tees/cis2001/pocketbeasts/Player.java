/*
 * This file is part of PocketBeasts.
 *
 * PocketBeasts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PocketBeasts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package uk.ac.tees.cis2001.pocketbeasts;

/**
 *
 * @author James Fairbairn
 * @author Steven Mead
 * @author w9101532
 * 
 * Super player class. Character classes will inherit this class.
 */
public class Player
{
    //Player attribrutes.
    private final int MAX_MANA = 10;
    private final String name;
    private int manaAvailable;
    private int currentMaxMana;
    private int health;
    
    private Card selectedCard;

    //Player's decks.
    protected final Deck deck;
    protected final Hand hand;
    protected final Table table;
    protected final Graveyard graveyard;

    public Player(String name)
    {
        this.name = name;
        this.manaAvailable = 0;
        this.currentMaxMana = 1;
        this.health = 15;
        this.deck = new Deck();
        this.hand = new Hand();
        this.table = new Table();
        this.graveyard = new Graveyard();
    }
    
    /**
     * Sets up the table and hand for the initial state of the players.
     */
    public void newGame()
    {
        //Will take the first 2 "weak" cards and place them on the table.
        for (int i = 0; i < 2; i++) 
        {
            this.table.add(deck.draw());
        }
        
        //From now on, the cards taken from deck are random.
        this.deck.shuffle(5); 
        
        //Takes two random cards to hand.
        for (int i = 0; i < 2; i++) 
        {
            this.hand.add(this.deck.draw());
        }
    }

    /**
     * Gets the player's name.
     * @return  Returns the name as a string.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets current mana.
     * @return  returns player's current mana.
     */
    public int getManaAvailable()
    {
        return this.manaAvailable;
    }

    /**
     * Gets current health.
     * @return  returns player's current health.
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * Gets deck.
     * @return  returns the player's deck.
     */
    public Deck getDeck()
    {
        return this.deck;
    }

    /**
     * Gets player's hand.
     * @return  returns player's hand.
     */
    public Hand getHand()
    {
        return this.hand;
    }

    /**
     * Gets the table.
     * @return  returns the player's table.
     */
    public Table getTable()
    {
        return this.table;
    }

    /**
     * Gets player's graveyard.
     * @return  returns the player's graveyard.
     */
    public Graveyard getGraveyard()
    {
        return this.graveyard;
    }
    
    /**
     * Gets the player's currently selected card.
     * @return  returns the player's selected card.
     */
    public Card getSelectedCard()
    {
        return this.selectedCard;
    }
    
    /**
     * Sets the player's selected card.
     * @param selectedCard  card object (most likely used in other logic) to set player's selected card.
     */
    public void setSelectedCard(Card selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    /**
     * Adds 1 additional mana to player's max mana pool.
     */
    public void addMana()
    {
        if (currentMaxMana < MAX_MANA)
        {
            currentMaxMana++;
        }
        
        this.manaAvailable = currentMaxMana;
    }

    /**
     * Player has a pool of mana that can be used. This function will use an amount
     * depending on the parameter's value.
     * @param amount   amount of mana to take away from current mana pool.
     */
    public void useMana(int amount)
    {
        this.manaAvailable -= amount;
    }

    /**
     * Will add remove a card from the deck and place it in the player's hand.
     */
    public void drawCard()
    {
        this.hand.add(this.deck.draw());
    }
    
    /**
     * Places chosen card onto the table.
     * @param card  Selected card.
     */
    public void placeCard(Card card)
    {
        this.table.add(card);
    }
    
    /**
     * Player's damage function (taken). Player can take damage themselves and will
     * die if damage was fatal.
     * @param amount   amount of damage the player will take.
     * @return         Returns true if damage taken was fatal.
     */
    public boolean damage(int amount)
    {
        this.health -= amount;
        
        return health <= 0;
    }

    
    /**
     * Death of this player. Other player wins and the game ends.
     */
    public void death()
    {
         System.out.println(this.getName() + " dies.");
         System.out.println("Game over.");
         System.exit(1);
    }
    
    /**
     * Visualisation of the player's deck, hand, table, and graveyard. Including name, health and mana.
     * @return  returns the string that encompasses these details.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-9s HEALTH(%d) MANA(%d)\n", this.name, this.health, this.manaAvailable));

        for (int i = 0; i < 2; i++)
        {
            sb.append("+-------+ ");
        }
        sb.append("\n");

        for (int i = 0; i < 2; i++)
        {
            sb.append("|       | ");
        }

        sb.append("\n");

        sb.append("| DECK  | ");
        sb.append("| GRAVE | ");

        sb.append("\n");

        sb.append(String.format("| %-6d| ", this.deck.count()));
        sb.append(String.format("| %-6d| ", this.graveyard.count()));

        sb.append("\n");

        for (int i = 0; i < 2; i++)
        {
            sb.append("|       | ");
        }

        sb.append("\n");

        for (int i = 0; i < 2; i++)
        {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        sb.append(String.format("%d card(s) in hand.\n", this.hand.count()));
        sb.append("\n");
        sb.append(this.hand.toString());
        sb.append("\n");
        sb.append(String.format("%d card(s) on table. \n", this.table.count()));
        sb.append(this.table.toString());

        return sb.toString();
    }

}
