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
    public String line;
    public String[] numbers;
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        boolean errors = false;
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
         try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
             String line; String[] numbers; 
             int prev_num = 0, next_num = 1, count = 1; 
            while ((line = reader.readLine()) != null) {
                numbers = line.split(",");
                if(numbers.length == 1 && numbers[0].trim().equals("")){
                    continue;
                }
                next_num = Integer.parseInt(numbers[0].trim());
                if(next_num > prev_num ){
                    prev_num = next_num;
                }
                else{   
                    System.out.println("Error: Input file is not in correct format (not in ascending)");
                    return;
                }
                if(count == 1 && next_num != 1){
                    System.out.println("Error: Input file is not in correct format (not starting from 1) ");
                    return;
                }
                count++;
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = numbers[i].trim();
                }
                
                int[] nodes = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    nodes[i] = Integer.parseInt(numbers[i]);
                }
                graph.put(nodes[0], new HashSet<>()); 
                for (int i = 1; i < nodes.length; i++) {
                    graph.get(nodes[0]).add(nodes[i]);
                }                      

            }
        } catch (IOException e) {
        System.out.println("Something went wrong while reading...... :(");
        errors = true;}

        catch (NumberFormatException e) {
        System.out.println("Error: Invalid number format in input file (not an integer)");
        errors = true;}

        registerAllocate problem_instance = new registerAllocate(graph);
        problem_instance.allocate();

        if(!errors){ try {
                FileWriter fileWriter = new FileWriter(outputFile);

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                if(problem_instance.registerMap.size() > 0){for( Integer keys : problem_instance.registerMap.keySet()){
                    bufferedWriter.write(keys + "," + problem_instance.registerMap.get(keys) + "\n");
                }}

                bufferedWriter.close();

                System.out.println("Data has been written to the file successfully.");
            } catch (IOException e) {
                System.out.println("Something went wrong while writing...... :(");}
            }}
}
    

