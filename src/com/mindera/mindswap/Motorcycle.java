package com.mindera.mindswap;

import static com.mindera.mindswap.VehicleType.MOTORCYCLE;
import static com.mindera.mindswap.Constants.MOTORCYCLE_MAX_SPEED;

public class Motorcycle extends Vehicle {

    public Motorcycle(String model, int gasConsumption) {
        super(MOTORCYCLE, model, gasConsumption, MOTORCYCLE_MAX_SPEED);
    }
}
