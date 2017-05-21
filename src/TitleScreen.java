import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	/**
	 * Create a TitleScreen object.
	 * @param m the Main class that starts the program
	 */
	public TitleScreen(Main m) {
		super();
		setSize(960, 540);
		setBackground(new Color(157, 190, 204));

		this.m = m;
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		
		play = new JButton("Play");
		instructions = new JButton("Instructions");
		play.addActionListener(this);
		instructions.addActionListener(this);
		play.setOpaque(false);
		
		JTextArea banner = new JTextArea("M A T E R  T U A");
		banner.setEditable(false);
		banner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
		banner.setOpaque(false);
		banner.setAlignmentX(CENTER_ALIGNMENT);
		
		p.add(Box.createVerticalStrut(100));
		p.add(banner);
		p.add(Box.createVerticalStrut(100));
		p.add(play);
		p.add(Box.createVerticalStrut(100));
		p.add(instructions);		
		
		add(p);
	}

	/**
	 * Starts the game if the Play button is pressed, and 
	 * displays the InstructionScreen if the Instructions button is pressed,.
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (play == arg0.getSource())
			m.changePanel("2");
		if(instructions == arg0.getSource()){
			m.changePanel("3");
		}
	}
}
