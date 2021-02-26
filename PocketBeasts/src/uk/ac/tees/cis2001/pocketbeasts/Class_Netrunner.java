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
    public final Card[] NETRUNNER_CARDS = new Card[] 
    {
        new Card("HR", "Hacker", 2, 4, 1, 2), //2 mana, 4 attack, 1 HP, 2 cybernetic slots.
        new Card("NR", "Netrunner", 3, 3, 3, 3), //3 mana, 3 attack, 3 HP, 3 cybernetic slot.s
        new Card("SR", "Sector", 4, 4, 5, 5), //4 mana, 4 attack, 5 HP, 5 cybernetic slots.
        new Card("SN", "SHODAN", 10, 10, 10, 4) //10 mana, 10 attack, 10 HP, 4 cybernetic slots.
    }; 
    
    /**
     * Constructor.
     * @param name  Input a name for the user. 
     */
    public Class_Netrunner(String name)
    {
        super("Netrunner " + name);
        this.deck.addToDeck(NETRUNNER_CARDS); //Add unique cards to deck.
    }
    
}
