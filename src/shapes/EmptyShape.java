package shapes;

public class EmptyShape extends Shape {

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public String menuName() {
        return "<Available space>";
    }

    @Override
    public String toString() {

        return "======= EMPTY SHAPE =======";
    }
}
