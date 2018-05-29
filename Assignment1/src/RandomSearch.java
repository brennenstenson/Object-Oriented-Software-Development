import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class RandomSearch extends BattleshipSearch{
	
	public ArrayList<Point> carrier;
	public ArrayList<Point> submarine;
	public HashSet<Integer> used;
	
	public int search(int[][] grid){
		
		int x;
		int y;
		int intFound = 0;
		carrier = new ArrayList<Point>();
		submarine = new ArrayList<Point>();
		used = new HashSet<Integer>();
		
		int myRand;
		
		for (int i = 0; i < 625; i++){
			Random rand = new Random();
			while(used.contains(myRand = rand.nextInt(625))){				//generate a random number not used yet
				rand = new Random();
			}
			
			used.add(myRand);
			
			x = myRand/25;													// x coordinate
			y = myRand%25;													// y coordinate
			
			if (grid[x][y] == 1){
				carrier.add(new Point(x,y));
				intFound++;
			}
			else if (grid[x][y] == 2){
				submarine.add(new Point(x,y));
				intFound++;
			} 
			if (intFound == 8) return used.size();
		} return -1;
	}
	
	public String getName(){
		return "Random Search";
	}

	public int getCellsSearched(){
		return used.size();
	}
		
	public String getPosition(int type){
		ArrayList<Point> shipPosition = (type==1)?carrier:submarine;
		Collections.sort(shipPosition, new Comparator<Point>(){
			public int compare(Point o1, Point o2) {
		        int diff = o1.x - o2.x;
		        if (diff == 0) {
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
