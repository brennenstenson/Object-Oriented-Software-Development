import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Battleship {
	
	public int[][] myGrid;
	public static ArrayList<int[][]> allGrids = new ArrayList<int[][]>();
	
	public Battleship(){}
		
	public void placeShip(int x, int y, int type){
				myGrid[x][y] = type;		
	}
	
	public void readBattleshipFile(String fileName){
		try{
			FileReader reader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(reader);
			String s;
			StringTokenizer token;
			int gridNumber = 0;
			while((s=buffReader.readLine()) != null){						//instantiate new grid for every line
				myGrid = new int[25][25];
				token = new StringTokenizer(s, "()");
				int i = 0;
				while(token.hasMoreTokens()){								//for every coordinate insert x and y value onto grid as a ship
					String[] pos = token.nextToken().split(",");
					int X = Integer.parseInt(pos[0]);
					int Y = Integer.parseInt(pos[1]);
					if (i < 5) placeShip(X,Y,1);							// 1 = carrier
					else placeShip(X,Y,2);									// 2 = submarine
					i++;
				}
				allGrids.add(gridNumber++, myGrid);							//add grid to grid storage
			}
			reader.close();
			
		} catch (IOException e) {											//this ain't no grid
			e.printStackTrace();
		} return;
	}

	public void search(){		
		
		BattleshipSearch battleshipsearch;												//connection to extra-terrestrials (other java classes)
		ArrayList<BattleshipSearch> allSearch = new ArrayList<BattleshipSearch>();			//arrayList of search types
		allSearch.add(0, new HorizontalSweep());
		allSearch.add(1, new RandomSearch());
		allSearch.add(2, new StrategicSearch());

		for (int i= 0; i < allGrids.size(); i++){
			System.out.println();
			System.out.println("Game "+ (i+1) +":");
			for (int ii= 0; ii < allSearch.size(); ii++){								//for loop in for loop since we need i^2 results
				battleshipsearch = allSearch.get(ii);
				System.out.println("Strategy: "+ battleshipsearch.getName());			
				int s = battleshipsearch.search(allGrids.get(i));						//don't even need a getCellsSearched method
				System.out.println("Number of cells searched: "+ s);
				System.out.println(battleshipsearch.getPosition(1)+ battleshipsearch.getPosition(2));		
			}
		}
	}
	
	public static void main(String[] args){
		Battleship battleship = new Battleship();
		battleship.readBattleshipFile("src/input.txt");
		battleship.search();
	}
}
