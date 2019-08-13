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
 * This class contains methods that are useful throughout the AndroidsRus 
 * program
 * 
 * @author Wellington Regis
 */
public class Utilities {
    private static Utilities INSTANCE = null;
    private static Random r;
    private static Scanner scan;

    /*
     * Constructor for class Utilities
     */
    private Utilities() {
        r = new Random();
        scan = new Scanner(System.in);
    }

    /**
     * Used to create an instance of class <code>Utilities</code> to be used 
     * throughout the entire program
     * 
     * @return an instance of class <code>Utilities</code>
     */
    public static Utilities getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Utilities();
        }
        return INSTANCE;
    }
    
    /**
     * Picks a random option among available strings
     * 
     * @param first holds the first option
     * @param second holds the second option
     * @param third holds the third option
     * @return one of the available options
     */
    public String pickARandomString(String first, String second, String third) {
        int randomNum = r.nextInt(3);
        if (randomNum == 0) {
            return first;
        } else if (randomNum == 1) {
            return second;
        }
        return third;
    }
    
    /**
     * Picks a random option among available strings
     * 
     * @param first holds the first option
     * @param second holds the second option
     * @param third holds the third option
     * @param forth holds the forth option
     * @return one of the available options
     */
    public String pickARandomString(String first, String second, String third, String forth) {
        int randomNum = r.nextInt(4);
        switch(randomNum) {
            case 0:
                return first;
            case 1:
                return second;
            case 2:
                return third;
            default:
                return forth;
        }
    }
    
    /**
     * Picks a random option among available strings
     * 
     * @param first holds the first option
     * @param second holds the second option
     * @param third holds the third option
     * @param forth holds the forth option
     * @param fifth holds the fifth option
     * @return one of the available options
     */
    public String pickARandomString(String first, String second, String third, String forth, String fifth) {
        int randomNum = r.nextInt(5);
        switch(randomNum) {
            case 0:
                return first;
            case 1:
                return second;
            case 2:
                return third;
            case 3:
                return forth;
            default:
                return fifth; 
        }
    }
    
    /**
     * This method returns a random and unique serial number with 7 digits
     * 
     * @param map holds a hash map containing serial numbers of existing robots
     * as keys and <code>Robot</code> objects as values.
     * @return a random and unique serial number
     */
    public int pickARandomSerialNumber(HashMap map) {
        int randomNum = (r.nextInt(10000000));
        if(map.containsKey(randomNum) || randomNum < 999999) {
            Utilities.getInstance().pickARandomSerialNumber(map);
        }
        return randomNum;
    }
    
    /**
     * Handles user input so that the user has to input a valid option
     *
     * @param size holds the last valid option (from 1 to 'size')
     * @return an integer with a valid option
     */
    public int optionInputHandler(int size) {

        int option;
        while (true) {
            try {
                option = scan.nextInt();
                if (option > 0 && option <= size) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Please type in a valid option: ");
                }
            } catch (NumberFormatException ex) {
                System.out.print("INVALID OPTION! Please type in a valid option: ");
            } catch (InputMismatchException ex) {
                System.out.print("INVALID OPTION! Please type in a valid option: ");
                scan.next();
            }
        }
        scan.nextLine();
        return option;
    }
    
    /**
     * This method prints information on a particular robot from the old line 
     * of robots
     * 
     * @param robot holds an object of class <code>Robot</code>
     */
    public void robotDetailsPrinter(Robot robot) {
        System.out.println("\nModel: " + robot.getModel() + "\nSerial Number: "
                + robot.getSerialNumber() + "\nBrain: " + robot.getBrain()
                + " GHz\nMobility: " + robot.getMobility() + "\nVision: "
                + robot.getVision() + "\nArms: " + robot.getArms()
                + "\nMedia Center: " + robot.getMediaCenter()
                + "\nPower Plant: " + robot.getPowerPlant()
        );
    }
    
    /**
     * This method prints information on a particular robot from the new line 
     * of robots
     * 
     * @param robot holds instance of class <code>Robot</code> 
     * @param origin holds hash map containing part name Strings as keys and 
     * serial number as values. It is used to obtain the origin of a part of 
     * parameter 'robot'
     * @param robots holds a hash with serial numbers as keys and <code>Robot
     * </code> objects as values. It is used to obtain a robot from its serial 
     * number
     */
    public void robotDetailsPrinter(Robot robot, HashMap<String, Integer> origin, HashMap<Integer,Robot> robots) {
        System.out.println("\nModel: " + robot.getModel() + "\nSerial Number: "
                + robot.getSerialNumber() + "\nBrain: " + robot.getBrain() 
                + " GHz (" + robots.get(origin.get("brain")).getModel()
                + ": serialNumber: " + origin.get("brain") + ")\nMobility: " 
                + robot.getMobility() + " (" 
                + robots.get(origin.get("mobility")).getModel() 
                + ": serialNumber: " + origin.get("mobility") + ")\nVision: "
                + robot.getVision() + " (" 
                + robots.get(origin.get("vision")).getModel() 
                + ": serialNumber: " + origin.get("vision") + ")\nArms: " 
                + robot.getArms() + " (" 
                + robots.get(origin.get("arms")).getModel() 
                + ": serialNumber: " + origin.get("arms")+ ")\nMedia Center: " 
                + robot.getMediaCenter() + " (" 
                + robots.get(origin.get("mediaCenter")).getModel() 
                + ": serialNumber: " + origin.get("mediaCenter")
                + ")\nPower Plant: " + robot.getPowerPlant() + " (" 
                + robots.get(origin.get("powerPlant")).getModel() 
                + ": serialNumber: " + origin.get("powerPlant") + ")"
        );
    }

    /**
     * Gets the object r of class <code>Random</code>
     * 
     * @return object r of class <code>Random</code>
     */
    public Random getR() {
        return r;
    }   
}
