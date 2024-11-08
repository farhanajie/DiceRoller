package DiceRoller;

/**
 * <h1>Dice</h1>
 * A class that represents a single dice with a certain number of sides.
 *
 * @author Farhan Purnama Adjie
 * @version 1.0
 * @since 2024-11-08
 */
public class Dice {
    private int side;

    /**
     * Class constructor specifying the number of sides on the dice.
     */
    public Dice(int side) {
        this.side = side;
    }

    /**
     * Rolls the dice and returns a random integer between 1 and the number of sides
     * on the dice.
     *
     * @return a random integer representing the result of the dice roll.
     */
    public int roll() {
        return (int) (Math.random() * this.side) + 1;
    }

    /**
     * Returns the number of sides on this dice.
     *
     * @return the number of sides on this dice.
     */
    public int getSide() {
        return this.side;
    }

    /**
     * Returns the name of this dice in the format "Dx" where x is the number of
     * sides.
     *
     * @return the name of this dice.
     */
    public String getDiceName() {
        return "D" + this.side;
    }
}
