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

import java.util.ArrayList;

/**
 *
 * @author w9101532
 */
public class Class_CyberWarrior extends Player implements WarSubject
{  
    /**
     * Unique cards for this class.
     */
    public final Card[] CYBERWARRIOR_CARDS = new Card[] 
    {
        new PlayerCard("AI", "Armoured Imp", 3, 1, 5), //3 mana, 1 attack, 5 HP.
        new PlayerCard("AD", "Armoured Demon", 3, 2, 5), //3 mana, 2 attack, 5 HP.
        new PlayerCard("TK", "Tank", 5, 3, 7), //5 mana, 3 attack, 7 HP.
        new PlayerCard("TT", "Tyrant", 10, 15, 15) //10 mana, 15 attack, 15 HP.
    };
    
    private ArrayList<WarObserver> observers;
    private WarriorState state;
    private WarriorStateStrategy strategy;
    
    /**
     * Constructor.
     * @param name  Input a name for the user. 
     */
    public Class_CyberWarrior(String name)
    {
        super("Cyber Warrior " + name);
        observers = new ArrayList<>();
        NormalState normal = new NormalState();
        BerserkState berserk = new BerserkState();
        RageState rage = new RageState();
        observers.add(normal);
        observers.add(berserk);
        observers.add(rage);
        this.deck.addToDeck(CYBERWARRIOR_CARDS); //Add unique cards to deck.
        state = null;
    }
    
    /**
     * Sets the strategy.
     * @param strategy  The strategy object to be executed.
     */
    public void setStrategy(WarriorStateStrategy strategy)
    {
        this.strategy = strategy;
    }
    
    /**
     * Executes the current strategy.
     */
    public void executeStrategy()
    {
        strategy.activateStrategy(this);
    }
    
    /**
     * Sets the Cyber Warrior's state.
     * @param state     The chosen state.
     */
    public void setState(WarriorState state)
    {
        this.state = state;
    }
    
    /**
     * Returns the current state.
     * @return      Returns the current state.
     */
    public WarriorState getState()
    {
        return state;
    }
    
    /**
     * Player's damage function (taken). Player can take damage themselves and will
     * die if damage was fatal.
     * @param amount   amount of damage the player will take.
     * @return         Returns true if damage taken was fatal.
     */
    @Override
    public boolean damage(int amount)
    {
        this.health -= amount;
        
        this.notifyUpdate(this); //Notifies the available states to check if warrior should enter a state.
        
        return health <= 0;
    }
    
    /**
     * Notifies all observers to be "updated", allowing them to check conditions and change state
     * based on them.
     * @param war   The cyber warrior player.
     */
    @Override
    public void notifyUpdate(Class_CyberWarrior war)
    {
        for (WarObserver o: observers)
        {
            o.update(this);
        }
    }
}
