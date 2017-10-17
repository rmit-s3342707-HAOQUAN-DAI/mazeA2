package mazeGenerator;

import java.util.*;
import maze.Maze;
import maze.Cell;

public class RecursiveBacktrackerGenerator implements MazeGenerator {
	private boolean[][] visited; // visited array for generation
	private int visitedCount; // how many empty spaces have been visited in the maze


	
	
	@Override
	public void generateMaze(Maze maze) {
		ArrayDeque<Cell> q = new ArrayDeque<>();//save the visited cell
		Cell cell = new Cell(maze.entrance.r, maze.entrance.c);
		q.push(cell);
		visited = new boolean[maze.sizeR][maze.sizeC];
		visited[maze.entrance.r][maze.entrance.c] = true;
		int visits = 0;
		
		// map = new Cell[sizeR][sizeC + (sizeR + 1) / 2];  <---------!!
//		if (isIn(entR, entC + (entR + 1) / 2))
//			entrance = map[entR][entC + (entR + 1) / 2];
		// e.g., the northeast neighbor of map[r][c] would be map[r + deltaR[NORTHEAST][c + deltaC[NORTHEAST]]		
		//public final static int deltaR[] = { 0, 1, 1, 0, -1, -1 };
		//public final static int deltaC[] = { 1, 1, 0, -1, -1, 0 };
		//public final static int oppoDir[] = { 3, 4, 5, 0, 1, 2 };
//
//		public boolean isOnEdge(int r, int c) {
//			if (isIn(r, c + (r + 1) / 2) && (r == 0 || r == sizeR - 1 || c == 0 || c == sizeC - 1)) 
//				return true;
//			return false;
//		} // end of isOnEdge()
		
//		protected boolean isIn(int r, int c) {
//			return r >= 0 && r < sizeR && c >= (r + 1) / 2 && c < sizeC + (r + 1) / 2;
//		} // end of isIn()

/*
 * 	
 * @see mazeGenerator.MazeGenerator#generateMaze(maze.Maze)
 *  HEX
 * 
 * 
 * 
 */
		if(maze.type == HEX){
			System.out.println("generating hex maze---------------------------------------------------");
			System.out.println("Maze size: " + maze.sizeR + " * " + maze.sizeC);
			while (visits < visitedCount || !q.isEmpty()){
				while(//(cell.c < maze.sizeC  && !visited[cell.r][cell.c+1])||
					  (cell.c-1 >= 0 && !visited[cell.r][cell.c-1])||
				      (cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c+deltaC[NORTHEAST]])||
				      (cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c-deltaC[NORTHWEST]])||
				      (cell.r-1 >= 0 && !visited[cell.r-1][cell.c+deltaC[SOUTHWEST]])|| 
					  (cell.r-1 >= 0 && !visited[cell.r-1][cell.c-deltaC[SOUTHEAST]])){
							int HexRand	= (int)(Math.random()* 6);
							System.out.println("Current Row: " + cell.r + " " + "Current Column: " + cell.c + " " + "Next Direction: " + HexRand);
							System.out.println("---------------------------------------------------------");
			
						//check East
						//same row, one cell right, so + 1
						if (HexRand == 0 && cell.c+1 <= maze.sizeC && !visited[cell.r][cell.c+1]){
							// remove the east wall
							 if(maze.map[cell.r][cell.c].wall[EAST].present = false){
									continue;
								}else{
									maze.map[cell.r][cell.c].wall[EAST].present = false;
								}	
							Cell nxCell = new Cell(cell.r, cell.c+1);
							visited[cell.r][cell.c+1] = true;
							q.add(nxCell);
							visits++; 
							cell = nxCell;
						}
						
						
						//check NorthEast
						//one row up, half cell right, so r + 1, c + 1/2
						if (HexRand == 1 && cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c+deltaC[NORTHEAST]]) { //&& cell.c+deltaC[NORTHEAST] < maze.sizeC +deltaC[NORTHEAST]
							// remove the northeast wall  
							if(cell.c+(cell.c+1)/2 == maze.sizeC+(cell.c+1)/2 && cell.r%2 == 1){
								break;
							}
							if(maze.map[cell.r][cell.c].wall[NORTHEAST].present = false){
								continue;
							}else{
								maze.map[cell.r][cell.c].wall[NORTHEAST].present = false;
							}
							Cell nxCell = new Cell(cell.r+1, cell.c+deltaC[NORTHEAST]);
							visited[cell.r+1][cell.c+deltaC[NORTHEAST]] = true;
							q.add(nxCell);
							visits++; 
							cell = nxCell;
						}
						
						//check NorthWest
						//one row up, half cell left
						if (HexRand == 2 && cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c-deltaC[NORTHWEST]]){ //&& cell.c-deltaC[NORTHEAST] <=0 ) {
							// remove the northwest wall
							if(cell.c == 0 && cell.r%2 == 1){
								break;
							}else 
							if(maze.map[cell.r][cell.c].wall[NORTHWEST].present = false){
								continue;
							}else{
								maze.map[cell.r][cell.c].wall[NORTHWEST].present = false;
							}
							Cell nxCell = new Cell(cell.r+deltaR[NORTHWEST], cell.c-deltaC[NORTHWEST]);
							visited[cell.r+1][cell.c-deltaC[NORTHWEST]] = true;
							q.add(nxCell);
							visits++;  
							cell = nxCell;
						}
						
						
						//check West
						//same row, one cell left
						if (HexRand == 3 && cell.c-1 >= 0 && !visited[cell.r][cell.c-1]) {
							// remove the west wall
							maze.map[cell.r][cell.c].wall[WEST].present = false; 
							Cell nxCell = new Cell(cell.r, cell.c-1);
							visited[cell.r][cell.c-1] = true;
							q.add(nxCell);
							visits++;
							cell = nxCell;
						}
						   
						//check SouthWest
						//one row down, half cell left
						if (HexRand == 4 && cell.c-deltaC[SOUTHWEST] >= 0 && cell.r-1 >=0 && !visited[cell.r-deltaR[SOUTHWEST]][cell.c-deltaC[SOUTHWEST]]) {
							// remove the west wall
							if(cell.c==0 && cell.r%2==0){
								break;
							}
							if(maze.map[cell.r][cell.c].wall[SOUTHWEST].present = false){
								continue;
							}else{
								maze.map[cell.r][cell.c].wall[SOUTHWEST].present = false;
							}
							Cell nxCell = new Cell(cell.r-deltaR[SOUTHWEST],cell.c-deltaC[SOUTHWEST]);
							visited[cell.r-deltaR[SOUTHWEST]][cell.c-deltaC[SOUTHWEST]] = true;
							q.add(nxCell);
							visits++;
							cell = nxCell; 
						}
						
						//check SouthEast
						//one row down, half cell right
						if (HexRand == 5 && cell.c+deltaC[SOUTHEAST] < maze.sizeC && cell.r-1 >=0 && !visited[cell.r-deltaR[SOUTHEAST]][cell.c+deltaC[SOUTHEAST]]) {
							// remove the west wall
							if(cell.c-1 == maze.sizeC && cell.r%2==1){
								break;
							}
							if(maze.map[cell.r][cell.c].wall[SOUTHEAST].present = false){
								continue;
							}else{
								maze.map[cell.r][cell.c].wall[SOUTHEAST].present = false;
							}
							Cell nxCell = new Cell(cell.r-deltaR[SOUTHEAST], cell.c+deltaC[SOUTHEAST]);
							visited[cell.r-deltaR[SOUTHEAST]][cell.c+deltaC[SOUTHEAST]] = true;
							q.add(nxCell);
							visits++;
							cell = nxCell;
						}	
				}				
			}
		}	
			
			
			
		
		
		
//-------------------------type normal starts here----------------------------------------------------
//----------------------------------------------------------------------------------------------------		
		if(maze.type == NORMAL || maze.type == TUNNEL){
			while(visits < visitedCount || !q.isEmpty()) {
				while((cell.c+1 < maze.sizeC && !visited[cell.r][cell.c+1])||
					  (cell.c-1 >= 0 && !visited[cell.r][cell.c-1])||
			        	(cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c])||
					  (cell.r-1 >= 0 && !visited[cell.r-1][cell.c])) {
					int rand = (int)(Math.random() * 5 + 1);
					System.out.println("Direction: " + rand + " x: " + cell.c + " y: " + cell.r);
					System.out.println("--------------------------------");
					// check up
					if (rand == 1 && cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c]) {
						// remove the upper wall
						maze.map[cell.r][cell.c].wall[NORTH].present = false;
						Cell nxCell = new Cell(cell.r+1, cell.c);
						visited[cell.r+1][cell.c] = true;
						q.add(nxCell);
						visits++;
						cell = nxCell;
					} 
					// check down  
					if (rand == 2 && cell.r-1 >= 0 && !visited[cell.r-1][cell.c]) {
						// remove the wall below
						maze.map[cell.r][cell.c].wall[SOUTH].present = false;
						Cell nxCell = new Cell(cell.r-1, cell.c);
						visited[cell.r-1][cell.c] = true;
						q.add(nxCell);
						visits++;
						cell = nxCell;
					}
					// check left
					if (rand == 3 && cell.c-1 >= 0 && !visited[cell.r][cell.c-1]) {
						// remove the west wall
						maze.map[cell.r][cell.c].wall[WEST].present = false;
						Cell nxCell = new Cell(cell.r, cell.c-1);
						visited[cell.r][cell.c-1] = true;
						q.add(nxCell);
						visits++;
						cell = nxCell; 
					}
					// check right
					if (rand == 4 && cell.c+1 < maze.sizeC && !visited[cell.r][cell.c+1]) {
						// removed the wall
						maze.map[cell.r][cell.c].wall[EAST].present = false;
						Cell nxCell = new Cell(cell.r, cell.c+1);
						visited[cell.r][cell.c+1] = true;
						q.add(nxCell);
						visits++;
						cell = nxCell;
					}
					// if has tunnel
					if (rand ==5 && cell.tunnelTo != null && !visited[cell.tunnelTo.r][cell.tunnelTo.c]) {
						//Cell nxCell = cell.tunnelTo;
					
							Cell nxCell = new Cell(cell.tunnelTo.r, cell.tunnelTo.c);
							visited[cell.tunnelTo.r][cell.tunnelTo.c] = true;
							q.add(nxCell);
							visits++;
							cell=nxCell;
						
					}
				}
				if (!q.isEmpty()) {
					cell = q.pop();
				}
			}
		}
	} // end of generateMaze()

	// recursive-backtracking algorithm implementation
//	private void carvePassagesFrom(int cx, int cy, Maze maze, int[][] grid, ArrayList stack, boolean isComplete, boolean hasVisitedCells, int count) {
//
//		if (stack.size() == 0 && hasVisitedCells) {
//			isComplete = true;
//		} else {
//			// shuffle possible directions
//			if (maze.type == NORMAL || maze.type == TUNNEL) {
//				Integer[] directions = new Integer[4];
//				directions[0] = new Integer(EAST);
//				directions[1] = new Integer(NORTH);
//				directions[2] = new Integer(WEST);
//				directions[3] = new Integer(SOUTH);
//				java.util.Collections.shuffle(java.util.Arrays.asList(directions));
//
//				for (int k = 0; k < 4; ++k) {
//					int dir = directions[k].intValue();
//					int nx = cx + deltaC[dir];
//					int ny = cy + deltaR[dir];
////					System.out.println(directions[k] + ",: " + (nx >= 0 && ny >= 0 && nx < maze.sizeC && ny < maze.sizeR && grid[ny][nx] == 0) + ", x: " + cx + ", y: " + cy + ", nx: " + nx + ", ny: " + ny);
//					if (!(nx >= 0 && ny >= 0 && nx < maze.sizeC && ny < maze.sizeR && grid[ny][nx] == 0))
//						System.out.println("count: " + count);
//					System.out.println("--------");
//
//					// if new cell is unvisited carve passage
//					if (nx >= 0 && nx < maze.sizeC && ny >=0 && ny < maze.sizeR && grid[ny][nx] == 0) {
//						count = 0;
//						grid[cy][cx] |= dir;
//						grid[ny][nx] |= oppoDir[dir];
//						maze.map[cy][cx].wall[dir].present = false;
//	//					System.out.println("wall is " + maze.map[ny][nx].wall[oppoDir[dir]].present);
//						// Push each cell to the stack
//						stack.add(new Cell(cy, cx));
//						hasVisitedCells = true;
//						//recursively calling the method until one is found or stack is empty
//						carvePassagesFrom(nx, ny, maze, grid, stack, isComplete, hasVisitedCells, count);
//					} else {
//						count++;
//					}
//				}
//				if (count == 4) {
//					//this cell is a deadend, pop a stack cell and try it
//					Cell top = (Cell) stack.remove(stack.size() - 1);
//					int nx = top.c;
//					int ny = top.r;
//					count = 0;
//					//recursively calling the method until one is found or stack is empty
//					carvePassagesFrom(nx, ny, maze, grid, stack, isComplete, hasVisitedCells, count);
//				}
//			} else if (maze.type == HEX) {
//				Integer[] directions = new Integer[NUM_DIR];
//				for (int i = 0; i < NUM_DIR; ++i) {
//					directions[i] = new Integer(i);
//				}
//				java.util.Collections.shuffle(java.util.Arrays.asList(directions));
//
//				for (int k = 0; k < NUM_DIR; ++k) {
//					int dir = directions[k].intValue();
//					int nx = cx + deltaC[dir];
//					int ny = cy + deltaR[dir];
//					System.out.println(directions[k] + ",: " + (nx >= 0 && ny >= 0 && nx < maze.sizeC && ny < maze.sizeR && grid[ny][nx] == 0) + ", x: " + cx + ", y: " + cy + ", nx: " + nx + ", ny: " + ny);
//					System.out.println("--------");
//
//					// if new cell is unvisited carve passage
//					if (nx >= 0 && nx < maze.sizeC && ny >=0 && ny < maze.sizeR && grid[ny][nx] == 0) {
//						grid[cy][cx] |= dir;
//						grid[ny][nx] |= oppoDir[dir];
//						carvePassagesFrom(ny, nx, maze, grid, stack, isComplete, hasVisitedCells, count);
//					}
//				}
//			} else {
//				System.out.println("Unknown maze type.");
//			}
//		}
//	}

//	private int returnUnvisitedNeighbour(Maze maze, ArrayList stack, boolean isComplete, boolean hasVisitedCells, int cx, int cy) {
//		int[][] grid = new int[maze.sizeR][maze.sizeC];
//
//		// Save the number of times facing wall (maximum 4 for normal and tunnel maze, 6 for hex maze)
//		// to check if the cell is a deadend
//		int count = 0;
//
//		if (stack.size() == 0 && hasVisitedCells) {
//			isComplete = true;
//		} else {
//			// shuffle possible directions
//			if (maze.type == NORMAL || maze.type == TUNNEL) {
//				Integer[] directions = new Integer[4];
//				directions[0] = new Integer(EAST);
//				directions[1] = new Integer(NORTH);
//				directions[2] = new Integer(WEST);
//				directions[3] = new Integer(SOUTH);
//				java.util.Collections.shuffle(java.util.Arrays.asList(directions));
//
//				for (int k = 0; k < 4; ++k) {
//					int dir = directions[k].intValue();
//					int nx = cx + deltaC[dir];
//					int ny = cy + deltaR[dir];
//					//				System.out.println(directions[k] + ",: " + (nx >= 0 && ny >= 0 && nx < maze.sizeC && ny < maze.sizeR && grid[ny][nx] == 0) + ", x: " + cx + ", y: " + cy + ", nx: " + nx + ", ny: " + ny);
//					//				System.out.println("--------");
//
//					// if the new cell is unvisited, return its direction
//					if (nx >= 0 && nx < maze.sizeC && ny >= 0 && ny < maze.sizeR && grid[ny][nx] == 0) {
//						count = 0;
//					} else {
//						count++;
//					}
//					if (count >= 4) {
//						//this cell is a deadend, pop a stack cell and try it
//						Cell stackTop = (Cell) stack.remove(stack.size() - 1);
//						cx = stackTop.c;
//						cy = stackTop.r;
//
//						//recursively calling the method until one is found or stack is empty
//						returnUnvisitedNeighbour(maze, stack, isComplete, hasVisitedCells, cx, cy);
//					}
//				}
//			}
//		}
//	}
} // end of class RecursiveBacktrackerGenerator