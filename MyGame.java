

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyGame extends SnakeGame{
	private boolean gameOver = false;
	//things you inherited from SnakeGame
	//protected Snake player;
	//protected BodySegment food;
	//protected double waitSeconds;
	double time;
	int scor;
	String name;
	int stuff = 0;
	public MyGame(){
		super();
		setBG(Color.BLACK);
		setGridColor( new Color(0,255,255,100));
		Font f = new Font("bold", Font.BOLD, 20);//makes a font for the timer and score
		timer.setFont(f);//sets it to that font
		score.setFont(f);//sets it to the font
		timer.setHorizontalAlignment(JTextField.CENTER);//centers the text
		score.setHorizontalAlignment(JTextField.CENTER);//centers the text
		
		playGame();
		time = 0;//starts the time at 0
		scor = 0;//starts the score at 0
	}
	
	public void gameFrame(){
			if(gameOver)
				return;
			int headx = player.head().getX();
			int heady = player.head().getY();
			if(player.overlapping() || (headx > WIDTH-25 || headx<=0 || heady > HEIGHT-25 || heady<=0)){// will end the game if the snake touches itself or the edges
				gameOver = true;
				JOptionPane.showMessageDialog(null,"GAME OVER");
				name = JOptionPane.showInputDialog(null, " Enter Your Name");
				new ScoreFrame(new ScoreRecord(name, time , scor));//adds it to the scoreboard
			}
			
				
			if(player.head().isTouching(food)){//if it's close enough to the food, it eats it and places new food
				player.addFirst();//adds one to the body
				placeFood();//generates new food
				scor+=10;//adds 10 to the score when it eats
				if(stuff == 0)//inverts the colors every time it eats
					stuff = 1;
				else if(stuff == 1)
					stuff = 0;
			}
			
			//Time
			double currtime = System.currentTimeMillis();//gets the current time
			double alltime = (((currtime)-(start))/1000);//gets the time you've been playing for
			int min = (int)(alltime/60);//gets the minutes
			int sec = (int) (alltime - (min*60));//gets the seconds
			int mili = (int)((alltime - (min*60 + sec))*1000);//gets the milliseconds and rounds them
			time = sec+(min*60);
			timer.setText("Time: "+min + ":" + sec + ":" + mili);// makes the time pretty
			
			//Score
			score.setText("Score : " + scor );//displays the current score
			
			//score.setText("Score");
			player.addFirst();
			player.removeLast();
			//do this at some point	
			if(stuff == 0){//manages the colors
				player.crazy();
				bgColor = Color.BLACK;

			}
			else if(stuff == 1){//manages the colors
				bgColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
				player.normal();
			}
				//gridColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			drawGame();
	}
	
	public static void main(String[] args){new MyGame();}	
}
