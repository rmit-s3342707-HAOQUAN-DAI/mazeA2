package mazeGenerator;

import java.util.ArrayList;
import java.util.Random;
import maze.Maze;
import maze.Cell;


public class ModifiedPrimsGenerator implements MazeGenerator {
	private Cell[][] myCells;
	private boolean[][] horiz_walls_removed;
	private boolean[][] vert_walls_removed;
	private boolean debug_out;
	private String myNewLine = System.getProperty("line.separator");
	
	
	@Override
	public void generateMaze(Maze maze) {
		
		    ArrayList<WallPiece> wall_list = new ArrayList<WallPiece>();
		    myCells = new Cell[maze.sizeR][maze.sizeC];
	        int total_cells = maze.sizeR * maze.sizeC;
	        //theres' one extra row of X's at the bottom
	        horiz_walls_removed = new boolean[maze.sizeR + 1][maze.sizeC];
	        //there's one extra column of X's at the far right 
	        vert_walls_removed = new boolean[maze.sizeR][maze.sizeC + 1];
	        Random rn = new Random();
	        
	        //remove the entrance and exit walls right away
	        //horiz_walls_removed[0][0] = true;
	        horiz_walls_removed[maze.entrance.r][maze.entrance.c] = true;
	        //horiz_walls_removed[maze.sizeC][maze.sizeR - 1] = true;
	        horiz_walls_removed[maze.exit.r][maze.exit.c] = true;
	        //add all the cells the cell array
	        for (int r = 0; r < maze.sizeR; r++) {
	            for(int c = 0; c < maze.sizeC; c++) {
	                final Cell temp = new Cell(r, c);
	                myCells[r][c] = temp;
	            }
	        }
	        final int init_row = rn.nextInt(maze.sizeR);
	        final int init_col = rn.nextInt(maze.sizeC);
	        Cell current_cell = myCells[init_row][init_col];
	        
	        //add the starting random cell and place its 4 wall markers
	        //in the list (if not maze boundary walls).
	        current_cell.added = true;
	        //check left wall
	        if(init_col > 0) {
	            wall_list.add(new WallPiece(init_row, init_col, WallPiece.VERTICAL_LEFT));
	        }
	        //check right wall
	        if(init_col < maze.sizeR - 1) {
	            wall_list.add(new WallPiece(init_row, init_col, WallPiece.VERTICAL_RIGHT));
	        }
	        //check north wall
	        if(init_row > 0) {
	            wall_list.add(new WallPiece(init_row, init_col, WallPiece.HORIZONTAL_TOP));
	        }
	        //check south wall
	        if(init_row < maze.sizeC - 1) {
	            wall_list.add(new WallPiece(init_row, init_col, WallPiece.HORIZONTAL_BOTTOM));
	        }
	        int cells_finished = 1;
	        while (!wall_list.isEmpty() && cells_finished <= total_cells) {
	        //for (int i=1;i<5;i++){
	            //if (debug_out) display();
	            //fetch a random wall marker from the wall list
	            final WallPiece rand_wall = wall_list.remove(rn.nextInt(wall_list.size()));
	            //get coordinates and type of wall piece
	            final int wall_row = rand_wall.myRow;
	            final int wall_col = rand_wall.myCol;
	            final String wall_type = rand_wall.myType;            
	            if (wall_type == WallPiece.HORIZONTAL_TOP
	                    && !myCells[wall_row - 1][wall_col].added) {
	                //is the cell north of the current cell in the maze?
	                current_cell = myCells[wall_row - 1][wall_col];
	                current_cell.added = true;
	                //now remove the wall from the wall marker array
	                horiz_walls_removed[wall_row][wall_col] = true;                    
	            } else if (wall_type == WallPiece.HORIZONTAL_BOTTOM
	                    && !myCells[wall_row + 1][wall_col].added) {
	                //check if the cell south of the current cell is in the maze
	                current_cell = myCells[wall_row + 1][wall_col];
	                current_cell.added = true;
	                //now remove the wall
	                horiz_walls_removed[wall_row + 1][wall_col] = true;                    
	            } else if (wall_type == WallPiece.VERTICAL_LEFT
	                    && !myCells[wall_row][wall_col - 1].added) {
	                //check east
	                current_cell = myCells[wall_row][wall_col - 1];
	                current_cell.added = true;
	                //now remove the wall
	                vert_walls_removed[wall_row][wall_col] = true;                    
	            } else if (wall_type == WallPiece.VERTICAL_RIGHT
	                    && !myCells[wall_row][wall_col + 1].added) {
	                //check west
	                current_cell = myCells[wall_row][wall_col + 1];
	                current_cell.added = true;
	                //now remove the wall
	                vert_walls_removed[wall_row][wall_col + 1] = true;                    
	            }
	            
	            //now add the new current cell's walls to the list
	            //cells_finished++;
	            final int current_row = current_cell.r;
	            final int current_col = current_cell.c;
	            //check if the left wall of cell is not left wall of maze and
	            //that is is not already removed
	            if (current_col > 0 && !myCells[current_row][current_col - 1].added) {
	                wall_list.add(new WallPiece(current_row, current_col,
	                                            WallPiece.VERTICAL_LEFT));
	            }
	            //check right wall
	            if (current_col < maze.sizeR - 1 && !myCells[current_row][current_col + 1].added) {
	                wall_list.add(new WallPiece(current_row, current_col,
	                                            WallPiece.VERTICAL_RIGHT));
	            }
	            //check north wall
	            if (current_row > 0 && !myCells[current_row - 1][current_col].added) {
	                wall_list.add(new WallPiece(current_row, current_col,
	                                            WallPiece.HORIZONTAL_TOP));
	            }
	            //check south wall
	            if (current_row < maze.sizeC - 1 && !myCells[current_row + 1][current_col].added) {
	                wall_list.add(new WallPiece(current_row, current_col,
	                                            WallPiece.HORIZONTAL_BOTTOM));
	            }   
	        }
	} // end of generateMaze()
	
	private class WallPiece{
        /**
         * Wall piece for the west side of a cell.
         */
        public static final String VERTICAL_LEFT = "VL";
        /**
         * Wall piece the east side of a cell.
         */
        public static final String VERTICAL_RIGHT = "VR";
        /**
         * Wall piece for the north side of a cell.
         */
        public static final String HORIZONTAL_TOP = "HT";
        /**
         * Wall piece for the south side of a cell.
         */
        public static final String HORIZONTAL_BOTTOM = "HB";
        /**
         * Row of the cell this wall is associated with.
         */
        public int myRow;
        /**
         * Column of the cell this wall is associated with.
         */
        public int myCol;
        /**
         * The wall type of this wall (VERTICAL_RIGHT, ...etc).
         */
        public String myType;
        /**
         * Constructs a new wall piece. Big-Oh(1).
         * @param row The row of the cell associated with this wall piece.
         * @param col The column of the cell associated with this wall piece.
         * @param type The wall piece type of this wall piece (should be
         * one of the class constants).
         */
        public WallPiece(final int row, final int col, final String type) {
            myRow = row;
            myCol = col;
            myType = type;
        }
    }
	
//    private class Cell{
//        /**
//         * The row of this cell.
//         */
//        public int myRow;
//        /**
//         * The column of this cell.
//         */
//        public int myCol;
//        /**
//         * Flags whether this cell has been added to the maze.
//         */
//        public boolean added;
//        /**
//         * Construct a new cell. Big-Oh(1).
//         * @param row the row of this cell.
//         * @param col the column of this cell.
//         */
//        public Cell(final int row, final int col) {
//            myRow = row;
//            myCol = col;
//        }
//    }
    
    
//    public void display() {
//        final StringBuilder sb = new StringBuilder();
//        for (int row = 0; row < maze.sizeR; row++) {
//            //print walls above the row of cell
//            for (int col = 0; col < maze.sizeC; col++) {
//                if (!horiz_walls_removed[row][col]) {
//                    sb.append("X X ");
//                } else {
//                    sb.append("X   ");
//                }
//            }
//            //now put last X on the far right wall
//            sb.append("X" + myNewLine);
//            //now print the vertical walls for the
//            //same row of cells as above
//            for (int col = 0; col < maze.sizeC; col++) {
//                if (!vert_walls_removed[row][col]) {
//                    if (myCells[row][col].added && debug_out) {
//                        sb.append("X V ");                        
//                    } else {
//                        sb.append("X   ");
//                    }
//
//                } else {
//                    if (myCells[row][col].added && debug_out) {                        
//                        sb.append("  V ");
//                    } else {
//                        sb.append("    ");
//                    }
//                }
//            }
//            //now put last X on the far right wall
//            sb.append("X" + myNewLine);
//            
//        }
//        //when all rows are done need to print the bottom row of
//        //horizontal walls
//        for (int col = 0; col < maze.sizeC; col++) {
//            if (!horiz_walls_removed[maze.sizeR][col]) {
//                sb.append("X X ");
//            } else {
//                sb.append("X   ");
//            }
//        }
//        //now put last X on the far right wall
//        sb.append("X" + myNewLine);
//        System.out.println(sb);
//    }
//    

} // end of class ModifiedPrimsGeneratortor
