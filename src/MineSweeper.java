import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber, colNumber, size;
    int[][] map;
    int[][] board;
    boolean isGame = true;


    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    MineSweeper(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = columnNumber;
        this.size = rowNumber * columnNumber;
        map = new int[rowNumber][columnNumber];
        board = new int[rowNumber][columnNumber];
    }

    // Oyunu çalıştıran ana metod
    public void run() {
        int row, col, success = 0;
        prepareGame();
        //print(map);
        System.out.println("Oyun Başladı");
        // Oyun devam ettiği sürece döngü
        while (isGame) {
            print(map);// Mayınların bulunduğu haritayı yazdırma
            System.out.println("=====================");
            print(board);// Oyuncunun gördüğü oyun tahtasını yazdırma
            System.out.print("Satır sayısı :");
            row = input.nextInt();
            System.out.print("Sütun sayısı :");
            col = input.nextInt();


            if (map[row][col] != -1) {
                checkMine(row, col);
                success++;
                // Eğer kullanıcı tüm boş hücreleri açtıysa
                if (success == (size - (size / 4))) {
                    System.out.println("Tebrikler");
                    break;
                }
            } else {
                isGame = false; // Kullanıcı bir mayına bastıysa oyunu sonlandırma
                System.out.print("Oyun Bitti...! :");
            }
            //break;
        }
    }

    // Seçilen hücrenin etrafındaki mayınları kontrol eden metod
    public void checkMine(int r, int c) {
        // Eğer seçilen hücrede mayın yoksa
        if (map[r][c] == 0) {
        }
        // Sağdaki hücrede mayın varsa
        if ((c < colNumber - 1) && (map[r][c + 1] == -1)) {
            board[r][c]++;
        }
        // Soldaki hücrede mayın varsa
        if ((r < rowNumber - 1) && (map[r + 1][c] == -1)) {
            board[r][c]++;
        }// Üstteki hücrede mayın varsa
        if ((r > 0) && (map[r - 1][c] == -1)) {
            board[r][c]++;
        }
        // Alttaki hücrede mayın varsa
        if ((c > 0) && (map[r][c - 1] == -1)) {
            board[r][c]++;
        }// Sağ üst çapraz hücrede mayın varsa
        if (r > 0 && c < colNumber - 1 && map[r - 1][c + 1] == -1) {
            board[r][c]++;
        }
        // Sol üst çapraz hücrede mayın varsa
        if (r > 0 && c > 0 && map[r - 1][c - 1] == -1) {
            board[r][c]++;
        }
        // Sağ alt çapraz hücrede mayın varsa
        if (r < rowNumber - 1 && c < colNumber - 1 && map[r + 1][c + 1] == -1) {
            board[r][c]++;
        }
        // Sol alt çapraz hücrede mayın varsa
        if (r < rowNumber - 1 && c > 0 && map[r + 1][c - 1] == -1) {
            board[r][c]++;
        }
        if (board[r][c] == 0) {
            board[r][c] = '*';
        }
    }

    public void prepareGame() {
        int randRow, randCol, count = 0;
        // Haritada belirlenen mayın sayısına ulaşana kadar döngü
        while (count != (size / 4)) {
            randRow = rand.nextInt(rowNumber);
            randCol = rand.nextInt(colNumber);
            // Eğer belirlenen hücrede daha önce mayın yoksa
            if (map[randRow][randCol] != -1) {
                map[randRow][randCol] = -1;
                count++;
            }
        }
    }

    public void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] >= 0) {
                    System.out.print(" ");
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

