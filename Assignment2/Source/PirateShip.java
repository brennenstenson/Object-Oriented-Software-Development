import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import org.w3c.dom.events.EventException;

public class PirateShip implements Observer{
	
	public Point currentLocation;
	int[][] islandMap;
	
	
	// moves PS towards Ship. checks if it can move. prioritizes moves in order: E, W, N, S.
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Ship)
		{
			Ship ship = (Ship)o;
			Point ShipLocation = (Point)arg;
			while (true){
				if(currentLocation.x < ship.getShipLocation().x){
					if(currentLocation.x != 9 && islandMap[currentLocation.x+1][currentLocation.y] != 1){
						goEast(islandMap);
						break;
					}
				}if(currentLocation.x > ShipLocation.x){
					if(currentLocation.x != 0 && islandMap[currentLocation.x-1][currentLocation.y] != 1){
						goWest(islandMap);
						break;	
					}
				}if(currentLocation.y > ShipLocation.y){
					if(currentLocation.y != 0 && islandMap[currentLocation.x][currentLocation.y-1] != 1){
						goNorth(islandMap);
						break;
					}
				}if(currentLocation.y < ShipLocation.y){
					if(currentLocation.y != 9 && islandMap[currentLocation.x][currentLocation.y+1] != 1){
						goSouth(islandMap);
						break;
					}
				}
			}
		}
	}
	
	public void getMap(int[][] map){
		islandMap = map;
	}
	

	public PirateShip(OceanMap oceanMap){
		Random rand = new Random();
		Point myPoint = new Point(rand.nextInt(10), rand.nextInt(10));
		while (oceanMap.myGrid[myPoint.x][myPoint.y] != 0) {
				rand = new Random();
				myPoint = new Point(rand.nextInt(10), rand.nextInt(10));
		}
		setPirateShip(myPoint.x, myPoint.y, oceanMap);
	}
	
	private void setPirateShip(int i, int j, OceanMap oceanMap) {			//for instantiating a pirate ship. sets point on oceanMap == 2 to avoid another pirate landing on it
		currentLocation = new Point(i,j);
		oceanMap.setPirateShipLocation(i,j);
	}
	
	private void setCurrentLocation(int i, int j) {
		currentLocation = new Point(i,j);
	}

	public Point getShipLocation(){
		return currentLocation;	
	}

	public void goEast(int[][] islandMap) throws EventException {
		Point x = getShipLocation();
		try{
			if (x.getX() == 9 || islandMap[x.x+1][x.y] == 1) throw new NullPointerException();
			else setCurrentLocation(x.x +1, x.y);
		} catch (NullPointerException e) {
			System.out.println("can not go right!");
		}
	}
	public void goWest(int[][] islandMap) {
		Point x = getShipLocation();
		try{
			if (x.getX() == 0 || islandMap[x.x-1][x.y] == 1) throw new NullPointerException();
			else setCurrentLocation(x.x-1, x.y);
		} catch (NullPointerException e) {
			System.out.println("can not go left!");
		}
	}
	
	public void goNorth(int[][] islandMap) {
		Point y = getShipLocation();
		try{
			if (y.getY() == 0 || islandMap[y.x][y.y-1] == 1) throw new NullPointerException();
			else setCurrentLocation(y.x, y.y-1);
		} catch (NullPointerException e) {
			System.out.println("can not go up!");
		}
	}
	
	public void goSouth(int[][] islandMap) {
		Point y = getShipLocation();
		try{
			if (y.getY() == 9 || islandMap[y.x][y.y+1] == 1) throw new NullPointerException();
			else setCurrentLocation(y.x, y.y+1);
		} catch (NullPointerException e) {
			System.out.println("can not go down!");
		}
	}

}
