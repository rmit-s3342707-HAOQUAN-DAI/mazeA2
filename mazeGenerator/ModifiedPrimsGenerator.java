package mazeGenerator;

import java.util.*;
import maze.Maze;
import maze.Cell;


public class ModifiedPrimsGenerator implements MazeGenerator {
	private boolean[][] visited;
	private int visitedCount; 
	@Override 
	public void generateMaze(Maze maze) {
//		ArrayDeque<Cell> q = new ArrayDeque<>();//save the visited cell
//		Cell cell = new Cell(maze.entrance.r, maze.entrance.c);
//		q.push(cell);
//		
			
		
		
		// TODO Auto-generated method stub
		Random random = new Random();

		// add start cell
//		Cell randCell = maze.map[random.nextInt(maze.sizeR)][random.nextInt(maze.sizeC)];
		ArrayDeque<Cell> C = new ArrayDeque<>();
		Cell cell = new Cell(maze.entrance.r, maze.entrance.c);
		C.push(cell);
		//C.add(maze.entrance); //add to cell
		visited = new boolean[maze.sizeR][maze.sizeC];
		visited[maze.entrance.r][maze.entrance.c] = true;
		int visits = 0;
		
		//maze.entrance.isVisited = true;
		System.out.println("Cell Location is : " + maze.entrance.c + ", " + maze.entrance.r);
 
		//available neighbor of cell
		ArrayList<Cell> temp = new ArrayList<>();
		//create frontier to store all neighbors
		ArrayList<Cell> frontier = new ArrayList<>();
		
		switch(maze.type){
//--------------------------------------HEX type---------------------------------------		
	
		case HEX:
			
			while (visits < visitedCount || !C.isEmpty()){
				while((cell.c+ (1/2) < maze.sizeC && !visited[cell.r][cell.c+ (1/2)])||
					  (cell.c-(1/2) >= 0 && !visited[cell.r][cell.c-(1/2)])||
				      (cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c])||
					  (cell.r-1 >= 0 && !visited[cell.r-1][cell.c])) {
							int HexRand	= (int)(Math.random()* 6);
							System.out.println("Direction: " + HexRand + " x: " + (cell.c+ 1/2) + " y: " + cell.r);
							System.out.println("---------------------------------------------------------");
			
						//check East
						//same row, one cell right, so + 1
						//East
							if (HexRand == 0 && cell.c+1 < maze.sizeR && !visited[cell.r][(cell.c+1/2)]) {
								for (int i = 0; i < 6; i++) {
									if (maze.entrance.neigh[i] != null) {
										frontier.add(maze.entrance.neigh[i]);
									}
								}
							// remove the east wall
							maze.map[cell.r][cell.c].wall[EAST].present = false;
							Cell nxCellE = new Cell(cell.r, cell.c+1);
							Cell nxCellNE = new Cell(cell.r, cell.c+1);
							Cell nxCellNW = new Cell(cell.r, cell.c+1);
							Cell nxCellW = new Cell(cell.r, cell.c+1);
							Cell nxCellSW = new Cell(cell.r, cell.c+1);
							Cell nxCellSE = new Cell(cell.r, cell.c+1);
							
							visited[cell.r][cell.c+1] = true;
							temp.add(nxCellE);
							temp.add(nxCellNE);
							temp.add(nxCellNW);
							temp.add(nxCellW);
							temp.add(nxCellSW);
							temp.add(nxCellSE);
							visits++;
							cell = get(random.);
						}
				}
			
//				for (int i = 0; i < 6; i++) {
//					if (maze.entrance.neigh[i] != null) {
//						frontier.add(maze.entrance.neigh[i]);
//					}
//				}
//	
//				while(frontier.size() != 0 ){
//					System.out.println(frontier.size());
//					int indexF = random.nextInt(frontier.size());
//					Cell neighborF = frontier.get(indexF);
//	
//	//				System.out.print("The original frontier is: ");
//	//				for (int n = 0; n < frontier.size(); n++) {
//	//					System.out.print(frontier.get(n).c + ", " + frontier.get(n).r + " | ");
//	//				}
//	//				System.out.println();
//	
//					frontier.remove(neighborF);
//					System.out.println("neighor selected: " + neighborF.r + ", " + neighborF.c);
//					
//					neighborF.isVisited = true;
//	
//					//find available neighbor of current neighbourF
//	//				System.out.print("neighborF.neigh[i]: ");
//					for (int i = 0; i < 6; i++) {
//						if (neighborF.neigh[i] != null && !(neighborF.neigh[i].isVisited)) {
//	//						System.out.println("x: " + neighborF.neigh[i].c + " y: " + neighborF.neigh[i].r + "  visited?: " + neighborF.neigh[i].isVisited);
//							temp.add(neighborF.neigh[i]);
//						}
//					}
//
//					//remove the wall
//					Integer[] directions = new Integer[6];
//					directions[0] = new Integer(EAST);
//					directions[1] = new Integer(NORTHEAST);
//					directions[2] = new Integer(NORTHWEST);
//					directions[3] = new Integer(WEST);
//					directions[4] = new Integer(SOUTHWEST);
//					directions[5] = new Integer(SOUTHEAST);
//					
//					java.util.Collections.shuffle(java.util.Arrays.asList(directions));
//	
//					if (neighborF != null) {
//						for (int i = 0; i < 6; i++) {
//							int dir = directions[i].intValue();
//							if (neighborF.neigh[dir]!= null && neighborF.neigh[dir].isVisited) {
//								neighborF.wall[dir].present = false;
//								neighborF.neigh[dir].wall[oppoDir[dir]].present = false;
//								break;
//							}
//							System.out.println("Direction selected:" + directions);
//						}
//						
//					}
//					
//					
//					//Add cell to Cell set C
//					C.add(neighborF);
//					System.out.println("Moved to x: " + neighborF.r + " y: " + neighborF.c);
//					
//					//check
//					boolean isDuplicate = false;
//					for (int i = 0; i < temp.size(); i++) {
//						for (int j = 0; j < frontier.size(); j++) {
//							if ((temp.get(i).r == frontier.get(j).r) && (temp.get(i).c == frontier.get(j).c)) {
//								isDuplicate = true;
//							}
//						}
//						if (!isDuplicate) {
//							if (!temp.get(i).isVisited) {
//								frontier.add(temp.get(i));
//							}
//						}
//					}
//	
//					temp.clear();
//	
//					for (int i = 0; i < 6; i++) {
//						if (neighborF.neigh[i] != null) {
//							if (!neighborF.neigh[i].isVisited && (!frontier.contains(neighborF.neigh[i]))) {
//								frontier.add(neighborF.neigh[i]);
//								
//							}
//						}
//					}
//					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
			}//-------------------------------------HEX----------------------------------------------------------
		
		
//-----------------------------------NORMAL type-----------------------------------------
		case NORMAL:	
			//store start cell
			for (int i = 0; i < 4; i++) {
				if (maze.entrance.neigh[i] != null) {
					frontier.add(maze.entrance.neigh[i]);
				}
			}
	
			while(frontier.size() != 0){
				//randomly select a cell from the frontier and remove it
				int indexF = random.nextInt(frontier.size());
	
				Cell neighborF = frontier.get(indexF);
	
	//			System.out.print("The original frontier is: ");
	//			for (int n = 0; n < frontier.size(); n++) {
	//				System.out.print(frontier.get(n).c + ", " + frontier.get(n).r + " | ");
	//			}
	//			System.out.println();
	
				frontier.remove(neighborF);
				System.out.println("neighor selected: " + neighborF.c + ", " + neighborF.r);
				neighborF.isVisited = true;
	
				//find available neighbor of current neighbourF
	//			System.out.print("neighborF.neigh[i]: ");
				for (int i = 0; i < 4; i++) {
					if (neighborF.neigh[i] != null && !(neighborF.neigh[i].isVisited)) {
	//					System.out.println("x: " + neighborF.neigh[i].c + " y: " + neighborF.neigh[i].r + "  visited?: " + neighborF.neigh[i].isVisited);
						temp.add(neighborF.neigh[i]);
					}
				}
	
				//remove the wall
				Integer[] directions = new Integer[4];
				directions[0] = new Integer(EAST);
				directions[1] = new Integer(NORTH);
				directions[2] = new Integer(WEST);
				directions[3] = new Integer(SOUTH);
				java.util.Collections.shuffle(java.util.Arrays.asList(directions));
	
				if (neighborF != null) {
					for (int i = 0; i < 4; i++) {
						int dir = directions[i].intValue();
						if (neighborF.neigh[dir]!= null && neighborF.neigh[dir].isVisited) {
							neighborF.wall[dir].present = false;
							neighborF.neigh[dir].wall[oppoDir[dir]].present = false;
							break;
						}
					}
				}
	
				//Add cell to Cell set C
				C.add(neighborF);
				System.out.println("Moved to x: " + neighborF.c + " y: " + neighborF.r);
	
				boolean isDuplicate = false;
				for (int i = 0; i < temp.size(); i++) {
					for (int j = 0; j < frontier.size(); j++) {
						if ((temp.get(i).r == frontier.get(j).r) && (temp.get(i).c == frontier.get(j).c)) {
							isDuplicate = true;
						}
					}
					if (!isDuplicate) {
						if (!temp.get(i).isVisited) {
							frontier.add(temp.get(i));
						}
					}
				}
	
				temp.clear();
	
				for (int i = 0; i < 4; i++) {
					if (neighborF.neigh[i] != null) {
						if (!neighborF.neigh[i].isVisited && (!frontier.contains(neighborF.neigh[i]))) {
							frontier.add(neighborF.neigh[i]);
						}
					}
				}
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		}	
//-------------------------------------------NORMAL---------------------------------------------------------------		
	} //end of generateMaze()
} // end of class ModifiedPrimsGeneratortor
