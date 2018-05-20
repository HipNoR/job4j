package ru.job4j.calculator;

/**
 * Class Car describes car checking for ready to drive.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Car {
    /**
     * Contains predicted range of the car. Filling with 0 when initialised.
     */
    private double volume;

    /**
     * Method driving - how much we want to drive.
     * @param kilometer - sets the distance of driving.
     * Subtracts the specified distance from the predicted range.
     */
    public void drive(int kilometer) {
        this.volume = this.volume - kilometer;
    }

    /**
     * Method filling the car tank with fuel.
     * @param gas - sets the amount of fuel to be injected into the vehicle.
     * 1 liter of gas for 10 kilometers of predicted range.
     */
    public void fill(int gas) {
        this.volume = this.volume + 10 * gas;
    }

    /**
     * Method checks - is car ready to go?
     * @return true or false.
     * If volume = 0 - car is not ready.
     * If volume more than 0 - ready to go.
     */
    public boolean canDrive() {
        boolean result = this.volume > 0;
        return result;
    }

    /**
     * Method prints predicted range of the car.
     */
    public void gasInfo() {
        System.out.println("I can drive " + this.volume + " kilometers.");
    }
}