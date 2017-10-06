package mazeGenerator;

import java.util.*;

import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
	// Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"
	
	double threshold = 0.1;

	int[][] grid;
	
	@Override
	public void generateMaze(Maze maze) {
		ArrayList cells = new ArrayList();
		grid = new int[maze.sizeR][maze.sizeC];
		// initialize the grid
		for (int i = 0; i < maze.sizeC; ++i) {
			for (int j = 0; j < maze.sizeR; ++j) {
				grid[j][i] = 0;
			}
		}

		// Add entrance to the cell list
//		System.out.println("The entrance of maze is:" + maze.entrance.r + ", " + maze.entrance.c);
		cells.add(maze.entrance);

		// start of growing tree algorithm

		for (int m = 0; m < 20; ++m) {

			if (cells.size() > 0) {
				// backtrack to newest cell
				int index = cells.size() - 1;
				Cell cell = (Cell) cells.get(index);
				int x = cell.c;
				int y = cell.r;

				// shuffle possible directions
//			System.out.println("The maze type is:" + maze.type);
				if (maze.type == NORMAL || maze.type == TUNNEL) {
					Integer[] directions = new Integer[4];
					directions[0] = new Integer(EAST);
					directions[1] = new Integer(NORTH);
					directions[2] = new Integer(WEST);
					directions[3] = new Integer(SOUTH);
					java.util.Collections.shuffle(java.util.Arrays.asList(directions));

					digPass(4, x, y, maze, index, cells, directions);
				} else if (maze.type == HEX) {
					Integer[] directions = new Integer[NUM_DIR];
					for (int i = 0; i < NUM_DIR; ++i) {
						directions[i] = new Integer(i);
					}
					java.util.Collections.shuffle(java.util.Arrays.asList(directions));

					digPass(6, x, y, maze, index, cells, directions);
				} else {
					System.out.println("Unknown maze type.");
				}
			}
		}
	}

	private void digPass(int dirNum, int x, int y, Maze maze, int index, ArrayList cells, Integer[] directions) {
		// Try remove a wall of a random direction
		for (int k = 0; k < dirNum; ++k) {
			int dir = directions[k].intValue();
			int nx = x + deltaC[dir];
			int ny = y + deltaR[dir];
			System.out.println(directions[k] + ",: " + (nx >= 0 && ny >= 0 && nx < maze.sizeC && ny < maze.sizeR && grid[ny][nx] == 0) + ", x: " + x + ", y: " + y + ", nx: " + nx + ", ny: " + ny);
			System.out.println("--------");

			// if new cell is unvisited carve passage
			if (nx >= 0 && ny >= 0 && nx < maze.sizeC && ny < maze.sizeR && grid[ny][nx] == 0) {
//				System.out.println("Before: grid[y][x]: " + grid[y][x] + ", dir: " + dir);
				grid[y][x] |= dir;
//				System.out.println("After: grid[y][x]: " + grid[y][x]);
//				System.out.println("Before: grid[ny][nx]: " + grid[ny][nx] + ", oppoDir: " + oppoDir[dir]);
				grid[ny][nx] |= oppoDir[dir];
//				System.out.println("After: grid[ny][nx]: " + grid[ny][nx]);
				Cell currCell = new Cell(ny, nx);
				cells.add(currCell);
//				maze.drawFtPrt(currCell);
				index = -1;
				break;
			}
		}

		// dead end: remove cell from list
		if (index != -1) {
			cells.remove(index);
		}
	}
}
