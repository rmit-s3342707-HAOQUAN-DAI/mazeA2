package maze;

/**
 * Class of wall object used in Cell objects.
 * 
 * @author Youhan Xia
 * @author Jeffrey Chan
 * 
 * The solvers require that same wall object are shared by two cell objects next to the wall.
 */
public class Wall {
	/**
	 * True if the wall is present in the maze. Otherwise false.
	 */
	public boolean present = true;
	
	/**
	 * Used only for visualization functions.
	 */
	public boolean drawn = false;
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
	public Wall(final int row, final int col, final String type) {
		myRow = row;
		myCol = col;
		myType = type;
	}
} // end of class Wall
