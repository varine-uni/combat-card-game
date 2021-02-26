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

/**
 * Close to what the main deck is suppose to be. But takes in cybernetics cards.
 * (Removed methods such as shuffle because upgrading cards isn't random).
 * @author w9101532
 */
public class CyberDeck
{
    ArrayList<Cybernetics> cyberneticCards = new ArrayList<>();
    
    private final Cybernetics[] CYBERNETIC_CARDS = new Cybernetics[]
    {
        //Test cybernetics. TODO: Change this up, add names.
        new Cybernetics(1, 2, 2),
        new Cybernetics(2, 1, 1),
        new Cybernetics(1, 5, 5)
    };
    
    CyberDeck()
    {
        addToDeck(CYBERNETIC_CARDS);
    }
    
    /**
     * Add an array of cards to the players cyberdeck.
     * @param cardArray     Input the card array.
     */
    private void addToDeck(Cybernetics[] cardArray)
    {
        for (int i = 0; i < 2; i++)
        {
            for (Cybernetics cybernetics : cardArray)
            {
                cyberneticCards.add(new Cybernetics(cybernetics));
            }
        }
    }
    
    /**
     * Counts how many cards are in the cyberdeck.
     * @return      Returns the size of the cyberdeck.
     */
    public int count() {
        return this.cyberneticCards.size();
    }
}
