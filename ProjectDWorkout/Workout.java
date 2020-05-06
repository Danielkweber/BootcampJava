/**
 * Class to store details of a general workout.
 * @author Daniel Weber JHED: dweber11
 * Date: 01/22/2020
 */
public class Workout {
   /** The default duration of a workout. Static var. Defaults to 60. */
   static int defaultDuration = 60;
   /** Stores clientID as a string. */
   private String clientID;
   /** Stores boolean for whether workout is cardio or strength. 
    * Defaults to true for cardio.  */
   private boolean isCardio = true;
   /** Stores length of workout in minutes. */
   private int workoutDuration;

   /**
    * Constructor for class Workout called with only client name.
    * @param client Client ID
    */
   public Workout(String client) {
      this.workoutDuration = defaultDuration;
      this.clientID = client;
   }

   /**
    * Constructor for class Workout with params client and workout type.
    * @param client Client ID
    * @param type boolean to represent whether workout is cardio.
    */
   public Workout(String client, boolean type) {
      this.clientID = client;
      this.isCardio = type;
      this.workoutDuration = defaultDuration;
   }

   /**
    * Constructor for class Workout with params client and duration.
    * @param client Client ID
    * @param duration Length of workout in minutes. Defaults to 60.
    */
   public Workout(String client, int duration) {
      this.clientID = client;
      this.workoutDuration = duration;
   }

   /**
    * Constructor for class Workout with params client, duration, and type.
    * @param client Client ID
    * @param duration Length of workout in minutes. Defaults to 60.
    * @param type Boolean to represent whether workout is cardio.
    */
   public Workout(String client, int duration, boolean type) {
      this.clientID = client;
      this.isCardio = type;
      this.workoutDuration = duration;
   }

   /**
    * Calculate amount of calories burned by workout.
    * @return float value for number of calories burned.
    */
   public double getCaloriesBurned() {
      double numCalories;
      if (this.isCardio) {
         numCalories = this.workoutDuration * 8.0;
      }
      else {
         numCalories = this.workoutDuration * 5.0;
      } 
      return numCalories;

   }

   /**
    * Overriden toString method to returns 
    the string represenation of the object.
    * @return String representation of object.
    */
   public String toString() {
      String stringRepr;
      if (this.isCardio) {
         stringRepr = "[" + this.clientID + " completed cardio workout: " 
                           + this.workoutDuration + " mins, " 
                           + getCaloriesBurned() + " cals]";
      }
      else {
         stringRepr = "[" + this.clientID + " completed strength workout: " 
                           + this.workoutDuration + " mins, " 
                           + getCaloriesBurned() + " cals]";
      }
      return stringRepr;
   }

   /**
    * Compares two workout objects and returns their relationship (<, >, =).
    * @param workoutToComp Workout to compare to object the method is called on.
    * @return 1 if workoutToComp is larger, 
             -1 if workoutToComp is smaller, 0 if equal.
    */
   public int compareTo(Workout workoutToComp) {
      if (this.getCaloriesBurned() < workoutToComp.getCaloriesBurned()) {
         return -1;
      }
      else if (this.getCaloriesBurned() > workoutToComp.getCaloriesBurned()) {
         return 1;
      }
      else {
         return 0;
      }
   }

   /**
    * Setter for Client ID.
    * @param inputID Input with which to set ClientID
    */
   public void setClientID(String inputID) {
      this.clientID = inputID;
   }

   /**
    * Getter for Client ID.
    * @return Client ID
    */
   public String getClientID() {
      return this.clientID;
   }

   /**
    * Setter for isCardio variable.
    * @param wasCardio Whether workout was cardio or not.
    */
   public void setCardioWorkout(boolean wasCardio) {
      this.isCardio = wasCardio;
   }

   /**
    * Getter for isCardio.
    * @return boolean for isCardio.
    */
   public boolean isCardioWorkout() {
      return this.isCardio;
   }

   /**
    * Setter for duration.
    * @param workoutMins Input with which to set duration.
    */
   public void setDuration(int workoutMins) {
      this.workoutDuration = workoutMins;
   }

   /**
    * Getter for duration.
    * @return Duration of workout in mins.
    */
   public int getDuration() {
      return this.workoutDuration;
   }

   /**
    * Getter for workout type.
    * @return "other" for base class workout.
    */
   public String getWorkoutType() {
      return "other";
   }

   /**
    * Sets default workout time.
    * @param defaultTime New default workout time.
    */
   public static void setDefaultDuration(int defaultTime) {
      defaultDuration = defaultTime;
   }

}