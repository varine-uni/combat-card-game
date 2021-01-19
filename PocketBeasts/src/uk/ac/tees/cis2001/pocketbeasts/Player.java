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
        deck.clearDeck();
        hand.clearHand();
        graveyard.clearGrave();
        for (int i = 0; i < 4; i++) //Will take the first 4 "weak" cards.
        {
            this.hand.add(this.deck.draw());
        }
        this.deck.shuffle(); //From now on, the cards taken from deck are random.
        
        addMana(); //New game, add the initial amount of mana (1 point).   
    }

    public String getName()
    {
        return this.name;
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
        sb.append(String.format("%-9s HEALTH/%-5d MANA/%d\n", this.name, this.health, this.manaAvailable));

        for (int i = 0; i < this.table.count() + 2; i++)
        {
            sb.append("+-------+ ");
        }
        sb.append("\n");

        for (int i = 0; i < 2; i++)
        {
            sb.append("|       | ");
        }
        for (int i = 0; i < this.table.count(); i++)
        {
            sb.append(String.format("|%7d| ", this.table.getCard(i).getManaCost()));
        }
        sb.append("\n");

        sb.append("| DECK  | ");
        sb.append("| GRAVE | ");
        for (int i = 0; i < this.table.count(); i++)
        {
            sb.append(String.format("|  %-5s| ", this.table.getCard(i).getId()));
        }
        sb.append("\n");

        sb.append(String.format("| %-6d| ", this.deck.count()));
        sb.append(String.format("| %-6d| ", this.graveyard.count()));
        for (int i = 0; i < this.table.count(); i++)
        {
            sb.append("|       | ");
        }
        sb.append("\n");

        for (int i = 0; i < 2; i++)
        {
            sb.append("|       | ");
        }
        for (int i = 0; i < this.table.count(); i++)
        {
            sb.append(String.format("|%-2d %4d| ", this.table.getCard(i).getAttack(), this.table.getCard(i).getHealth()));
        }
        sb.append("\n");

        for (int i = 0; i < this.table.count() + 2; i++)
        {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        sb.append(String.format("%d card(s) in hand.\n", this.hand.count()));
        sb.append("\n");

        sb.append(this.hand.toString());

        return sb.toString();
    }
}
