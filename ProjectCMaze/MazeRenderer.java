/**
 * Render a Maze object.
 * 
 * @author TK (original), JS (revised), SM (revised)
 *
 * THIS CLASS IS COMPLETE; STUDENT SHOULD NOT MODIFY IT.
 */

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class to create a window representing a maze.
 */
public class MazeRenderer extends JFrame {
   
   /** serial versioning number to make checkstyle happy. */
   private static final long serialVersionUID = 1L;

   /** the initial length and width for this JFrame. */
   private static final int INIT_SIZE = 500;


   /** the maze this window will represent. */
   private Maze maze;
   /** the frame into which we will place tiles to represent cells. */
   private GridLayout grid; 

   /**
    * Constructor which makes the basic window to hold a maze.
    * @param theMaze the maze this window will hold
    */    
   public MazeRenderer(Maze theMaze) {
      super("Maze Renderer");
      this.init(theMaze);
   }

   /**
    * Constructor which makes the basic window to hold a maze, but allows
    * a custom pane subtitle.
    * @param theMaze the maze this window will hold
    * @param customSubtitle extra information to be displayed in pane title
    */    
   public MazeRenderer(Maze theMaze, String customSubtitle) {
      super("Maze Renderer (" + customSubtitle + ")");
      this.init(theMaze);
   }

   /**
    * Perform initialization steps for window which will display maze.
    * This method is called by two constructors.
    * @param theMaze the maze this window will hold
    */
   public void init(Maze theMaze) {
      final int scale = 50;
      this.maze = theMaze;
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(this.maze.getNumCols() * scale, 
                  this.maze.getNumRows() * scale);
      this.grid = new GridLayout(this.maze.getNumRows(), 
                           this.maze.getNumCols());
      this.setLayout(this.grid);
   }

   /**
    * Draw lines onto window and make this item visible.
    */
   public void createAndShowGUI() {
      for (int row = 0; row < this.maze.getNumRows(); row++) {
         for (int col = 0; col < this.maze.getNumCols(); col++) {
            this.add(new MyJLabel(this.maze.getCellAt(row, col)));
         }
      }
      this.setVisible(true);
   }


   /**
    * Class defining the label objects that are placed into grid.
    */
   private class MyJLabel extends JLabel {
   
      /** serial versioning number to make checkstyle happy. */
      private static final long serialVersionUID = 1L;
   
      /** The maze cell to be drawn. */
      private Cell cell;
   
      /** Construct a new JLabel that will hold a single cell. 
       * @param c the cell to draw
       */
      MyJLabel(Cell c) {
         super(c.getData().toString(), CENTER);
         this.cell = c;
      }
   
      /* Graphics component x,y coordinates:
   
      0,0       ...   0,width
   
                ...
   
      0,height  ...  width,height
   
      */
   
      @Override
      public void paintComponent(Graphics g) {
         final int weight = 5;
         super.paintComponent(g);
      
         // make cell outline
         g.setColor(Color.LIGHT_GRAY);
         g.drawRect(0, 0, getWidth(), getHeight());
      
         // make black walls where needed
         g.setColor(Color.BLACK);
         if (this.cell.hasNorth()) {
            g.fillRect(0, 0, getWidth(), 0 + weight);
         }
         if (this.cell.hasSouth()) {
            g.fillRect(0, getHeight() - weight, getWidth(), getHeight());
         }
         if (this.cell.hasEast()) {
            g.fillRect(getWidth() - weight, 0, getWidth(), getHeight());
         }
         if (this.cell.hasWest()) {
            g.fillRect(0, 0, 0 + weight, getHeight());
         }
         
      }
   }
}
