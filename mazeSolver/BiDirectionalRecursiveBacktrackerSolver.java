package mazeSolver;

import maze.Maze;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {
	// modified maze
	int[][] grid;

	@Override
	public void solveMaze(Maze maze) {
		grid = int[maze.sizeC * 3][maze.R * 3];
		initializeGrid(maze);


	} // end of solveMaze()

	// recursive backtracking algorithm
	private boolean solve(int y, int x) {
		// out of boundary
		if (y < 0 || x < 0) {
			return false;
		}

		// In case a path is found
		if (isExit(y, x) {
			return true;
		}

		// In following cases, no path is found
		if (isEntrance(y, x) || isWall(y, x) || isVisited{y, x} {
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
		return (y == maze.entrance.r && x == maze.entrance.c);
	}

	// check if a cell is the wall
	private boolean isWall(int y, int x) {

	}

	private void initializeGrid(Maze maze) {
		for (int x = 0; x < maze.c; ++x) {
			for (int y = 0; y < maze.r; ++y) {
				grid[x][y] = maze.map[y][x];
			}

		}
	}

	@Override
	public boolean isSolved() {
		// TODO Auto-generated method stub
		return false;
	} // end if isSolved()


	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		return 0;
	} // end of cellsExplored()

} // end of class BiDirectionalRecursiveBackTrackerSolver