package mazeGenerator;

import java.util.ArrayList;
import java.util.*;
import maze.Maze;
import maze.Cell;


public class ModifiedPrimsGenerator implements MazeGenerator {
//	// parameters for saving states for wall digging
//	public final static int ISENTRANCE = 0;
//	public final static int HAS_UNVISITED_NEIGHBOUR = 1;
//	public final static int NO_UNVISITED_NEIGBOUR = 2;
//	private int cellType = ISENTRANCE;

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		Random random = new Random();

		// add start cell
//		Cell randCell = maze.map[random.nextInt(maze.sizeR)][random.nextInt(maze.sizeC)];
		ArrayList<Cell> C = new ArrayList<>();

		C.add(maze.entrance); //add to cell
		maze.entrance.isVisited = true;
		System.out.println("Cell Location is : " + maze.entrance.c + ", " + maze.entrance.r);

		//available neighbor of cell
		ArrayList<Cell> temp = new ArrayList<>();
		//create frontier to store all neighbors
		ArrayList<Cell> frontier = new ArrayList<>();

		//store start cell
		for (int i = 0; i < 6; i++) {
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
			for (int i = 0; i < 6; i++) {
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


//				if (cellFromAvailableNeighbors != null && cellFromAvailableNeighbors.neigh[i] != null && cellFromAvailableNeighbors.neigh[i].isVisited) {
//					System.out.println("i: " + i);
//					int m += i;
//
//					// vertical move
//					if (i == 2 || (i == 3 &&)) {
//						if (neighborF.r > cellFromAvailableNeighbors.r) {
//							neighborF.wall[SOUTH].present = false;
//							cellFromAvailableNeighbors.wall[NORTH].present = false;
//						} else if (neighborF.r < cellFromAvailableNeighbors.r) {
//							neighborF.wall[NORTH].present = false;
//							cellFromAvailableNeighbors.wall[SOUTH].present = false;
//						}
//					}
//					// horizontal move
//					if (i == 5 || i && neighborF.c < cellFromAvailableNeighbors.c) {
//						neighborF.wall[EAST].present = false;
//						cellFromAvailableNeighbors.wall[WEST].present = false;
//					} else if (i == 5 && neighborF.c > cellFromAvailableNeighbors.c) {
//						neighborF.wall[WEST].present = false;
//						cellFromAvailableNeighbors.wall[EAST].present = false;
//					}
//
//				}
//				if (cellFromAvailableNeighbors == null) {
//
//				}
//			}

//				for (int j = 0; j < 4; j++) {
//					if (cellFromAvailableNeighbors.neigh[i].isVisited) {
//						System.out.print("i: " + i + "                   ");
//						System.out.println("neighborF.wall[i] + cellFromAvailableNeighbors.wall[j]: " + (Math.abs(i - j)));
//						System.out.println("neighborF.wall[i] != null: " + (neighborF.wall[i] != null));
//						System.out.println("----------------------------------------------------");

//						neighborF.wall[i].present = false;
//						cellFromAvailableNeighbors.wall[j].present = false;
//						break;
//					}
//				}
//			}

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

			for (int i = 0; i < 6; i++) {
				if (neighborF.neigh[i] != null) {
					if (!neighborF.neigh[i].isVisited && (!frontier.contains(neighborF.neigh[i]))) {
						frontier.add(neighborF.neigh[i]);
					}
				}
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	} //end of generateMaze()
} // end of class ModifiedPrimsGeneratortor
