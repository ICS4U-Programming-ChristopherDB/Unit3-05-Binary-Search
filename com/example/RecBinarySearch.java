import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Recursive binary search.
 *
 * @author Christopher Di Bert
 * @version 1.0
 * @since 2024-05-01
 */

// RecBinarySearch class
public final class RecBinarySearch {

  /** Private constructor to prevent instantiation. */
  private RecBinarySearch() {
    throw new UnsupportedOperationException("Cannot instantiate");
  }

  /**
   * This is the main method.
   *
   * @param args Unused
   */
  public static void main(final String[] args) {
    // Creates the input and output file paths.
    final File inputPath =
        new File("/home/vscode/ICS4U/Unit3/Unit3-05/Unit3-05-Binary-Search/com/example/input.txt");
    final File outputPath =
        new File("/home/vscode/ICS4U/Unit3/Unit3-05/Unit3-05-Binary-Search/com/example/output.txt");

    try {
      // Creates scanner and writer.
      Scanner sc = new Scanner(inputPath);
      FileWriter fileWriter = new FileWriter(outputPath);
      BufferedWriter writer = new BufferedWriter(fileWriter);
      while (sc.hasNext()) {
        // Tries to cast the input to an int and pass it to RecFib.
        try {
          // Gets the element to search for.
          int targetNum = Integer.parseInt(sc.nextLine());
          // Gets the input array.
          String inputElements = sc.nextLine();
          // Creates string array from input line.
          String[] lineArr = inputElements.split(" ");
          // Casts from string to int stream.
          IntStream ints = Arrays.stream(lineArr).mapToInt(Integer::parseInt);
          // Converts int stream to int array.
          int[] intArr = ints.toArray();
          // Makes a copy of array to check if the original is sorted.
          int[] tempIntArr = Arrays.copyOf(intArr, intArr.length);
          Arrays.sort(tempIntArr);
          // If the array is sorted, run binary search and write to file.
          if (Arrays.equals(tempIntArr, intArr)) {
            int index = BinarySearch(intArr, targetNum, 0, intArr.length - 1);
            String indexString = Integer.toString(index);
            writer.write(indexString);
          } else {
            writer.write("You must enter a sorted array!");
          }
        } catch (Exception e) {
          writer.write("Must enter an integer!");
        }
        // Creates a new line in the output file.
        writer.newLine();
      }
      sc.close();
      writer.close();
      // Error thrown if an invalid file path was defined.
    } catch (Exception e) {
      System.out.println("Invalid path(s)!");
    }
  }

  /**
   * Binary search method.
   *
   * @param array
   * @param targetNum
   * @param low
   * @param high
   * @return -1 if key does not exist, mid if found, recursive calls if not.
   */
  private static int BinarySearch(int[] array, int targetNum, int low, int high) {
    // Used to check if target num exists in array.
    if (high >= low) {
      // Calculates the middle index.
      int mid = (low + high) / 2;
      // Return middle index if array at middle index equals target num.
      if (array[mid] == targetNum) {
        return mid;
        // If target is larger than middle number, set low to mid index + 1
      } else if (targetNum > array[mid]) {
        return BinarySearch(array, targetNum, mid + 1, high);
        /*If target is smaller than middle number, set high to
         * mid index + 1
         */
      } else {
        return BinarySearch(array, targetNum, low, mid - 1);
      }
      // Return -1 if element is not in array.
    } else {
      return -1;
    }
  }
}
