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
 * @author James Fairbairn
 * @author Steven Mead
 * @author w9101532
 *
 * Card object class. All cards are based on this object.
 */
public class Card implements Comparable<Card>
{

    protected String id = "";
    protected String name = "";
    protected int manaCost = 0;
    protected int attack = 0;
    protected int health = 0;
    protected int armSlots = 0;
    protected int legSlots = 0;
    protected int miscSlots = 0;
    protected CardStrategy strategy;

    Card()
    {
        
    }

    public Card(String id, String name, int manaCost, int attack, int health)
    {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }

    public Card(Card card)
    {
        this.id = card.id;
        this.name = card.name;
        this.manaCost = card.manaCost;
        this.attack = card.attack;
        this.health = card.health;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getManaCost()
    {
        return this.manaCost;
    }

    public int getAttack()
    {
        return this.attack;
    }

    public int getHealth()
    {
        return this.health;
    }

    /**
     * Attack function for all cards. Dealing out damage will damage
     * the player's card.
     * @param enemyCard     Input the selected enemy card to deal damage to.
     * @param player        Player is inputted to use removeMana method for using a card.
     * @return              Returns true if the attack was successful. False otherwise.
     */
    public boolean attack(Card enemyCard, Player player)
    {
        boolean removeMana = player.removeMana(this.manaCost);
        
        if (removeMana == true)
        {
            enemyCard.damage(attack);
            damage(enemyCard.getAttack());
            
            return true;
        }
        else
        {
            System.out.println("You do not have enough mana.");
            return false;
        }
    }
    
    public void damage(int amount)
    {
        this.health -= amount;
    }
    
    public int getArmSlots()
    {
        return this.armSlots;
    }
    
    public int getLegSlots()
    {
        return this.legSlots;
    }
    
    public int getMiscSlots()
    {
        return this.miscSlots;
    }
    
    public void setArmSlots(int value)
    {
        if (value != 0)
        {
           this.armSlots = value; 
        }
        else
        {
            System.out.println("Error: No slots available.");
        }
    }

    public void setLegSlots(int value)
    {
        if (value != 0)
        {
           this.legSlots = value; 
        }
        else
        {
            System.out.println("Error: No slots available.");
        }
    }
    
    public void setMiscSlots(int value)
    {
        if (value != 0)
        {
           this.miscSlots = value; 
        }
        else
        {
            System.out.println("Error: No slots available.");
        }
    }
    
    public void setStrategy(CardStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void useStrategy()
    {
        System.out.println(strategy.toString());
    }

    /**
     * Displays information about each card in simple text.
     * @return      return string of information. 
     */
    @Override
    public String toString()
    {
        return this.name + " (" + this.id + ") Mana Cost(" + this.manaCost + ")"
                + " Attack(" + this.attack + ") Health(" + this.health + ")\n";
    }

    @Override
    public int compareTo(Card o)
    {
        return Integer.compare(this.getManaCost(), o.getManaCost());
    }
}
