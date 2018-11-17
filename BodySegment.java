

import java.awt.Color;
import java.awt.Graphics;

public class BodySegment {
	//this BodySegment will act like a NODE
	//  he will point to the next BodySegment in the Snake
	//  You will need a variable for this
	
	private BodySegment prev;//he knows who is behind him
	private BodySegment next;//he knows who is in front of him
	
	
	
	public static final int SIZE=15;//sets the size
	public Color hue = Color.WHITE;//sets the color
	public static int nextID = 0;
	public int myID;
	private int xPos, yPos;
	
	public BodySegment(int x, int y){
		xPos = x;
		yPos = y;
		
		myID = nextID++;
	}
	
	//~~~~~~~~~~~~~~~~accessors and mutators~~~~~~~~~~~~~~~~~~~~~~
	
	public BodySegment getPrev(){return prev;}//returns the next segment

	public BodySegment getNext(){return next;}//returns the next segment

	public void setPrev(BodySegment n){prev = n;}//sets the previous segment
	
	public void setNext(BodySegment n){next = n;}//sets the next segment
	
	public void setHue(Color n){hue = n;}//returns the color of the bodysegment
	
	public int getX(){return xPos;}
	
	public int getY(){return yPos;}
	
	public boolean isTouching( BodySegment sp){
		double distx = Math.abs(this.getX()-sp.getX());
		double disty =Math.abs(this.getY()-sp.getY());
		if(distx<=SIZE-1 && disty<=SIZE-1)//if it's close enough to the food it returns true
			return true;
		return false;
	}
	
	//each BodySegment knows how to draw himself :)
	public void draw(Graphics g){
		g.setColor(hue);
		g.fillOval(xPos, yPos, SIZE, SIZE);
		//just for testing purposes, you can take this out later
		g.setColor(Color.BLACK);
		//g.drawString(""+myID, xPos+SIZE/4, yPos+SIZE);
	}
}
