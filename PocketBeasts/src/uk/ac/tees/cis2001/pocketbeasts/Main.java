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
        System.out.println("At the start of the game each player draws 5 cards from their deck to hand.");
        System.out.println("Two of those cards are automatically placed on the table.");
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

        //Sets up the game for the players.
        newGame(players);
        
        //An instance of the object that contains methods and logic used for the game.
        GameLogic gameLogic = new GameLogic();
        
        int index = 0; //Index alternates each loop
        Boolean run = true;
        while (run)
        {
            for (Player player : players)
            {
                if (index == 0)
                    index++;
                else
                    index--;

                //Enemy player variable.
                Player enemy = players[index];
                
                //Add mana to current player. (1 every round).
                player.addMana();
                
                //After confirming your turn. Draw a cand into hand.
                player.drawCard();
                
                //Print initial play state
                System.out.println(player.toString());
                
                //First stage.
                do 
                {
                    while (true)
                    {
                        System.out.println(player.getName() + ", Choose an option\nSelect by typing in the index number.");
                        System.out.println("1. Play turn\n2. Access card builder");
                        
                        //Max input is two as there are only two available options. Returns original input.
                        switch (gameLogic.checkPlayerInput(2)) 
                        {
                            case 1:
                                break;

                            case 2:
                                if (gameLogic.cardBuilderInterface(player) == false) //If player decides to quit the card builder: repeat options.
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
                //TODO: Remove this condition so it doesnt ask if the user is sure.
                while (!gameLogic.checkConfirmation()); //Check if confirmationInput says yes or no, otherwise upper code continues to loop.
                
                                
                //Second stage.
                do 
                {
                    System.out.println("Player: " + player.getName() + "'s turn\n");
                    System.out.println(enemy);
        
                    gameLogic.menu(player, enemy);
                    
                    System.out.println("End turn?");
                } 
                while (!gameLogic.checkConfirmation()); //Check if confirmationInput says yes or no, otherwise method continues to loop.
                

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
    private static void newGame(Player[] playerArray)
    {
        for (Player players : playerArray)
        {
            players.newGame();
        }
    }
}