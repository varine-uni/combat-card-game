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
public abstract class CyberneticDecorator extends Card
{
    protected Card card;
    
    /**
     * Constructor.
     * @param card  Takes in a card parameter and sets it in the global variable.
     */
    public CyberneticDecorator(Card card)
    {
        this.card = card;
    }
    
    /**
     * Gets health.
     * @return  Returns the health value plus the wrapper.
     */
    @Override
    public int getHealth()
    {
        return card.getHealth() + health;
    }
    
    /**
     * Gets attack.
     * @return  Returns the attack value plus the wrapper.
     */
    @Override
    public int getAttack()
    {
        return card.getAttack() + attack;
    }
    
    /**
     * Gets the name.
     * @return  Returns the name plus the wrapper.
     */
    @Override
    public String getName()
    {
        return card.getName() + name;
    }
    
    /**
     * Gets ID.
     * @return  Returns the ID value plus the wrapper.
     */
    public String getID()
    {
        return card.getId() + id;
    }
    
    /**
     * Gets mana cost.
     * @return  Returns the mana value plus the wrapper.
     */
    @Override
    public int getManaCost()
    {
        return card.getManaCost() + manaCost;
    }
}
