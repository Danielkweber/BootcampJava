/** Driver for Project D Warmup.
 *  To be used as a guide on how to set up the workout class.
 */
public class ProjectDWarmupDriver {
   
   /** Main test driver program for Project D Warmup.
    *  @param args  command-line arguments, ignored
    */
   public static void main(String[] args) {
   
      Workout myBike = new Workout("user01", 30, true);
      Workout myYoga = new Workout("user01", false);
      Workout myOther = new Workout("user01", 10);
      Workout myElliptical = new Workout("user01");
      
      System.out.println("Calories for my bike workout are: "
         + myBike.getCaloriesBurned());
      System.out.println("Duration for my elliptical workout is: "
         + myElliptical.getDuration());
      System.out.println("Calories for my elliptical workout at this "
         + "duration are: " + myElliptical.getCaloriesBurned());
      myElliptical.setDuration(myBike.getDuration());
      System.out.print("New elliptical duration is: "
         + myElliptical.getDuration());
      System.out.println(" and calories burned are "
         + myElliptical.getCaloriesBurned());
      System.out.println("Is yoga a cardio workout? "
         + myYoga.isCardioWorkout());
      System.out.println("Is other a cardio workout? "
         + myOther.isCardioWorkout());
      System.out.println("Is elliptical a cardio workout? "
         + myElliptical.isCardioWorkout());
      System.out.println("My bike workout information is \n"
         + myBike);
      System.out.println("Calories for my yoga workout are: "
         + myYoga.getCaloriesBurned());
      System.out.println("Does my yoga come before my other? "
         + (myYoga.compareTo(myOther) < 0));
      System.out.println("Does my other come before my bike? "
         + (myOther.compareTo(myBike) < 0));
      
      Workout.setDefaultDuration(30);
      Workout myWalk = new Workout("user02");
      System.out.println("Original walk info: " + myWalk);
      myWalk.setCardioWorkout(false);
      System.out.println("Updated strength walk calories: "
         + myWalk.getCaloriesBurned());
      myWalk.setDuration(45);
      System.out.println("Updated 45 min walk calories: "
         + myWalk.getCaloriesBurned());
      System.out.println("All updated walk info: " + myWalk);
      
      //myWalk.setCalories(1000);  // this line should not compile!    
   }
}

/* Expected output if you run this test program:
Calories for my bike workout are: 240.0
Duration for my elliptical workout is: 60
Calories for my elliptical workout at this duration are: 480.0
New elliptical duration is: 30 and calories burned are 240.0
Is yoga a cardio workout? false
Is other a cardio workout? true
Is elliptical a cardio workout? true
My bike workout information is 
[user01 completed cardio workout: 30 mins, 240.0 cals]
Calories for my yoga workout are: 300.0
Does my yoga come before my other? false
Does my other come before my bike? true
Original walk info: [user02 completed cardio workout: 30 mins, 240.0 cals]
Updated strength walk calories: 150.0
Updated 45 min walk calories: 225.0
All updated walk info: [user02 completed strength workout: 45 mins, 225.0 cals]

*/
