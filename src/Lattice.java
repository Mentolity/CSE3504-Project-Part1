import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Lattice{
	private ArrayList<Point> vistedPoints = new ArrayList<Point>(); //using arrayList instead of boolean[][] for infinite lattice case
	private int xPos; //current x position in lattice
	private int yPos; //current y position in lattice
	private int dim; //max dim of lattice
	private int pathLength = 0; 
	private boolean isInfinite; //whether the lattice is infinite or not
	
	//vars for Practice 2
	private int xStart;
	private int yStart;
	
	public Lattice(){
		this(0, true);
	}
	
	public Lattice(int dimension, boolean isInfinite){
		this.isInfinite = isInfinite;
		dim = dimension;
		xPos = dim/2;
		yPos = dim/2;
		vistedPoints.add(new Point(xPos, yPos));
		xStart = xPos;
		yStart = yPos;
	}
	
	public int getPathLength(){
		return pathLength;
	}
	
	
	///////////methods for practice 2
	public int xStart(){
		return xStart;
	}	
	public int yStart(){
		return yStart;
	}
	
	public int xMax(){
		int max = xStart;
		for(Point p : vistedPoints){
			if(p.getX() > max)
				max = p.getX();
		}
		return max;
	}
	public int xMin(){
		int min = xStart;
		for(Point p : vistedPoints){
			if(p.getX() < min)
				min = p.getX();
		}
		return min;
	}
	
	public int yMax(){
		int max = yStart;
		for(Point p : vistedPoints){
			if(p.getY() > max)
				max = p.getY();
		}
		return max;
	}
	public int yMin(){
		int min = yStart;
		for(Point p : vistedPoints){
			if(p.getY() < min)
				min = p.getY();
		}
		return min;
	}
	
	public int xEnd(){
		return vistedPoints.get(vistedPoints.size()-1).getX();
	}
	public int yEnd(){
		return vistedPoints.get(vistedPoints.size()-1).getY();
	}
	///////////
	
	public boolean walk(){ //return true if a valid path was taken
		//System.out.println("X: " + xPos + " Y: " + yPos);
		int x = xPos;
		int y = yPos;
		double r = Math.random();
		if(r < 0.25){
			y++; //up
		}else if(r < 0.50){
			y--; //down
		}else if(r < 0.75){
			x--; //left
		}else{
			x++; //right
		}
		if(!visted(x,y) && (!outOfBounds(x, y) || isInfinite)){
			xPos = x;
			yPos = y;
			vistedPoints.add(new Point(xPos, yPos));
			pathLength++;
			return true;
		}else if(!stuck()){ //if not stuck keep trying till we take one of the available paths path
			walk();
			return true;
		}
		return false;
	}
	
	private boolean stuck(){
		//check if its possible to go in each direction
		if(!visted(xPos+1,yPos) && (!outOfBounds(xPos+1, yPos) || isInfinite)){
			return false;
		}
		if(!visted(xPos-1,yPos) && (!outOfBounds(xPos-1, yPos) || isInfinite)){
			return false;
		}
		if(!visted(xPos,yPos+1) && (!outOfBounds(xPos, yPos+1) || isInfinite)){
			return false;
		}
		if(!visted(xPos,yPos-1) && (!outOfBounds(xPos, yPos-1) || isInfinite)){
			return false;
		}
		return true;
	}
	
	private boolean outOfBounds(int x, int y){//returns true if the given dimension is out of bounds
		if((x > dim || y > dim) || (x < 0 || y < 0))
			return true;
		else
			return false;
	}
	
	private boolean visted(int x, int y){//returns true if the point x,y has already been visited
		Point point = new Point(x, y);
		for(Point p : vistedPoints){
			if(p.equals(point))
				return true;
		}
		return false;
	}
	
	public void display(String title, int scale){
		int SCALE = scale;
		int WIDTH = (xMax() - xMin())*(SCALE);
		int HEIGHT = (yMax() - yMin())*(SCALE);
		//System.out.println(WIDTH + " : " + HEIGHT);
		//JFrame settings//
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension (WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension (WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        Display disp = new Display(vistedPoints, scale, WIDTH, HEIGHT);
        frame.add(disp);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.repaint();
        ///////////////////
	}
	
	
	public class Point{
		int x;
		int y;
		
		public Point(int xPos, int yPos){
			x = xPos;
			y = yPos;
		}
		
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public boolean equals(Point p) {
			if(x == p.getX() && y == p.getY())
				return true;
			else
				return false;
		}
	}
        public ArrayList<Point> pointsList(){
            return vistedPoints;
        }
        public int getX(){
            return xPos;
        }
        public int getY(){
            return yPos;
        }
}