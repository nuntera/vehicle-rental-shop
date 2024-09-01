package com.mindera.mindswap;


public class Trip {
    private Vehicle vehicle;
    private double distance; // Km
    private double time; // Hours

    public Trip(Vehicle vehicle, double distance, double time){
        this.vehicle = vehicle;
        this.distance = distance;
        this.time = time;
    }


    public boolean canMeetSpeedRequirement() {
        double necessarySpeed = distance / time;
        return necessarySpeed <= vehicle.getMaxSpeed();
    }

    public boolean hasEnoughFuel() {
        double requiredFuel = (vehicle.getGasConsumption() * distance) / 100;
        return requiredFuel <= vehicle.getAvailableFuel();
    }


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
