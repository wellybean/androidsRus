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
 * This class contains a method user for user interaction
 *
 * @author Wellington Regis
 */
public class Menu {

    /**
     * This method provides the user with a menu from which they can retrieve
     * information on robots or robot parts
     *
     * @param s contains an instance of the <code>Setup</code> class holding all
     * the information concerning robots and the origin and destination of robot
     * parts
     */
    public static void run(Setup s) {

        int menuOption; // Used for dealing with user choices
        int robotOption; // Used for dealing with user choices
        int robotSerialNumber; // Stores serial number from user input
        boolean exit = false; // Used for exiting the program
        int andyCounter = 0; // Used to count amount of robots
        int bettyCounter = 0; // Used to count amount of robots
        int bobiCounter = 0; // Used to count amount of robots
        int fredCounter = 0; // Used to count amount of robots

        /*
         * Counts the amount of robots for each type of robot from the new line
         */
        Collection<Robot> robots = s.getRobotViaSerial().values();
        Iterator<Robot> itr = robots.iterator();
        while (itr.hasNext()) {
            Robot robot = itr.next();
            switch (robot.getModel()) {
                case "Andy the Android":
                    andyCounter++;
                    break;
                case "Betty the Busibot":
                    bettyCounter++;
                    break;
                case "Bobi the Racedroid":
                    bobiCounter++;
                    break;
                case "Fred the Friendlybot":
                    fredCounter++;
                    break;
                default:
                    break;
            }
        }

        /*
         * Runs until user decides to exit program
         */
        while (!exit) {
            System.out.print("\n**********Welcome to AndroidsRus************"
                    + "\n\nHere are the available options: "
                    + "\n    (1) View all robots of a particular model from the new line of robots"
                    + "\n    (2) View details on a particular robot from the new line of robots"
                    + "\n    (3) View total count of robots from the new line of robots by model"
                    + "\n    (4) View donation details from a particular old robot"
                    + "\n    (5) Exit"
                    + "\nPlease select one of the options above: "
            );

            // Gets option from user
            menuOption = Utilities.getInstance().optionInputHandler(5);

            switch (menuOption) {

                /*
                 * PRINTS ALL ROBOTS OF A PARTICULAR MODEL FROM THE NEW LINE     
                 */
                case 1:
                    System.out.print("    Here are the available options: "
                            + "\n        (1) Andy the Android"
                            + "\n        (2) Betty the Busibot"
                            + "\n        (3) Bobi the Racedroid"
                            + "\n        (4) Fred the Friendlybot"
                            + "\n    Please select one of the models above: "
                    );

                    // Gets option from user
                    robotOption = Utilities.getInstance().optionInputHandler(4);

                    switch (robotOption) {
                        case 1:
                            printsAllRobotsOfModel("Andy the Android", s);
                            break;
                        case 2:
                            printsAllRobotsOfModel("Betty the Busibot", s);
                            break;
                        case 3:
                            printsAllRobotsOfModel("Bobi the Racedroid", s);
                            break;
                        case 4:
                            printsAllRobotsOfModel("Fred the Frienlybot", s);
                            break;
                    }
                    break;

                /*
                 * ASKS USER FOR SERIAL NUMBER AND PRINTS THE FOLLOWING
                 */
                case 2:
                    // Asks user for input
                    System.out.print("    Please select one of the options above: ");
                    robotSerialNumber = Utilities.getInstance().optionInputHandler(10000000);

                    // In case there is a robot with this serial number
                    if (s.getRobotViaSerial().containsKey(robotSerialNumber)) {
                        Robot robot = s.getRobotViaSerial().get(robotSerialNumber);

                        // Prints robot info if robot is from the new line or 
                        // advises user to use option 4 on the menu in case 
                        // robot belongs to old line of robots
                        if (robot.getModel().equals("Andy the Android")
                                || robot.getModel().equals("Betty the Busibot")
                                || robot.getModel().equals("Bobi the Racedroid")
                                || robot.getModel().equals("Fred the Friendlybot")) {
                            Utilities.getInstance().robotDetailsPrinter(robot, s.getPartOrigin().get(robot.getSerialNumber()), s.getRobotViaSerial());
                        } else {
                            System.out.println("\nThe serial number entered belongs "
                                    + "to a robot from our old line of robots. "
                                    + "\nPlease use option 4 on the menu to "
                                    + "obtain information on that robot."
                            );
                        }
                    } else {
                        // Prints this in case no match is found for the serial number
                        System.out.println("\nNo such robot found in our database.");
                    }
                    break;

                /*
                 * DISPLAYS NUMBERS OF ROBOTS FROM NEW LINE BY MODEL
                 */
                case 3:
                    // Prints info
                    System.out.println("\nThere are:\n"
                            + "    " + andyCounter + " robots of model Andy the Android\n"
                            + "    " + bettyCounter + " robots of model Betty the Busibot\n"
                            + "    " + bobiCounter + " robots of model Bobi the Racedroid\n"
                            + "    " + fredCounter + " robots of model Fred the Friendlybot\n"
                    );
                    break;

                /*
                 * DISPLAY INFO ON OLD ROBOTS, AS WELL AS INFO ON THE RECEIPIENTS OF THEIR PARTS
                 */
                case 4:
                    // Asks for user input
                    System.out.print("    Please type in a valid serial number for one of our old Androids: ");
                    robotSerialNumber = Utilities.getInstance().optionInputHandler(10000000);

                    if (s.getRobotViaSerial().containsKey(robotSerialNumber)) {
                        Robot robot = s.getRobotViaSerial().get(robotSerialNumber);

                        // Advises user to use option 1 on the menu in case 
                        // robot belongs to new line of robots or else prints 
                        // robot info if robot is from the old line
                        if (robot.getModel().equals("Andy the Android")
                                || robot.getModel().equals("Betty the Busibot")
                                || robot.getModel().equals("Bobi the Racedroid")
                                || robot.getModel().equals("Fred the Friendlybot")) {
                            System.out.println("\nThe serial number entered belongs "
                                    + "to a robot from our new line of robots. "
                                    + "\nPlease use option 1 on the menu to "
                                    + "obtain information on that robot."
                            );
                        } else {
                            System.out.println("\nThe robot requested has the following details:");
                            Utilities.getInstance().robotDetailsPrinter(robot);

                            // For each part, checks if it has been donated. If 
                            // yes, prints details of receipient. If no, prints
                            // a messaged saying that part has not been used yet
                            // Brain
                            printReceipientInfo(s, "brain", robotSerialNumber);
                            printReceipientInfo(s, "mobility", robotSerialNumber);
                            printReceipientInfo(s, "vision", robotSerialNumber);
                            printReceipientInfo(s, "arms", robotSerialNumber);
                            printReceipientInfo(s, "mediaCenter", robotSerialNumber);
                            printReceipientInfo(s, "powerPlant", robotSerialNumber);
                        }
                    } else {
                        // Printed in case input serial number doesn't match any robots
                        System.out.println("Robot not found");
                    }
                    break;

                /*
                 * EXITS PROGRAM    
                 */
                case 5:
                    exit = true;
                    break;
            }
        }
    }

    /*
     * Takes a robot from the old line and a part name and checks the 
     * destination whether this part from this robot has or has not been used.
     * If the part has already been used, prints receipient's details. If not,
     * prints message to user saying that the part hasn't been used yet.
     */
    private static void printReceipientInfo(Setup s, String part, int robotSerialNumber) {
        int newRobotSerialNumber;

        if (s.getPartDestination().get(robotSerialNumber).containsKey(part)) {
            System.out.println("\nThe " + part + " of Android " 
                    + robotSerialNumber + " was donated to:"
            );
            newRobotSerialNumber = Integer.parseInt(s.getPartDestination().get(robotSerialNumber).get(part).toString());
            Robot newRobot = s.getRobotViaSerial().get(newRobotSerialNumber);
            Utilities.getInstance().robotDetailsPrinter(newRobot, s.getPartOrigin().get(newRobot.getSerialNumber()), s.getRobotViaSerial());
        } else {
            System.out.println("\nThe " + part + " has not been usued yet.");
        }
    }

    /*
     * Checks all robots and prints the ones with a particular model.
     */
    private static void printsAllRobotsOfModel(String model, Setup s) {
        Collection<Robot> robots = s.getRobotViaSerial().values();
        Iterator<Robot> itr = robots.iterator();
        while (itr.hasNext()) {
            Robot robot = itr.next();
            if (robot.getModel().equals(model)) {
                Utilities.getInstance().robotDetailsPrinter(robot, s.getPartOrigin().get(robot.getSerialNumber()), s.getRobotViaSerial());
            }
        }
    }
}
