//package ch20;

/*********************************************************************************
 * GamePanel class creates all the buttons for the game including
 * start, stop, next game, and 26 alphabet letters
 * This class also has methods to listen to mouse and keyboard, to player audio clips
 *
 *********************************************************************************/

//import java.text.*;
package game;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;

/*
 * Remove the following for testing. A hassle to install javafx separately.
 */
//import javafx.application.Application;
//import javafx.scene.media.AudioClip;
//import javafx.stage.Stage;

/**
 * Stub the AudioClip class.
 */
class AudioClip {}
class Stage {
    public void show() {};
}

@SuppressWarnings("serial")
class GamePanel extends JPanel implements ActionListener {
	long totalLetters;
	int  rightButton = 0,
	     wrongButton = 0;

	String player;
	public GameLetterPanel gp;
	private JTextArea letterTextArea;
	private JTextField rightButtonTextField,
					   wrongButtonTextField;
	private JLabel rightClickLabel,
				   wrongClickLabel;

    private JButton aButton,   // Button 1
	bButton,  // Button 2
	cButton,  // Button 3
	dButton,  //  4
	eButton,  // 5
	fButton, // 6
	gButton,
	hButton,
	iButton, // 9
	jButton, // 0
					startButton,
					//scoreButton,
					quitButton,
					stopGameButton;

	private AudioClip audioClip;
			URL audioUrl;
	MusicHandler musicHandler;

	public GamePanel(String player) {
		this.player = player;
		this.setLayout(new BorderLayout());
		// start, next game, quit button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		startButton = new JButton("Start", new ImageIcon("images/play.gif"));
		startButton.addActionListener(this);
		buttonPanel.add(startButton);

		stopGameButton = new JButton("Stop", new ImageIcon("images/stop.gif"));
		stopGameButton.addActionListener(this);
		buttonPanel.add(stopGameButton);

		quitButton = new JButton( "Quit",new ImageIcon("images/Shutdown.gif"));
		quitButton.addActionListener(this);
		buttonPanel.add(quitButton);

		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.white);
		scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(scorePanel);

		rightClickLabel = new JLabel("Right: ");
		scorePanel.add(rightClickLabel);

		rightButtonTextField = new JTextField(3);
		rightButtonTextField.setEditable(false);
		rightButtonTextField.setFocusable(false);
		scorePanel.add(rightButtonTextField);

		wrongClickLabel = new JLabel("Wrong: ");
		scorePanel.add(wrongClickLabel);


		wrongButtonTextField = new JTextField(3);
		wrongButtonTextField.setEditable(false);
		wrongButtonTextField.setFocusable(false);
		scorePanel.add(wrongButtonTextField);

		this.add(buttonPanel, BorderLayout.NORTH);

		// letters panel display 10 numbers
		JPanel lettersPanel = new JPanel();
		lettersPanel.setBackground(Color.white);
		lettersPanel.setLayout(new BorderLayout());

		JPanel firstPanel = new JPanel(); // hold 10 numbers
		firstPanel.setBackground(Color.yellow);

		/**
		 * TODO:
		 *   We need to produce 1.gif, 2.gif, ... 0.gif files to
		 *   display on the number JButtons. For now, just use the
		 *   plain boring default number.
		 *
		 * Once the ?.gif's files are available under the images
		 * directory, the following lines should be replaced with
		 * something like this line,
		 *
		 * aButton = new JButton(new ImageIcon("images/1.gif"));
		 */
		aButton = new JButton("1");
		bButton = new JButton("2");
		cButton = new JButton("3");
		dButton = new JButton("4");
		eButton = new JButton("5");
		fButton = new JButton("6");
		gButton = new JButton("7");
		hButton = new JButton("8");
		iButton = new JButton("9");
		jButton = new JButton("0");

		firstPanel.add(aButton);
		firstPanel.add(bButton);
		firstPanel.add(cButton);
		firstPanel.add(dButton);
		firstPanel.add(eButton);
		firstPanel.add(fButton);
		firstPanel.add(gButton);
		firstPanel.add(hButton);
		firstPanel.add(iButton);
		firstPanel.add(jButton);

		lettersPanel.add(firstPanel, BorderLayout.NORTH);

        //change the size of the alphabet Letters and stopGameButton
		aButton.setPreferredSize(new java.awt.Dimension(50, 50));
		bButton.setPreferredSize(new java.awt.Dimension(50, 50));
		cButton.setPreferredSize(new java.awt.Dimension(50, 50));
		dButton.setPreferredSize(new java.awt.Dimension(50, 50));
		eButton.setPreferredSize(new java.awt.Dimension(50, 50));
		fButton.setPreferredSize(new java.awt.Dimension(50, 50));
		gButton.setPreferredSize(new java.awt.Dimension(50, 50));
		hButton.setPreferredSize(new java.awt.Dimension(50, 50));
		iButton.setPreferredSize(new java.awt.Dimension(50, 50));
		jButton.setPreferredSize(new java.awt.Dimension(50, 50));

		stopGameButton.setPreferredSize(new java.awt.Dimension(120, 40));
		
        // add listener to the buttons
		this.add(lettersPanel, BorderLayout.SOUTH);

		aButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex(); // get random dropping letter
						matchingLetter(ansIdx, "1");	// compare dropping letter with button leter
						}
				}
					  );
		bButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex();
						matchingLetter(ansIdx, "2");
					}
				}
					  );
		cButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex();
						matchingLetter(ansIdx, "3");
					}
				}
			);
		dButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex(); // get random dropping letter
						matchingLetter(ansIdx, "4");	// compare dropping letter with button leter
					}
				}

			);
			eButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex();
						matchingLetter(ansIdx, "5");
					}
				}
			);
			fButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex();
						matchingLetter(ansIdx, "6");
					}
				}
			);
			gButton.addActionListener(
					new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex(); // get random dropping letter
						matchingLetter(ansIdx, "7");	// compare dropping letter with button letter
					}
				}

			);
			hButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex();
						matchingLetter(ansIdx, "8");
					}
				}
			);
			iButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex();
						matchingLetter(ansIdx, "9");
					}
				}
			);
			jButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ansIdx = gp.getQuestionIndex(); // get random dropping letter
						matchingLetter(ansIdx, "0");	// compare dropping letter with button leter
					}
				}

			);

			// add keyboard Listeners for each button through keyStup function
			keySetup(aButton);
			keySetup(bButton);
			keySetup(cButton);
			keySetup(dButton);
			keySetup(eButton);
			keySetup(fButton);
			keySetup(gButton);
			keySetup(hButton);
			keySetup(iButton);
			keySetup(jButton);

			keySetup(startButton);
			keySetup(quitButton);

		// display panel will display dropping letter or photos of alphabets....
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(Color.white);
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		// letter text field
		letterTextArea = new JTextArea(300,40);
		letterTextArea.setBackground(Color.white);
		//letterTextArea.disable();
		displayPanel.add(letterTextArea);
		this.add(displayPanel, BorderLayout.CENTER);
	}
	/*
		method: matchingLetter
		purpose: compare dropping letter and button letter that the user selected
				 display message when matching or mismatch
	*/
    //	public void matchingLetter(String droppingLetter, String buttonLetter) {
	public void matchingLetter(int questionIdx, String buttonLetter) {
	    //	    if(droppingLetter.equalsIgnoreCase(buttonLetter)) {
	    if(gp.matchAnswer(questionIdx, buttonLetter)) {
		try {
				    File file = new File("C:\\NewJavaBook\\DropLetterGame\\sounds\\cheer.wav");
					musicHandler = new MusicHandler();			//create object of MusicHandler
					musicHandler.soundPlayer(file, audioClip, false);  //call method to play the sound
				   
					}catch(Exception e){
			            System.out.println(e.toString());
			       }

			JOptionPane.showMessageDialog(null, "You got It");
			rightButton += 1;
			rightButtonTextField.setText(Integer.toString(rightButton));
			//Object source = startButton;
		}
		else {

			 try {
					File file = new File("C:\\NewJavaBook\\DropLetterGame\\sounds\\error.wav");
					musicHandler.soundPlayer(file, audioClip, false); 
				   }catch(Exception e){
			            System.out.println(e.toString());
			       }

			JOptionPane.showMessageDialog(null, "Wrong Letter");
			wrongButton += 1;
			wrongButtonTextField.setText(Integer.toString(wrongButton));
		}
	}

	/*
		method: ActionPerformed
		purpose: listen to buttons
	*/
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == quitButton) {
			System.exit(0);
		}
		else if (source == stopGameButton) {
			gp.stop();
			JOptionPane.showMessageDialog(null, "Thank you " + player + " for playing this game. Your Score is "
											+ rightButton + " right and " + wrongButton + " wrong out of "
											+ GameLetterPanel.nLetterDropped + " letters");
			GameLetterPanel.nLetterDropped = 0;
			rightButton = 0;
			wrongButton = 0;
			rightButtonTextField.setText(Integer.toString(rightButton));
			wrongButtonTextField.setText(Integer.toString(wrongButton));
		}

		/****************************************************
		* Start the game and resets the buttons to 0
		*
		*****************************************************/
		else if (source == startButton) {
			rightButton = 0;
			wrongButton = 0;
			rightButtonTextField.setText(Integer.toString(rightButton));
			wrongButtonTextField.setText(Integer.toString(wrongButton));
			gp = new GameLetterPanel();
			JPanel displayPanel = gp;
			displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			this.add(displayPanel, BorderLayout.CENTER);
			Font f = new Font("SansSerif", Font.BOLD, 48);
			letterTextArea.setFont(f);
			
			File file = new File("C:\\NewJavaBook\\DropLetterGame\\sounds\\AlphabetSong.wav");  //play the background music...
			musicHandler = new MusicHandler();			//create object of MusicHandler
			musicHandler.soundPlayer(file, audioClip, true);  //call method to play the sound
		}
    }

	 /***************************************************************************************
     * Assign  the <code> Component</code> keyListener to call matchLetter() with the associated letter
     * @param <code> Component </code> for any Component in the GamePanel
     ************************************************************************************/
	public void keySetup(Component theCandidate){
		 theCandidate.addKeyListener( new KeyAdapter() {
		      public void keyPressed( KeyEvent ke ) {
		        if (ke.getKeyCode()==ke.VK_1){matchingLetter(gp.getQuestionIndex(), "1");}
		        else if (ke.getKeyCode()==ke.VK_2){matchingLetter(gp.getQuestionIndex(), "2");}
		        else if (ke.getKeyCode()==ke.VK_3){matchingLetter(gp.getQuestionIndex(), "3");}
		        else if (ke.getKeyCode()==ke.VK_4){matchingLetter(gp.getQuestionIndex(), "4");}
		        else if (ke.getKeyCode()==ke.VK_5){matchingLetter(gp.getQuestionIndex(), "5");}
		        else if (ke.getKeyCode()==ke.VK_6){matchingLetter(gp.getQuestionIndex(), "6");}
		        else if (ke.getKeyCode()==ke.VK_7){matchingLetter(gp.getQuestionIndex(), "7");}
		        else if (ke.getKeyCode()==ke.VK_8){matchingLetter(gp.getQuestionIndex(), "8");}
		        else if (ke.getKeyCode()==ke.VK_9){matchingLetter(gp.getQuestionIndex(), "9");}
		        else if (ke.getKeyCode()==ke.VK_0){matchingLetter(gp.getQuestionIndex(), "0");};
		      };
		    } );
	}
    class MusicHandler /*extends Application*/ {		//handle music play
		
		public void start(Stage stage) {
			stage.show();
		}
		public void soundPlayer(File file, AudioClip audioClip, boolean repeat) {
		    /*				audioClip = new AudioClip(file.toURI().toString());
				if (repeat) {
					audioClip.setCycleCount(10);
					audioClip.play();
				}
				else audioClip.play();
		    */
		}
	}	
	
}
