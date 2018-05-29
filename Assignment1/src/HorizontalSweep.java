import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HorizontalSweep extends BattleshipSearch{
	
	public ArrayList<Point> carrier;
	public ArrayList<Point> submarine;
	public int cellCount;
	
	public int search(int[][] grid){
		int intFound = 0;
		cellCount = 0;
		carrier = new ArrayList<Point>();
		submarine = new ArrayList<Point>();

		for(int x = 0; x < 25; x++){													//check all y coordinates for every x coordinate
			for(int y = 0; y < 25; y++){
				
				if (grid[x][y] == 1){													//if match then add to carrier and increment # of hits
					carrier.add(new Point(x,y));
					intFound++;
				}
				else if (grid[x][y] == 2){												//if match then add to submarine and increment # of hits
					submarine.add(new Point(x,y));
					intFound++;
				} 
				cellCount++;
				if (intFound == 8) return cellCount;									//stop when get 8 hits - when both ships are down
			}
		}
		return -1;
	}
	
	public String getName(){
		return "Horizontal Sweep";
	}

	public int getCellsSearched(){
		return cellCount;
	}
	
	public String getPosition(int type){											//pretty sweet code
		ArrayList<Point> shipPosition = (type==1)?carrier:submarine;				
		Collections.sort(shipPosition, new Comparator<Point>(){						//no sorting method? just create one then
			public int compare(Point o1, Point o2) {
		        int diff = o1.x - o2.x;
		        if (diff == 0) {													// if x coordinates are same, then compare y coordinates
		            diff = o1.y - o2.y;
		        }
		        return diff;
			}
		});
		
		return (type==1)?
				("Carrier found: (" + shipPosition.get(0).x + "," + shipPosition.get(0).y + ") to (" +
				shipPosition.get(4).x + "," + shipPosition.get(4).y + ") ")
				:
				("Submarine found: (" + shipPosition.get(0).x + "," + shipPosition.get(0).y + ") to (" +
				shipPosition.get(2).x + "," + shipPosition.get(2).y + ") ");
	}

}
