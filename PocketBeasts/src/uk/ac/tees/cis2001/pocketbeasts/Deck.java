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

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author James Fairbairn
 * @author Steven Mead
 * @author w9101532
 * 
 * Cards to draw from to hand.
 */
public class Deck extends CardCollection
{
    public final Card[] STARTER_CARDS = new Card[]
    {
        new Card("IP", "Imp", 1, 1, 1, 0), //1 mana, 1 attack, 1 HP, 0 cybernetic slots.
        new Card("DN", "Demon", 2, 2, 1, 0), //2 mana, 2 attack, 1 HP, 1 cybernetic slot.
        new Card("HD", "Hellhound", 2, 2, 2, 1), //2 mana, 2 attack, 2 HP, 1 cybernetic slot.
        new Card("ZE", "Zombie", 3, 3, 2, 0), //3 mana, 3 attack, 2 HP, 0 cybernetic slots.
        new Card("US", "Undead Soldier", 3, 2, 3, 2), //3 mana, 2 attack, 3 HP, 2 cybernetic slots.
        new Card("TN", "Titan", 4, 3, 10, 3), //4 mana, 3 attack, 10 HP, 3 cybernetic slots.
        new Card("CC", "Caco", 5, 4, 6, 3), //5 mana, 4 attack, 6 HP, 3 cybernetic slots.
        new Card("DL", "Dark Lord", 6, 6, 8, 4) //6 mana, 6 attack, 8 HP, 4 cybernetic slots.
    };

    
    
    Deck()
    {
        super();
        addToDeck(STARTER_CARDS);
    }
    
    /**
     * Gets the base deck.
     * @return  Returns the base deck.
     */
    public ArrayList<Card> getBaseDeck()
    {
        return super.getCards();
    }
    
    /**
     * Add an array of cards to the players deck.
     * @param cardArray     Input the card array.
     */
    public void addToDeck(Card[] cardArray)
    {
        for (int i = 0; i < 2; i++)
        {
            for (Card card : cardArray)
            {
                super.add(new Card(card));
            }
        }
    }
    
    /**
     * Removes a card from the arraylist and returns it. (Usually for putting into hand).
     * @return  Returns the card that the player is drawing.
     */
    public Card draw()
    {
        Card card = super.getCards().get(0);
        super.getCards().remove(0);
        return card;
    }

    /**
     * Shuffles the deck.
     * @param loops     How many times to loop (increase of randomness).
     */
    public void shuffle(int loops)
    {
        for (int i = 0; i < loops; i++)
        {
            Collections.shuffle(super.getCards());
        }
    }
}
