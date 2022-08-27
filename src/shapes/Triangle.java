package shapes;

public class Triangle extends Shape {
    private final double width;
    private final double height;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 1.0 / 2.0 * width * height;
    }

    @Override
    public String menuName() {
        return "Triangle";
    }

    @Override
    public String toString() {
        //return "Triangle, base: " + width + ", height: " + height + ", surface: " + getArea();
        return  "======= TRIANGLE =======\n" +
                "Width: " + width + "\n" +
                "Height: " + height + "\n" +
                "Area: " + getArea();
    }
}
