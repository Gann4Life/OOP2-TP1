package shapes;

import application.Application;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle() {
        System.out.print("Width: ");
        this.width = Application.userInput.nextDouble();

        System.out.print("Height: ");
        this.height = Application.userInput.nextDouble();
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String menuName() {
        return "Rectangle";
    }

    @Override
    public String toString() {
        //return "Rectangle, width: " + width + ", height: " + height + ", surface: " + getArea();
        return  "======= RECTANGLE =======\n" +
                "Width: " + width + "\n" +
                "Height: " + height + "\n" +
                "Area: " + getArea();
    }
}
