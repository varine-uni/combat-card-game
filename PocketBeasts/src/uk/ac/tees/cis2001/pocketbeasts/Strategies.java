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
 * This contains all the strategies that can be used by a card.
 * It's encapsulated in a class to allow easier output using conditionals.
 * @author w9101532
 */
public class Strategies
{
    CardStrategy strategy = null;
    Action_Resurrect resurrect = new Action_Resurrect();
    Action_InstaKill instaKill = new Action_InstaKill();
    
    public CardStrategy selectStrategy(int input)
    {
        switch (input)
        {
            case 1:
                strategy = resurrect;
                break;

            case 2:
                strategy = instaKill;
                break;
                
            default:
                strategy = null;
                break;
        }
        
        return strategy;
    }
}
