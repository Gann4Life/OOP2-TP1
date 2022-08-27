package shapes;

public abstract class Shape {
    private char type;

    // PRE: The string passed must have correct formatting. Unexpected issues might happen otherwise.
    //      Example: '<char> <double> <double>'
    //      Any other format is invalid for this method.
    // POS: Returns a new shape based on the given data.
    public static Shape getNewShapeFromData(String data) {
        String[] splitData = data.split(" ");

        double valueA = Double.parseDouble(splitData[1]);
        double valueB = splitData.length > 2 ? Double.parseDouble(splitData[2]) : -1;

        switch(splitData[0]){
            case "C": return new Circle(valueA);
            case "R": return new Rectangle(valueA, valueB);
            case "T": return new Triangle(valueA, valueB);
            default: return null;
        }
    }

    public abstract double getArea();
    public abstract String menuName();
    public abstract String toString();
    /*public String toString() {
        return "Type: " + getType() + " | Value A: " + getA() + ", Value B: " + getB() + " | Area: " + getSurface();
    }*/
}
