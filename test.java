/*Sort a given list of numbers by the number of 1's present in the binary representation of the number, if 2 numbers have the same number of 1's then sort them by their value. Solve it by counting the number of 1's in each number and generating a map of count to list of numbers, also store the count in a separate array. then sort the count array and used it to grab the list of numbers in the right order.*/

import java.util.*;
import java.lang.Integer; 

public class HelloWorld{

     public static void main(String []args){
        int[] list = {511,65436,321,7654,7,54,6,54376,35,432,542,7, 6, 5, 4, 3, 2, 1}; // list of numbers
        //bin = [111, 110, 101, 100, 011, 010, 001] // list of nums in binary
        //int count = [3, 2, 2, 1, 2, 1, 1];
        
        // list of numbers but in as a List, 
        // probably needed since comparators work with objects and not with primitives
        //ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(7, 6, 5, 4, 3, 2, 1));
    	ArrayList<Integer> list2= new ArrayList<Integer>();
    	/*There is no shortcut for converting from int[] to List<Integer> as Arrays.asList does not deal with boxing and will just create a List<int[]> which is not what you want. You have to make a utility method.*/
    	for (int i : list){
            list2.add(i);
        }

        // arraylist of the bit counts
        ArrayList<Integer> count = new ArrayList<Integer>();
        list2.forEach(n -> count.add(Integer.bitCount(n)) );
        
        // make the map
        Map<Integer,Integer> hm =  new HashMap<Integer, Integer>();

        // print out the list of numbers and then the bit counds
        System.out.println(list2.toString()); 
        System.out.println(count.toString()); 
        
        // create the sorting
        Comparator<Integer> cmp = Comparator.<Integer> comparingInt(p -> Integer.bitCount(p))
                                  .thenComparingInt(p -> p);
        // do the sorting
        Collections.sort(list2,  cmp);
        
        // print out after the fact
        System.out.println(list2.toString()); 
        
        // clears the array
        count.clear();
        list2.forEach(x -> count.add(Integer.bitCount(x)) );
        System.out.println(count.toString()); 
     }
}