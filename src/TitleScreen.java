import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class TitleScreen extends JPanel implements ActionListener{
	private JButton play, instructions;
	private JPanel playPanel,instruPanel;
	private Main m;

	public TitleScreen(Main m) {
		super();
		setSize(960,540);
		this.m = m;

		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		initButtons();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0,0,960,540);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (play == arg0.getSource())
			m.changePanel("2");
		if(instructions == arg0.getSource()){
			m.changePanel("3");
		}
	}

	private void initButtons(){
		FlowLayout settings = new FlowLayout();
		settings.setVgap(50);
		playPanel = new JPanel(settings);
		instruPanel = new JPanel(settings);
		
		JPanel blankPanel = new JPanel(settings);
		JPanel blankPanel2 = new JPanel(settings);
		
		playPanel.setOpaque(false);
		instruPanel.setOpaque(false);
		blankPanel.setOpaque(false);
		blankPanel2.setOpaque(false);

		play = new JButton("Play");
		instructions = new JButton("Instructions");
		play.addActionListener(this);
		instructions.addActionListener(this);


		playPanel.add(play);
		instruPanel.add(instructions);
		add(blankPanel);
		add(blankPanel2);
		add(playPanel);
		add(instruPanel);
	}
}
