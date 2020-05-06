import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Implement a basic maze and solve it with recursion.
 * @author Daniel Weber JHED: dweber11
 * Date: 01/20/2020
 * Summer 2019
 */
public class Maze {

   /** Default maze size (rows and columns), set to 8. */
   private static final int SIZE = 8;

   /** The grid of cells. */
   private Cell[][] grid;

   /** How many rows are in the maze. */
   private int rows;

   /** How many columns are in the maze. */
   private int cols;
   /** Killswitch to end the redursion. */
   private boolean killSwitch = false;


   /** Create the internal structure of a maze of a default size. */
   public Maze() {
      init(SIZE, SIZE);
   }

   /**
    * Create the internal structure a maze of a specified size. 
    * @param r the desired number of rows in the maze
    * @param c the desired number of columns in the maze 
    */
   public Maze(int r, int c) {
      init(r, c);
   }


   /** 
    * Initialize the internal structure for a maze of a specific size.
    * This is a helper method which is called from all class constructors. 
    * @param r the number of rows
    * @param c the number of columns 
    */
   private void init(int r, int c) {
      this.grid = new Cell[r][c];
      this.rows = r;
      this.cols = c; //TO DO: REPLACE THIS STUB
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.cols; j++) {
            this.grid[i][j] = new Cell();
         }
      }
   }


   /**
    * Create and return one (long) string that
    * contains the row and column dimensions of the maze, then a
    * newline, followed by the string representation of each cell,
    * one row at a time, with each cell separated from the next with
    * one space and each row separated from the next by a newline
    * ('\n').
    * @return the string representation
    */
   public String toString() {
      String stringRepr = this.rows + " " + this.cols + "\n";
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.cols; j++) {
            stringRepr.concat(this.grid[i][j].toString() + " ");
         }
         stringRepr.concat("\n");
      }
      return stringRepr;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Read a maze from a plain text file whose name is supplied as
    * a parameter to this method, and validate the mazes's wall structure.
    * This method assumes the specified file exists.
    * The first line in the text file must contain the number of rows and
    * columns, respectively. Each subsequent line provides the wall
    * information for the cells in a single row, using a 4-character 
    * string ("bit string") in NESW (north-east-south-west) order for
    * each cell. A 1 "bit" indicates the wall exists, a 0 "bit" (or any
    * character other than 1) means no wall.
    * @param s is the external name of the file to read
    * @return true if a valid maze is created, false otherwise
    * @throws FileNotFoundException if file does not exist
    */
   public boolean readMaze(String s) throws FileNotFoundException {
      FileInputStream mazeFile = new FileInputStream(s);
      Scanner mazeScnr = new Scanner(mazeFile);
      int mazeRows = mazeScnr.nextInt();
      int mazeCols = mazeScnr.nextInt();
      init(mazeRows, mazeCols);
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.cols; j++) {
            this.grid[i][j].setWalls(mazeScnr.next());
         }
      }
      boolean mazeValidity = validate();
      mazeScnr.close();
      return mazeValidity;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Validate the cells of a maze as being consistent with respect
    * to neighboring internal walls. For example, suppose some cell
    * C has an east wall. Then for the maze to be valid, the cell
    * to C's east must have a west wall. (This method does not consider
    * external walls.) This method does not check for solvability
    * of the maze.
    * @return true if valid, false otherwise
    */
   public boolean validate() {
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.cols; j++) {
            if (this.grid[i][j].hasNorth() && (i >= 1)) {
               if (!this.grid[i - 1][j].hasSouth()) {
                  return false;
               }
            }
            if (this.grid[i][j].hasSouth() && (i < this.rows - 1)) {
               if (!this.grid[i + 1][j].hasNorth()) {
                  return false;
               }
            }
            if (this.grid[i][j].hasWest() && (j >= 1)) {
               if (!this.grid[i][j - 1].hasEast()) {
                  return false;
               }
            }
            if (this.grid[i][j].hasEast() && (j < this.cols - 1)) {
               if (!this.grid[i][j + 1].hasWest()) {
                  return false;
               }
            }
         }
      }
      return true;  //TO DO: REPLACE THIS STUB
   }


   /**
    * Return the Cell object stored at the given (row, column) position.
    * This method assumes its arguments describe a legal position.
    * @param r the row position of the Cell in the Maze object
    * @param c the col position of the Cell in the Maze object
    * @return the Cell object that is at the specified position
    */
   public Cell getCellAt(int r, int c) {
      return this.grid[r][c];  //TO DO: REPLACE THIS STUB
   }


   /**
    * Set the contents of a Cell in a given (row, column) position.
    * This method assumes its arguments describe a legal position.
    * @param r the row position of the Cell in the Maze object
    * @param c the col position of the Cell in the Maze object
    * @param d the data String to store at the specified position
    * @return the former contents of the cell
    */
   public String setCellAt(int r, int c, String d) {
      String oldCellData = grid[r][c].getData();
      grid[r][c].setData(d);
      return oldCellData;  //TO DO: REPLACE THIS STUB
   }


   /**
    * Return the number of rows in the maze.
    * @return the number of rows in the maze
    */
   public int getNumRows() {
      return this.rows;  //TO DO: REPLACE THIS STUB
   }


   /**
    * Return the number of columns in the maze.
    * @return the number of columns in the maze
    */
   public int getNumCols() {
      return this.cols;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Solve the maze, assuming start in top left cell and finish
    * in bottom right cell. This method changes data values inside
    * explored cells, so that cells which are determined to be part
    * of the final path ("the solution") through the maze will now
    * contain the string P as their data, while cells which 
    * were explored but not selected as part of the solution path 
    * will now contain x as their data. If no complete solution path
    * in the maze exists, no cells' data will be permanently changed
    * to P, but many may now have x. 
    * @return true if solved, false if fails
    */
   public boolean solve() {
      if (solve(0, 0, this.rows - 1, this.cols - 1)) {
         return true;
      }
      return false;   //TO DO: REPLACE THIS STUB
   }
   
   /**
    * Solve the maze from a given starting point to ending cell.
    * This method changes data inside explored cells,
    * so that cells which are part of the final path through the
    * maze contain P as their data, while cells which were explored
    * but not selected as part of the solution path contain x as
    * their data. If no complete solution path in the maze exists, 
    * no cells' data will be permanently changed to P, but many may
    * now have x. 
    * @param srow the start row index
    * @param scol the start col index
    * @param erow the end row index
    * @param ecol the end col index
    * @return true if solved, false otherwise
    */
   public boolean solve(int srow, int scol, int erow, int ecol) {
      Cell currentCell = this.grid[srow][scol];
      if ((currentCell.getData().equals("P")) || 
          (currentCell.getData().equals("x"))) {
         return false;
      }
      currentCell.setData("P");
      if (currentCell.equals(this.grid[erow][ecol])) {
         killSwitch = true;
         return true;
      }
      if (srow != 0) {
         if ((!currentCell.hasNorth()) && !killSwitch) {
            solve(srow - 1, scol, erow, ecol);
         }
      }
      if ((scol != this.getNumCols() - 1) && !killSwitch) {
         if (!currentCell.hasEast()) {
            solve(srow, scol + 1, erow, ecol);
         }
      }
      if ((srow != this.getNumRows() - 1) && !killSwitch) {
         if (!currentCell.hasSouth()) {
            solve(srow + 1, scol, erow, ecol);
         }
      }
      if ((scol != 0)) {
         if (!currentCell.hasWest() && !killSwitch) {
            solve(srow, scol - 1, erow, ecol);
         }
      }
      if (!killSwitch) {
         currentCell.setData("x");
         return false;
      }
      else {
         return true;
      }
   }

}