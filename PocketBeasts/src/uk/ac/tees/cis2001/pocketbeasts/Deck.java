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
 * Cards to choose from.
 */
public class Deck
{

    private final ArrayList<Card> deck = new ArrayList<>();

    public final Card[] STARTER_CARDS = new Card[]
    {
        new Card("IP", "Imp", 1, 1, 1),
        new Card("DN", "Demon", 2, 2, 1),
        new Card("HD", "Hellhound", 2, 2, 2),
        new Card("ZE", "Zombie", 3, 3, 2),
        new Card("US", "Undead Soldier", 3, 2, 3),
        new Card("TN", "Titan", 4, 2, 10),
        new Card("ER", "Enslaver", 4, 4, 6),
        new Card("DL", "Dark Lord", 5, 6, 8)
    };

    public ArrayList<Card> getBaseDeck()
    {
        return deck;
    }
    
    Deck()
    {
        addToDeck(STARTER_CARDS);
    }
    
    public void addToDeck(Card[] cardArray)
    {
        for (int i = 0; i < 2; i++)
        {
            for (Card card : cardArray)
            {
                deck.add(new Card(card));
            }
        }
    }
    
    public Card draw()
    {
        Card card = this.deck.get(0);
        this.deck.remove(0);
        return card;
    }

    public void shuffle(int loops)
    {
        for (int i = 0; i < loops; i++)
        {
            Collections.shuffle(this.deck);
        }
    }
    
    public int count() {
        return this.deck.size();
    }
}
