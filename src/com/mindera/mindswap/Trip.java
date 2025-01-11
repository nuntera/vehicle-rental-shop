package com.mindera.mindswap;

import static com.mindera.mindswap.Constants.MOTORCYCLE_MAX_TRIP_DISTANCE;

/**
 * Represents a trip to be performed by a vehicle
 * Handles trip requirements and validation
 */
public class Trip {
    private Vehicle vehicle;  // Vehicle performing the trip
    private double distance;  // Trip distance in Km
    private double time;      // Expected trip duration in Hours

    /**
     * Creates a new trip with specified parameters
     * @param vehicle Vehicle to perform the trip
     * @param distance Trip distance in kilometers
     * @param time Expected duration in hours
     * @throws IllegalArgumentException if distance or time are negative or zero
     */
    public Trip(Vehicle vehicle, double distance, double time) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        if (time <= 0) {
            throw new IllegalArgumentException("Time must be positive");
        }
        this.vehicle = vehicle;
        this.distance = distance;
        this.time = time;
    }

    /**
     * Checks if the motorcycle trip distance is within allowed limits
     * @return true if the distance is acceptable for the vehicle type
     */
    public boolean isDistanceAllowed() {
        if (vehicle.getType() == VehicleType.MOTORCYCLE) {
            return distance <= MOTORCYCLE_MAX_TRIP_DISTANCE;
        }
        return true; // Cars have no distance limitation
    }

    /**
     * Checks if the vehicle can meet the required speed for the trip
     * @return true if the vehicle can maintain the necessary speed
     */
    public boolean canMeetSpeedRequirement() {
        double necessarySpeed = distance / time;
        return necessarySpeed <= vehicle.getMaxSpeed();
    }

    /**
     * Checks if the vehicle has enough fuel for the trip
     * @return true if the vehicle has sufficient fuel
     */
    public boolean hasEnoughFuel() {
        double requiredFuel = distance / vehicle.getGasConsumption();
        return requiredFuel <= vehicle.getAvailableFuel();
    }

    // Getters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "vehicle=" + vehicle.getModel() +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
