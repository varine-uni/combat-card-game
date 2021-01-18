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
 * @author w9101532
 */
public class Class_Netrunner extends Player
{
    /**
     * Unique cards for this class.
     */
    public static final Card[] NETRUNNER_CARDS = new Card[] 
    {
        new Card("FH", "Flayed Hacker", 2, 4, 1),
        new Card("SB", "System Breaker", 3, 3, 3),
        new Card("BS", "Bad Sector", 4, 4, 5),
        new Card("S", "Someone", 7, 10, 10)
    }; 
    
    /**
     * Constructor.
     * @param name  Input a name for the user. 
     */
    public Class_Netrunner(String name)
    {
        super("Netrunner" + name);
        this.deck.addToDeck(NETRUNNER_CARDS); //Add unique cards to deck.
    }
    
}
