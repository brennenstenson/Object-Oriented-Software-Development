import javafx.geometry.Point2D;

public interface ShapeInterface {
	
	public default void move(double x, double y) {}
	public default boolean contains(Point2D point){ return false;}
}
