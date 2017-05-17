import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class TitleScreen extends JPanel implements ActionListener{

	JButton play, instructions;
	JPanel playPanel,instruPanel,blankPanel;
	
	public TitleScreen() {
		super();
		setSize(960,540);
		
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		initButtons();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0,0,960,540);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	private void initButtons(){
		FlowLayout settings = new FlowLayout();
		playPanel = new JPanel(settings);
		instruPanel = new JPanel(settings);
		blankPanel = new JPanel(settings);
		
		playPanel.setOpaque(false);
		instruPanel.setOpaque(false);
		blankPanel.setOpaque(false);
		
		play = new JButton("play");
		instructions = new JButton("instructions");
		play.addActionListener(this);
		instructions.addActionListener(this);
		
		
		playPanel.add(play);
		instruPanel.add(instructions);
		add(blankPanel);
		add(playPanel);
		add(instruPanel);
	}
}
