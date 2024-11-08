package DiceRoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * <h1>DiceRoller</h1>
 * Program untuk menambahkan, menghapus, dan mengocok list dadu.
 *
 * @author Farhan Purnama Adjie
 * @version 1.0
 * @since 2024-11-08
 */
public class DiceRoller {
    private ArrayList<Dice> dice_list;
    private Scanner scanner;

    /**
     * Fungsi utama. Membuat objek <code>DiceRoller</code> baru, dan masuk ke
     * infinite loop yang berisi clear console, menampilkan semua dadu dalam
     * program, dan kemudian menampilkan dan meminta pengguna untuk memilih pilihan
     * menu.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        DiceRoller diceRoller = new DiceRoller();
        while (true) {
            cls();
            diceRoller.showAllDice();
            diceRoller.menu();
        }
    }

    /**
     * Constructor kelas yang membuat objek list dadu dan scanner untuk
     * input.
     */
    public DiceRoller() {
        this.dice_list = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Menampilkan semua dadu yang ada dalam {@link #dice_list}.
     */
    public void showAllDice() {
        System.out.print("Dice List: ");
        if (!this.dice_list.isEmpty()) {
            this.printDiceList();
        }
        System.out.println("");
    }

    /**
     * Menampilkan semua dadu yang ada dalam {@link #dice_list} dengan menuliskan
     * setiap nama dadu, dengan spasi sebagai pemisah.
     */
    private void printDiceList() {
        for (Dice dice : this.dice_list) {
            System.out.print(dice.getDiceName() + " ");
        }
    }

    /**
     * Menampilkan menu, mengambil input dari pengguna, dan mengeksekusi aksi yang
     * sesuai dengan pilihan pengguna.
     */
    public void menu() {
        this.showMenu();
        int choice = this.inputMenu();
        this.chooseMenu(choice);
    }

    /**
     * Menampilkan menu yang dapat dipilih oleh pengguna untuk melakukan aksi. Aksi
     * yang dapat dilakukan adalah menambahkan dadu, menghapus dadu, mengocok semua
     * dadu, menghapus semua dadu, atau keluar dari program.
     */
    private void showMenu() {
        System.out.println("1. Add a dice");
        System.out.println("2. Remove a dice");
        System.out.println("3. Roll all dice");
        System.out.println("4. Delete all dice");
        System.out.println("5. Exit");
    }

    /**
     * Meminta input pengguna dan mengembalikan sebuah integer yang
     * merepresentasikan pilihan pengguna.
     *
     * @return sebuah integer yang merepresentasikan pilihan pengguna
     */
    private int inputMenu() {
        System.out.print("Enter your choice: ");
        return this.scanner.nextInt();
    }

    /**
     * Melakukan aksi yang sesuai dengan pilihan pengguna.
     *
     * @param choice sebuah integer yang merepresentasikan pilihan pengguna, yaitu:
     *               1: menambahkan dadu
     *               2: menghapus dadu
     *               3: mengocok semua dadu
     *               4: menghapus semua dadu
     *               5: keluar
     *               selain itu: menampilkan pesan bahwa pilihan tidak valid
     */
    private void chooseMenu(int choice) {
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
     * Meminta input dari pengguna untuk jumlah sisi dadu, membuat objek
     * {@link Dice} baru dengan jumlah sisi yang diberikan, dan menambahkan
     * objek ini ke {@link #dice_list}.
     */
    private void addDice() {
        int side = inputDiceSideNumber();
        Dice dice = this.makeDice(side);
        this.dice_list.add(dice);
    }

    /**
     * Meminta input dari pengguna untuk jumlah sisi dadu, mencari dadu dengan
     * sisi yang diberikan, dan menghapus dadu tersebut dari {@link #dice_list}.
     * Jika tidak ada dadu dengan jumlah sisi tersebut, tidak melakukan apa-apa.
     */
    private void removeDice() {
        int side = inputDiceSideNumber();
        int index = this.searchDice(side);
        if (index >= 0) {
            this.dice_list.remove(index);
        }
    }

    /**
     * Clear console, menampilkan semua dadu dari map yang berisi nama dan hasil
     * kocokan dadu, dan menunggu pengguna untuk menekan tombol.
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
     * Mengocok semua dadu dalam {@link #dice_list} dan mengembalikan sebuah map
     * yang merepresentasikan nama dan hasil kocokan dadu.
     *
     * @return sebuah map yang berisi nama dan hasil kocokan dadu
     */
    private Map<String, Integer> rollAllDice() {
        Map<String, Integer> result = new java.util.HashMap<>();
        for (Dice dice : this.dice_list) {
            result.put(dice.getDiceName(), dice.roll());
        }
        return result;
    }

    /**
     * Menghapus semua dadu dalam {@link #dice_list}.
     */
    private void removeAllDice() {
        this.dice_list.clear();
    }

    /**
     * Mencari dadu dalam {@link #dice_list} dengan sisi yang diberikan dan
     * mengembalikan indeksnya. Jika tidak ada dadu dengan sisi tersebut,
     * mengembalikan -1.
     *
     * @param side jumlah sisi dari dadu yang ingin dicari
     * @return indeks dari dadu pertama yang ditemukan, atau -1 jika tidak ditemukan
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
     * Membuat objek {@link Dice} baru dengan jumlah sisi yang diberikan.
     *
     * @param side jumlah sisi dari dadu yang ingin dibuat.
     * @return objek {@link Dice} baru dengan sisi yang diberikan.
     */
    private Dice makeDice(int side) {
        Dice dice = new Dice(side);
        return dice;
    }

    /**
     * Meminta input dari pengguna untuk jumlah sisi dadu dan mengembalikan
     * jumlah sisi yang diberikan.
     *
     * @return sebuah integer yang merepresentasikan jumlah sisi dadu.
     */
    private int inputDiceSideNumber() {
        System.out.print("Enter the number of sides: ");
        int side = this.scanner.nextInt();
        return side;
    }

    /**
     * Menghapus layar konsol. Saat ini hanya mendukung
     * membersihkan layar konsol pada sistem operasi Windows.
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
     * Menunggu pengguna menekan tombol apapun untuk melanjutkan program.
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
