package DiceRoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * <h1>DiceRoller</h1>
 * A program that allows the user to add, remove, roll, and delete a list of
 * dice.
 *
 * @author Farhan Purnama Adjie
 * @version 1.0
 * @since 2024-11-08
 */
public class DiceRoller {
    private ArrayList<Dice> dice_list;
    private Scanner scanner;

    public static void main(String[] args) {
        DiceRoller diceRoller = new DiceRoller();
        while (true) {
            cls();
            diceRoller.showAllDice();
            diceRoller.showMenu();
            int choice = diceRoller.inputMenu();
            diceRoller.chooseMenu(choice);
        }
    }

    /**
     * Class constructor specifying an empty list of dice and a scanner.
     */
    public DiceRoller() {
        this.dice_list = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a list of all the dice that are currently in the program.
     */
    public void showAllDice() {
        System.out.print("Dice List: ");
        if (!this.dice_list.isEmpty()) {
            this.printDiceList();
        }
        System.out.println("");
    }

    /**
     * Prints out a list of all the dice in the program by printing each dice's
     * name, separated by spaces.
     */
    private void printDiceList() {
        for (Dice dice : this.dice_list) {
            System.out.print(dice.getDiceName() + " ");
        }
    }

    /**
     * Prints out a menu for the user to select an option with. The options are
     * to add a dice, remove a dice, roll all dice, delete all dice, or exit the
     * program.
     */
    public void showMenu() {
        System.out.println("1. Add a dice");
        System.out.println("2. Remove a dice");
        System.out.println("3. Roll all dice");
        System.out.println("4. Delete all dice");
        System.out.println("5. Exit");
    }

    /**
     * Asks the user for input and returns an integer representing the user's
     * choice, which should be one of the numbers 1 to 5.
     *
     * @return an integer representing the user's choice.
     */
    public int inputMenu() {
        System.out.print("Enter your choice: ");
        return this.scanner.nextInt();
    }

    /**
     * Executes an action based on the user's menu choice.
     *
     * @param choice an integer representing the user's menu selection, where:
     *               1 - adds a dice,
     *               2 - removes a dice,
     *               3 - rolls all dice and shows the result,
     *               4 - deletes all dice,
     *               5 - exits the program.
     *               Any other choice prints an invalid choice message.
     */
    public void chooseMenu(int choice) {
        switch (choice) {
            case 1:
                this.addDice();
                break;
            case 2:
                this.removeDice();
                break;
            case 3:
                this.showRollResult();
                break;
            case 4:
                this.removeAllDice();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    /**
     * Asks the user for the number of sides of a dice, creates a new
     * {@link Dice} object with that number of sides, and adds it to the
     * {@link #dice_list}.
     */
    private void addDice() {
        Dice dice = this.makeDice();
        this.dice_list.add(dice);
    }

    /**
     * Asks the user for the number of sides of a dice and creates a new
     * {@link Dice} object with that number of sides.
     *
     * @return a new {@link Dice} object created with the user's input.
     */
    private Dice makeDice() {
        int side = inputDiceSideNumber();
        Dice dice = new Dice(side);
        return dice;
    }

    /**
     * Asks the user for the number of sides of a dice and returns the
     * number that the user entered.
     *
     * @return the number of sides of the dice that the user entered.
     */
    private int inputDiceSideNumber() {
        System.out.print("Enter the number of sides: ");
        int side = this.scanner.nextInt();
        return side;
    }

    /**
     * Asks the user for the number of sides of a dice and removes the
     * first dice with that number of sides from the {@link #dice_list}.
     * If there is no dice with that number of sides, does nothing.
     */
    private void removeDice() {
        int side = inputDiceSideNumber();
        int index = this.searchDice(side);
        if (index >= 0) {
            this.dice_list.remove(index);
        }
    }

    /**
     * Searches the {@link #dice_list} for the first dice with a certain number
     * of sides and returns the index of that dice in the list. If no dice with
     * that number of sides is found, returns -1.
     *
     * @param side the number of sides to search for.
     * @return the index of the first dice with the given number of sides, or
     *         -1 if no dice with that number of sides is found.
     */
    private int searchDice(int side) {
        int index = -1;
        for (Dice dice : this.dice_list) {
            if (dice.getSide() == side) {
                index = this.dice_list.indexOf(dice);
                break;
            }
        }
        return index;
    }

    /**
     * Clears the console, prints out the result of rolling all the dice in the
     * program, and then waits for the user to press any key.
     */
    private void showRollResult() {
        cls();
        System.out.println("Result: ");
        Map<String, Integer> result = this.rollAllDice();
        for (String key : result.keySet()) {
            System.out.println(key + " = " + result.get(key));
        }
        pause();
    }

    /**
     * Rolls all the dice in the program and returns a map where the keys are
     * the names of the dice and the values are the results of the dice roll.
     *
     * @return a map where the keys are the names of the dice and the values are
     *         the results of the dice roll.
     */
    private Map<String, Integer> rollAllDice() {
        Map<String, Integer> result = new java.util.HashMap<>();
        for (Dice dice : this.dice_list) {
            result.put(dice.getDiceName(), dice.roll());
        }
        return result;
    }

    /**
     * Clears the {@link #dice_list} by removing all the dice from it. This
     * means that there will be no dice left in the program.
     */
    private void removeAllDice() {
        this.dice_list.clear();
    }

    /**
     * Clears the console screen. Currently, this implementation only supports
     * clearing the screen on Windows operating systems. If an error occurs
     * during the execution of the command, the stack trace is printed.
     */
    public final static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Waits for the user to press any key before continuing. This is used to
     * pause the program and prevent the console window from closing
     * immediately after the program is run from an IDE.
     */
    public final static void pause() {
        try {
            System.out.print("Press any key to continue...");
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
