package DiceRoller;

/**
 * <h1>Dice</h1>
 * Kelas yang merepresentasikan dadu.
 *
 * @author Farhan Purnama Adjie
 * @version 1.0
 * @since 2024-11-08
 */
public class Dice {
    private int side;

    /**
     * Constructor kelas ini digunakan untuk membuat objek <code>Dice</code> baru
     * dengan menentukan jumlah sisi.
     */
    public Dice(int side) {
        this.side = side;
    }

    /**
     * Mengocok dadu dan mengembalikan nilai acak antara 1 dan jumlah sisi dadu.
     *
     * @return sebuah integer acak antara 1 dan jumlah sisi dadu.
     */
    public int roll() {
        return (int) (Math.random() * this.side) + 1;
    }

    /**
     * Mengembalikan jumlah sisi dadu.
     *
     * @return sebuah integer yang merepresentasikan jumlah sisi dadu.
     */
    public int getSide() {
        return this.side;
    }

    /**
     * Mengembalikan nama dadu dengan format "Dx" dimana x adalah jumlah sisi dadu.
     *
     * @return sebuah string yang merepresentasikan nama dadu.
     */
    public String getDiceName() {
        return "D" + this.side;
    }
}
