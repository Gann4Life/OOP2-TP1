package utilities;

import shapes.EmptyShape;
import shapes.Shape;
import java.util.Arrays;

public class ShapeHandler {
    private Shape[] shapes;
    public ShapeHandler(Shape[] shapes) {
        this.shapes = shapes;
    }

    // Adds a new shape to the array, if there's an empty slot in the array, it will be filled.
    public void add(Shape shape) {
        if(hasEmptyField()) fillEmptyField(shape);
        else addNewShapeResizingArray(shape);
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        shapes[index] = new EmptyShape();
    }

    // Returns processed shapes
    public Shape[] getResult() {
        return shapes;
    }

    // Checks if the given array has an empty field
    private boolean hasEmptyField() {
        for(Shape shape : shapes) {
            if(shape.isEmpty())
                return true;
        }
        return false;
    }

    // Finds the first empty field on the array and fills it with the given shape
    private void fillEmptyField(Shape shape) {
        for(int i = 0; i < shapes.length; i++) {
            if(shapes[i].isEmpty()) {
                shapes[i] = shape;
                break;
            }
        }
    }

    // Resizes the array and creates a new shape in the new field
    private void addNewShapeResizingArray(Shape shape) {
        shapes = Arrays.copyOf(shapes, shapes.length + 1);
        shapes[shapes.length - 1] = shape;
    }
}
