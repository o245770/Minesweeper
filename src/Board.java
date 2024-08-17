import javax.swing.*;
import java.util.Random;

public class Board {
    public int HORIZONTAL_SIZE = 8;
    public int VERTICAL_SIZE = 8;
    public int NUMBER_OF_MINES = 10;
    public int [][]board;

    Board(){
        board = new int[HORIZONTAL_SIZE][VERTICAL_SIZE];

        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_MINES; i++) {
            int row = rand.nextInt(HORIZONTAL_SIZE);
            int col = rand.nextInt(VERTICAL_SIZE);
            if(board[row][col] != -1) board[row][col] = -1;
            else --i;
        }


        for (int i = 0; i < VERTICAL_SIZE; i++) {
            for (int j = 0; j < HORIZONTAL_SIZE; j++) {
                if(board[i][j] == -1) continue;
                int numberOfNeighboringMines = 0;

                if(i!=0){
                    if (j!=0)
                        if(board[i-1][j-1] == -1) numberOfNeighboringMines++;
                    if(board[i-1][j] == -1) numberOfNeighboringMines++;
                    if(j!=HORIZONTAL_SIZE-1)
                        if(board[i-1][j+1] == -1) numberOfNeighboringMines++;
                }

                if (j!=0)
                    if(board[i][j-1] == -1) numberOfNeighboringMines++;
                if(j!=HORIZONTAL_SIZE-1)
                    if(board[i][j+1] == -1) numberOfNeighboringMines++;
                if(i!=VERTICAL_SIZE-1){
                    if (j!=0)
                        if(board[i+1][j-1] == -1) numberOfNeighboringMines++;
                    if(board[i+1][j] == -1) numberOfNeighboringMines++;
                    if(j!=HORIZONTAL_SIZE-1)
                        if(board[i+1][j+1] == -1) numberOfNeighboringMines++;
                }

                board[i][j] = numberOfNeighboringMines;
            }
        }
    }

    void printBoard(){
        for (int i = 0; i < HORIZONTAL_SIZE; i++) {
            for (int j = 0; j < VERTICAL_SIZE; j++) {
                if(board[i][j] == -1) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
    }
}