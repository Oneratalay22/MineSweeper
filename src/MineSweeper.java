import java.util.Scanner;

public class MineSweeper {
    private char[][] map; // Mayınların yerini tutacak matris
    private char[][] board; // Oyuncuya gösterilecek oyun tahtası
    private int size; // Matris boyutu
    private int mines; // Mayın sayısı
    private int openedCells; // Açılan hücre sayısı
    private boolean gameover; // Oyunun bitip bitmediği

    public MineSweeper(int rows, int cols) {
        this.size = rows * cols;
        this.map = new char[rows][cols];
        this.board = new char[rows][cols];
        this.mines = size / 4; // Mayın sayısı, eleman sayısının 1/4'ü
        this.openedCells = 0;
        this.gameover = false;
        initializeMap();
        initializeBoard();
    }

    // Haritayı başlangıç durumuna getirme
    private void initializeMap() {
        // Tüm hücreleri '-' ile doldur
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = '-';
            }
        }

        // Mayınları yerleştir
        int count = 0;
        while (count < mines) {
            int row = (int) (Math.random() * map.length);
            int col = (int) (Math.random() * map[0].length);
            if (map[row][col] != '*') {
                map[row][col] = '*';
                count++;
            }
        }
    }

    // Oyun tahtasını başlangıç durumuna getirme
    private void initializeBoard() {
        // Tüm hücreleri '-' ile doldur
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Oyun tahtasını yazdırma
    private void printBoard() {
        System.out.println("Oyun Tahtası:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Mayın haritasını yazdırma
    private void printMap() {
        System.out.println("Mayın Haritası:");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Kullanıcıdan giriş almak
    private int[] getInput(Scanner scanner) {
        int[] input = new int[2];
        System.out.print("Satır sayısı: ");
        input[0] = scanner.nextInt();
        System.out.print("Sütun sayısı: ");
        input[1] = scanner.nextInt();
        return input;
    }

    // Oyunu oynama
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!gameover) {
            printMap(); // Mayın haritasını yazdır
            printBoard(); // Oyun tahtasını yazdır
            int[] input = getInput(scanner);
            int row = input[0];
            int col = input[1];
            if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
                System.out.println("Geçersiz giriş. Lütfen tekrar deneyin.");
                continue;
            }
            if (board[row][col] != '-') {
                System.out.println("Bu koordinat zaten seçildi. Lütfen başka bir koordinat girin.");
                continue;
            }
            if (map[row][col] == '*') {
                System.out.println("Mayına bastınız! Oyun bitti.");
                gameover = true;
            } else {
                openCell(row, col);
                if (openedCells == size - mines) {
                    System.out.println("Tebrikler! Tüm hücreleri açtınız. Oyunu kazandınız!");
                    gameover = true;
                }
            }
        }
        scanner.close();
    }

    // Seçilen hücreyi açma
    private void openCell(int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length || board[row][col] != '-') {
            return;
        }
        int minesAround = countMines(row, col);
        board[row][col] = (char) (minesAround + '0');
        openedCells++;
        if (minesAround == 0) {
            openCell(row - 1, col); // Sol
            openCell(row + 1, col); // Sağ
            openCell(row, col - 1); // Yukarı
            openCell(row, col + 1); // Aşağı
            openCell(row - 1, col - 1); // Sol üst çapraz
            openCell(row - 1, col + 1); // Sağ üst çapraz
            openCell(row + 1, col - 1); // Sol alt çapraz
            openCell(row + 1, col + 1); // Sağ alt çapraz
        }
    }

    // Belirtilen hücrenin etrafındaki mayın sayısını sayma
    // Seçilen hücrenin etrafındaki mayın sayısını sayma
    private int countMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < map.length && j >= 0 && j < map[0].length && map[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

}
