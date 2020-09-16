import java.util.Arrays;
import java.util.Scanner;

/**
 * Gets input from the user and solves the Sudoku puzzle
 */
public class Sudoku {

    static int[][] board;

    /**
     * The user enters values for each row, 9 values at a time.
     * After 9 rows are entered, all 81 numbers are input.
     * Missing cells are inputted as 0.
     */
    public void enterBoard(){
        Scanner userInput = new Scanner(System.in);
        int row = 9;
        int column = 9;


        int[][] sudokuBoard = new int[row][column];
        System.out.println("Enter Sudoku numbers row by row from 1-9, and enter 0 to represent a blank space.");
        System.out.println("Please separate the numbers by spaces (no commas necessary) :)");

        for (int i=0; i<row; i++) {
            System.out.print("Row " + (i+1) + ": ");
            for (int j=0; j<column; j++) {
                int value = userInput.nextInt();
                sudokuBoard[i][j] = value;
            }
        }

        board = sudokuBoard;

    }

    /**
     * Checks if a number is correct to put in a particular grid.
     * @param row
     * @param col
     * @param num
     * @return true or false
     */
    public static boolean checkSafe(int row, int col, int num){
        /**
         * Checks Row
         */
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        /**
         * Checks column
         */
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == num) {
                return false;
            }
        }

        /**
         * Checks grid
         */
        int gridRow = row - (row % 3);
        int gridCol = col - (col % 3);
        for (int k = gridRow; k < gridRow + 3; k++) {
            for (int l = gridCol;
                 l < gridCol + 3; l++) {
                if (board[k][l] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Fills the board and returns a boolean whether the given board has a solution or not
     * @return true or false
     */
    public boolean fillBoard(){
        int row = 0;
        int col = 0;
        boolean empty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;

                    empty = false;
                    break;
                }
            }
            if (!empty) {
                break;
            }
        }
        if (empty){
            return true;
        }

        /**
         * Back tracking
         */
        for (int num = 1; num <= 9; num++) {
            if (checkSafe(row, col, num)) {
                board[row][col] = num;
                if (fillBoard()) {
                    return true;
                }
                else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    /**
     * Prints the board
     */
    public void printBoard() {
        System.out.println();
        for (int i=0; i<board.length; i++) {
            if (i%3==0) {
                System.out.println("--------------------------------");
            }
            for (int j=0; j<board[i].length; j++) {
                System.out.format("%-3s", board[i][j]);

                if (j==2 || j==5 || j==8) {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    /**
     * Checks if the board has a solution or not. If the board has a solution it prints the board.
     * Else it prints that there is no solution.
     */
    public void checkSolu(){
        if (fillBoard()){
            printBoard();
        } else {
            System.out.println("No Solution!!!");
        }
    }

    /**
     * Calls the methods from the Sudoku class
     * @param args
     */
    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();

        sudoku.enterBoard();
        sudoku.checkSolu();


    }
}
