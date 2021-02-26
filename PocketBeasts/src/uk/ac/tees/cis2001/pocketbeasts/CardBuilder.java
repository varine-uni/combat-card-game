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
public class CardBuilder
{   
    private Card card;
    
    CardBuilder(Card card)
    {
        this.card = card;
    }
    
    /**
     * Input a card and a cybernetic. Cybernetic card will wrap the card object.
     * @param cybernetic    Input a cybernetic card. 
     * @return              Returns the built card object.
     */
    public Card addCybernetics(Cybernetics cybernetic)
    {
        card = new Cybernetics(card, cybernetic);
        
        return build();
    }
    
    /**
     * Returns the card.
     * @return  Returns the card object.
     */
    private Card build()
    {
        return card;
    }
}
