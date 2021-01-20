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
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author James Fairbairn
 * @author Steven Mead
 * @author w9101532
 */
public class Main
{

    public static String getPrompt(String prompt, String[] validResponse)
    {
        System.out.print(prompt);

        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        if (Arrays.stream(validResponse).anyMatch(response::equals))
        {
            return response;
        }

        return getPrompt(prompt, validResponse);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        System.out.println("");
//        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
//        System.out.println("Welcome to PocketBeasts!");
//        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
//        System.out.println("");
//        System.out.println("This basic console application tests our underlying software design patterns.");
//        System.out.println("");
//        System.out.println("Here's a key for each card:");
//        System.out.println("");
//        System.out.println("                             +-------+ ");
//        System.out.println("M  = Mana Cost               |      M| ");
//        System.out.println("ID = Card identifier:        |  ID   | ");
//        System.out.println("A  = Attack:                 |       | ");
//        System.out.println("H  = Health:                 |A     H| ");
//        System.out.println("                             +-------+ ");
//        System.out.println("");
//        System.out.println("New players each start with 15 Health and 1 Mana to spend on playing cards.");
//        System.out.println("At the start of the game each player draws 4 cards from their deck to hand.");
//        System.out.println("");
//        System.out.println("Players each take turns. Each turn consists four phases:");
//        System.out.println("1. Add mana (mana increases by one each turn and replenishes in full).");
//        System.out.println("2. Draw a card.");
        //TODO: Remove step 3 of the game.
//        System.out.println("3. Cycle through your cards in play (if any), choosing whether to attack.");
//        System.out.println("   a. Attacking the other player directly with your card inflicts damage to their health.");
//        System.out.println("      equal to the attack power of the card.");
//        System.out.println("   b. Attacking another players beast will damage both cards (equal to their attack values).");
//        System.out.println("   c. Any beast with <= 0 health is removed from the play field and placed into the graveyard.");
//        System.out.println("4. Play cards from hand.");
//        System.out.println("");
//
//        System.out.println("Press ENTER to continue...");
//        Scanner sc = new Scanner(System.in);
//        sc.nextLine();
//        
        Player[] players = new Player[]
        {
            new Class_Netrunner("V"),
            new Class_CyberWarrior("Silverhand")
        };

        newGame(players);

        //TODO: Deal with this.
        //String outputMessage = "";
        Boolean run = true;
        while (run)
        {
            for (Player player : players)
            {
                int index = 0; //Index counter for knowing the second player's variable.

                if (index == 1) //Counter alternates between 1 and 0 for the player's array.
                {
                    index = 0;
                } 
                else
                {
                    index = 1;
                }

                /**
                 * Create scanners for confirmation and player inputs.
                 */
                Scanner confirmation = new Scanner(System.in);
                Scanner playerInput = new Scanner(System.in);

                //Print initial play state
                do
                {
                    System.out.println(player.toString());
                    System.out.println("Player: " + player.getName() + "'s turn");
                    System.out.println("Which card will you attack?\n" + players[index].getName() + "'s cards on table\n");
                    //TODO: Testing with hand but should be table.
                    for (int i = 0; i < players[index].getHand().getCards().size(); i++)
                    {   //The (i+1)+"." is just indexing each card in the loop starting from 1.
                        System.out.println((i + 1) + "." + players[index].getHand().getCards().get(i).toString());
                    }

                    int selectedEnemyCard = playerInput.nextInt();

                } 
                while (checkConfirmation(confirmation.nextLine())); //Check if confirmation says yes or no, otherwise method continues to loop.

                if (!run)
                {
                    break;
                }

                //Print final play state
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(player);
            }
        }

        //System.out.println(outputMessage);
    }

    public static void newGame(Player[] playerArray)
    {
        for (Player players : playerArray)
        {
            players.newGame();
        }
    }

    /**
     * Method checks input for yes or no string. If anything else, will
     * repeat itself till an appropriate response is put in.
     * @param input     Text to enter from confirmation scanner.
     * @return          Returns true or false (yes and no respectively).
     */
    public static boolean checkConfirmation(String input)
    {
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
            }
        }  
    }
}
