package com.mindera.mindswap;


public enum VehicleEnum {
    BMW(0, new Car("BMW M3", 15)),
    HONDA(1, new Car("Honda Civic", 8)),
    YAMAHA(2, new Motorcycle("Yamaha FZR", 6)),
    DUCATI(3, new Motorcycle("Ducati MONSTER",8));

    private final Vehicle vehicle;
    private final int id;

    VehicleEnum(int id, Vehicle vehicle) {
        this.id = id;
        this.vehicle = vehicle;

    }


    public Vehicle getVehicle(){
        return vehicle;
    }

    public int getId() {
        return id;
    }
}
