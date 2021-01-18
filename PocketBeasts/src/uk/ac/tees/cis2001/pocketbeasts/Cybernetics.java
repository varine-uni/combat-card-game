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
 * 
 * Cybernetics class extends the decorator as a cybernetic object can "wrap" a
 * card object. This is possible if a card can ask for a cybernetic arm,
 * leg, or misc.
 */
public class Cybernetics extends CyberDecorator 
{
    public boolean isLeg = false;
    public boolean isArm = false;
    public boolean isMisc = false;
    
    public Cybernetics(Card card, int attack, int health)
    {
        super(card);
        this.attack = attack;
        this.health = health;
    }
    
    public String getPartType()
    {
        String output = "Is leg? : " + isLeg + "\nIs arm? : " + isArm + "\nIs Misc? : " + isMisc;
        return output;
    }
}
