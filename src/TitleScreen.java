import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/** 
 * The <code>TitleScreen</code> is the screen the user
 * sees upon opening the game.
 * 
 * @author christine
 */

public class TitleScreen extends JPanel implements ActionListener {
	
	private JButton play, instructions;
	private Main m;
	private Image background;

	/**
	 * Create a TitleScreen object.
	 * @param m the Main class that starts the program
	 */
	public TitleScreen(Main m) {
		super();
		setSize(960, 540);
		setBackground(new Color(0, 11, 63));
		background = new ImageIcon("background.PNG").getImage();

		this.m = m;
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		
		play = new JButton("Play");
		play.addActionListener(this);
		play.setBackground(new Color(240, 240, 240, 200));
		
		instructions = new JButton("Instructions");
		instructions.addActionListener(this);
		instructions.setBackground(new Color(240, 240, 240, 200));
		
		p.add(Box.createVerticalStrut(900));
		p.add(play);
		p.add(Box.createHorizontalStrut(50));
		p.add(instructions);		
		
		add(p);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		double xRatio = getWidth() / 960.0;
    	double yRatio = getHeight() / 540.0;
    	
    	AffineTransform at = g2D.getTransform();
    	g2D.scale(xRatio, yRatio);
    	
    	g.drawImage(background, -25, 0, this);
    	
    	g2D.setTransform(at);
	}

	/**
	 * Starts the game if the Play button is pressed, and 
	 * displays the InstructionScreen if the Instructions button is pressedS.
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (play == arg0.getSource())
			m.changePanel("2");
		if(instructions == arg0.getSource()) {
			m.changePanel("3");
		}
	}
}
