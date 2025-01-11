package com.mindera.mindswap;

import static com.mindera.mindswap.Constants.FUEL_IN_TANK_SHOP_POLICY;

/**
 * Abstract base class for all vehicles in the rental shop
 * Provides common attributes and behaviors for all vehicle types
 */
public abstract class Vehicle {
    private VehicleType type;         // Type of vehicle (CAR or MOTORCYCLE)
    private String model;             // Model name of the vehicle
    private int gasConsumption;       // Fuel consumption in Km/l
    private int maxSpeed;             // Maximum speed in Km/hour
    private double availableFuel;     // Current fuel level in liters

    /**
     * Constructor for creating a new vehicle
     * @param type Vehicle type (CAR or MOTORCYCLE)
     * @param model Vehicle model name
     * @param gasConsumption Fuel consumption rate
     * @param maxSpeed Maximum speed capability
     */
    protected Vehicle(VehicleType type, String model, int gasConsumption, int maxSpeed) {
        this.type = type;
        this.model = model;
        this.gasConsumption = gasConsumption;
        this.maxSpeed = maxSpeed;
        availableFuel = FUEL_IN_TANK_SHOP_POLICY;
    }

    /**
     * Adds fuel to the vehicle
     * @param additionalFuel Amount of fuel to add in liters
     */
    public void refuel(double additionalFuel) {
        if (additionalFuel <= 0) {
            System.out.println("Invalid amount of fuel to add. It must be greater then zero.");
            return;
        }
        double newFuelLevel = getAvailableFuel() + additionalFuel;
        setAvailableFuel(newFuelLevel);
        System.out.println("Refueled " + additionalFuel + " liters. New fuel level: " + newFuelLevel + " liters.");
    }

    // Getters and setters
    public VehicleType getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public int getGasConsumption() {
        return gasConsumption;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getAvailableFuel() {
        return availableFuel;
    }

    public void setAvailableFuel(double availableFuel) {
        if (availableFuel < 0) {
            throw new IllegalArgumentException("Fuel level cannot be negative");
        }
        this.availableFuel = availableFuel;
    }
}
