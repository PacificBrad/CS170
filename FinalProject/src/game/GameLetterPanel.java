//package ch20;
/*********************************************************************************
 * GameLetterPanel.java
 * Panel class which which uses threads and overwrites the run method
 * to display a panel which has letters that go from the top of the panel
 * to the bottom. The colors are set each iteration to a new color.
 * It is used by GamePanel class
 *********************************************************************************/
package game;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

@SuppressWarnings("serial")
class GameLetterPanel extends JPanel implements Runnable {
    public static Thread letterThread = null;
    private int      y = 10;
    static long nLetterDropped = 1;
    RandNum         rn = new RandNum();
    Random        rand = new Random();
    int        $ranNum = rn.ranNum();
    Color $letterColor = rn.ranNumColor();

    public static String[][] MathQuestions = {  // Math questions with answer.
	{"1 + 0 = ?", "1"},
	{"2 + 2 = ?", "4"},
	{"1 + 2 = ?", "3"},
	{"1 + 3 = ?", "4"},
	{"2 + 3 = ?", "5"},
	{"2 * 2 = ?", "4"},
	{"3 + 3 = ?", "6"},
	{"3 + 4 = ?", "7"},
	{"4 + 4 = ?", "8"},
	{"4 + 5 = ?", "9"}};

    int        questionIdx = rand.nextInt(10);
    String     $letter     = MathQuestions[questionIdx][0];

/***************************************************************************************
    *  main method in the class for starting and stopping the thread
	************************************************************************************/
    GameLetterPanel()  {
        if (letterThread == null) {
		letterThread = new Thread(this);
		letterThread.start();
			}
  	}

/***************************************************************************************
    *  Creates the thread and uses Thread.sleep to set the speed of the movement
 	************************************************************************************/

    public void run() {
      	Thread myThread = Thread.currentThread();
        while (letterThread == myThread) {
          try{
              Thread.sleep(20);
           }
           catch (InterruptedException e){}
	  repaint();
	}
    }

/***************************************************************************************
	* the paint method draws the letter based on color(ranNumLetter), location($ranNum and y)
    * and speed (y += 3 with Thread.sleep from run())
    *************************************************************************************/

     public void paint(Graphics g) {
        g.setFont(new Font("Courier", Font.BOLD+Font.ITALIC, 48));
        g.setColor(Color.white);
        g.drawString($letter, y, $ranNum);
        y += 3;
        Dimension d = getSize();
        if (y > (d.width - 10))
           {y = 10;
           GameLetterPanel.nLetterDropped +=1;
           $ranNum = rn.ranNum();
	   //           this.$letter = rn.ranNumLetter();
           this.questionIdx = rand.nextInt(10);                // Index into the Questions array.
           this.$letter = this.MathQuestions[questionIdx][0]; // Get the question String.

	   this.$letterColor = rn.ranNumColor();
           }

       	g.setFont(new Font("Courier", Font.BOLD+Font.ITALIC, 48));
        g.setColor($letterColor);
        g.drawString($letter, y, $ranNum);
     }

/***************************************************************************************
    *  sets thread to null which stops the thread
	************************************************************************************/
     public static void stop() {
        letterThread = null;
     }

/***************************************************************************************
    *  Returns the random question string when called
	************************************************************************************/
	public String getLetter() {
		return this.$letter;
	}

    /***************************************************
     * Returns the index to the question flying across the screen.
     */
    public int getQuestionIndex () {
	return this.questionIdx;
    }

    /**
     * If the answer to the math question at questionIdx, is the number represented by buttonLetter, return true,
     * else, return false.
     */
    public boolean matchAnswer (int questionIdx, String buttonLetter) {
	return (buttonLetter.equals(this.MathQuestions[questionIdx][1]));
    }
}
