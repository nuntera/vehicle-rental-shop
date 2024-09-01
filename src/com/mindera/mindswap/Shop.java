package com.mindera.mindswap;

import static com.mindera.mindswap.Constants.FUEL_IN_TANK_SHOP_POLICY;

public class Shop {
    private VehicleEnum[] availableVehicles;
    private boolean[] isRented;

    public Shop() {
        availableVehicles = VehicleEnum.values();
        isRented = new boolean[availableVehicles.length];
    }


    public void displayAvailableVehicles(){
        System.out.println("Available Vehicles");
        for (int i = 0; i < availableVehicles.length; i++) {
            if (!isRented[i]){
                System.out.println(availableVehicles[i].getVehicle().getModel());
            }
        }
    }

    public VehicleEnum rentVehicle(VehicleType type) {
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i].getVehicle().getType() == type && !isRented[i]){
                System.out.println("Rented a: " + availableVehicles[i].getVehicle().getModel());
                isRented[i] = true;
                return availableVehicles[i];
            }
        }
        System.out.println("No vehicle of this type available to rent.");
        return null;
    }

    public void returnVehicle(Vehicle vehicle){
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i].getVehicle().equals(vehicle) && isRented[i]){
                if (vehicle.getAvailableFuel() == FUEL_IN_TANK_SHOP_POLICY){
                    isRented[i] = false;
                    System.out.println(vehicle.getModel() + " has been successfully returned.\n");
                } else {
                    System.out.println(vehicle.getModel() + " cannot be returned. The fuel tank must be exactly " +
                            FUEL_IN_TANK_SHOP_POLICY + " liters.");
                    // Implementation - do a random to refuel or return vehicle and charge the missing fuel.
                    int randomInt = (int) (Math.random() * 2);
                    if (randomInt < 1) {
                        double requiredFuel = (FUEL_IN_TANK_SHOP_POLICY - vehicle.getAvailableFuel());
                        double chargedAmount = (requiredFuel * 2.5);
                        System.out.println("Was charged " + chargedAmount + "€ for " + requiredFuel + "L of fuel.");
                    }
                }
            }
        }
    }


    public boolean[] getIsRented() {
        return isRented;
    }
}
