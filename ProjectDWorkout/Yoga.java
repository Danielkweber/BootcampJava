/**
 * Class to store details of a yoga workout.
 * @author Daniel Weber JHED: dweber11
 * Date: 01/22/2020
 */
public class Yoga extends Workout {
   /** Stores yoga workout intensity from 1-3. */
   private int workoutLevel = 1;

   /**
    * Constructor for Yoga class with 3 params.
    * @param client Client ID
    * @param duration Length in mins
    * @param level Intensity from 1-3
    */
   public Yoga(String client, int duration, int level) {
      super(client, duration);
      this.workoutLevel = level;
   }

   /**
    * Constructor for Yoga class with 2 params, client and duration.
    * @param client Client ID
    * @param duration Length in mins
    */
   public Yoga(String client, int duration) {
      super(client, duration);
   }

   /**
    * Constructor for Yoga class with 1 params.
    * @param client Client ID
    */
   public Yoga(String client) {
      super(client);
   }

   /**
    * Getter for workout level.
    * @return Workout intensity (1-3)
    */
   public int getWorkoutLevel() {
      return this.workoutLevel;
   }

   /**
    * Setter for workoutLevel.
    * @param level Input with which to set workout level
    */
   public void setWorkoutLevel(int level) {
      this.workoutLevel = level;
   }

   /** 
    * Overidden getter for yoga workout type.
    * @return "yoga" for yoga object.
    */
   public String getWorkoutType() {
      return "yoga";
   }

   /**
    * Overridden method to calculate calories burned for yoga workout.
    * @return Calories burned
    */
   public double getCaloriesBurned() {
      double calories = this.getDuration() * 2.1 * this.workoutLevel;
      calories = Math.floor(calories * 100) / 100; // Truncate Decimal
      return calories;
   }

   /**
    * Overridden toString method.
    * @return String representation of yoga object.
    */
   public String toString() {
      String stringRepr;
      String workoutLevelString;
      if (this.workoutLevel == 2) {
         workoutLevelString = "intermediate level";
      }
      else if (this.workoutLevel == 3) {
         workoutLevelString = "advanced level";
      }
      else {
         workoutLevelString = "beginner level";
      }

      stringRepr = "[" + this.getClientID() + " completed yoga workout: " 
                        + this.getDuration() + " mins, " + workoutLevelString
                        + ", " + this.getCaloriesBurned() + " cals]";
      return stringRepr;
   }
}