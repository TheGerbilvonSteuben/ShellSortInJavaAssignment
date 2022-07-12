/**
  * This program reads an input file of integers and then sorts it using
  * a shell sort method and a quick sort method. It then writes the 
  * results of the two sorts to their own respective output files.
  * @author Jerret Stovall
  * @author Brittany Simpson
  * Project 06
  * Windows 10, Acer Aspire R3-131T, jGRASP IDE version 2.0.5_06
  * White Hat: A hacker who tests computer systems for possible 
  *            vulnerabilities so that they can be fixed.
  * “In every job that must be done, there is an element of fun. 
  *  You find the fun and—snap!—the job’s a game!” 
  *                                              – Mary Poppins
*/
import java.util.*;
import java.io.*;

public class JerretStovall_06 
{

   // Declarations
   private static int[] shellSort = new int[100];
   private static int[] quickSort = new int[100];
   private static final String INPUT = "2050 Project 06_Input.txt";
   private static final String OUTPUT_ONE= "JerretStovall_06_Output1.txt";
   private static final String OUTPUT_TWO= "JerretStovall_06_Output2.txt";
   private static Scanner input;
   private static PrintWriter output;
   
   public static void main(String[] args) 
   {
      
      // Open the input file that contains unsorted numbers
      try
      {
         input = new Scanner(new File(INPUT));
      }
      catch(IOException e)
      {
         System.err.println(e.getMessage() + "Could not open: " + INPUT);
      }      
      // Populate the arrays to be sorted with the input data
      for(int i = 0; i < 100; i++) 
      {
         int j = input.nextInt();
         shellSort[i] = j;
         quickSort[i] = j;
      }  
      // Sort the arrays
      shellSort(shellSort);
      quickSort(quickSort, 0, 99);    
      // Write sorted numbers to output file
      generateReport();
      // Close input file 
      input.close();
      
   } // End main
   
//========================================================================
  
   /** 
      shellSort -            Takes an array of integers and shell sorts
                             it from smallest to greatest
      @param   inputData     Array holding integers to shell sorted 
   */
   private static void shellSort(int[] inputData) 
   {
      
      // n will be used to calculate the length of each interval
      int n = inputData.length;
      // Set up the intervals to sort through within the header of this 
      // for loop     
      for (int gap = n / 2; gap > 0; gap /= 2)
      {
      
         // Start sorting through each interval
         for (int i = gap; i < n; i += 1) 
         { 
          
            // temp holds the value of the current element and will
            // be compared to the values of the previous elements in the 
            // interval
            int temp = inputData[i];
            // j will act as the index of the element to be swapped
            // when needed, big emphasis on "when needed"
            int j;
            // This next loop requires that an unsorted element is 
            // encountered before the loop can execute and perform a 
            // swap. This precondition is contained within the header of 
            // this for loop as: (j >= gap) and (previous element in 
            // interval > current element in the interval)
            // If this precondition isn't met, the loop isn't activated
            // and the next element in the interval is evaluated
            for (j = i; j >= gap && inputData[j - gap] > temp; j -= gap) 
            {
           
               // Replace current element with previous element
               inputData[j] = inputData[j - gap];
               // Do not complete the swap inside this loop, the 
               // final value of j is determined by the precondition above
           
            } // End for
            // j is now either the index for the previous element if the
            // loop was activated or the index for the current element 
            // if the loop wasn't activated. If the former occurs, the 
            // swap is completed by giving the previous element the
            // value that temp recieved from the current element. 
            // If the latter happens, the current element is replaced 
            // with a copy of itself meaning that a swap did not occur
            // and the loop can move on to the next element without 
            // making any changes to the array
         
            inputData[j] = temp;
         
         } // End for
      } // End outer for   
   } // End shellSort
   
//========================================================================
   
   /** 
       quickSort -             Takes an array of integers and quick 
                               sorts it from smallest to greatest
       @param   inputData      Array of numbers to be quick sorted
       @param   first          Designates the lowest index to start 
                               sorting from.
       @param   last           Designates the highest index in the range 
                               to sort.
   */
   private static void quickSort(int[] inputData, int first, int last) 
   {
   
      if (first < last) 
      {
      
         // Find a partition index and place it into the correct place 
         // within the array
         int partitionIndex = partition(inputData, first, last);
         // Recursively sort elements before and after the partition
         quickSort(inputData, first, partitionIndex - 1);
         quickSort(inputData, partitionIndex + 1, last);
      
      } // End if   
   } // End quickSort
   
//========================================================================

   /**
      partition  -    Takes the last element's value as a pivot
                      and places it at the correct position in the array
                      after sorting all the other entries around 
                      it according to whether they are less than or
                      greater than the pivot. Entries that are equal to
                      the pivot are left in place.
                       
      @return         Returns i + 1 after completing quick sort
      @param   first  Designates the lowest index to start sorting from
      @param   last   Designates the highest index in the range to sort 
   */
   private static int partition (int inputData[], int first, int last) 
   {
      
      // Pivot value will come from the last element in the array
      int pivot = inputData[last];
      // i will act as the index of the last sorted item.
      // It will begin at -1 so it can catch unsorted entries at the
      // first element in the array.
      int i = (first - 1);  
      // Begin sorting the array, starting with the first element
      // NOTE: The sorting implementation is similar to a shell
      // sort, except the intervals depend upon each increment of i
      for (int j = first; j < last; j++) 
      {
         // Compare the current element's value against the pivot value
         if (inputData[j] <= pivot) 
         {  
         
            // If smaller, incremment i and swap the values of inputData[i]
            // and inputData[j]               
            i++;                          
            int temp = inputData[i];       
            inputData[i] = inputData[j];  
            inputData[j] = temp;          
                                                                            
         } // End if                      
      } // End for
      // After completing the sort, place the element containing the
      // the pivot value into the correct place
      int temp = inputData[i + 1];
      inputData[i + 1] = inputData[last];
      inputData[last] = temp;
      // Return the index of the pivot value
      return i + 1;
      
   } // End partition
 
//========================================================================
  
   /**
      generateReport - Writes the result of the two sorts to their own 
                       output files
   */
   private static void generateReport()
   {
   
      String[] files = {OUTPUT_ONE, OUTPUT_TWO};
      String[] sorts = {"Results of shell sort: \n",
                        "Results of quick sort: \n"};
      for(int i = 0; i < files.length; i++)
      {
         try
         {
            output = new PrintWriter(new FileWriter(files[i]));
            output.println("======================\n" + sorts[i]
                           + "======================\n");
            if(i == 0)
            {
               // Print shell sorted numbers to first output file
               displayResults(shellSort);
            }// End if
            else if(i == 1)
            {
               // Print quick sorted numbers to second output file 
               displayResults(quickSort);         
            }// End else if
         }// End try
         catch(IOException e)
         {
            output.println(e.getMessage() + 
                           "Could not create output file: " + files[i]);
         }// End catch
         // Close output stream
         output.close();
      }// End for 
       
   }// End generateReport
//========================================================================
   
   /**
      displayResults   -    Used when generating output results, writes 
                            the sorted numbers to an output file
      @param   sortedData   The array of sorted numbers to be written to
                            output file
   */
   private static void displayResults(int[] sortedData)
   {
      
      String nextTen = "";
      int numPerLine = 10;
      for(int j = 0; j < sortedData.length; j++) 
      {
      
         // Populate a string with the next ten numbers
         // in the sorted array and print it to a new line
         // in the output file
         nextTen += sortedData[j] + " ";
         if ((j+1) % numPerLine == 0)
         {
            output.println(nextTen);
            nextTen = "";
         }// End if
      
      } // End for           
   }// End displayResults
//========================================================================
} // End class