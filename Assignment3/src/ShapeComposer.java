import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeComposer extends Application{
				
	Pane root;
	Stage primaryStage;
	Scene scene;
	Point2D lastPosition;
	ArrayList<ShapeInterface> myMap;
	boolean amDragging;
	ShapeInterface keyNode;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		myMap = new ArrayList<ShapeInterface>();
		root = new AnchorPane();
		scene = new Scene(root,500,500);
		
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent mouseEvent) {
			Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			//iterate through arrayList to see if flower is clicked.
			// flowers are first, flowerBeds are last in the list
			
			if(!amDragging){
				for(ShapeInterface hi : myMap){
					if(hi.contains(clickPoint)){
						keyNode = hi;
						break;
					}
				}
			}
				
			switch(eventName){
			
			
			//moves the keyNode
			case ("MOUSE_DRAGGED"):
				amDragging = true;
				if(keyNode != null){
					double deltaX = clickPoint.getX()- lastPosition.getX();
					double deltaY = clickPoint.getY()- lastPosition.getY();
					keyNode.move(deltaX,deltaY);
				}
			
			break;
			
			case ("MOUSE_RELEASED"):
				//check to see if keyNode is Flower and was moved either in or out of a flowerBed, and applies the appropriate method
				if(keyNode != null && keyNode.getClass().getName() == "Flower"){
					for(ShapeInterface hi : myMap){
						if(hi.getClass().getName() == "FlowerBed" && ((FlowerBed) hi).contains(((Flower) keyNode).getPoint())){
							if (!((FlowerBed) hi).hasChild((Flower) keyNode)){
								((Flower) keyNode).circle.setFill(((FlowerBed) hi).rectangle.getStroke());
								((FlowerBed) hi).addChild((Flower) keyNode);
							}
						}else if(hi.getClass().getName() == "FlowerBed" && !((FlowerBed) hi).contains(((Flower) keyNode).getPoint())){
							if (((FlowerBed) hi).hasChild((Flower) keyNode)){
								((Flower) keyNode).circle.setFill(Color.BLACK);
								((FlowerBed) hi).removeChild((Flower) keyNode);
							}
						}
					}
					// checks if user clicks on empty space.
					// primary sets a new flower
				}else if (keyNode == null){
					if (mouseEvent.getButton().toString() == "PRIMARY"){
						try{
							Flower flower = new Flower();
							root.getChildren().add(flower.circle);
							
							// indexed 0 for priority clicking preference
							myMap.add(0,flower);
							primaryStage.setScene(scene);
						}catch (NullPointerException e){
							//System.err.println(e);
						}
					}
					// secondary sets a new flowerBed
					else if (mouseEvent.getButton().toString() == "SECONDARY"){
						try{
							FlowerBed flowerBed = new FlowerBed();
							root.getChildren().add(flowerBed.rectangle);
							myMap.add(flowerBed);						
							primaryStage.setScene(scene);
						}catch (NullPointerException e){
							//System.err.println(e);
						}
					}
				}
				keyNode = null;
				amDragging = false;
				
			break;
			
			}
			lastPosition = clickPoint;
		}
	};
	
	public static void main(String[] args){
		launch(args);
	}
}