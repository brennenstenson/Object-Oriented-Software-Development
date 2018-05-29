import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application{
	int[][] islandMap;
	Pane root;
	final int dimensions = 10;
	final int islandCount = 10;
	final int scale = 50;
	Image shipImage;
	Image pirateShipImage1;
	Image pirateShipImage2;
	ImageView shipImageView;
	ImageView pirateShipImageView1;
	ImageView pirateShipImageView2;
	OceanMap oceanMap;
	Scene scene;
	Ship ship;
	PirateShip pirateShip1;
	PirateShip pirateShip2;
	
	@Override
	public void start(Stage mapStage) throws Exception {
		oceanMap = new OceanMap(dimensions, islandCount);
		islandMap = oceanMap.getMap();
		
		root = new AnchorPane();
		drawMap();
		
		pirateShip1 = new PirateShip(oceanMap);
		loadPirateShipImage1(pirateShip1);
		pirateShip2 = new PirateShip(oceanMap);
		loadPirateShipImage2(pirateShip2);
		ship = new Ship(oceanMap);								//instantiate pirates first, then ship, in order to control ship. 
		loadShipImage(ship);
		
		pirateShip1.getMap(islandMap);
		pirateShip2.getMap(islandMap);
		
		ship.addObserver(pirateShip1);		//pirate ships now observe the ship
		ship.addObserver(pirateShip2);

		scene = new Scene(root,500,500);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.setScene(scene);
		mapStage.show();
		startSailing(islandMap);
	}
	
	private void startSailing(int[][] islandMap) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		public void handle(KeyEvent ke){
			switch(ke.getCode()){
			case RIGHT:
				ship.goEast(islandMap);
				break;
			case LEFT:
				ship.goWest(islandMap);
				break;
			case UP:
				ship.goNorth(islandMap);
				break;
			case DOWN:
				ship.goSouth(islandMap);
				break;
			default:
				break;
			}
			shipImageView.setX(ship.getShipLocation().x*scale);
			shipImageView.setY(ship.getShipLocation().y*scale);
			
			pirateShipImageView1.setX(pirateShip1.getShipLocation().x*scale);				//had to make separate methods to avoid overlapping
			pirateShipImageView1.setY(pirateShip1.getShipLocation().y*scale);
			
			pirateShipImageView2.setX(pirateShip2.getShipLocation().x*scale);
			pirateShipImageView2.setY(pirateShip2.getShipLocation().y*scale);

			
        }
			});
        }
						
	private void drawMap() {																//draws the map
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				rect.setStroke(Color.BLACK);
				if (islandMap[x][y] == 1) rect.setFill(Color.GREEN);
				else rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
		
	}

	public void loadShipImage(Ship ship){												//loads the ship
		Image shipImage = new Image("ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation(ship).x*scale);
		shipImageView.setY(oceanMap.getShipLocation(ship).y*scale);
		root.getChildren().add(shipImageView);
	}
	
	public void loadPirateShipImage1(PirateShip pirateShip){								//loads pirate ship 1 image
		Image pirateShipImage1 = new Image("pirateShip.png", 50, 50, true, true);
		pirateShipImageView1 = new ImageView(pirateShipImage1);
		pirateShipImageView1.setX(oceanMap.getShipLocation(pirateShip).x*scale);
		pirateShipImageView1.setY(oceanMap.getShipLocation(pirateShip).y*scale);
		root.getChildren().add(pirateShipImageView1);
	}
	
	public void loadPirateShipImage2(PirateShip pirateShip){								//loads pirate ship 2 image
		Image pirateShipImage2 = new Image("pirateShip.png", 50, 50, true, true);
		pirateShipImageView2 = new ImageView(pirateShipImage2);
		pirateShipImageView2.setX(oceanMap.getShipLocation(pirateShip).x*scale);
		pirateShipImageView2.setY(oceanMap.getShipLocation(pirateShip).y*scale);
		root.getChildren().add(pirateShipImageView2);
	}


	
	public static void main(String[] args){
		launch(args);
	}
}
