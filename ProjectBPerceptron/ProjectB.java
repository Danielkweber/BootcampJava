import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/** 
* The purpose of the program is to implement a simple perceptron.
* @author: Daniel Weber JHED: dweber11
* Date: January 17, 2020
*/
public class ProjectB {
/**
   * Method to read data from provided files.
   * @param fileName filename to be fed to open file datastream
   * @param totalRows number of rows in file
   * @throws IOException To handle file reading expceptions
   * @return double[][] array with feature values
   */
   public static double[][] readFileData(String fileName, int totalRows) 
      throws IOException {
      double[][] outputArray = new double[totalRows][4];
      FileInputStream fileData = new FileInputStream(fileName);
      Scanner inFileStream = new Scanner(fileData);
      int counter = 0;
      while (inFileStream.hasNextLine()) {
         String currLine = inFileStream.nextLine();
         String[] currLineArr = currLine.split(" ");
         for (int i = 0; i < 4; i++) {
            outputArray[counter][i] = Double.parseDouble(currLineArr[i]);
         }
         counter++;
      } 
      fileData.close();
      inFileStream.close();
      return outputArray;
   }
   /** 
    * Gets labels from provided files.
    * @param fileName Name of file to read from
    * @param totalRows Number of rows in file
    * @throws IOException 
    * @return double[] with label data
   */
   public static double[] getAccuracyData(String fileName, int totalRows) 
      throws IOException {
      double[] accuracyArray = new double[totalRows];
      FileInputStream fileData = new FileInputStream(fileName);
      Scanner inFileStream = new Scanner(fileData);
      int counter = 0;
      while (inFileStream.hasNextLine()) {
         String currLine = inFileStream.nextLine();
         String[] currLineArr = currLine.split(" ");
         double tempDouble = Double.parseDouble(currLineArr[4]);
         if ((tempDouble - 0.0) < 0.00001) {
            tempDouble = -1;
         }
         else if ((tempDouble - 1.0) < 0.00001) {
            tempDouble = 1;
         }
         accuracyArray[counter] = tempDouble;
         counter++;
      }
      fileData.close();
      inFileStream.close();
      return accuracyArray;
   }
   /**
    * Method to train perceptron through weight manipulation.
    * @param trainingData Takes 2d array which we geneated with above method
    * @param accuracyData 1d Array of label values from above method
    * @throws IOException Since we are dealing with files
    * @return double array of weights
    */
   public static double[] trainPerceptron(double[][] trainingData, 
                                          double[] accuracyData) 
      throws IOException {
      FileOutputStream outFile = new FileOutputStream("weights.txt");
      PrintWriter outFS = new PrintWriter(outFile);
      double[] perceptronWeights = new double[4]; 
      int curArrInd = 0;
      for (double[] currArray : trainingData) {
         double classifier = 0.0;
         for (int i = 0; i < currArray.length; i++) {
            classifier += perceptronWeights[i] * currArray[i];
         }
         boolean mismatch1 = (classifier <= 0) && (accuracyData[curArrInd] > 0);
         boolean mismatch2 = (classifier > 0) && (accuracyData[curArrInd] <= 0);
         if (mismatch1 || mismatch2) {
            for (int i = 0; i < perceptronWeights.length; i++) {
               perceptronWeights[i] += currArray[i] * accuracyData[curArrInd];
            } 
         }
         for (double printItem : perceptronWeights) {
            outFS.printf("%.5f ", printItem);
            outFS.flush();
         }
         outFS.println("");
         curArrInd++;
      }
      outFile.close();
      return perceptronWeights; 
   }
   /** 
    * Uses weights from perceptron training to predict status of new data.
    * @param fileName filename of new data
    * @param totalRows number of rows in file
    * @param finalWeights perceptron weights found in above method
    * @throws IOException 
   */
   public static void predictNewData(String fileName, int totalRows, 
                                    double[] finalWeights) throws IOException {
      FileOutputStream predictFile = new FileOutputStream("predict.txt");
      PrintWriter predictFS = new PrintWriter(predictFile);
      double[][] validationData = readFileData(fileName, totalRows);
      for (double[] currArray : validationData) {
         double classifier = 0.0;
         for (int i = 0; i < currArray.length; i++) {
            classifier += finalWeights[i] * currArray[i];
         }
         double[] arrayToWrite = new double[5];
         if (classifier < 0) {
            for (int i = 0; i < currArray.length; i++) {
               arrayToWrite[i] = currArray[i];
            }
            arrayToWrite[4] = -1.0;
         }
         else if (classifier >= 0) {
            for (int i = 0; i < currArray.length; i++) {
               arrayToWrite[i] = currArray[i];
            }
            arrayToWrite[4] = 1.0;
         }
         for (double item : arrayToWrite) {
            predictFS.print(item + " ");
            predictFS.flush();
         }
         predictFS.println("");
      } 
      predictFile.close();
   }
   /**
    * Compares predicted labels to actual labels to evaluate model performance.
    * @param validationLabels Actual labels from validation data
    * @param predictionLabels Predicted labels from model
    */
   public static void validatePredictions(double[] validationLabels, 
                                          double[] predictionLabels) {
      int correctCount = 0;
      int incorrectCount = 0;
      for (int i = 0; i < 325; i++) {
         if ((validationLabels[i] - predictionLabels[i]) < 0.1) {
            correctCount += 1;
         }
         else {
            incorrectCount += 1;
         }
      }
      double perceptronAccuracy = (double) correctCount / 325.0;
      System.out.println("Correct: " + correctCount);
      System.out.println("Incorrect: " + incorrectCount);
      System.out.println("Accuracy: " + perceptronAccuracy);
   }
      
/**
 * Main Method.
 * @param args Java will be Java.
 * @throws IOException 
 */
   public static void main(String[] args) throws IOException {
      final String trainingFile = "training.txt";
      final String validationFile = "validate.txt";
      final int trainRows = 1048;
      final int validateRows = 325;
      double[][] trainingData = readFileData(trainingFile, trainRows);
      double[] trainAccuracyData = getAccuracyData(trainingFile, trainRows);
      double[] finalWeights = trainPerceptron(trainingData, trainAccuracyData);
      predictNewData(validationFile, validateRows, finalWeights);
      double[] validateAccData = getAccuracyData(validationFile, validateRows);
      double[] predictAccData = getAccuracyData("predict.txt", validateRows);
      validatePredictions(validateAccData, predictAccData);
   }
}