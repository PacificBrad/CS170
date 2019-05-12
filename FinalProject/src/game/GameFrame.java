//package ch20;

/*********************************************************************************
 * Frame Class which sets the frame and gets the players information
 *It is used by GamePanel class
 *********************************************************************************/
package game;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
class GameFrame extends JFrame {  //or ignore warning
	String player = "";

	/***************************************************************************************
    *  Function to center the frame
 	************************************************************************************/
	private void centerWindow(Window w) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	}
	/***************************************************************************************
    *  gets player name and creates the main frame for the program to run in.
    *  Passes the player to the GamePanel
	************************************************************************************/

	public GameFrame() {
		player = JOptionPane.showInputDialog(null,"Enter your name: ");
		setTitle("Welcome "+ player +", to play Elementary Math Learning game");
		setSize(820,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new GamePanel(player);

		this.add(panel);
		centerWindow(this);
  }
}
