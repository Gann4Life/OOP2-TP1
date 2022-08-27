package optionSelector;

import java.util.Scanner;

public class SelectableOptions {
    private String inputMessage = "Select one of the options above: ";
    private String[] options;
    private IAction[] actions;

    @Deprecated(forRemoval = true)
    private int selectedIndex;

    public SelectableOptions(String[] options) {
        this.options = options;
        actions = new IAction[options.length];
    }

    // PRE: The index passed must be in the proper range.
    // POS: Executes a previously configured block of code.
    public void select(int index) throws IndexOutOfBoundsException {
        actions[index].onSelected();
    }

    public void waitForUserInputAndSelect() {
        System.out.print(inputMessage);

        Scanner scanner = new Scanner(System.in);
        try {
            select(scanner.nextInt());
        }
        catch(Exception e) {
            System.out.println("Invalid option! Try again.");
            waitForUserInputAndSelect();
        }
    }

    // PRE: As long as a non-string is passed, it should work. An empty string might confuse the user.
    // POS: The message will display on screen when the instance is in use.
    public void setInputMessage(String message) {
        inputMessage = message;
    }

    // PRE: The action has to be found in order to be assigned.
    // POS: The action will be saved and executed later when needed.
    public void configureAction(int index, IAction action){
        actions[index] = action;
    }

    // PRE: The array of available options and actions have to be previously set.
    // POS: Displays the available options that the user can choose.
    public void displayOptions() {
        for(int i = 0; i < options.length; i++) {
            System.out.println("[" + i + "] " + options[i]);
        }
    }

    // POS: Returns the lenght from all the actions available on the array of strings.
    public int getLength() {
        return options.length;
    }

    @Deprecated(forRemoval = true)
    public int getSelectedIndex() {
        return selectedIndex;
    }
}
