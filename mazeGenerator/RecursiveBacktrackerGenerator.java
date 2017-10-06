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
		while(visits < visitedCount || !q.isEmpty()) {
			while((cell.c+1 < maze.sizeC && !visited[cell.r][cell.c+1])||
				  (cell.c-1 >= 0 && !visited[cell.r][cell.c-1])||
		        	(cell.r+1 < maze.sizeR && !visited[cell.r+1][cell.c])||
				  (cell.r-1 >= 0 && !visited[cell.r-1][cell.c])) {
				int rand = (int)(Math.random() * 4 + 1);
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
				if (maze.type == TUNNEL && cell.tunnelTo != null ) {
					Cell nxCell = cell.tunnelTo;
					if (!visited[nxCell.r][nxCell.c]) {
						visited[nxCell.r][nxCell.c] = true;
						q.add(nxCell);
						visits++;
						cell = nxCell;
					}
				}
			}
			if (!q.isEmpty()) {
				cell = q.pop();
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