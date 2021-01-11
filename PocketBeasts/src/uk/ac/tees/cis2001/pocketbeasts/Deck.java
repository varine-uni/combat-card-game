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
 * 
 * Cards to choose from.
 */
public class Deck
{

    private final ArrayList<Card> baseDeck = new ArrayList<>();
    
    public static final Card[] STARTER_CARDS = new Card[]
    {
        new Card("I", "Imp", 1, 1, 1),
        new Card("DN", "Demon", 2, 2, 1),
        new Card("HD", "Hellhound", 2, 1, 2),
        new Card("ZE", "Zombie", 3, 3, 2),
        new Card("US", "Undead Soldier", 3, 2, 3),
        new Card("TN", "Titan", 3, 3, 3),
        new Card("ER", "Enslaver", 4, 4, 2),
        new Card("DL", "Dark Lord", 5, 4, 4)
    };
    
    Deck()
    {
       for (int i = 0; i < 2; i++)
        {
            for (Card card : STARTER_CARDS)
            {
                baseDeck.add(new Card(card));
            }
        }
    }
    
    public Card draw() {
        Card card = this.baseDeck.get(0);
        this.baseDeck.remove(0);
        return card;
    }
    
    public void shuffle() {
        Collections.shuffle(this.baseDeck);
    }
}
