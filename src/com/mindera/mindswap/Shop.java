package com.mindera.mindswap;

import static com.mindera.mindswap.Constants.FUEL_IN_TANK_SHOP_POLICY;

/**
 * Represents a vehicle rental shop that manages the rental operations
 * Maintains a list of available vehicles and their rental status
 */
public class Shop {
    private VehicleEnum[] availableVehicles;  // Array of all vehicles in the shop
    private boolean[] isRented;               // Tracks rental status of each vehicle

    /**
     * Constructor initializes the shop with all vehicles from VehicleEnum
     * and sets their initial rental status to false (available)
     */
    public Shop() {
        availableVehicles = VehicleEnum.values();
        isRented = new boolean[availableVehicles.length];
    }

    /**
     * Displays all vehicles that are currently available for rent
     */
    public void displayAvailableVehicles() {
        System.out.println("Available Vehicles");
        for (int i = 0; i < availableVehicles.length; i++) {
            if (!isRented[i]) {
                System.out.println(availableVehicles[i].getVehicle().getModel());
            }
        }
    }

    /**
     * Attempts to rent a vehicle of the specified type
     * @param type The type of vehicle to rent (CAR or MOTORCYCLE)
     * @return The rented vehicle or null if none available
     */
    public VehicleEnum rentVehicle(VehicleType type) {
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i].getVehicle().getType() == type && !isRented[i]) {
                System.out.println("Rented a: " + availableVehicles[i].getVehicle().getModel());
                isRented[i] = true;
                return availableVehicles[i];
            }
        }
        System.out.println("No vehicle of this type available to rent.");
        return null;
    }

    /**
     * Processes the return of a vehicle, checking fuel levels and charging if necessary
     * @param vehicle The vehicle being returned
     */
    public void returnVehicle(Vehicle vehicle) {
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i].getVehicle().equals(vehicle) && isRented[i]) {
                if (vehicle.getAvailableFuel() == FUEL_IN_TANK_SHOP_POLICY) {
                    isRented[i] = false;
                    System.out.println(vehicle.getModel() + " has been successfully returned.\n");
                } else {
                    System.out.println(vehicle.getModel() + " cannot be returned. The fuel tank must be exactly " +
                            FUEL_IN_TANK_SHOP_POLICY + " liters.");
                    double requiredFuel = (FUEL_IN_TANK_SHOP_POLICY - vehicle.getAvailableFuel());
                    double chargedAmount = (requiredFuel * 2.5);
                    System.out.println("Was charged " + chargedAmount + "€ for " + requiredFuel + "L of fuel.");
                    vehicle.refuel(requiredFuel);
                    isRented[i] = false;
                    System.out.println(vehicle.getModel() + " has been successfully returned after refueling.\n");
                }
            }
        }
    }

    /**
     * Gets the rental status array of all vehicles
     * @return Array indicating which vehicles are currently rented
     */
    public boolean[] getIsRented() {
        return isRented;
    }
}
