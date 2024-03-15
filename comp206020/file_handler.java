package comp206020;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class file_handler {
    public String line; // Variable to store the current line being read
    public String[] numbers; // Array to store the numbers extracted from each line
    public static void main(String[] args) {
        String inputFile = args[0]; // Input file path provided as command line argument
        String outputFile = args[1]; // Output file path provided as command line argument
        boolean errors = false; // Flag to track if there are any errors during processing
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>(); // Map to store the graph

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line; // Temporary variable to store the current line being read
            String[] numbers; // Temporary array to store the numbers extracted from each line
            int prev_num = 0, next_num = 1, count = 1; // Variables to track the previous and next numbers in the input

            // Read each line from the input file
            while ((line = reader.readLine()) != null) {
                numbers = line.split(","); // Split the line by comma to extract numbers
                
                // Skip empty lines
                if (numbers.length == 1 && numbers[0].trim().equals("")) {
                    continue;
                }

                next_num = Integer.parseInt(numbers[0].trim()); // Parse the first number from the line
                
                // Check if the numbers are in ascending order
                if (next_num > prev_num) {
                    prev_num = next_num;
                } else {
                    System.out.println("Error: Input file is not in correct format (not in ascending)");
                    return;
                }

                // Check if the numbers start from 1
                if (count == 1 && next_num != 1) {
                    System.out.println("Error: Input file is not in correct format (not starting from 1) ");
                    return;
                }
                count++;

                // Trim each number in the array
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = numbers[i].trim();
                }

                int[] nodes = new int[numbers.length]; // Array to store the parsed numbers
                // Parse each number in the array
                for (int i = 0; i < numbers.length; i++) {
                    nodes[i] = Integer.parseInt(numbers[i]);
                }

                graph.put(nodes[0], new HashSet<>()); // Initialize the set for the current node
                // Add the neighbors to the set for the current node
                for (int i = 1; i < nodes.length; i++) {
                    graph.get(nodes[0]).add(nodes[i]);
                }

            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading...... :(");
            errors = true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in input file (not an integer)");
            errors = true;
        }

        registerAllocate problem_instance = new registerAllocate(graph); // Create an instance of registerAllocate
        problem_instance.allocate(); // Allocate registers for the graph

        // If there are no errors, write the output to the file
        if (!errors) {
            try {
                FileWriter fileWriter = new FileWriter(outputFile); // Create a FileWriter for the output file
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // Create a BufferedWriter

                // Write the register allocation to the output file
                if (problem_instance.registerMap.size() > 0) {
                    for (Integer keys : problem_instance.registerMap.keySet()) {
                        bufferedWriter.write(keys + "," + problem_instance.registerMap.get(keys) + "\n");
                    }
                    System.out.println("Data has been written to the file successfully.");
                }

                bufferedWriter.close(); // Close the BufferedWriter

            } catch (IOException e) {
                System.out.println("Something went wrong while writing...... :(");
            }
        }
    }
}
