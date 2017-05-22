import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.*;

/** 
 * The <code>InstructionScreen</code> displays Mater Tua's instructions.
 * 
 * @author christine
 */

public class InstructionScreen extends JPanel implements ActionListener {

	private JButton back;
	private Main m;
	
	/**
	 * Create an InstructionScreen object.
	 * @param m the Main class that starts the program
	 */
	public InstructionScreen(Main m) {
		super();
		setSize(960,540);
		setBackground(new Color(157, 190, 204));

		this.m = m;
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		
		JTextArea banner = new JTextArea("Arrow keys: movement\n"
				+ "A: ranged attack (no cooldown)\n"
				+ "S: multi-directional ranged attack (4 second cooldown)\n"
				+ "D: heal (10 second cooldown)\n"
				+ "F: deal damage to all enemies (60 second cooldown)\n"
				+ "The dots in the top right corner will be green if the\n"
				+ "skill corresponding to the key is ready, or red if not.\n\n"
				+ "Attack enemies to kill them.\n"
				+ "Move on to the next level by killing all the enemies\n"
				+ "and touching the goal when you're done.\n"
				+ "Your will receive a small heal based on your missing health\n"
				+ "when you complete a level.\n"
				+ "If your HP hits zero, game over.\n"
				+ "If you can't beat the level before time runs out, game over.\n"
				+ "Half of the time left at the end of the level will be added to\n"
				+ "the next level's time limit.\n"
				+ "Skill cooldowns carry between levels.");
		banner.setEditable(false);
		banner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		banner.setOpaque(false);
		banner.setAlignmentX(CENTER_ALIGNMENT);
		
		back = new JButton("Back");
		back.addActionListener(this);
		
		p.add(Box.createVerticalStrut(15));
		p.add(banner);
		p.add(Box.createVerticalStrut(10));
		p.add(back);
		
		add(p);	
	}

	/**
	 * Displays the TitleScreen if the button is pressed.
	 */
	public void actionPerformed(ActionEvent arg0) {
		m.changePanel("1");
	}
}
