package ru.job4j.condition;

/**
 * Class Triangle calculates the area of a triangle.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Constructor of Triangle class.
     * @param a - point a(x, y).
     * @param b - point b(x, y).
     * @param c - point c(x, y).
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Method calculates half-perimeter through thee points.
     * Formula.
     * (ab + ac + bc) / 2
     * @param ab distance between points a b.
     * @param ac distance between points a c.
     * @param bc distance between points b c.
     * @return Перимент.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Method calculates the area of a triangle.
     * @return Returns area if exist, else returns -1.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Method checks possibility of the triangle building.
     * @param ab distance between points a b.
     * @param ac distance between points a c.
     * @param bc distance between points b c.
     * @return - returns is there a triangle?
     */
    private boolean exist(double ab, double ac, double bc) {
        return (ab != 0 && ac != 0 && bc != 0);
    }
}
