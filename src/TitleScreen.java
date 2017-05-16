import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TitleScreen extends JPanel {

	public TitleScreen() {
		super();
		setSize(960,540);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(100,100,960,540);
		
	}
}
