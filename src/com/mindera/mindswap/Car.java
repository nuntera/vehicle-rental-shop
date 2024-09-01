package com.mindera.mindswap;

import static com.mindera.mindswap.VehicleType.CAR;
import static com.mindera.mindswap.Constants.CAR_MAX_SPEED;

public class Car extends Vehicle {

    public Car(String model, int gasConsumption) {
        super(CAR, model, gasConsumption, CAR_MAX_SPEED);
    }
}
