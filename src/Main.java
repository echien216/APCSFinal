import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class Main extends JFrame{
	//screams internally
	private JPanel cardPanel;
	
	public static void main(String [] args){
		Main w = new Main("Mater Tua");
	}
	
	public Main(String title) {
		super(title);
		
		TitleScreen menu = new TitleScreen(this);    
	    Tester1 game = new Tester1(this);
	    InstructionScreen inst = new InstructionScreen(this);
	    
		setSize(menu.getSize());
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setVisible(true);
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    

	
	    cardPanel.add(menu,"1"); // Card is named "1"
	    cardPanel.add(game,"2"); // Card is named "2"
	    cardPanel.add(inst,"3"); // Card is named "3"
	    
	    add(cardPanel);
	    addKeyListener(game.getKeyHandler());
	    game.run();
	    setVisible(true);
	}

	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
		validate();
	}

}
