package shapes;

import application.Application;

public class Circle extends Shape {
    private final double radius;
    public Circle() {
        System.out.print("Radius: ");
        this.radius = Application.userInput.nextDouble();
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String menuName() {
        return "Circle";
    }

    @Override
    public String toString() {
        //return "Circle, radius: " + radius + ", surface: " + getArea();
        return  "======= CIRCLE =======\n" +
                "Radius: " + radius + "\n" +
                "Diameter: " + (radius * 2) + "\n" +
                "Area: " + getArea();

    }
}
