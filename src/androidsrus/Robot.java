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

/**
 * An object of class <code>Robot</code> holds the robot's model, serial number,
 * its brain parts, its mobility parts, its vision parts, its arms parts, its
 * media center parts and its power plant parts
 * 
 * @author Wellington Regis
 */
public class Robot {
    private final String model;
    private final int serialNumber;
    private final String brain;
    private final String mobility;
    private final String vision;
    private final String arms;
    private final String mediaCenter;
    private final String powerPlant;

    /**
     * Constructor for the <code>Robot</code> class
     * 
     * @param model holds a String with the robot's model
     * @param serialNumber holds an integer with the robot's serial number
     * @param brain holds a String with the robot's brain parts type
     * @param mobility holds a String with the robot's mobility parts type
     * @param vision holds a String with the robot's vision parts type
     * @param arms holds a String with the robot's arms parts type
     * @param mediaCenter holds a String with the robot's media center parts type
     * @param powerPlant holds a String with the robot's power plant parts type
     */
    public Robot(String model, int serialNumber, String brain, String mobility, String vision, String arms, String mediaCenter, String powerPlant) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.brain = brain;
        this.mobility = mobility;
        this.vision = vision;
        this.arms = arms;
        this.mediaCenter = mediaCenter;
        this.powerPlant = powerPlant;
    }

    /**
     * Gets the robot's model
     * 
     * @return a String with the robot's model 
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the robot's serial number
     * 
     * @return an integer with the robots serial number
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Gets the robot's brain type
     * 
     * @return a String with the robot's brain type
     */
    public String getBrain() {
        return brain;
    }

    /**
     * Gets the robot's mobility type
     * 
     * @return a String with the robot's mobility type
     */
    public String getMobility() {
        return mobility;
    }

    /**
     * Gets the robot's vision type
     * 
     * @return a String with the robot's vision type
     */
    public String getVision() {
        return vision;
    }

    /**
     * Gets the robot's arms type
     * 
     * @return a String with the robot's arms type
     */
    public String getArms() {
        return arms;
    }

    /**
     * Gets the robot's media center type
     * 
     * @return a String with the robot's media center type
     */
    public String getMediaCenter() {
        return mediaCenter;
    }

    /**
     * Gets the robot's power plant type
     * 
     * @return a String with the robot's power plant type
     */
    public String getPowerPlant() {
        return powerPlant;
    } 
}
