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
 * This is a decorator that will add cybernetic components to card objects.
 */
public abstract class CyberDecorator extends Card
{
    protected Card card;

    public CyberDecorator(Card card)
    {
        this.card = card;
    }
    
    @Override
    public int getAttack()
    {
        return card.getAttack() + this.attack;
    }
    
    @Override
    public int getHealth()
    {
        return card.getHealth() + this.health;
    }
    
    @Override
    public String toString()
    {
        return card.toString();
    }

    /**
     * Overridden card method. Initially this is just a bridge to the Cybernetics
     * class version of this method. But in the Cybernetics class, this method refers
     * right back to this one to set the strategy on the parent.
     * @param strat     Cybernetics class inputs a strategy in here.
     */
    @Override
    public void setStrategy(CardStrategy strat)
    {
        System.out.println("Decorator");
        super.setStrategy(strat);
    }

    /**
     * Overridden card method. This method is delegated when the superclass's (the card)
     * is called on the client. This method is not in the Cybernetics class because
     * it's unnecessary - it doesn't do anything extra (which is contrasted to how important
     * setStrategy method is to be in the Cybernetics class.
     */
    @Override
    public void useStrategy()
    {
        card.useStrategy();
    }
}
