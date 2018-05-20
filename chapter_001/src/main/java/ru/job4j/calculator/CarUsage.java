package ru.job4j.calculator;

/**
 * Class CarUsage describes driving by car.
 * Car consumption 10 liters per 100 kilometers.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class CarUsage {
    /**
     * Method main.
     * Creating a new Car class object.
     * Checking - is car ready for drive?
     * Filling the car tank with fuel.
     * Checking again - is car ready for drive?
     * Driving for a distance.
     * Check the predicted range.
     * @param args - args.
     */
    public static void main(String[] args) {
        Car honda = new Car();
        boolean driving = honda.canDrive();
        System.out.println("Can you drive? : " + driving);
        System.out.println("I am going to a gas station.");
        int gas = 25;
        honda.fill(gas);
        driving = honda.canDrive();
        System.out.println("Can you drive now? : " + driving);
        System.out.println("Now. I am going to my granny.");
        System.out.println("It's 10 kilometers from here.");
        int distance = 10;
        honda.drive(distance);
        honda.gasInfo();
    }
}