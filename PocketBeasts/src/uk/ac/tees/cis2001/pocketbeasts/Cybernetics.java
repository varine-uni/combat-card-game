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
public final class Cybernetics extends CyberDecorator 
{
    Strategies strats = new Strategies();
    int stratChoice = 0;
    
    public Cybernetics(Card card, int strategy, int attack, int hp)
    {
        super(card);
        this.health += hp;
        this.attack += attack;
        this.stratChoice = strategy;
    }
    
    public Cybernetics(int strategy, int attack, int hp)
    {
        super();
        this.health = hp;
        this.attack = attack;
        this.stratChoice = strategy;
    }
    
    public Cybernetics(Cybernetics cybernetic)
    {
        this.health = cybernetic.health;
        this.attack = cybernetic.attack;
        this.stratChoice = cybernetic.stratChoice;
    }
    
    public Cybernetics (Card card, Cybernetics cybernetic)
    {
        super(card);
        this.health += cybernetic.health;
        this.attack += cybernetic.attack;
        this.stratChoice = cybernetic.stratChoice;
    }
    
    /**
     * Overridden card method. This method refers right back to the decorator's version
     * of this method but inputs a strategy before the decorator's version is called.
     * @param strat     Due to the nature of this class, this parameter isn't used in this class. 
     */
    @Override
    public void setStrategy(CardStrategy strat)
    {
        super.card.setStrategy(strats.selectStrategy(this.stratChoice));
    }
}
