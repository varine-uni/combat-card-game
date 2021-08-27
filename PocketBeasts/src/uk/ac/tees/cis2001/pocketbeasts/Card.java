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
public abstract class Card implements Comparable<Card>
{

    protected String id = "";
    protected String name = "";
    protected int manaCost = 0;
    protected int attack = 0;
    protected int health = 0;

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
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getManaCost()
    {
        return manaCost;
    }

    /**
     * Gets the card's current attack value.
     * @return  Returns the attack value.
     */
    public int getAttack()
    {
        return attack;
    }

    /**
     * Gets the card's current health value.
     * @return  Returns the health value.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Attack function for all cards. Dealing out damage will damage
     * the player's card. Deducts mana.
     * @param enemyCard     Input the selected enemy card to deal damage to.
     * @param player        Player is inputted to access table and graveyard in the damage method. Logic for mana is used also.
     * @param enemy         Enemy to access enemy's table and graveyard. Because cards damage each other in one go.
     * @return              Returns true when the player wasn't out of mana when the attack begun. 
     */
    public boolean attack(Card enemyCard, Player player, Player enemy)
    {   
        if (player.getManaAvailable() >= this.manaCost)
        {
            /**
             * Death check. In the damage method, it returns true if the damage
             * take was fatal. This is where it's used.
             */
            if (enemyCard.damage(getAttack()) == true)
            {
                enemyCard.death(enemy);
            }
            if (damage(enemyCard.getAttack()) == true)
            {
                this.death(player);
            }
            
            //Use up player's mana.
            player.useMana(this.manaCost);
            
            return true;
        }
        else
        {
            System.out.println(player.getName() + " doesn't have enough mana. (" 
                    + player.getManaAvailable() + ")");
            return false;
        }
        
    }
    
    /**
     * Deducts from available HP.
     * @param amount    Damage dealt.
     * @return          Returns true if card dies. False otherwise.
     */
    public boolean damage(int amount)
    {
        this.health -= amount;

        if (health <= 0)
        {
            //Health wont appear in negatives on death.
            health = 0;

            return true;
        }
        else
        {
            return false;
        }
    }

    private void death(Player player)
    {
        player.graveyard.add(this);
        player.table.remove(this);
    }

    /**
     * Displays information about each card in simple text.
     * @return      return string of information. 
     */
    @Override
    public String toString()
    {
        return getName() + " (" + getId() + ") Mana Cost(" + getManaCost() + ")"
                + " Attack(" + getAttack() + ") Health(" + getHealth() + ")\n";
    }

    @Override
    public int compareTo(Card o)
    {
        return Integer.compare(this.getManaCost(), o.getManaCost());
    }
}
