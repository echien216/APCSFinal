import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class InstructionScreen extends JPanel implements ActionListener{
	JButton back;
	JPanel backPanel;
	Main m;
	
	public InstructionScreen(Main m) {
		super();
		setSize(960,540);
		this.m = m;
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		initButtons();
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(0,0,960,540);
		
		 if(g instanceof Graphics2D)
	      {
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	        g2.drawString("Instructions",70,20);
	      }

	}

	public void actionPerformed(ActionEvent arg0) {
		System.out.print("SAVEME");
		m.changePanel("1");
	}
	
	private void initButtons(){
		FlowLayout settings = new FlowLayout();
		backPanel = new JPanel(settings);

		backPanel.setOpaque(false);
		
		back = new JButton("back");
		back.addActionListener(this);
		
		
		backPanel.add(back);
		add(backPanel);
	}
}
