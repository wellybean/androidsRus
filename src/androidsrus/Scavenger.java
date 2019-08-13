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
 * This class contains methods for creating new robots from old ones, as well as
 * for storing information on the origin and destination of robot parts
 *
 * @author Wellington Regis
 */
public class Scavenger {

    /**
     * Creates new robots and stores info on the origin and destination of robot
     * parts
     *
     * @param s contains an instance of the <code>Setup</code> class holding all
     * the information concerning robots and the origin and destination of robot
     * parts
     */
    public static void createNewRobots(Setup s) {
        String model;
        int serialNumber;
        String brain;
        String mobility;
        String vision;
        String arms;
        String mediaCenter;
        String powerPlant;
        int brainSerialNumber;
        int mobilitySerialNumber;
        int visionSerialNumber;
        int armsSerialNumber;
        int mediaCenterSerialNumber;
        int powerPlantSerialNumber;

        // Iterates for the creation of the robots
        for (int i = 0; i < 400; i++) {

            // Randomly picks a model
            model = Utilities.getInstance().pickARandomString("Andy the Android", "Betty the Busibot", "Bobi the Racedroid", "Fred the Friendlybot");

            // Randomly pciks a serial number making sure it is unique
            serialNumber = Utilities.getInstance().pickARandomSerialNumber(s.getRobotViaSerial());

            /*
             * The following is done six times, once for each robot part
             * (1) Randomly picks the serial number of an available part making 
             * sure that no more than two parts come from the same old android
             * (2) Gets parts using previously picked serial numbers
             * (3) Stores information on the destination of parts
             * (4) Stores information on the origins of parts
             */
            
            // Brain
            brainSerialNumber = getRandomAvailablePart(s.getAvailableBrain(), "brain", null);
            brain = s.getRobotViaSerial().get(brainSerialNumber).getBrain();
            assignPartDestination(s, "brain", brainSerialNumber, serialNumber);
            assignPartOrigin(s, "brain", serialNumber, brainSerialNumber);
            
            // Mobility
            mobilitySerialNumber = getRandomAvailablePart(s.getAvailableMobility(), "mobility", null);
            mobility = s.getRobotViaSerial().get(mobilitySerialNumber).getMobility();
            assignPartDestination(s, "mobility", mobilitySerialNumber, serialNumber);
            assignPartOrigin(s, "mobility", serialNumber, mobilitySerialNumber);
            
            // Vision
            visionSerialNumber = getRandomAvailablePart(s.getAvailableVision(), "vision", s.getPartOrigin().get(serialNumber));
            vision = s.getRobotViaSerial().get(visionSerialNumber).getVision();
            assignPartDestination(s, "vision", visionSerialNumber, serialNumber);
            assignPartOrigin(s, "vision", serialNumber, visionSerialNumber);
            
            // Arms
            armsSerialNumber = getRandomAvailablePart(s.getAvailableArms(), "arms", s.getPartOrigin().get(serialNumber));
            arms = s.getRobotViaSerial().get(armsSerialNumber).getArms();
            assignPartDestination(s, "arms", armsSerialNumber, serialNumber);
            assignPartOrigin(s, "arms", serialNumber, armsSerialNumber);
            
            // Media Center
            mediaCenterSerialNumber = getRandomAvailablePart(s.getAvailableMediaCenter(), "mediaCenter", s.getPartOrigin().get(serialNumber));
            mediaCenter = s.getRobotViaSerial().get(mediaCenterSerialNumber).getMediaCenter();
            assignPartDestination(s, "mediaCenter", mediaCenterSerialNumber, serialNumber);
            assignPartOrigin(s, "mediaCenter", serialNumber, mediaCenterSerialNumber);
            
            // Power Plant
            powerPlantSerialNumber = getRandomAvailablePart(s.getAvailablePowerPlant(), "powerplant", s.getPartOrigin().get(serialNumber));
            powerPlant = s.getRobotViaSerial().get(powerPlantSerialNumber).getPowerPlant();
            assignPartDestination(s, "powerPlant", powerPlantSerialNumber, serialNumber);
            assignPartOrigin(s, "powerPlant", serialNumber, powerPlantSerialNumber);

            // Creates new robot and then adds it to pool of robots
            Robot robot = new Robot(model, serialNumber, brain, mobility, vision, arms, mediaCenter, powerPlant);
            s.getRobotViaSerial().put(serialNumber, robot);
        }
    }

    /*
     * Method used to store information on the destination of a particular robot
     * part
     *
     * @param s holds an instance of the class <code>Setup</code>
     * @param part holds a string with the name of the robot part (e.g. "arms")
     * @param origin holds an integer with the serial number of the donating
     * robot
     * @param destination holds an integer with the serial number of the robot
     * receiving the part
     */
    private static void assignPartDestination(Setup s, String part, int origin, int destination) {
        // Do this in case the donating robot has already donated a part, and
        // therefore there already is an entry for this robot in the hash map 
        // 'partDestination'.
        if (s.getPartDestination().containsKey(origin)) {
            s.getPartDestination().get(origin).put(part, destination);
        } // Otherwise, do this: firstly create and entry and then store info.
        else {
            s.getPartDestination().put(origin, new HashMap<>());
            s.getPartDestination().get(origin).put(part, destination);
        }
    }

    /*
     * Method used to store information on the origins of the parts of robots
     * from the new line
     *
     * @param s holds an instance of the class <code>Setup</code>
     * @param part holds a string with the name of the robot part (e.g. "arms")
     * @param destination holds an integer with the serial number of the robot
     * receiving the part
     * @param origin holds an integer with the serial number of the donating
     * robot
     */
    private static void assignPartOrigin(Setup s, String part, int destination, int origin) {
        // Do this in case the receiving robot has already received a part, and
        // therefore there already is an entry for this robot in the hash map 
        // 'partOrigin'.
        if (s.getPartOrigin().containsKey(destination)) {
            s.getPartOrigin().get(destination).put(part, origin);
        } // Otherwise, do this: firstly create and entry and then store info.
        else {
            s.getPartOrigin().put(destination, new HashMap<>());
            s.getPartOrigin().get(destination).put(part, origin);
        }
    }

    /*
     * This method receives a hash set with serial numbers from available robot
     * parts and keeps picking a random serial number until the following rule
     * is not broken: No robot from the new line should have more than two parts
     * originating from the same old android. Once a suitable part has been
     * found, the entry for that part is removed from the hash set.
     *
     * @param set holds a hash set with serial numbers from all available parts
     * of a particular type (such as "brain")
     * @param part holds a String with the name of the part (e.g. "arms" or
     * "vision")
     * @param alreadyChosenParts holds a hash map with part name Strings as keys
     * and serial numbers as values. It has all the serial numbers from all the
     * parts that have already been chosen for the construction of the new robot
     * @return suitable serial number for robot part
     */
    private static int getRandomAvailablePart(HashSet<Integer> set, String part, HashMap alreadyChosenParts) {

        Random r = Utilities.getInstance().getR();

        // Puts all available entries into array and then randomly selects one.
        Object[] keys = set.toArray();
        int randomPartSerialNumber = Integer.parseInt(keys[r.nextInt(keys.length)].toString());

        // Since "brain" and "mobility" are always chosen first, they will never
        // break the rule of a maximum of 2 parts from same robot, therefore
        // remove entry from hash sets of available parts and return suitable
        // serial number.
        if (part.equals("brain") || part.equals("mobility")) {
            set.remove(randomPartSerialNumber);
            return randomPartSerialNumber;
        } // For all the other parts, origins of the previously picked parts need 
        // to be checked.
        else {
            int counter = 0; // counter for previous occurances of same serial number

            // Puts serial numbers from already chosen parts into a collection
            Collection originSet = alreadyChosenParts.values();

            // Iterates through collection 
            Iterator itr = originSet.iterator();
            while (itr.hasNext()) {
                int otherSerialNumbers = Integer.parseInt(itr.next().toString());

                // If previously chosen part serial number equals the randomly
                // picked serial number being currently checked, increase 
                // counter
                if (otherSerialNumbers == randomPartSerialNumber) {
                    counter++;
                }
            }
            // In case there are already two parts originating from the same 
            // robot, call function again.
            if (counter >= 2) {
                return getRandomAvailablePart(set, part, alreadyChosenParts);
            } // In case serial number is suitable, remove it from set of 
            // available parts and return it
            else {
                set.remove(randomPartSerialNumber);
                return randomPartSerialNumber;
            }
        }
    }
}
