import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    public static int SQUARE_SIZE = 60;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 100, 100);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 60));
        g.drawString("3", 85, 120);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Square");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.add(new Square());
        frame.setVisible(true);

    }
}
