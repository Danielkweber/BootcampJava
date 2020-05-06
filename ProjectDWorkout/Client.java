import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to store details of a client.
 * @author Daniel Weber JHED: dweber11
 * Date: 01/22/2020
 */
public class Client {
   /** Class-wide counter to set unique client IDs.  */
   static int counter = 100;
   /** Stores first and last name of client. */
   private String clientName;
   /** Stores unique client ID. */
   private int clientNumber;
   /** List of workouts comppleted by client. */
   private Workout[] workoutList = new Workout[1];

   /**
    * Constructor for class Client.
    * @param name Client's name
    */
   public Client(String name) {
      this.clientName = name;
      this.clientNumber = counter;
      counter++;
   }

   /**
    * Getter for clientNumber.
    * @return Client ID
    */
   public int getClientNumber() {
      return this.clientNumber;
   }

   /**
    * Getter for clientName.
    * @return Client Name
    */
   public String getClientName() {
      return this.clientName;
   }

   /**
    * Getter for number of workout completed by client.
    * @return length of client's workout list
    */
   public int getNumWorkouts() {
      if (this.workoutList[0] == null) {
         return 0;
      }
      else {
         return this.workoutList.length;
      }
   }

   /**
    * Getter for workout list.
    * @return Client workout list
    */
   public Workout[] getWorkouts() {
      if (this.workoutList[0] == null) {
         return new Workout[0];
      }
      return this.workoutList;
   }

   /**
    * Displays string reps of all workout completed by the client.
    */
   public void displayWorkouts() {
      for (Workout workout : this.workoutList) {
         System.out.println(workout.toString());
      }
   }

   /**
    * Overridden toString method for Client.
    * @return String representation of client object.
    */
   public String toString() {
      String stringRepr = "[" + clientNumber + ", " + clientName + "]";
      return stringRepr;
   }

   /**
    * Method to add workout to client's workout list.
    * @param workoutToAdd workout object to add
    */
   public void addWorkout(Workout workoutToAdd) {
      if (this.workoutList[0] == null) {
         this.workoutList[0] = workoutToAdd;
      }
      else {
         Workout[] tempWorkoutList = this.workoutList;
         this.workoutList = new Workout[this.workoutList.length + 1];
         for (int i = 0; i < tempWorkoutList.length; i++) {
            this.workoutList[i] = tempWorkoutList[i];
         }
         this.workoutList[tempWorkoutList.length] = workoutToAdd;
      }
   }

   /**
    * Counts number of workouts of each type client has done.
    * @return Array of counts in order [yoga, bike, other]
    */
   public int[] countWorkoutTypes() {
      int yogaCount = 0;
      int bikeCount = 0;
      int otherCount = 0;
      for (Workout workout : workoutList) {
         if (workout == null) {
            continue;
         }
         else {
            String workoutType = workout.getWorkoutType();
            if (workoutType.equals("yoga")) {
               yogaCount++;
            }
            else if (workoutType.equals("bike")) {
               bikeCount++;
            }
            else {
               otherCount++;
            }
         }
      }
      return new int[] {yogaCount, bikeCount, otherCount};
   }

   /**
    * Static method to load list of clients from file.
    * @param fileName file with client names
    * @return Array of client objects for data found in file
    * @throws FileNotFoundException If file is not found
    */
   public static Client[] loadClients(String fileName) 
      throws FileNotFoundException {
      FileInputStream clientFile = new FileInputStream(fileName);
      Scanner clientScnr = new Scanner(clientFile);
      Client[] clientList = new Client[1];
      while (clientScnr.hasNextLine()) {
         if (clientList[0] == null) {
            clientList[0] = new Client(clientScnr.nextLine());
         }
         else {
            Client[] tempClientList = clientList;
            clientList = new Client[clientList.length + 1];
            for (int i = 0; i < tempClientList.length; i++) {
               clientList[i] = tempClientList[i];
            }
             //80 character rule makes for uglier code
            clientList[tempClientList.length] = 
            new Client(clientScnr.nextLine());
         }
      }
      clientScnr.close();
      return clientList;
   }


}