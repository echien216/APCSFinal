import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/** 
 * The <code>Main</code> class runs the program.
 * 
 * @author christine
 */

public class Main extends JFrame {

	private JPanel cardPanel;
	private Overworld game;
	
	/**
	 * The main method that starts the program.
	 * @param args the supplied command-line arguments
	 */
	public static void main(String [] args) {
		Main w = new Main("Mater Tua");
	}
	
	/**
	 * Create a Main object.
	 * @param title the title of the window containing the game
	 */
	public Main(String title) {
		super(title);
		setBounds(100, 100, 960, 540);
		
		TitleScreen menu = new TitleScreen(this);    
	    game = new Overworld(this);
	    InstructionScreen inst = new InstructionScreen(this);
	    
		setSize(menu.getSize());
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	
	    cardPanel.add(menu,"1"); // Card is named "1"
	    cardPanel.add(game,"2"); // Card is named "2"
	    cardPanel.add(inst,"3"); // Card is named "3"
	    
	    add(cardPanel);
	    setVisible(true);
	}

	/**
	 * Changes which panel is currently being displayed.
	 * @param name the name of the panel to display
	 */
	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		
		requestFocus();
		validate();
		
		if (name.equals("2")) 
		{
			addKeyListener(game.getKeyHandler());
			new Thread(game).start();		
		}
	}

}
