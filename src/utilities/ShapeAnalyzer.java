package utilities;

import shapes.EmptyShape;
import shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeAnalyzer {
    private Shape[] shapes;

    // PRE: The data has to be properly formatted in order to load the shapes into the array.
    // POS: Creates shapes from the .txt file.
    public Shape[] dataToShapes(String[] data) {
        shapes = new Shape[Integer.parseInt(data[0])];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = Shape.getNewShapeFromData(data[i + 1]);
        }
        return shapes;
    }

    // PRE: At least one shape has to be in the array,
    // POS: Returns the shape that has greater area from the array.
    public Shape shapeWithGreatestArea() {
        Shape result = shapes[0];
        for (Shape shape : shapes){
            if(shape.getArea() > result.getArea()) {
                result = shape;
            }
        }
        return result;
    }

    // PRE: At least one shape has to be in the array.
    // POS: Returns the shape that has the least area from the array.
    public Shape shapeWithLeastArea() {
        // We need to exclude the empty shapes, otherwise they will appear as the ones with the least area.
        List<Shape> shapeList = new ArrayList<>();
        Shape[] shapeArray;
        for(Shape shape : shapes){
            if(shape.getClass() != EmptyShape.class) shapeList.add(shape);
        }
        shapeArray = shapeList.toArray(new Shape[0]);

        // Now we search through the ones that are not empty.
        Shape result = shapeArray[0];
        for(Shape shape : shapeArray) {
            if(shape.getArea() < result.getArea()){
                result = shape;
            }
        }
        return result;
    }
}
