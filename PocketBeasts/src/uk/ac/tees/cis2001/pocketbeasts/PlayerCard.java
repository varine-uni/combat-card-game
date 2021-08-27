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
public class PlayerCard extends Card
{
    public PlayerCard(String id, String name, int manaCost, int attack, int health)
    {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }
    
    public PlayerCard(Card card)
    {
        this.id = card.id;
        this.name = card.name;
        this.manaCost = card.manaCost;
        this.attack = card.attack;
        this.health = card.health;
    }
    
    public PlayerCard()
    {
        
    }
}
