//package ch20;

/*
 *Application software development project: Alphabet Dropping Letter Game
 *GameApp.java using GUI components, event handling, color, java fx audio 
 *play and so on to build a alphabet learning game for preschool kids
 *
 */

package game;
import javax.swing.*;

public class GameApp {
	public static void main(String[] args) {
		JFrame frame = new GameFrame();
		frame.setVisible(true);

		System.out.println("Move images directory to below the following directory.");
		System.out.println("Current dir: " + System.getProperty("user.dir"));
	}
}
