import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends JPanel implements MouseListener {
    public int HORIZONTAL_SIZE = 8;
    public int VERTICAL_SIZE = 8;
    public int NUMBER_OF_MINES = 10;
    public int [][]board;
    boolean [][] clickedSquares;
    int SQUARE_SIZE = 60;
    boolean gameOver;

    Board(){
        board = new int[HORIZONTAL_SIZE][VERTICAL_SIZE];
        clickedSquares = new boolean[HORIZONTAL_SIZE][VERTICAL_SIZE];
        gameOver = false;

        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_MINES; i++) {
            int row = rand.nextInt(HORIZONTAL_SIZE);
            int col = rand.nextInt(VERTICAL_SIZE);
            if(board[row][col] != -1) board[row][col] = -1;
            else --i;
        }


        for (int i = 0; i < VERTICAL_SIZE; i++) {
            for (int j = 0; j < HORIZONTAL_SIZE; j++) {
                clickedSquares[i][j] = false;
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

        addMouseListener(this);
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

                if(!clickedSquares [i][j]){
                    g.setColor(Color.GRAY);
                    g.fillRect(j* SQUARE_SIZE +j+2, i* SQUARE_SIZE +i+2, SQUARE_SIZE -1, SQUARE_SIZE -1);
                } else if (board[i][j] == -1) {
                    g.setColor(Color.red);
                    g.fillRect(j* SQUARE_SIZE +j+2, i* SQUARE_SIZE +i+2, SQUARE_SIZE -1, SQUARE_SIZE -1);
                    g.setColor(Color.WHITE);
                    g.drawString("*", x + SQUARE_SIZE /4,y + SQUARE_SIZE);
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
                    g.drawString(String.valueOf(board[i][j]), x + SQUARE_SIZE /4,y + SQUARE_SIZE*3/4);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(HORIZONTAL_SIZE * SQUARE_SIZE +HORIZONTAL_SIZE, VERTICAL_SIZE * SQUARE_SIZE +VERTICAL_SIZE);
    }

    void clickEmptySquares(int x, int y){
        if(x<0 || y<0 || x>=HORIZONTAL_SIZE || y >=VERTICAL_SIZE) return;
        if(clickedSquares[y][x]) return;

        clickedSquares[y][x] = true;
        if(board[y][x]!=0) return;

        clickEmptySquares(x-1,y-1);
        clickEmptySquares(x,y-1);
        clickEmptySquares(x+1,y-1);
        clickEmptySquares(x-1,y);
        clickEmptySquares(x+1,y);
        clickEmptySquares(x-1,y+1);
        clickEmptySquares(x,y+1);
        clickEmptySquares(x+1,y+1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gameOver) return;

        int x = e.getX()/SQUARE_SIZE;
        int y = e.getY()/SQUARE_SIZE;

        if(board[y][x]==-1){
            clickedSquares[y][x] = true;
            repaint();
            if (e.getButton() == MouseEvent.BUTTON1) {
                //lewy
                gameOver = true;
            }
        }

        if(!clickedSquares[y][x]){
            clickEmptySquares(x,y);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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