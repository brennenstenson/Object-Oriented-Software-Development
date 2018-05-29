import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class StrategicSearch extends BattleshipSearch{
	
	public ArrayList<Point> carrier;
	public ArrayList<Point> submarine;
	HashSet<Point> used;
	int intFound;

	public int search(int[][] grid){
		intFound = 0;
		carrier = new ArrayList<Point>();
		submarine = new ArrayList<Point>();
		used = new HashSet<Point>();
		
		for(int x = 0; x < 25; x++){
			for(int y = 0 + (x%3) ; y < 25; y=y+3){					// diagonal search by increment of smallest ship
				if (!used.contains(new Point(x,y))){
					used.add(new Point(x,y));
					if (grid[x][y] == 1){
						carrier.add(new Point(x,y));
						intFound++;
						superSearch(x,y,grid);
					}
					else if (grid[x][y] == 2){
						submarine.add(new Point(x,y));
						intFound++;
						superSearch(x,y,grid);
					}
				}
				if (intFound == 8) return used.size();
			}
		}
		return -1;
	}
		
	private void superSearch(int x, int y, int[][] grid) {		//recursion search NESW from hit missile.
		if (y!=0){												// if not at edge, can subtract 1. 
			if (!used.contains(new Point(x,y-1))){				//check to see if HashSet contains coordinates, i.e. already searched
				used.add(new Point(x,y-1));
			
				if (grid[x][y-1] == 1){
					carrier.add(new Point(x,y-1));
					if(++intFound==8) {return;}
					superSearch(x,y-1,grid);
				}
				else if (grid[x][y-1] == 2){
					submarine.add(new Point(x,y-1));
					if(++intFound==8) {return;}
					superSearch(x,y-1,grid);
				}
			}
		} if (y!=24){											// if not at edge, can add 1. 
			if (!used.contains(new Point(x,y+1))){
				used.add(new Point(x,y+1));
			
				if (grid[x][y+1] == 1){
					carrier.add(new Point(x,y+1));
					if(++intFound==8) {return;}
					superSearch(x,y+1,grid);
				}
				else if (grid[x][y+1] == 2){
					submarine.add(new Point(x,y+1));
					if(++intFound==8) {return;}
					superSearch(x,y+1,grid);
				}
			}
		} if (x!=0){
			if (!used.contains(new Point(x-1,y))){
				used.add(new Point(x-1,y));
			
				if (grid[x-1][y] == 1){
					carrier.add(new Point(x-1,y));
					if(++intFound==8) {return;}
					superSearch(x-1,y,grid);
				}
				else if (grid[x-1][y] == 2){
					submarine.add(new Point(x-1,y));
					if(++intFound==8) {return;}
					superSearch(x-1,y,grid);
				}
			}
		} if (x!=24){
			if (!used.contains(new Point(x+1,y))){
				used.add(new Point(x+1,y));
			
				if (grid[x+1][y] == 1){
					carrier.add(new Point(x+1,y));
					if(++intFound==8) {return;}
					superSearch(x+1,y,grid);
				}
				else if (grid[x+1][y] == 2){
					submarine.add(new Point(x+1,y));
					if(++intFound==8) {return;}
					superSearch(x+1,y,grid);
				}
			}
		}
	}

	public String getName(){
		return "Strategic Search";
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
