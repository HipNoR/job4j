package ru.job4j.condition;

/**
 * Class Point - point-to-point distance calculation.
 * Calculates point-to-point distance in 2D.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Point {
    private int x;
    private int y;

    /**
     * Constructor class Point with two coordinates.
     * @param x - x coordinate.
     * @param y - y coordinate.
     */
    public  Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method calculates point-to-points distance.
     * @param that point #2.
     * @return distance in double.
     */
    public double distanceTo(Point that) {
        double result = Math.sqrt(Math.pow(that.x - this.x, 2) + Math.pow(that.y - this.y, 2));
        return result;
    }

    /**
     * Method main.
     * Takes the coordinates of points and displays them and the distance between them.
     * @param args - args.
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);

        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками A и B : " + result);
    }
}
