import optionSelector.SelectableOptions;
import shapes.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Shape[] shapes;

    public static void main(String[] args) {
        try {
            shapes = getShapesFromData(DataReader.getFileLines("data.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainMenuScreen();
    }

    // PRE: The data has to be properly formatted in order to load the shapes into the array.
    // POS: Creates shapes from the .txt file.
    static Shape[] getShapesFromData(String[] data) {
        shapes = new Shape[Integer.parseInt(data[0])];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = Shape.getNewShapeFromData(data[i + 1]);
        }
        return shapes;
    }

    // PRE: At least one shape has to be in the array,
    // POS: Returns the shape that has greater area from the array.
    static Shape shapeWithGreatestArea() {
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
    static Shape shapeWithLeastArea() {
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

    // PRE: At least one shape has to be found in the array.
    // POS: Returns a list of strings containing the shapes menu name and a 'Back' text.
    static String[] submenuWithShapes() {
        List<String> result = new ArrayList<String>();
        result.add("Back");
        for(Shape shape : shapes) {
            result.add(shape.menuName());
        }

        return result.toArray(new String[0]);
    }

    static void mainMenuScreen() {
        fakeClearScreen();

        SelectableOptions menuOptions = new SelectableOptions(new String[]{
                "Inspect shape",
                "Remove shape",
                "Create shape",
                "View all shapes",
                "View maximum area",
                "View minimum area",
                "Quit"
        });

        menuOptions.configureAction(0, Main::viewShapeScreen);
        menuOptions.configureAction(1, Main::removeShapeScreen);
        menuOptions.configureAction(2, Main::addShapeScreen);
        menuOptions.configureAction(3, Main::showAllShapesScreen);
        menuOptions.configureAction(4, Main::showShapeWithGreatestArea);
        menuOptions.configureAction(5, Main::showShapeWithLeastArea);
        menuOptions.configureAction(menuOptions.getLength()-1, () -> System.exit(1));

        menuOptions.displayOptions();
        menuOptions.waitForUserInputAndSelect();
    }
    static void viewShapeScreen() {
        fakeClearScreen();

        SelectableOptions options = new SelectableOptions(submenuWithShapes());
        options.setInputMessage("Inspect the element: ");
        options.configureAction(0, Main::mainMenuScreen);
        for (int i = 0; i < options.getLength(); i++) {
            final int finalI = i - 1;
            if(i == 0) continue;

            options.configureAction(i, () -> {
                fakeClearScreen();
                System.out.println(shapes[finalI].toString());
            });
        }

        options.displayOptions();
        options.waitForUserInputAndSelect();
        waitForConfirmation("'Enter' to continue.");
        viewShapeScreen();
    }
    static void removeShapeScreen() {
        fakeClearScreen();

        SelectableOptions options = new SelectableOptions(submenuWithShapes());
        options.setInputMessage("Remove element: ");
        options.configureAction(0, Main::mainMenuScreen);
        for (int i = 0; i < options.getLength(); i++) {
            final int finalI = i - 1;
            if(i == 0) continue;

            options.configureAction(i, () -> {
                //System.out.println("Element information:  " + shapes[finalI].toString());
                shapes[finalI] = new EmptyShape();
            });
        }

        options.displayOptions();
        options.waitForUserInputAndSelect();

        removeShapeScreen();
    }
    static void addShapeScreen() {
        fakeClearScreen();

        SelectableOptions options = new SelectableOptions(submenuWithShapes());
        options.setInputMessage("Add element at position: ");
        options.displayOptions();

        options.configureAction(0, Main::mainMenuScreen);
        for (int i = 0; i < options.getLength(); i++) {
            final int finalI = i - 1;
            if(i == 0) continue;

            // Action for the current selected shape's position.
            options.configureAction(i, () -> {
                fakeClearScreen();
                if(shapes[finalI].getClass() == EmptyShape.class)
                {
                    Scanner scanner = new Scanner(System.in);
                    // Request shape type
                    SelectableOptions shapeTypeOptions = new SelectableOptions(new String[]{"Circle", "Rectangle", "Triangle"});
                    shapeTypeOptions.setInputMessage("Create shape of type: ");
                    shapeTypeOptions.displayOptions();

                    shapeTypeOptions.configureAction(0, () -> {
                        System.out.print("Radius: ");

                        shapes[finalI] = new Circle(scanner.nextDouble());

                        addShapeScreen();
                    });

                    shapeTypeOptions.configureAction(1, () -> {
                        System.out.print("Width: ");
                        double w = scanner.nextDouble();
                        System.out.print("Height: ");
                        double h = scanner.nextDouble();

                        shapes[finalI] = new Rectangle(w, h);

                        addShapeScreen();
                    });

                    shapeTypeOptions.configureAction(2, () -> {
                        System.out.print("Width: ");
                        double w = scanner.nextDouble();
                        System.out.print("Height: ");
                        double h = scanner.nextDouble();

                        shapes[finalI] = new Triangle(w, h);

                        addShapeScreen();
                    });

                    shapeTypeOptions.waitForUserInputAndSelect();
                } else {
                    waitForConfirmation("Please, select an empty element!");
                    addShapeScreen();
                }
            });
        }

        options.waitForUserInputAndSelect();
    }

    static void showAllShapesScreen() {
        fakeClearScreen();
        for(Shape shape : shapes) {
            System.out.println(shape.toString());
        }
        waitForConfirmation("'Enter' to continue");
        mainMenuScreen();
    }

    static void showShapeWithGreatestArea() {
        fakeClearScreen();
        System.out.println(shapeWithGreatestArea().toString());
        waitForConfirmation("'Enter' to continue");
        mainMenuScreen();
    }

    static void showShapeWithLeastArea() {
        fakeClearScreen();
        System.out.println(shapeWithLeastArea().toString());
        waitForConfirmation("'Enter' to continue");
        mainMenuScreen();
    }

    static void unavailableOptionScreen() {
        fakeClearScreen();
        waitForConfirmation("/!\\ This option is unavailable.\nPress 'enter' to continue.");
    }

    static void waitForConfirmation(String message) {
        // I don't want to create new lines in the console if they're empty :)
        if(!message.isEmpty()) System.out.println(message);
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void fakeClearScreen() {
        for(int i = 0; i < 100; i++) System.out.println("\n");
    }
}
