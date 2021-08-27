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
        new PlayerCard("IP", "Imp", 0, 6, 1), //1 mana, 1 attack, 1 HP.
        new PlayerCard("DN", "Demon", 2, 2, 1), //2 mana, 2 attack, 1 HP.
        new PlayerCard("HD", "Hellhound", 2, 2, 2), //2 mana, 2 attack, 2 HP.
        new PlayerCard("ZE", "Zombie", 3, 3, 2), //3 mana, 3 attack, 2 HP.
        new PlayerCard("US", "Undead Soldier", 3, 2, 3), //3 mana, 2 attack, 3 HP.
        new PlayerCard("TN", "Titan", 4, 3, 10), //4 mana, 3 attack, 10 HP.
        new PlayerCard("CC", "Caco", 5, 4, 6), //5 mana, 4 attack, 6 HP.
        new PlayerCard("DL", "Dark Lord", 6, 6, 8) //6 mana, 6 attack, 8 HP.
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
                super.add(new PlayerCard(card));
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
