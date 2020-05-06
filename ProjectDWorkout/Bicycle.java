/**
 * Class to store details of a bicycle workout.
 * @author Daniel Weber JHED: dweber11
 * Date: 01/22/2020
 */
public class Bicycle extends Workout {
   /** Stores bike resistance as int from 1-10. Default 1. */ 
   private int bikeResistance = 1;

   /**
    * Constructor for class Bicycle with inputs client, 
      duration, and resistance.
    * @param client Client ID
    * @param duration Duration of workout in mins.
    * @param resistance Resistance Level of bike workout.
    */
   public Bicycle(String client, int duration, int resistance) {
      super(client, duration);
      this.bikeResistance = resistance;
   }

   /**
    * Constructor for class Bicycle with inputs client, duration.
    * @param client Client ID
    * @param duration Length of workout in mins.
    */
   public Bicycle(String client, int duration) {
      super(client, duration);
   }

   /**
    * Constructor for class Bicycle with input client.
    * @param client Client ID
    */
   public Bicycle(String client) {
      super(client);
   }

   /**
    * Getter for bike resistance.
    * @return Value of var bikeResistance.
    */
   public int getBikeResistance() {
      return this.bikeResistance;
   }

   /**
    * Setter for bike resistance.
    * @param resistance Input with which to set bikeResistance.
    */
   public void setBikeResistance(int resistance) {
      this.bikeResistance = resistance;
   }

   /**
    * Overridden getter for workout type.
    * @return "bike" for bike workout.
    */
   public String getWorkoutType() {
      return "bike";
   }

   /**
    * Calculates bike workout distance based on resistance and duration.
    * @return Distance Biked.
    */
   public double getBikeDistance() {
      double distance = this.getDuration() / (8.0 * bikeResistance);
      distance = Math.floor(distance * 1000) / 1000; //Truncate Decimal
      return distance;
   }

   /**
    * Overridden calories burned method to calculate bike ride burned cals.
    * @return Calories burned
    */
   public double getCaloriesBurned() {
      double calories = this.getDuration() * (bikeResistance / 3.0) * 2.0;
      calories = Math.floor(calories * 100) / 100; //Truncate Decimal
      return calories;
   }

   /**
    * Overridden toString method.
    * @return String representation of object.
    */
   public String toString() {
      String stringRepr;
      stringRepr = "[" + this.getClientID() + " completed bicycle workout: " 
                        + this.getDuration() + " mins, " 
                        + this.getCaloriesBurned() + " cals, resistance "
                        + this.getBikeResistance() + ", " 
                        + this.getBikeDistance() + " mi]";
      return stringRepr;
   }
}