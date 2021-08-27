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
public class RageState implements WarriorState, WarObserver
{
    /**
     * Changes the state based on the condition.
     * @param war   The Cyber Warrior player.
     */
    @Override
    public void action(Class_CyberWarrior war)
    {
        if (war.getHealth() <= war.getMaxHealth() / 4)
        {
            System.out.println("Warrior is feeling enraged.");
            war.setState(this);
            war.setStrategy(new RageAbility());
        }
    }

    /**
     * Calls the state's action to check whether to change states or not. (All state objects receive this call).
     * @param war   The Cyber Warrior player.
     */
    @Override
    public void update(Class_CyberWarrior war)
    {
        action(war);
    }
}
