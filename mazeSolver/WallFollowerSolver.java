package mazeSolver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import maze.Cell;
import maze.Maze;

/**
 * Implements WallFollowerSolver
 */

public class WallFollowerSolver implements MazeSolver {
	
//https://github.com/briankcook/Maze/blob/master/mazemaker/maze/solvers/WallFollower.java

	
//https://github.com/green131/maze-solver/blob/master/src/src/MazeSolver.java
	
	int[][] solution;
	private int[][] visited; // track visited area in maze
	private int[][] location;  // track current position in maze
	
	
	@Override
	public void solveMaze(Maze maze) {
		// TODO Auto-generated method stub
		
	
	} // end of solveMaze()
	
	
	

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

} // end of class WallFollowerSolver

//------------------example 1 ends----------------------------


//-------------------------example 2-----------------------------------

//public class WallFollowerSolver implements MazeSolver {
//	
//	
//	
//	@Override
//	public void solveMaze(Maze maze) {
//		// TODO Auto-generated method stub
//        
//	} // end of solveMaze()
//    
//    
//	@Override
//	public boolean isSolved() {
//		// TODO Auto-generated method stub
//		return false;
//	} // end if isSolved()
//    
//    
//	@Override
//	public int cellsExplored() {
//		// TODO Auto-generated method stub
//		return 0;
//	} // end of cellsExplored()
//
//} // end of class WallFollowerSolver