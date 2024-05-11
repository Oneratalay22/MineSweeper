import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row,column;
        do {
            System.out.println("Mayın Tarlasına Hoşgeldiniz...!");
            System.out.println("Satır sayısı giriniz ! :");
            row = sc.nextInt();
            System.out.println("Sütun sayısı giriniz ! :");
            column = sc.nextInt();
        }while (row <2 && column<2 );
        MineSweeper ms = new MineSweeper(row,column);
        ms.run();
    }
}
