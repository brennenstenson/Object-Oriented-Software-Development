import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;

class Flower implements ShapeInterface{
	Circle circle;

	public Flower(){
		draw();
	}

	public void draw(){
		circle = new Circle();
		circle.setCenterX(250);
		circle.setCenterY(250);
		circle.setRadius(15);
		circle.setFill(Color.BLACK);
		circle.setStrokeWidth(1);
	}
	
	public void move(double x, double y){
		circle.setCenterX(circle.getCenterX()+x);
		circle.setCenterY(circle.getCenterY()+y);
	}
		
	public boolean contains(Point2D point){
		if (circle.contains(point))return true;
		else return false;
	}
	public Point2D getPoint(){
		return new Point2D(circle.getCenterX(), circle.getCenterY());
		}

}
