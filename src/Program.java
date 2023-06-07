import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';

    private static final Scanner SCANNER = new Scanner(System.in);

    private static char[][] field;

    private static final Random random = new Random();

    private static int fieldSizeX;
    private static int fieldSizeY;






    public static void main(String[] args) {

        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил"))
                    break;
            }
            System.out.println("Желаете сыграть еще? Y - да");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }

    }
    
    private static void initialize() { 
        // размерность поля
        fieldSizeX = 3;
        fieldSizeY = 3;
        
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field [x][y] = DOT_EMPTY;
            }
            
        }
        
    }

    private static void printField() {

        System.out.print("┌");
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "─" : i / 2 + 1);
        }
        System.out.print("┐");
        System.out.println();


        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(i + 1 + "│");
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[i][j] + "│");
            }
            System.out.println();
        }


        
        System.out.print("└");
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print("─");
        }
        System.out.print("┘");
        System.out.println();


    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода X Y (от 1 до " + fieldSizeX + "по X и до "
                    + fieldSizeY + "по Y) >>>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x,y) || !isCellEmpty(x,y));
        field[x][y] = DOT_HUMAN;
    }

    private static void aiTurn() {
        int x, y;
        do {

            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x,y));
        field[x][y] = DOT_AI;
    }


    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    public static void doProcess(int x, double y, String s) {

    }

    static boolean checkWin(char c) {
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }


    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    static boolean gameCheck(char c, String str){
        if (checkWin(c)){
            System.out.println(str);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }
}
