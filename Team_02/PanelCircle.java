/**
 * @author sarvansh prasher
 * Created on 01-27-2020
 * @author Surya
 * modified on 01-28-2020
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelCircle extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelCircle() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClickedShape objClickedShape = new ClickedShape();
				objClickedShape.returnShape("Circle");
			}
		});
		JPanel objCirclePane = new JPanel();
		repaint();

		objCirclePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		objCirclePane.setLayout(null);
	}

	public void paintComponent(Graphics objGraphics) {
		Graphics2D obj2D = (Graphics2D) objGraphics;
		Shape objCircle = new Ellipse2D.Double(105, 60, 80, 80);
		obj2D.draw(objCircle);
	}
}
