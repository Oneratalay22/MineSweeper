import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Satır sayısı Giriniz ! : ");
        int rows = scanner.nextInt();
        System.out.print("Sütun sayısı Girinizi ! : ");
        int cols = scanner.nextInt();
        if (rows < 2 || cols < 2) {
            System.out.println("Satır ve sütun sayıları en az 2 olmalıdır.! ");
            return;
        }
        MineSweeper game = new MineSweeper(rows, cols);
        game.play();
    }
}
