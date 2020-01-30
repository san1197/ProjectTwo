import javax.swing.*;
import java.awt.*;

/*
 * @author Karandeep Singh Grewal
 * @since 01-26-2020
 */

public class mainFrame extends JFrame {
    static String sh;
    
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("SER574-Project-Team07");
        mainWindow.setSize(1000, 800);

        
        // AB
        //
        JPanel jp1 = new JPanel();
        jp1.setBounds(50, 50, 100, 100);
        jp1.setBackground(Color.RED);
        mainWindow.add(jp1);
        jp1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sh="Shape1";
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
        });

        JPanel jp2 = new JPanel();
        jp2.setBounds(50, 250, 100, 100);
        jp2.setBackground(Color.RED);
        mainWindow.add(jp2);
        jp2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sh="Shape2";
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
        });


        JPanel jp3 = new JPanel();
        jp3.setBounds(50, 450, 100, 100);
        jp3.setBackground(Color.RED);
        mainWindow.add(jp3);
        jp3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sh="Shape3";
                jp3.setBackground(Color.BLUE);
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
        });

        //
        //
        
        
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 200, 800);
        leftPanel.setBackground(Color.gray);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(200, 0, 800, 800);
        rightPanel.setBackground(Color.WHITE);

        mainWindow.add(rightPanel);
        mainWindow.add(leftPanel);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
    }
}

