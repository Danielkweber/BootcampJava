/**
 * The purpose of the code is to create a class 
 * to represent one tile of the maze.
 * Cell represents a single tile of a maze.
 *
 * STUDENT WILL NEED TO COMPLETE THIS CLASS AS INDICATED BELOW
 * @author Daniel Weber JHED: dweber11
 * Date: 01/19/2020
 */
public class Cell {

   /** this counter is used to number the cells in a maze; we give
       it package-level (not private or public) access on purpose. */
   static int count = 0;

   /** the number of the cell in the grid, counting left to right,
       starting at 0. */
   private int num;
   /** the data stored in the cell. */
   private String data;
   
   /** Boolean to store if cell has north wall. */
   private boolean northWall;
   /** Boolean to store if cell has south wall. */
   private boolean southWall;
   /** Boolean to store if cell has west wall. */
   private boolean westWall;
   /** Boolean to store if cell has east wall. */
   private boolean eastWall;


   
   /**
    * Construct a cell that has all four walls by default and
    * which is given a String value that matches the uniquely-
    * assigned number of the Cell.
    */
   public Cell() {
      this.num = count++;
      this.data = String.valueOf(this.num);
      this.northWall = true;
      this.southWall = true;
      this.westWall = true;
      this.eastWall = true;
   }
   
   /**
    * Cell constructor with defined wall parameters and
    * which is given a String value that matches the uniquely-
    * assigned number of the Cell.
    * @param n true if north side of the cell should have a wall
    * @param s true if south side of the cell should have a wall
    * @param w true if west side of the cell should have a wall
    * @param e true if east side of the cell should have a wall
    */
   public Cell(boolean n, boolean s, boolean e, boolean w) {
      this.num = count++;
      this.data = String.valueOf(this.num);
      this.northWall = n;
      this.southWall = s;
      this.westWall = w;
      this.eastWall = e;
   }
   
   

   /**
    * Use a "bit" string in NESW (north-east-south-west) order to 
    * represent and set the walls of this cell.  A 1 bit indicates 
    * that the wall exists, a 0 (or anything else) means no wall.
    * The given string is assumed to have length at least 4; any 
    * characters in it beyond the first four will be ignored.
    * @param walls the bit string to parse
    */
   public void setWalls(String walls) {
      boolean[] wallBooleans = new boolean[4];
      for (int i = 0; i < 4; i++) {
         if (walls.charAt(i) == '1') {
            wallBooleans[i] = true;
         }
      }
      this.northWall = wallBooleans[0];
      this.eastWall = wallBooleans[1];
      this.southWall = wallBooleans[2];
      this.westWall = wallBooleans[3];
   }

   /**
    * Get a "bit string" representation of this cell's walls, in 
    * NESW (north-east-south-west) order.
    * A 1 represents that a wall exists, and a 0 represents no wall.
    * For example, "1001" is returned when only the north and west
    * walls exist for a cell.
    * @return the 4-character "bit string"
    */
   public String toString() {
      String returnString = "0000";
      if (northWall) {
         returnString = "1" + returnString.substring(1); 
      }
      if (eastWall) {
         returnString = returnString.substring(0, 1) + "1" 
                        + returnString.substring(2);
      }
      if (southWall) {
         returnString = returnString.substring(0, 2) + "1" 
                        + returnString.substring(3);
      }
      if (westWall) {
         returnString = returnString.substring(0, 3) + "1";
      }
      return returnString;  
   }

   /**
    * Return whether this cell's north wall exists.
    * @return true if and only if the north wall exists
    */
   public boolean hasNorth() {
      return this.northWall;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's north wall should exist.
    * @param northVal  true if wall should exist; false otherwise
    */
   public void setNorth(boolean northVal) {
      this.northWall = northVal;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's south wall exists.
    * @return true if and only if the south wall exists
    */
   public boolean hasSouth() {
      return this.southWall;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's south wall should exist.
    * @param southVal  true if wall should exist; false otherwise
    */
   public void setSouth(boolean southVal) {
      this.southWall = southVal;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's west wall exists.
    * @return true if and only if the west wall exists
    */
   public boolean hasWest() {
      return this.westWall;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's west wall should exist.
    * @param westVal  true if wall should exist; false otherwise
    */
   public void setWest(boolean westVal) {
      this.westWall = westVal;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's east wall exists.
    * @return true if and only if the east wall exists
    */
   public boolean hasEast() {
      return this.eastWall;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's east wall should exist.
    * @param eastVal  true if wall should exist; false otherwise
    */
   public void setEast(boolean eastVal) {
      this.eastWall = eastVal;  //TO DO: REPLACE THIS STUB
   }

   /** 
    * Return the String contents of this cell.
    * @return the data value
    */
   public String getData() {
      return this.data;  //TO DO: REPLACE THIS STUB
   }

   /** 
    * Set the String contents of this cell.
    * @param contents the cell's new data
    * @return the original contents
    */
   public String setData(String contents) {
      String oldData = this.data;
      this.data = contents;
      return oldData;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Get the cell number in the grid (row x column order).
    * @return the number
    */
   public int getNumber() {
      return this.num;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Check if two cells are the same in a grid, based on number.
    * @param other the other cell to compare to this
    * @return true if same, false otherwise.
    */
   public boolean equals(Cell other) {
      if (this.num == other.getNumber()) {
         return true;
      }
      return false;
   }
   
   /**
    * Check if an Object is same as this cell, based on number. If
    * the Object is not a cell, then return false.
    * THIS METHOD IS COMPLETE; STUDENT SHOULD NOT MODIFY IT.
    * @param other the other cell to compare to this
    * @return true if same, false otherwise.
    */
   public boolean equals(Object other) {
      if (!(other instanceof Cell)) {
         return false;
      }
      // now other must be a cell, so we can call the other equals method
      return this.equals((Cell) other);
   }

}
