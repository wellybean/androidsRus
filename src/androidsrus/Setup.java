/* 
 * Copyright (C) 2019 Wellington Regis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package androidsrus;

import java.util.*;

/**
 * An instance of class <code>Setup</code> holds info on all existing robots, 
 * as well as info on origin and destination of robot parts
 * 
 * @author Wellington Regis
 */
public class Setup {
    // Used to hold data
    private final HashMap robotViaSerial;
    private final HashMap partOrigin;
    private final HashMap partDestination;
    
    // Used to help create new robots from old androids
    private final HashSet availableBrain;
    private final HashSet availableMobility;
    private final HashSet availableVision;
    private final HashSet availableArms;
    private final HashSet availableMediaCenter;
    private final HashSet availablePowerPlant;

    /**
     * Constructor for the <code>Setup</code> class
     */
    public Setup() {
        this.robotViaSerial = new HashMap<>();
        this.partOrigin = new HashMap<>();
        this.partDestination = new HashMap<>();
        this.availableBrain = new HashSet<>();
        this.availableMobility = new HashSet<>();
        this.availableVision = new HashSet<>();
        this.availableArms = new HashSet<>();
        this.availableMediaCenter = new HashSet<>();
        this.availablePowerPlant = new HashSet<>();
    }

    /**
     * This method initialises the first 500 androids to be then used when 
     * creating the new line of robots
     */
    public void setUp() {
        String model;
        int serialNumber;
        String brain;
        String mobility;
        String vision;
        String arms;
        String mediaCenter;
        String powerPlant;
        
        for(int i = 0; i < 500; i++) {
            // Randomisation of parts and serial number (which is unique)
            model = Utilities.getInstance().pickARandomString("mk1", "mk2", "mk3", "mk4", "mk5");
            serialNumber = Utilities.getInstance().pickARandomSerialNumber(robotViaSerial);
            brain = Utilities.getInstance().pickARandomString("2.5", "3.5", "4.5");
            mobility = Utilities.getInstance().pickARandomString("catapilar tracks", "wheels", "legs");
            vision = Utilities.getInstance().pickARandomString("heat vision", "full colour", "night vision");
            arms = Utilities.getInstance().pickARandomString("claws", "grippers", "articulated fingers");
            mediaCenter = Utilities.getInstance().pickARandomString("sony", "jvc", "meridian");
            powerPlant = Utilities.getInstance().pickARandomString("lithium", "hydrogen", "plasma");
            
            // Creates robot and adds it to hash map holding pool of robots
            Robot robot = new Robot(model, serialNumber, brain, mobility, vision, arms, mediaCenter, powerPlant);
            this.robotViaSerial.put(serialNumber, robot);
            
            // Adds the serial number of the newly created robot to hash sets 
            // that will be used when creating new robots. If a serial number 
            // is in the hash set, that means it is available. If a serial 
            // number is chosen, it is then deleted from the set.
            availableBrain.add(serialNumber);
            availableMobility.add(serialNumber);
            availableVision.add(serialNumber);
            availableArms.add(serialNumber);
            availableMediaCenter.add(serialNumber);
            availablePowerPlant.add(serialNumber);
            
        }
    }
    
    /**
     * Gets hash map containing serial numbers as keys and <code>Robot</code>
     * objects as values for all objects. It is used to quickly access a 
     * <code>Robot</code> instance through its serial number
     * 
     * @return hash map containing serial numbers as keys and <code>Robot</code>
     * objects as values for all objects
     */
    public HashMap<Integer,Robot> getRobotViaSerial() {
        return robotViaSerial;
    }
    
     /**
      * Gets hash map used to find out the origins of the parts of a robot from 
      * the new line. It contains serial numbers of new robots as keys and 
      * other hash maps as values (which have strings with robot part names as 
      * keys and serial numbers as values). 
      * 
      * @return hash map containing serial numbers of new robots as keys and 
      * other hash maps as values (which has strings with robot part names as 
      * keys and serial numbers as values)
      */
    public HashMap<Integer, HashMap> getPartOrigin() {
        return partOrigin;
    }

    /**
     * Gets hash map used to find out the destination of the parts of a robot 
     * from the old line. It contains serial numbers of old robots as keys and 
     * other hash maps as values (which have strings with robot part names as 
     * keys and serial numbers as values). If the second hash map does not 
     * contain an entry with a robot part name as key, this means that the 
     * robot part was not yet used.
     *
     * @return hash map containing serial numbers of old robots as keys and 
     * other hash maps as values (which have strings with robot part names as 
     * keys and serial numbers as values)
     */
    public HashMap<Integer, HashMap> getPartDestination() {
        return partDestination;
    }

    /**
     * Gets hash set with the serial numbers of available brain parts
     * 
     * @return hash set with the serial numbers of available brain parts
     */
    public HashSet getAvailableBrain() {
        return availableBrain;
    }

    /**
     * Gets hash set with the serial numbers of available mobility parts
     * 
     * @return hash set with the serial numbers of available mobility parts
     */
    public HashSet getAvailableMobility() {
        return availableMobility;
    }

    /**
     * Gets hash set with the serial numbers of available vision parts
     * 
     * @return hash set with the serial numbers of available vision parts
     */
    public HashSet getAvailableVision() {
        return availableVision;
    }

    /**
     * Gets hash set with the serial numbers of available arms parts
     * 
     * @return hash set with the serial numbers of available arms parts
     */
    public HashSet getAvailableArms() {
        return availableArms;
    }

    /**
     * Gets hash set with the serial numbers of available media center parts
     * 
     * @return hash set with the serial numbers of available media center parts
     */
    public HashSet getAvailableMediaCenter() {
        return availableMediaCenter;
    }
    
    /**
     * Gets hash set with the serial numbers of available power plant parts
     * 
     * @return hash set with the serial numbers of available power plant parts
     */
    public HashSet getAvailablePowerPlant() {
        return availablePowerPlant;
    }
}
