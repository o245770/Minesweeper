import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        int SQUARE_SIZE = 60;
        g.fillRect(0, 0, HORIZONTAL_SIZE* SQUARE_SIZE +10, VERTICAL_SIZE* SQUARE_SIZE +10);

        g.setFont(new Font("Serif", Font.BOLD, SQUARE_SIZE *5/6));
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < HORIZONTAL_SIZE; i++) {
            for (int j = 0; j < VERTICAL_SIZE; j++) {
                int x =j* SQUARE_SIZE +j+2;
                int y = i* SQUARE_SIZE +i+2;
                g.setColor(Color.DARK_GRAY);
                g.fillRect(j* SQUARE_SIZE +j+2, i* SQUARE_SIZE +i+2, SQUARE_SIZE -1, SQUARE_SIZE -1);

                if (board[i][j] == -1) {
                    g.setColor(Color.WHITE);
                    g.drawString("*", x + SQUARE_SIZE /4,y - SQUARE_SIZE /8);
                } else if (board[i][j] > 0) {
                    switch (board[i][j]) {
                        case 1:
                            g.setColor(Color.BLUE);
                            break;
                        case 2:
                            g.setColor(Color.GREEN);
                            break;
                        case 3:
                            g.setColor(Color.RED);
                            break;
                        case 4:
                            g.setColor(Color.magenta);
                            break;
                        case 5:
                            g.setColor(Color.cyan);
                            break;
                        case 6:
                            g.setColor(Color.magenta);
                            break;
                        case 7:
                            g.setColor(Color.ORANGE);
                            break;
                        case 8:
                            g.setColor(Color.BLACK);
                            break;

                    }
                    g.drawString(String.valueOf(board[i][j]), x + SQUARE_SIZE /4,y - SQUARE_SIZE /4);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int SQUARE_SIZE = 60;
        return new Dimension(HORIZONTAL_SIZE * SQUARE_SIZE +HORIZONTAL_SIZE, VERTICAL_SIZE * SQUARE_SIZE +VERTICAL_SIZE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        Board board = new Board();
        frame.getContentPane().add(board);
        frame.pack();
        frame.setVisible(true);


    }
}