package comp206020;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;

public class registerAllocate {
    private Set<Character> registerSet = new HashSet<Character>(); // Set to store available registers (A-Z)
    public Map<Integer, Character> registerMap = new HashMap<Integer, Character>(); // Map to store register allocation for each variable
    private Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>(); // Graph representing variable dependencies

    // Constructor to initialize with the given graph
    public registerAllocate(Map< Integer, Set<Integer> > graph) {
        this.graph = graph;
    }

    // Method to allocate registers
    public void allocate(){
        // Add all registers (A-Z) to the register set
        for(char c = 'A'; c <= 'Z'; c++){
            registerSet.add(c);
        }
        // Temporary set to track available registers for each variable
        Set<Character> duplicate;
        // Sort keys based on the number of dependencies (descending order)
        ArrayList<Integer> keys = new ArrayList<Integer>(graph.keySet());
        keys.sort(Comparator.comparingInt(key -> -graph.get(key).size()));  
        for (int key : keys){
            // Create a copy of register set for each variable
            duplicate = new HashSet<Character>(registerSet);
            // Remove registers that are already allocated to dependent variables
            for(Integer i : graph.get(key)){
                if(registerMap.containsKey(i)){
                    duplicate.remove(registerMap.get(i));
                }
            }
            // Assign an available register to the variable
            Iterator<Character> iterator = duplicate.iterator();
            if(iterator.hasNext()){
                registerMap.put(key, iterator.next());
            }
            else{
                // If no register is available, print error and clear the register map
                System.out.println("Error: Graph cannot be colored");
                registerMap.clear();
                return;
            }
        }
    }
}
