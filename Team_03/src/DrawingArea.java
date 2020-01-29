
import javax.swing.*;
import java.awt.*;

/**
 * @author srinivasan sundar,Ashwin Srinivasan
 * @since 01/26/2020
 * @version 1.0
 */
public class DrawingArea extends JPanel {

    DrawingArea(){
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800,600));
        MouseListener ml = new MouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);
    }

}
