/*
   RandomIntegerGenerator - generate a file of random integers, one per line
   
   Author: Dr. Salim Lakhani
   Modified by: Mr. David Kramer
   
   Command line parameters, all optional with <default value>
   
   args  Value 
     0   Name of file to create <2050 Project 06_Input.txt>
     1   Number of numbers to generate <100>
     2   Maximum number to generate + 1 <10000>
   
*/

import java.io.*;
import java.util.Random;

public class RandomIntegerGenerator {
   public static void main (String[] args) throws IOException {
   
      int defaultNumNumbers  = 100;    // Default # of random #s to generate
      int defaultMaxNumber = 10000;    // Default Maximum number to generate + 1
      final String defaultFileName = "2050 Project 06_Input.txt";
                                       // Default file name
      final String OUTPUT_FILE;        // Name of output file supplied below
      final int NUM_NUMBERS;           // # of random #s to generate
      final int MAX_NUMBER;            // Maximum number to generate, + 1
      Random randomNumbers = new Random();
      
      // Hard-code the output file or get it from the command line
      if (args.length >= 1) { OUTPUT_FILE = args[0]; }
      else                  { OUTPUT_FILE = defaultFileName; }
      
      if (args.length >= 2) { NUM_NUMBERS = Integer.parseInt(args[1]); }
      else                  { NUM_NUMBERS = defaultNumNumbers; } 

      if (args.length >= 3) { MAX_NUMBER  = Integer.parseInt(args[2]); }
      else                  { MAX_NUMBER  = defaultMaxNumber; } 

      File write = new File (OUTPUT_FILE);
      PrintWriter output = new PrintWriter (write);
      
      for (int i = 0; i < NUM_NUMBERS; i++) {
         output.println (randomNumbers.nextInt(MAX_NUMBER));
      }
      
      output.close();
      System.out.println(NUM_NUMBERS + " random integers from 0 to "
                       + (MAX_NUMBER - 1)  + " generated to file '"
                       + OUTPUT_FILE + "'");
      
   }
}