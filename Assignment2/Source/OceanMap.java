import java.awt.Point;
import java.util.Random;

public class OceanMap{
	public int[][] myGrid;
	public OceanMap(int dimensions, int islandCount){
		myGrid = new int[10][10];
		myGrid[5][5] = 2;			// ship position is 2. just need to be defaulted when we populate islands and 
									// check for mygrid[x][y] != 0 to prevent putting island on ship
		populateIslands();
	}

	public int[][] getMap(){
		return myGrid;
		
	}

	public Point getShipLocation(PirateShip pirateship) {			
		return new Point(pirateship.getShipLocation());
	}
	
	public Point getShipLocation(Ship ship) {						// returns ship location.
		return new Point(ship.getShipLocation());
	}

	public void setPirateShipLocation(int i, int j){				//takes pirateShip location and sets it on myGrid to 2, therefore prevening any other ships to be built there
		myGrid[i][j] = 2;
	}
	

	
	public void populateIslands(){
		Random rand = new Random();
		Point myPoint = new Point(rand.nextInt(10), rand.nextInt(10));
		
		for(int i = 0; i < 10; i++){
			while (myGrid[myPoint.x][myPoint.y] != 0) {
				rand = new Random();
				myPoint = new Point(rand.nextInt(10), rand.nextInt(10));
			}
			myGrid[myPoint.x][myPoint.y] = 1;
			myPoint = new Point(rand.nextInt(10), rand.nextInt(10));
		}
	}
}
