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

import java.util.Scanner;

/**
 *
 * @author w9101532
 */
public class GameLogic
{
    /**
     * Produces a list of the enemy's cards. Allows an input to select a card to
     * attack. The player can also choose the enemy player if wanted.
     * @param enemy     The enemy player object.
     * @return          Returns the chosen card.
     */
    public Card selectEnemyCard(Player enemy)
    {       
        System.out.println("Select an enemy card from their table.");
        
        //Shows enemies cards.
        for (int i = 0; i < enemy.getTable().getCards().size(); i++)
        {
            //The (i+1)+"." is just indexing each card in the loop starting from 2 (since 1. is taken by the enemy player).
            System.out.println((i + 1) + "." + enemy.getTable().getCards().get(i).toString());
        }
        
        //Finds the chosen card as listed in the for loop above. The -1 just allows you to use 1 for the first choice and not 0.
        Card selectedEnemyCard = enemy.getTable().getCards().get(checkPlayerInput(enemy.getTable().count()) - 1);

        return selectedEnemyCard;
    }
    
    /**
     * Produces a list of the player's cards. Allows an input to select a card to
     * use (attack or action).
     * @param player    The player object.
     * @return          Returns the chosen card.
     */
    public Card selectCard(Player player)
    {
        System.out.println("Select a card from your table.");

        //Shows your cards.
        for (int i = 0; i < player.getTable().getCards().size(); i++)
        {
            //The (i+1)+"." is just indexing each card in the loop starting from 1.
            System.out.println((i + 1) + "." + player.getTable().getCards().get(i).toString());
        }
        
        //Gets a reference to card on your table. -1 is to convert natural number index to integer index.
        Card selectedCard = player.getTable().getCard(checkPlayerInput(player.getTable().count()) - 1);
        
        return selectedCard;
    }
    
    
    /**
     * Basically the main action menu. The player can end their turn, play a card, attack the player
     * or place a card from their hand onto the table.
     * @param player    Player reference to access card collections.
     * @param enemy     Enemy reference to access card collections.
     */
    public void menu(Player player, Player enemy)
    {
        while (true)
        {
            System.out.println("Select enemy type.");
            System.out.println("1." + enemy.getName());
            System.out.println("2." + enemy.getName() + "'s table");
            System.out.println("3.Use hand.");
            System.out.println("4.End turn.");
            
            switch (checkPlayerInput(3))
            {
                case 1:
                    attackSequencePlayer(player, enemy); //Attack player.
                    break;

                case 2:
                    attackSequenceCard(player, enemy); //Attack player's cards.
                    break;
                    
                case 3:
                    useHand(player);
                    break;
                    
                case 4:
                    return; //End turn.
            }
        }
    }
    
    
    /**
     * The attack methods for damaging an enemy player via selected card.
     * @param player    Player reference to access cards.
     * @param enemy     Enemy reference to deal damage to.
     */
    public void attackSequencePlayer(Player player, Player enemy)
    {
        //Player selects their card before the attack sequence begins.
        player.setSelectedCard(selectCard(player));

        if (player.getManaAvailable() >= player.getSelectedCard().getManaCost())
        {
            //Use up player's mana.
            player.useMana(player.getSelectedCard().getManaCost());

            if (enemy.damage(player.getSelectedCard().getAttack()))
            {
                enemy.death();
            }

            System.out.println(player.getName() + " strikes " + enemy.getName()
                    + " for " + player.getSelectedCard().getAttack() + " damage.");
        } 
        else
        {
            System.out.println(player.getName() + " doesn't have enough mana. ("
                    + player.getManaAvailable() + ")");
        }
    }
    
    /**
     * This method inputs the player and their card, and the enemy's card. The player
     * can choose to end their turn or continue attacking.
     * @param player        Player reference for multiple functions.
     * @param enemy         Enemy reference for multiple functions.
     */
    public void attackSequenceCard(Player player, Player enemy)
    {
        //Create input for player's decision.
        Scanner newInput = new Scanner(System.in);
        
        //Player selects their card before the attack sequence begins.
        player.setSelectedCard(selectCard(player));
        
        //Player selects an enemy before the attack sequence begins.
        Card selectedEnemy = selectEnemyCard(enemy);
        
        while (true)
        {   
            //Using the player's selected card, and selected enemy card. Attack.
            //Since a successful attack returns true, it'll keep looping after each successful attack until player decides to end turn (e.g no mana).
            boolean attackFlag = player.getSelectedCard().attack(selectedEnemy, player, enemy);
                
            if (!attackFlag)
            {
                //Log for failed attack sequence.
                System.out.println("Attack failed. Not enough mana.");
                System.out.println("Enemy has (" + player.getHealth() + ") HP");
                System.out.println("You have " + player.getManaAvailable() + " mana left");
                System.out.println("Select new card? (yes/no)");
                System.out.println("\nOr press ENTER to end turn.");
            } 
            else
            {
                //Log for attack sequence.
                System.out.println("You dealt " + player.getSelectedCard().getAttack() + " damage to " + selectedEnemy.getName());
                System.out.println("Enemy has (" + player.getHealth() + ") HP");
                System.out.println("You have " + player.getManaAvailable() + " mana left");
                System.out.println("Select new card? (yes/no)");
                System.out.println("\nOr press ENTER to end turn.");
            }
            
            while (true)
            {
                //Clear buffer. Is required if response is valid on succesful attack.
                newInput.reset();
                String userInput = newInput.nextLine();
                
                if (userInput.equalsIgnoreCase("Yes") || userInput.equalsIgnoreCase("y")) //Yes to select new card and then select new enemy.
                {
                    player.setSelectedCard(selectCard(player));

                    System.out.println("Select enemy to attack.");
                    selectedEnemy = selectEnemyCard(enemy);
                    
                    break;
                } 
                else if (userInput.equalsIgnoreCase("No") || userInput.equalsIgnoreCase("n")) //No to keep card and select new enemy.
                {
                    System.out.println("Select enemy to attack.");
                    selectedEnemy = selectEnemyCard(enemy);
                    
                    break;
                } 
                else if (userInput.equals("")) //Pressing ENTER will end turn;
                {
                    return;
                } 
                else //Invalid response, will loop for valid answer.
                {
                    System.out.println("Invalid response.");
                    
                    //Clear buffer for new input.
                    newInput.reset();
                }
            }      
        }
    }
    
    /**
     * Add cybernetics to player's cards.
     * @param player    Player whose cards will be modified.
     * @return          Returns false if exit. 
     */
    public boolean cardBuilderInterface(Player player)
    {
        while (true)
        {
            System.out.println("Choose from the following options.");
            System.out.println("1. Add cybernetics\n2. Exit");
            Scanner newInput = new Scanner(System.in);

            //Choice 1 is to add cybernetics.
            if (checkPlayerInput(2) == 1)
            {
                //Clear buffer.
                newInput.reset();

                //Select card from hand.
                //You can only upgrade cards that are in your hand.
                System.out.println("Please select a card to upgrade.");
                
                //Shows player's hand.
                for (int i = 0; i < player.getHand().getCards().size(); i++)
                {
                    //The (i+1)+"." is just indexing each card in the loop starting from 2 (since 1. is taken by the enemy player).
                    System.out.println((i + 1) + "." + player.getHand().getCards().get(i).toString());
                }
                
                int choice = checkPlayerInput(player.getHand().count() - 1);
                Card c = player.getHand().getCard(choice);
                c = new Cybernetic(c);
                player.replaceInHand(c, choice);
                
                //Shows player's hand.
                for (int i = 0; i < player.getHand().getCards().size(); i++)
                {
                    //The (i+1)+"." is just indexing each card in the loop starting from 2 (since 1. is taken by the enemy player).
                    System.out.println((i + 1) + "." + player.getHand().getCards().get(i).toString());
                }
                
                return true;
                
            } 
            else if (checkPlayerInput(2) == 2) //Choice 2 is go back.
            {
                return false;
            }
        }
    }
    
    /**
     * Check player's hand and allow selection to place on Table.
     * @param player    The current player's reference for access of card collections.
     */
    public void useHand(Player player)
    {
        System.out.println("Select a card from your hand, and place it on the table.\n");
        
        //Shows your cards.
        for (int i = 0; i < player.getHand().getCards().size(); i++)
        {
            //The (i+1)+"." is just indexing each card in the loop starting from 1.
            System.out.println((i + 1) + "." + player.getHand().getCards().get(i).toString());
        }
        
        player.placeCard(player.getHand().getCard(checkPlayerInput(player.getHand().count()) - 1));
    }
    
    /**
     * Method checks input for yes or no string. If anything else, will
     * repeat itself till an appropriate response is put in.
     * @return          Returns true or false (yes and no respectively).
     */
    public boolean checkConfirmation()
    {
        Scanner newInput = new Scanner(System.in);
        String input = newInput.nextLine();
        
        while (true)
        {
            if (input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("y"))
            {
                return true;
            }
            else if (input.equalsIgnoreCase("No") || input.equalsIgnoreCase("n"))
            {
                return false;
            } 
            else
            {
                System.out.println("Please enter an appropriate input. (Y/Yes or N/No)");
                newInput.reset();
                input = newInput.nextLine();
            }
        }  
    }
    
    /**
     * Players select certain choices via integers. If a number input is over
     * the max input it'll repeat the method.
     * @param maxInput      Max input that can be entered and compared to first parameter.
     * @return              Returns original input if condition is true.
     */
    public int checkPlayerInput(int maxInput)
    {
        Scanner newInput = new Scanner(System.in);
        int input = newInput.nextInt();
        
        while (true)
        {
            if (input <= maxInput)
            {
                return input;
            }
            else
            {
                System.out.println("Please enter a number equal or less than " + maxInput);
                
                //Clear buffer, take in new input.
                newInput.reset();
                input = newInput.nextInt();
            }
        }
    }
}
