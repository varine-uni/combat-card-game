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

    private final int MAX_MANA = 10;

    private final String name;

    private int manaAvailable;
    private int currentMaxMana;
    private int health;

    protected final Deck deck;
    protected final Hand hand;
    protected final Table table = new Table();
    protected final Graveyard graveyard;

    public Player(String name)
    {
        this.name = name;
        this.manaAvailable = 0;
        this.currentMaxMana = 0;
        this.health = 15;
        this.deck = new Deck();
        this.hand = new Hand();
        this.graveyard = new Graveyard();
    }
    
    public void newGame()
    {
        //Will take the first 2 "weak" cards and place them on the table.
        for (int i = 0; i < 2; i++) 
        {
            this.table.add(deck.draw());
        }
        
        //From now on, the cards taken from deck are random.
        this.deck.shuffle(5); 
        
        //Takes two random cards in hand.
        for (int i = 0; i < 2; i++) 
        {
            this.hand.add(this.deck.draw());
        }
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * Method to deduct player's mana.
     * @param manaCost      Cost of the card being used.
     * @return              Returns true if successfully removed mana. False otherwise.
     */
    public boolean removeMana(int manaCost)
    {
        if (manaCost <= manaAvailable)
        {
            manaAvailable -= manaCost;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int getManaAvailable()
    {
        return this.manaAvailable;
    }

    public int getHealth()
    {
        return this.health;
    }

    public Deck getDeck()
    {
        return this.deck;
    }

    public Hand getHand()
    {
        return this.hand;
    }

    public Table getTable()
    {
        return this.table;
    }

    public Graveyard getGraveyard()
    {
        return this.graveyard;
    }

    public void addMana()
    {
        if (currentMaxMana < MAX_MANA)
        {
            currentMaxMana++;
        }
        
        this.manaAvailable = currentMaxMana;
    }

    public void useMana(int amount)
    {
        this.manaAvailable -= amount;
    }

    public void drawCard()
    {
        this.hand.add(this.deck.draw());
    }

    public Boolean damage(int amount)
    {
        this.health -= amount;
        return this.health <= 0;
    }

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
