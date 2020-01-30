import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class LeftPanelMouse extends MouseAdapter{
	private RoundButton roundButton = new RoundButton("Round");
	private TriangleButton triangleButton = new TriangleButton("Triangle");
	private RectangleButton rectangleButton = new RectangleButton("Rectangle");
	private RightPanel rightPanel;
	public LeftPanelMouse(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
	
	public void mouseClicked(MouseEvent e) {
		String button = e.getSource().getClass().getName();
		if(button == "RoundButton") {
			rightPanel.AddRound();
		}
		else if(button == "TriangleButton") {
			rightPanel.AddTriangle();
		}
		else {
			rightPanel.AddRectangle();
		}
	}
	
	
	public void setRightPanel() {
		
	}
}