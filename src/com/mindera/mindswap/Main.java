package com.mindera.mindswap;

import static com.mindera.mindswap.Constants.FUEL_IN_TANK_SHOP_POLICY;
import static com.mindera.mindswap.Constants.MOTORCYCLE_MAX_TRIP_DISTANCE;

/**
 * Main class that simulates the vehicle rental shop operations
 * Contains the main simulation loop and helper methods for trip management
 */
public class Main {
    public static void main(String[] args) {
        // Simulation loop - runs 6 times to demonstrate different rental scenarios
        int counter = 0;
        while (counter < 6) {
            counter++;

            // Create a new shop instance and display available vehicles
            Shop rentalShop = new Shop();
            rentalShop.displayAvailableVehicles();

            // Rent different vehicles (2 cars and 2 motorcycles)
            VehicleEnum rentedCar1 = rentalShop.rentVehicle(VehicleType.CAR);
            VehicleEnum rentedMotorcycle1 = rentalShop.rentVehicle(VehicleType.MOTORCYCLE);
            VehicleEnum rentedCar2 = rentalShop.rentVehicle(VehicleType.CAR);
            VehicleEnum rentedMotorcycle2 = rentalShop.rentVehicle(VehicleType.MOTORCYCLE);

            // Create and attempt different trips for each rented vehicle
            Trip carTrip1 = new Trip(rentedCar1.getVehicle(), 300, 3);
            System.out.println(carTrip1);
            canPerformTrip(carTrip1, rentalShop, rentedCar1);

            Trip motorcycleTrip1 = new Trip(rentedMotorcycle1.getVehicle(), 300, 4);
            System.out.println(motorcycleTrip1);
            canPerformTrip(motorcycleTrip1, rentalShop, rentedMotorcycle1);

            Trip carTrip2 = new Trip(rentedCar2.getVehicle(), 200, 3);
            System.out.println(carTrip2);
            canPerformTrip(carTrip2, rentalShop, rentedCar2);

            Trip motorcycleTrip2 = new Trip(rentedMotorcycle2.getVehicle(), 100, 0.9);
            System.out.println(motorcycleTrip2);
            canPerformTrip(motorcycleTrip2, rentalShop, rentedMotorcycle2);
        }
    }

    /**
     * Checks if a vehicle can perform the requested trip based on speed and fuel requirements
     * @param trip The trip to be performed
     * @param shop The rental shop instance
     * @param vehicle The vehicle to perform the trip
     */
    private static void canPerformTrip(Trip trip, Shop shop, VehicleEnum vehicle) {
        if (!trip.isDistanceAllowed()) {
            System.out.println(vehicle.getVehicle().getModel() + " cannot perform trips longer than " + 
                MOTORCYCLE_MAX_TRIP_DISTANCE + "km. Requested distance: " + trip.getDistance() + "km");
            shop.returnVehicle(vehicle.getVehicle());
        } else if (!trip.canMeetSpeedRequirement()) {
            System.out.println(vehicle.getVehicle().getModel() + " don't have the necessary speed to perform trip.");
            shop.returnVehicle(vehicle.getVehicle());
        } else if (!trip.hasEnoughFuel()) {
            System.out.println(vehicle.getVehicle().getModel() + " don't have the necessary fuel to perform trip.");
            shop.returnVehicle(vehicle.getVehicle());
        } else {
            startTrip(trip, shop, vehicle);
        }
    }

    /**
     * Reduces the available fuel in the vehicle based on trip distance and consumption
     * @param trip The trip being performed
     */
    private static void reduceAvailableFuel(Trip trip) {
        // Consumption is in Km/L, so divide distance by consumption to get liters needed
        double requiredFuel = trip.getDistance() / trip.getVehicle().getGasConsumption();
        double newFuelLevel = trip.getVehicle().getAvailableFuel() - requiredFuel;
        trip.getVehicle().setAvailableFuel(newFuelLevel);
        System.out.println("Fuel reduced by " + requiredFuel + " liters. New fuel level: " + newFuelLevel + " liters.");
    }

    /**
     * Starts the trip, reduces fuel, and handles vehicle return
     * @param trip The trip to start
     * @param shop The rental shop instance
     * @param vehicle The vehicle performing the trip
     */
    private static void startTrip(Trip trip, Shop shop, VehicleEnum vehicle) {
        System.out.println("Start the trip");
        reduceAvailableFuel(trip);

        shop.returnVehicle(vehicle.getVehicle());

        if (shop.getIsRented()[vehicle.getId()]) {
            vehicle.getVehicle().refuel(FUEL_IN_TANK_SHOP_POLICY - vehicle.getVehicle().getAvailableFuel());
            shop.returnVehicle(vehicle.getVehicle());
        }
        shop.displayAvailableVehicles();
    }
}
