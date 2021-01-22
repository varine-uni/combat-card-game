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
 * @author James Fairbairn
 * @author Steven Mead
 * @author w9101532
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Welcome to PocketBeasts!");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("");
        System.out.println("This basic console application tests our underlying software design patterns.");
        System.out.println("");
        System.out.println("Here's a key for each card:");
        System.out.println("");
        System.out.println("                             +-------+ ");
        System.out.println("M  = Mana Cost               |      M| ");
        System.out.println("ID = Card identifier:        |  ID   | ");
        System.out.println("A  = Attack:                 |       | ");
        System.out.println("H  = Health:                 |A     H| ");
        System.out.println("                             +-------+ ");
        System.out.println("");
        System.out.println("New players each start with 15 Health and 1 Mana to spend on playing cards.");
        System.out.println("At the start of the game each player draws 4 cards from their deck to hand.");
        System.out.println("");
        System.out.println("Players each take turns. Each turn consists four phases:");
        System.out.println("1. Add mana (mana increases by one each turn and replenishes in full).");
        System.out.println("2. Draw a card.");
        System.out.println("3. Choose a card (or the player) to attack.");
        System.out.println("   a. Attacking the other player directly with your card inflicts damage to their health.");
        System.out.println("      equal to the attack power of the card.");
        System.out.println("   b. Attacking another players beast will damage both cards (equal to their attack values).");
        System.out.println("   c. Any beast with <= 0 health is removed from the play field and placed into the graveyard.");
        System.out.println("4. Play cards from hand.");
        System.out.println("");

        System.out.println("Press ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        
        //TODO: Allows name change and class selection.
        Player[] players = new Player[]
        {
            new Class_Netrunner("V"),
            new Class_CyberWarrior("Silverhand")
        };

        //Some methods when static causes a lot of problems. Including selecting cards and fighting them.
        Main main = new Main();
        
        //Set up the intial game for the players.
        main.newGame(players);

        Boolean run = true;
        while (run)
        {
            for (Player player : players)
            {
                //Index counter for knowing the second player's variable.
                int index = 0; 
                
                //Counter alternates between 1 and 0 for the player's array.
                if (index == 1) 
                {
                    index = 0;
                } 
                else
                {
                    index = 1;
                }

                //Enemy player variable.
                Player enemy = players[index];
                
                //Add mana to current player. (1 every round).
                player.addMana();
                
                //Print initial play state
                System.out.println(player.toString());
                
                //First stage.
                do 
                {
                    while (true)
                    {
                        System.out.println(player.getName() + ", Choose an option\nSelect by typing in the index.");
                        System.out.println("1. Play turn\n2. Access card builder");
                        
                        //Max input is two as there are only two available options. Returns original input.
                        switch (main.checkPlayerInput(2)) 
                        {
                            case 1:
                                break;

                            case 2:
                                if (main.cardBuilder(player) == false) //If player decides to quit the card builder: repeat options.
                                {
                                    continue;
                                }
                                else //Else continue as normal.
                                {
                                    break;
                                }
                                    
                            default:
                                System.out.println("Going back2.");
                                continue;       
                        }
                        break;
                    }   
                    System.out.println("Are you sure?");
                }
                while (!main.checkConfirmation()); //Check if confirmationInput says yes or no, otherwise upper code continues to loop.
                
                //Second stage.
                do 
                {
                    System.out.println("Player: " + player.getName() + "'s turn\n");
                    System.out.println(enemy);
                    
                    main.selectCard(player);
                    
                    main.attackSequence(main.selectCard(player), main.selectEnemy(enemy), player, enemy);
                    
                    System.out.println("Are you sure?");
                } 
                while (!main.checkConfirmation()); //Check if confirmationInput says yes or no, otherwise method continues to loop.

                if (!run)
                {
                    break;
                }

                //Print final play state
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(player);
            }
        }
    }
    
    /**
     * Gets the array of players, sets up a new game for them.
     * @param playerArray   The player array.
     */
    private void newGame(Player[] playerArray)
    {
        for (Player players : playerArray)
        {
            players.newGame();
        }
    }
    
    /**
     * Produces a list of the enemy's cards. Allows an input to select a card to
     * attack. The player can also choose the enemy player if wanted.
     * @param enemy     The enemy player object.
     * @return          Returns the chosen card.
     */
    private Card selectEnemy(Player enemy)
    {
        System.out.println("Select an enemy card from their table.");

        //Shows enemies cards.
        for (int i = 0; i < enemy.getTable().getCards().size(); i++)
        {
            //The (i+1)+"." is just indexing each card in the loop starting from 1.
            System.out.println((i + 1) + "." + enemy.getTable().getCards().get(i).toString());
        }

        //Gets a reference to card on enemy's table. -1 is to convert natural number index to integer index.
        Card selectedEnemyCard = enemy.getTable().getCard(checkPlayerInput(enemy.getTable().count()) - 1);

        return selectedEnemyCard;
    }
    
    /**
     * Produces a list of the player's cards. Allows an input to select a card to
     * use (attack or action).
     * @param player    The player object.
     * @return          Returns the chosen card.
     */
    private Card selectCard(Player player)
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
     * This method inputs the player and their card, and the enemy's card. The player
     * can choose to end their turn or continue attacking if they have the right mana.
     * @param playerCard    Player's selected card.
     * @param enemyCard     Enemy's selected card.
     * @param player        Player reference for multiple functions.
     * @param enemy         Enemy reference for multiple functions.
     */
    private void attackSequence(Card playerCard, Card enemyCard, Player player, Player enemy)
    {
        //Create input for player's decision.
        Scanner newInput = new Scanner(System.in);
        
        //Using the player's selected card, and selected enemy card. Attack. Will deduct mana from player.
        //Since a successful attack returns true, it'll loop until you cannot attack any longer (no mana).
        while (playerCard.attack(enemyCard, player))
        {
            System.out.println("You dealt " + playerCard.getAttack() + " damage to " + enemyCard.getName());
            System.out.println("Enemy has (" + player.getHealth() + ") HP");
            System.out.println("You have (" + player.getManaAvailable() + ") mana available.");
            System.out.println("Select new card?");
            System.out.println("Or press ENTER to end turn.");
            
            
            
            while (true)
            {
                //Clear buffer. Is required if response is valid on succesful attack.
                newInput.reset();
                String example = newInput.nextLine();
                
                if (example.equalsIgnoreCase("Yes") || example.equalsIgnoreCase("y")) //Yes to select new card and then select new enemy.
                {
                    playerCard = selectCard(player);

                    System.out.println("Select enemy to attack.");
                    enemyCard = selectEnemy(enemy);
                    
                    break;
                } 
                else if (example.equalsIgnoreCase("No") || example.equalsIgnoreCase("n")) //No to keep card and select new enemy.
                {
                    System.out.println("Select enemy to attack.");
                    enemyCard = selectEnemy(enemy);
                    
                    break;
                } 
                else if (example.equals("")) //Pressing ENTER will end turn;
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
     * @return          Returns false if exit. Returns true to continue rest of code.
     */
    private boolean cardBuilder(Player player)
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
            Card selectedCard = player.getHand().getCards().get(newInput.nextInt()); 
            
            
            //Enter card into the card builder class.
            CardBuilder builder = new CardBuilder(selectedCard); 
        }
        else if (checkPlayerInput(2) == 2) //Choice 2 is go back.
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Method checks input for yes or no string. If anything else, will
     * repeat itself till an appropriate response is put in.
     * @return          Returns true or false (yes and no respectively).
     */
    private boolean checkConfirmation()
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
    private int checkPlayerInput(int maxInput)
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
