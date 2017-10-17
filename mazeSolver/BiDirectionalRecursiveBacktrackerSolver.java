package mazeSolver;

import maze.Maze;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {
	// modified maze
	private char[][] grid;

	private int entranceX = 0;
	private int entranceY = 0;

	@Override
	public void solveMaze(Maze maze) {
		grid = new char[3 * maze.sizeC][3 * maze.sizeR];
		initializeGrid(maze);
		for (int i = 0; i < 3*maze.sizeC; ++i) {
			for (int j = 0; j < 3*maze.sizeR; ++j) {
				System.out.print(" " + grid[j][i]);
			}
			System.out.println();
		}
		entranceX = maze.entrance.c;
		entranceY = maze.entrance.r;
	} // end of solveMaze()

	// recursive backtracking algorithm
	private boolean solve(int y, int x) {
		// out of boundary
		if (y < 0 || x < 0) {
			return false;
		}

		// In case a path is found
		if (isExit(y, x)) {
			return true;
		}

		// In following cases, no path is found
		if (isEntrance(y, x) || isWall(y, x) || isVisited(y, x)) {
			return false;
		}

		// Mark the current cell as visited
		visit(y, x);

		// Recursively explore the neighbouring squares
		if (solve(y+1, x) || solve(y-1, x) || solve(y, x+1) || solve(y, x-1)) {
			return true;
		} else {
			failed(y, x);
		}

		return false;
	}

	// check if a cell is the entrance
	private boolean isEntrance(int y, int x) {
		return (grid[y][x] == 'e');
	}

	// check if a cell is the wall
	private boolean isWall(int y, int x) {
		return (grid[y][x] == 'w');
	}

	// check if the cell has already been visited
	private boolean isVisited(int y, int x) {
		return (grid[y][x] == 'v' || grid[y][x] == 'd');
	}

	// check if the exit is found
	private boolean isExit(int y, int x) {
		return (grid[y][x] == 'f');
	}

	// visit a cell
	private void visit(int y, int x) {
		grid[y][x] = 'v';
	}

	// mark a cell that leads to a dead end
	private void failed(int y, int x) {
		grid[y][x] = 'd';
	}

	/**
	 * Convert each cell of the original maze to 3 * 3 array for normal maze
	 * Also will convert the hex maze map
	 */
	private void initializeGrid(Maze maze) {
		// initialize byteMap as blank
		for (int x = 0; x < 3*maze.sizeC; ++x) {
			for (int y =0; y < 3*maze.sizeR; ++y) {
				grid[y][x] = 'w';
			}
		}

		for (int x = 0; x < maze.sizeC; ++x) {
            for (int y = 0; y < maze.sizeR; ++y) {
            	grid[1+3*y][1+3*x] = 'p';
                for (int i = 0; i < NUM_DIR; ++i) {
                    if (maze.map[y][x].wall[i] != null && !maze.map[y][x].wall[i].present) {
                        if (i == 0) {
                            grid[1+3*y][1+3*x + 1] = 'p';
                        }
                        if (i == 2) {
                            grid[1+3*y + 1][1+3*x] = 'p';
                        }
                        if (i == 3) {
                            grid[1+3*y][1+3*x - 1] = 'p';
                        }
                        if (i == 5) {
                            grid[1+3*y - 1][1+3*x] = 'p';
                        }
                    }
                }
				if (x == maze.entrance.c && y == maze.entrance.r) {
					grid[1+3*y][1+3*x] = 'e';
				}
				if (x == maze.exit.c && y == maze.exit.r) {
                	grid[1+3*y][1+3*x] = 'f';
				}
			}
        }
	}

	@Override
	public boolean isSolved() {
		return solve(entranceY, entranceX);
	} // end if isSolved()


	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		return 0;
	} // end of cellsExplored()

} // end of class BiDirectionalRecursiveBackTrackerSolver
