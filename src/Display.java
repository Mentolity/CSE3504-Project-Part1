import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class Display extends JPanel {
	ArrayList<Lattice.Point> vistedPoints;
	int SCALE;
	int WIDTH;
	int HEIGHT;
	
	int x1;
	int x2;
	
	int y1;
	int y2;

	
    public Display(ArrayList<Lattice.Point> vp, int scale, int width, int height){
		vistedPoints = vp;
		SCALE = scale;
		WIDTH = width;
		HEIGHT = height;
		
		x1 = (WIDTH/2);
		x2 = (WIDTH/2);
		
		y1 = (HEIGHT/2);
		y2 = (HEIGHT/2);
	}

	@Override
    public void paintComponent (Graphics g){
		
        super.paintComponent(g);
        this.setBackground (Color.WHITE);
        
        for(int i=0; i<vistedPoints.size()-1; i++){
        	if(i == 0)
        		g.setColor(Color.GREEN);
        	else if(i == vistedPoints.size()-2)
        		g.setColor(Color.RED);
        	else
        		g.setColor(Color.BLACK);
        	
        	if(vistedPoints.get(i).getX() < vistedPoints.get(i+1).getX()){//moving right
        		x2 += SCALE/2;
        	}
        	if(vistedPoints.get(i).getX() > vistedPoints.get(i+1).getX()){//moving left
        		x2 -= SCALE/2;
        	}
        	if(vistedPoints.get(i).getY() < vistedPoints.get(i+1).getY()){//moving up
        		y2 -= SCALE/2;
        	}
        	if(vistedPoints.get(i).getY() > vistedPoints.get(i+1).getY()){//moving down
        		y2 += SCALE/2;
        	}
        	
        	g.drawLine(x1, y1, x2, y2);
        	/*System.out.println("i: " + i);
        	System.out.println("x1: " + x1);
        	System.out.println("x2: " + x2);
        	System.out.println("y1: " + y1);
        	System.out.println("y2: " + y2);
        	System.out.println("___________________");*/
        	
        	int tx1 = x2;
        	int ty1 = y2;
        	x1 = tx1;
        	y1 = ty1;
        }
        
        x1 = (WIDTH/2);
		x2 = (WIDTH/2);
		
		y1 = (HEIGHT/2);
		y2 = (HEIGHT/2);
    }
}
