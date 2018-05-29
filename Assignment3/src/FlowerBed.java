import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class FlowerBed implements ShapeInterface{
	Rectangle rectangle;
	ArrayList<Flower> myChildren;
	
	public FlowerBed(){
		myChildren = new ArrayList<Flower>();
		draw();
	}
	
	public void draw(){
		rectangle = new Rectangle();
		rectangle.setHeight(150);
		rectangle.setWidth(150);
		rectangle.setX(50);
		rectangle.setY(200);
		
		//easy way to generate random color
		Random r = new Random();
		rectangle.setStroke(Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		
		rectangle.setStrokeWidth(5);
		rectangle.setFill(Color.WHITE);
		rectangle.setOpacity(0.5);
		rectangle.setScaleX(1);
		rectangle.setScaleY(1);
	}
	
	public void move(double x, double y){
		for(Flower child : myChildren) child.move(x, y);
		
		rectangle.setX(rectangle.getX()+x);
		rectangle.setY(rectangle.getY()+y);
	}
		
	public void addChild(Flower child){
		myChildren.add(child);
	}
	
	public void removeChild(Flower child){
		myChildren.remove(child);		
	}
	
	public boolean hasChild(Flower child){
		if (myChildren.contains(child)) return true;
		else return false;
	}
	
	public boolean contains(Point2D point){
		if (rectangle.contains(point)) return true;
		else return false;
	}
}
