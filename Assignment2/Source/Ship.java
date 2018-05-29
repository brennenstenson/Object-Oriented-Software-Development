import java.awt.Point;
import java.util.Observable;

import org.w3c.dom.events.EventException;

public class Ship extends Observable{
	public Point currentLocation;

	public Ship(OceanMap oceanMap){
		setCurrentLocation(5,5);
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
			if (x.getX() == 9 || islandMap[x.x+1][x.y] == 1) throw new NullPointerException();		//check if value is at edge, or if it is running into an island
			else{
				setCurrentLocation(x.x +1, x.y);
				setChanged();
				notifyObservers(currentLocation);

			}
		} catch (NullPointerException e) {
			System.out.println("can not go right!");
		}
	}
	public void goWest(int[][] islandMap) {
		Point x = getShipLocation();
		try{
			if (x.getX() == 0 || islandMap[x.x-1][x.y] == 1) throw new NullPointerException();
			else{
				setCurrentLocation(x.x-1, x.y);
				setChanged();
				notifyObservers(currentLocation);
			}
		} catch (NullPointerException e) {
			System.out.println("can not go left!");
		}
	}
	
	public void goNorth(int[][] islandMap) {
		Point y = getShipLocation();
		try{
			if (y.getY() == 0 || islandMap[y.x][y.y-1] == 1) throw new NullPointerException();
			else{
				setCurrentLocation(y.x, y.y-1);
				setChanged();
				notifyObservers(currentLocation);
			}
		} catch (NullPointerException e) {
			System.out.println("can not go up!");
		}
	}
	
	public void goSouth(int[][] islandMap) {
		Point y = getShipLocation();
		try{
			if (y.getY() == 9 || islandMap[y.x][y.y+1] == 1) throw new NullPointerException();
			else{
				setCurrentLocation(y.x, y.y+1);
				setChanged();
				notifyObservers(currentLocation);
			}
		} catch (NullPointerException e) {
			System.out.println("can not go down!");
		}
	}

}
