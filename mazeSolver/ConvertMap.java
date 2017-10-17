package mazeSolver;

import maze.Maze;

/**
 * Convert each cell of the original maze to 3 * 3 array for normal maze
 * Also will convert the hex maze map
 */
public class ConvertMap {

    /**
     * constants which are common to any type of maze
     */
    // types of maze;
    public final static int NORMAL = 0;
    public final static int TUNNEL = 1;
    public final static int HEX = 2;
    //	directions used for indices
    public final static int EAST = 0;
    public final static int NORTHEAST = 1;
    public final static int NORTHWEST = 2;
    public final static int NORTH = 2;
    public final static int WEST = 3;
    public final static int SOUTHWEST = 4;
    public final static int SOUTHEAST = 5;
    public final static int SOUTH = 5;
    public final static int NUM_DIR = 6;
    // used for move along a deriction, for both square and hexagon
    // e.g., the northeast neighbor of map[r][c] would be map[r + deltaR[NORTHEAST][c + deltaC[NORTHEAST]]
    public final static int deltaR[] = { 0, 1, 1, 0, -1, -1 };
    public final static int deltaC[] = { 1, 1, 0, -1, -1, 0 };
    public final static int oppoDir[] = { 3, 4, 5, 0, 1, 2 };

    // modified map
//    private char[][] byteMap;

    protected char[][] convert(Maze maze, char[][] byteMap) {
        if (maze.type == NORMAL) {
//            byteMap = new char[maze.sizeC*3][maze.sizeR*3];
            initializeByteMap(maze, byteMap);
        } else {    // hex maze
            for (int x = 0; x < 3*maze.sizeC; ++x) {
                for (int y = 0; y < 3*maze.sizeR; ++y) {
                    byteMap[x][y] = 's';
                }
            }
        }
        return byteMap;
    }

    private void initializeByteMap(Maze maze, char[][] byteMap) {
        // initialize byteMap as blank
        for (int x = 0; x < 3*maze.sizeC; ++x) {
            for (int y =0; y < 3*maze.sizeR; ++y) {
                byteMap[x][y] = 'm';
            }
        }

//        for (int x = 0; x < maze.sizeC; ++x) {
//            for (int y = 0; y < maze.sizeR; ++y) {
//                for (int i = 0; i < NUM_DIR; ++i) {
//                    if (maze.map[y][x].wall[i] != null && !maze.map[y][x].wall[i].present) {
//                        if (i == 0) {
//                            byteMap[1+3*x + 1][1+3*y] = 'p';
//                        }
//                        if (i == 2) {
//                            byteMap[1+3*x][1+3*y + 1] = 'p';
//                        }
//                        if (i == 3) {
//                            byteMap[1+3*x - 1][1+3*y] = 'p';
//                        }
//                        if (i == 5) {
//                            byteMap[1+3*x][1+3*y - 1] = 'p';
//                        }
//                    }
//                }
//            }
//        }

        // initialize byteMap as blank


//        for (int x = 0; x < 3*maze.sizeC; ++x) {
//            for (int y =0; y < 3*maze.sizeR; ++y) {
//                byteMap[x][y] = ' ';
//            }
//        }
//
//        for (int y = 0; y < 3*maze.sizeR; ++y) {
//            byteMap[0][y] = 'w';
//            byteMap[3*maze.sizeC - 1][y] = 'w';
//        }
//
//        for (int x = 0; x < 3*maze.sizeC; ++x) {
//            byteMap[x][0] = 'w';
//            byteMap[x][3*maze.sizeR - 1] = 'w';
//        }

//        for (int x = 1; x < maze.sizeC-1; ++x) {
//            for (int y = 1; y < maze.sizeR-1; ++y) {
////                // cell is always empty
////                byteMap[1 + 3 * x][1 + 3 * y] = ' ';
//                // case entrance
//                if (1 + 3 * x == maze.entrance.c && 1 + 3 * y == maze.entrance.r) {
//                    byteMap[1 + 3 * x][1 + 3 * y] = 's';
//                }
//                // case exit
//                if (1 + 3 * x == maze.exit.c && 1 + 3 * y == maze.exit.r) {
//                    byteMap[1 + 3 * x][1 + 3 * y] = 'e';
//                }
////                // set all four corners as walls
////                byteMap[(1+3*x)-1][(1+3*y)-1] = byteMap[(1+3*x)-1][(1+3*y)+1] =
////                        byteMap[(1+3*x)+1][(1+3*y)-1] = byteMap[(1+3*x)+1][(1+3*y)+1] = 'w';
////                // read and set the walls
////                for (int i = 0; i < NUM_DIR; ++i) {
////                    if (i == 1)
////                        continue;
////                    if (i == 4)
////                        continue;
////                    if (maze.map[y][x].wall[i].present) {
////                        if (i == 0) { byteMap[x+1][y] = 'w'; }
////                        if (i == 2) { byteMap[x][y+1] = 'w'; }
////                        if (i == 3) { byteMap[x-1][y] = 'w'; }
////                        if (i == 5) { byteMap[x][y-1] = 'w'; }
////                    }
////                }
//            }
//        }
    }
}