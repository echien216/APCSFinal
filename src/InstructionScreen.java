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

public class InstructionScreen extends JPanel implements ActionListener{

	private JButton back;
	private JPanel backPanel;
	private Main m;
	
	public InstructionScreen(Main m) {
		super();
		setSize(960,540);
		this.m = m;
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		initButtons();
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,960,540);
		
		BufferedImage b = null;
		try{
			b = ImageIO.read(new File("dist\\Instructions.png"));
		}
		catch(IOException e){
			
		}
		g.drawImage(b, 0, -20, null);

	}

	public void actionPerformed(ActionEvent arg0) {
		m.changePanel("1");
	}
	
	private void initButtons(){
		FlowLayout settings = new FlowLayout();
		settings.setVgap(65);
		backPanel = new JPanel(settings);
		JPanel blankPanel = new JPanel(settings);
		JPanel blankPanel2 = new JPanel(settings);
		JPanel blankPanel3 = new JPanel(settings);
		
		blankPanel.setOpaque(false);
		blankPanel2.setOpaque(false);
		blankPanel3.setOpaque(false);
		backPanel.setOpaque(false);
		
		back = new JButton("Back");
		back.addActionListener(this);
		
		
		backPanel.add(back);
		add(blankPanel3);
		add(blankPanel);
		add(blankPanel2);
		add(backPanel);
	}
}
