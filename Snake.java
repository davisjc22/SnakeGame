

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

public class Snake implements KeyListener{
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;
	int size = 0;
	//variables you will need
	// 1) pointer to the first and/or last piece of the snake
	// 2) a variable to keep track of what direction this snake is facing
	
	private BodySegment last;
	private int dir;
	
	public Snake(){//makes first BodySegment
		last = new BodySegment(200,300); //go ahead and give him 1 BodySegment
		last.setPrev(last);//points to itself
		last.setNext(last);//points to itself
		dir = RIGHT; //tell him what direction he is moving		
		size++;
	}
	
	public int size(){//returns size
		return size;//adds and subtracts from size in addFirst and removeLast
	}
	
	public void addFirst(){
		int x = last.getNext().getX();//saves the x of the head of the head of the snake
		int y  = last.getNext().getY();//saves the y of the head of the snake
		BodySegment n;
		if(dir == UP)//adds the bodysegment in the right direction
			n = new BodySegment(x, y-15);
		else if(dir == DOWN)
			n = new BodySegment(x, y+15);
		else if(dir == LEFT)
			n = new BodySegment(x-15, y);
		else
			n = new BodySegment(x+15, y);//if it's not the other directions, it assumes it's right
		
		BodySegment oldFirst = last.getNext();
		n.setNext(oldFirst);//points the new head towards the old
		oldFirst.setPrev(n);//
		n.setPrev(last);
		last.setNext(n);//points the tail to the new head
		size++;//adds one to the size
	}
	
	public void removeLast(){
		BodySegment first = last.getNext();
		BodySegment newLast = last.getPrev();//
		
		newLast.setNext(first);
		first.setPrev(newLast);
		last = newLast;//recognizes newlast as the new last
		size--;//subtracts one from the size
	}
	
	public boolean overlapping(){//lets the snake know if two of its segments are touching
		BodySegment curr = last.getNext().getNext().getNext();
		while(curr!=last.getNext()){
			if(head().isTouching(curr))
				return true;
			curr = curr.getNext();
		}
		return false;
		
	}
	public void crazy(){//makes the snake random colors
		BodySegment curr = last.getNext();
		while(curr!=last){
			curr.setHue(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			curr = curr.getNext();
		}
	}
	public void normal(){//sets the snake to all white
		BodySegment curr = last.getNext();
		while(curr!=last){
			curr.setHue(Color.WHITE);
			curr = curr.getNext();
		}
	}
	
	
	public BodySegment getLast(){return last;}
	
	public BodySegment head(){return last.getNext();}
	
	public void draw(Graphics g){
		BodySegment curr = last.getNext();
		while(curr != last){//draws every segment but the last
			curr.draw(g);
			curr = curr.getNext();
		}
		last.draw(g);//draws the last
		//tell EACH segment to draw (and pass it g)
	}
	
	
	@Override
	//make the snake respond to key presses
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_UP)//if up key is pressed
			if(dir!=DOWN)//can't do a 180
				dir = UP;//responds to key press
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			if(dir!=UP)//can't do a 180
				dir = DOWN;//responds to key press
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			if(dir!=RIGHT)//can't do a 180
				dir = LEFT;//responds to key press
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			if(dir!=LEFT)//can't do a 180
				dir = RIGHT;//responds to key press
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
