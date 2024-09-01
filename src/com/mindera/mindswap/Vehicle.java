package com.mindera.mindswap;

import static com.mindera.mindswap.Constants.FUEL_IN_TANK_SHOP_POLICY;

public abstract class Vehicle {
    private VehicleType type;
    private String model;
    private int gasConsumption; // Km/l
    private int maxSpeed;// Km/hour
    private double availableFuel;

    protected Vehicle(VehicleType type, String model, int gasConsumption, int maxSpeed){
        this.type = type;
        this.model = model;
        this.gasConsumption = gasConsumption;
        this.maxSpeed = maxSpeed;
        availableFuel = FUEL_IN_TANK_SHOP_POLICY;
    }


    public void refuel(double additionalFuel){
        if (additionalFuel <= 0){
            System.out.println("Invalid amount of fuel to add. It must be greater then zero.");
            return;
        }
        double newFuelLevel = getAvailableFuel() + additionalFuel;
        setAvailableFuel(newFuelLevel);
        System.out.println("Refueled " + additionalFuel + " liters. New fuel level: " + newFuelLevel + " liters.");
    }


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
        this.availableFuel = availableFuel;
    }
}
