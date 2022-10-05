package application;

import utilities.DataReader;
import optionSelector.SelectableOptions;
import shapes.*;
import utilities.ShapeAnalyzer;
import utilities.ShapeHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static Scanner userInput = new Scanner(System.in);
    Shape[] shapes;
    ShapeAnalyzer shapeAnalyzer = new ShapeAnalyzer();

    public Application() {
        try {
            shapes = shapeAnalyzer.dataToShapes(DataReader.getFileLines("data.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainMenuScreen();
    }

    // PRE: At least one shape has to be found in the array.
    // POS: Returns a list of strings containing the shapes menu name and a 'Back' text.
    String[] submenuWithShapes() {
        List<String> result = new ArrayList<>();
        result.add("Back");
        for(Shape shape : shapes) {
            result.add(shape.menuName());
        }

        return result.toArray(new String[0]);
    }

    void mainMenuScreen() {
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

        menuOptions.configureAction(0, this::viewShapeScreen);
        menuOptions.configureAction(1, this::removeShapeScreen);
        menuOptions.configureAction(2, this::addShapeScreen);
        menuOptions.configureAction(3, this::showAllShapesScreen);
        menuOptions.configureAction(4, this::showShapeWithGreatestArea);
        menuOptions.configureAction(5, this::showShapeWithLeastArea);
        menuOptions.configureAction(menuOptions.getLength()-1, () -> System.exit(1));

        menuOptions.displayOptions();
        menuOptions.waitForUserInputAndSelect();
    }
    void viewShapeScreen() {
        fakeClearScreen();

        SelectableOptions options = new SelectableOptions(submenuWithShapes());
        options.setInputMessage("Inspect the element: ");
        options.configureAction(0, this::mainMenuScreen);
        for (int i = 0; i < options.getLength(); i++) {
            final int finalI = i - 1;
            if(i == 0) continue;

            options.configureAction(i, () -> {
                fakeClearScreen();
                System.out.println(shapes[finalI]);
            });
        }

        options.displayOptions();
        options.waitForUserInputAndSelect();
        waitForConfirmation("'Enter' to continue.");
        viewShapeScreen();
    }
    void removeShapeScreen() {
        fakeClearScreen();

        SelectableOptions options = new SelectableOptions(submenuWithShapes());
        options.setInputMessage("Remove element: ");
        options.configureAction(0, this::mainMenuScreen);
        for (int i = 0; i < options.getLength(); i++) {
            final int finalI = i - 1;
            if(i == 0) continue;

            options.configureAction(i, () -> shapes[finalI] = new EmptyShape());
        }

        options.displayOptions();
        options.waitForUserInputAndSelect();

        removeShapeScreen();
    }
    void addShapeScreen() {
        fakeClearScreen();
        ShapeHandler shapeHandler = new ShapeHandler(shapes);

        SelectableOptions shapeTypeOptions = new SelectableOptions(new String[]{"Circle", "Rectangle", "Triangle"});
        shapeTypeOptions.setInputMessage("Select the type of shape to create: ");
        shapeTypeOptions.displayOptions();

        shapeTypeOptions.configureAction(0, () -> shapeHandler.add(new Circle()));
        shapeTypeOptions.configureAction(1, () -> shapeHandler.add(new Rectangle()));
        shapeTypeOptions.configureAction(2, () -> shapeHandler.add(new Triangle()));

        shapeTypeOptions.waitForUserInputAndSelect();

        shapes = shapeHandler.getResult();
        mainMenuScreen();
    }

    void showAllShapesScreen() {
        fakeClearScreen();
        for(Shape shape : shapes) {
            System.out.println(shape);
        }
        waitForConfirmation("'Enter' to continue");
        mainMenuScreen();
    }

    void showShapeWithGreatestArea() {
        fakeClearScreen();
        System.out.println(shapeAnalyzer.shapeWithGreatestArea());
        waitForConfirmation("'Enter' to continue");
        mainMenuScreen();
    }

    void showShapeWithLeastArea() {
        fakeClearScreen();
        System.out.println(shapeAnalyzer.shapeWithLeastArea());
        waitForConfirmation("'Enter' to continue");
        mainMenuScreen();
    }

    void unavailableOptionScreen() {
        fakeClearScreen();
        waitForConfirmation("/!\\ This option is unavailable.\nPress 'enter' to continue.");
    }

    void waitForConfirmation(String message) {
        // I don't want to create new lines in the console if they're empty :)
        if(!message.isEmpty()) System.out.println(message);
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void fakeClearScreen() {
        for(int i = 0; i < 5; i++)
            System.out.print("\n");
    }
}
