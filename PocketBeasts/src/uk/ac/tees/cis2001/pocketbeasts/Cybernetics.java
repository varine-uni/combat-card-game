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
    public boolean isLeg = false;
    public boolean isArm = false;
    public boolean isMisc = false;
    Strategies strats = new Strategies();
    int stratChoice;
    
    public Cybernetics(Card card, int stratChoice)
    {
        super(card);
        //Choice is chosen in the client.
        this.stratChoice = stratChoice;
    }
    
    /**
     * Overridden card method. This method refers right back to the decorator's version
     * of this method but inputs a strategy before the decorator's version is called.
     * @param strat     Due to the nature of this method, this parameter isn't used in this class. 
     */
    @Override
    public void setStrategy(CardStrategy strat)
    {
        //Super here refers to the decorator.
        super.card.setStrategy(strats.selectStrategy(this.stratChoice));
    }
}
