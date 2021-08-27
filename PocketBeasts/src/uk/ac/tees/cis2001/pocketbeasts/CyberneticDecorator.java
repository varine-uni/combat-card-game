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
    
    public CyberneticDecorator(Card card)
    {
        this.card = card;
    }
    
    @Override
    public int getHealth()
    {
        return card.getHealth() + health;
    }
    
    @Override
    public int getAttack()
    {
        return card.getAttack() + attack;
    }
    
    @Override
    public String getName()
    {
        return card.getName() + name;
    }
    
    public String getID()
    {
        return card.getId() + id;
    }
    
    @Override
    public int getManaCost()
    {
        return card.getManaCost() + manaCost;
    }
}
