package comp206020;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;

public class registerAllocate {
    private Set<Character> registerSet = new HashSet<Character>();
    public Map<Integer, Character> registerMap = new HashMap<Integer, Character>();
    private Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();

    public registerAllocate(Map< Integer, Set<Integer> > graph) {
        this.graph = graph;
    }

    public void allocate(){
        for(char c = 'A'; c <= 'Z'; c++){
            registerSet.add(c);
        }
        Set<Character> duplicate;
        ArrayList<Integer> keys = new ArrayList<Integer>(graph.keySet());
        keys.sort(Comparator.comparingInt(key -> -graph.get(key).size()));  
        for (int key : keys){
            duplicate = new HashSet<Character>(registerSet);
            for(Integer i : graph.get(key)){
                if(registerMap.containsKey(i)){
                    duplicate.remove(registerMap.get(i));
                }
            }
            Iterator<Character> iterator = duplicate.iterator();
            if(iterator.hasNext()){
                registerMap.put(key, iterator.next());
            }
            else{
                System.out.println("Error: Graph cannot be colored");
                registerMap.clear();
                return;
            }
        }
    
    
    }
}
