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
public class Cybernetics extends Card 
{

    public Cybernetics()
    {
        
    }
    
    enum BodyPart //Cybernetic objects can either be an arm or leg cybernetic.
    {
        LEGS,
        ARMS
    }
    
    int c_Damage; //Cybernetic damage, if there is any.
    BodyPart c_BodyPart; //Body part selection.  
}
