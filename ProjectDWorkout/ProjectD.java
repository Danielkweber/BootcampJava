import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class to drive workout tracker program.
 * @author Daniel Weber JHED: dweber11
 * Date: 01/22/2020
 */
public class ProjectD {
   /**
    * Main method to drive workout tracker.
    * @param args No command line arguments taken
    * @throws FileNotFoundException If file is not found
    */
   public static void main(String[] args) 
      throws FileNotFoundException {
      Scanner keyboardScnr = new Scanner(System.in);
      Client[] clientList = new Client[0];
      int inputNum = 10;
      while (inputNum != 0) {
         printOptions();
         inputNum = keyboardScnr.nextInt();
         switch (inputNum) {
            case 0:
               keyboardScnr.close();
               break;
            case 1:
               System.out.print("Enter File Name:");
               String clientFileName = keyboardScnr.next();
               clientList = Client.loadClients(clientFileName);
               break;
            case 2:
               System.out.print("Enter File Name:");
               String workoutFileName = keyboardScnr.next();
               readWorkoutFile(workoutFileName, clientList);
               break;
            case 3:
               displayAllWorkouts(clientList);
               break;
            case 4:
               System.out.print("Enter Client Number:");
               int clientInputNum = keyboardScnr.nextInt();
               displayClientWorkouts(clientList, clientInputNum);
               break;
            case 5:
               System.out.print("Enter Client Number:");
               int clientInputNum5 = keyboardScnr.nextInt();
               for (Client client : clientList) {
                  if (client.getClientNumber() == clientInputNum5) {
                     double totalCals = 0;
                     for (Workout workout : client.getWorkouts()) {
                        totalCals += workout.getCaloriesBurned();
                     }
                     System.out.println("Calories Burned: " + totalCals);
                     break;
                  }
               }
               break;
            case 6:
               System.out.print("Enter Client Number:");
               int clientInputNum6 = keyboardScnr.nextInt();
               displayAvgCalsBurned(clientList, clientInputNum6);
               break;
            case 7:
               sortWorkouts(clientList);
               break;
            case 8:
               clientsWithYogaBike(clientList);
               break;
            default:
               System.out.println("Not a valid selection. Please try again.");
               break;
         }
      }
   }
   
   /**
    * Method to print program options.
    */
   public static void printOptions() {
      //This looks disgusting
      System.out.println("0)     Quit the program");
      System.out.println("1)     Add clients from a plain text file");
      System.out.println("       Enter file name: [Get user input for the " 
                         + "name of the input file]");
      System.out.println("2)     Add workouts for clients "
                         + "from a plain text file");
      System.out.println("       Enter file name: [Get user input "
                         + "for the name of the input file]");
      System.out.println("3)     Display all client identification "
                         + "strings and their names, "
                         + "along with total number of");
      System.out.println("       workouts completed, "
                         + "and total numbers of bicycle "
                         + "workouts and yoga workouts");
      System.out.println("4)     Display details of all workouts "
                         + "completed by a given client");
      System.out.println("       Enter client identification string: " 
                         + "[Get user input for the name of the client]");
      System.out.println("5)     Display total calories "
                         + "burned by a given client");
      System.out.println("       Enter client identification string: " 
                         + "[Get user input for the name of the client]");
      System.out.println("6)     Display average calories burned "
                         + "per workout for a given client");
      System.out.println("       Enter client identification string: "
                         + "[Get user input for the name of the client]");
      System.out.println("7)     Display all workouts in the "
                         + "system, sorted by calories burned, " 
                         + "with the");
      System.out.println("       lowest-calorie-burning workout listed first");
      System.out.println("8)     Display a list of client identification "
                         + "strings containing only those clients who have "
                         + "completed");
      System.out.println("       at least one bicycle workout "
                         + "and at least one yoga workout");

      System.out.println("Enter the number of your choice ->");
   }

   /**
    * Static method to read file with workouts and store
    them in respective client workout lists.
    * @param fileName name of workout file
    * @param clientList List of clients to assign workouts too
    * @throws FileNotFoundException If file is not found
    */
   public static void readWorkoutFile(String fileName, Client[] clientList) 
      throws FileNotFoundException {
      FileInputStream workoutFile = new FileInputStream(fileName);
      Scanner workoutScnr = new Scanner(workoutFile);
      while (workoutScnr.hasNextLine()) {
         String currLine = workoutScnr.nextLine();
         String[] splitLine = currLine.split(" ");
         for (Client client : clientList) {
            if (currLine.equals("")) {
               break;
            }
            else {
               if (Integer.parseInt(splitLine[0]) == client.getClientNumber()) {
                  if (splitLine[1].equals("bike")) {
                     switch (splitLine.length) {
                        case 3:
                           client.addWorkout(new Bicycle(splitLine[0], 
                                             Integer.parseInt(splitLine[2])));
                           break;
                        case 4:
                           client.addWorkout(new Bicycle(splitLine[0], 
                                             Integer.parseInt(splitLine[2]),
                                             Integer.parseInt(splitLine[3])));
                           break;
                        default:
                           break;
                     }
                     break;
                  }
                  else if (splitLine[1].equals("yoga")) {
                     switch (splitLine.length) {
                        case 3:
                           client.addWorkout(new Yoga(splitLine[0], 
                                             Integer.parseInt(splitLine[2])));
                           break;
                        case 4:
                           client.addWorkout(new Yoga(splitLine[0], 
                                             Integer.parseInt(splitLine[2]),
                                             Integer.parseInt(splitLine[3])));
                           break;
                        default:
                           break;
                     }
                     break;
                  }
                  else {
                     switch (splitLine.length) {
                        case 3:
                           client.addWorkout(new Workout(splitLine[0], 
                                             Integer.parseInt(splitLine[2])));
                           break; 
                        case 4:
                           if (splitLine[3].equals("yes")) {
                              client.addWorkout(new Workout(splitLine[0], 
                                                Integer.parseInt(splitLine[2]),
                                                true));
                           }
                           else {
                              client.addWorkout(new Workout(splitLine[0], 
                                                Integer.parseInt(splitLine[2]),
                                                false));
                           }
                           break;
                        default:
                           break;
                     }
                     break;
                  }
               }
            }
         }
      }
      workoutScnr.close();
   }

   /**
    * Static mehtod that displays all workouts done by all clients.
    * @param clientList List of clients to get workouts from.
    */
   public static void displayAllWorkouts(Client[] clientList) {
      for (Client client : clientList) {
         int numWorkouts = client.getNumWorkouts();
         int[] workoutCounts = client.countWorkoutTypes();
         String clientString = client.toString() + " has completed " 
                              + numWorkouts + " workout(s). Yoga: " 
                              + workoutCounts[0] + ". Bike: " 
                              + workoutCounts[1] + ".";
         System.out.println(clientString);
      }
   }

   /**
    * Static method that sorts all workouts 
      by ascending order based on calories burned.
    * @param clientList List of clients to get workouts from.
    */
   public static void sortWorkouts(Client[] clientList) {
      int numWorkouts = 0;
      for (Client client : clientList) {
         numWorkouts += client.getNumWorkouts();
      }
      Workout[] allWorkouts = new Workout[numWorkouts];
      int workoutListIndex = 0;
      for (Client client : clientList) {
         for (int i = 0; i < client.getWorkouts().length; i++) {
            allWorkouts[workoutListIndex] = client.getWorkouts()[i];
            workoutListIndex++;
         }
      }
      Workout[] sortedWorkouts = quickSortWorkouts(allWorkouts, 0, 
                                                   allWorkouts.length - 1);
      for (Workout workout : sortedWorkouts) {
         System.out.println(workout.toString());
      }
   }
   /**
    * Implements quicksort algorthim for workout objects.
    * @param workoutArray Array to Sort.
    * @param lowerIndex Lower index to sort from (used in recursion)
    * @param upperIndex Upper index to sort to (used in recursion)
    * @return Sorted workout array
    */
   public static Workout[] quickSortWorkouts(Workout[] workoutArray, 
                                    int lowerIndex, int upperIndex) {
      int initialLower = lowerIndex;
      int initialUpper = upperIndex;
      Workout pivot = workoutArray[lowerIndex + (upperIndex - lowerIndex) / 2];
      while (lowerIndex <= upperIndex) {
         while (workoutArray[lowerIndex].compareTo(pivot) == -1) {
            lowerIndex++;
         }
         while (workoutArray[upperIndex].compareTo(pivot) == 1) {
            upperIndex--;
         }
         if (lowerIndex <= upperIndex) {
            Workout temp = workoutArray[lowerIndex];
            workoutArray[lowerIndex] = workoutArray[upperIndex];
            workoutArray[upperIndex] = temp;
            lowerIndex++;
            upperIndex--;
         }
      }
      if (initialLower < upperIndex) {
         quickSortWorkouts(workoutArray, initialLower, upperIndex);
      }
      if (lowerIndex < initialUpper) {
         quickSortWorkouts(workoutArray, lowerIndex, initialUpper);
      }
      return workoutArray;
   }

   /**
    * Static method to print clients who have
      completed one bike and yoga workouts.
    * @param clientList List of all clients 
    */
   public static void clientsWithYogaBike(Client[] clientList) {
      for (Client client : clientList) {
         int numYoga = client.countWorkoutTypes()[0];
         int numBike = client.countWorkoutTypes()[1];
         if ((numYoga > 0) && (numBike > 0)) {
            System.out.println(client.getClientNumber());
         }
      }
   }

   /**
    * Displays avg cals burned by client.
    * @param clientList List of all clients
    * @param clientNum Client to display
    */
   public static void displayAvgCalsBurned(Client[] clientList, int clientNum) {
      for (Client client : clientList) {
         if (client.getClientNumber() == clientNum) {
            double totalCals = 0;
            for (Workout workout : client.getWorkouts()) {
               totalCals += workout.getCaloriesBurned();
            } 
            double averageCals = totalCals 
                                 / (double) client.getNumWorkouts(); 
            System.out.println("Average Calories Burned: " 
                               + averageCals); 
            break;
         }
      }
   }

   /**
    * Display all workouts done by client.
    * @param clientList List of all clients
    * @param clientNum Client to display
    */
   public static void displayClientWorkouts(Client[] clientList, 
                                            int clientNum) {
      for (Client client : clientList) {
         if (client.getClientNumber() == clientNum) {
            client.displayWorkouts();
            break;
         }
      }
   }
}
