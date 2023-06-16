package ocp.collections.Collections2;

import java.util.*;

/**
 * Here we will use the Collections API
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	/**
    	 * A list has order, duplicates and allows
    	 * <code>null</code> values
    	 */
        List<String> myList = new ArrayList<String>();
        
        /**
         * We can do all the methods you are used to
         * 
         * Some important ones are toArray which
         * needs an instance of the array so it
         * can get the class form it to know
         * what kind to instantiate
         */
        myList.add("Hello");
        myList.add("Hello");
        String[] stuff = myList.toArray(new String[] {});
        System.out.println(Arrays.toString(stuff));
        
        
        /**
         * We build a comparator here which will work
         * with our `List<String>`
         */
        class LenCmp implements Comparator<String>
        {
			@Override
			public int compare(String o1, String o2)
			{
				return o1.length()-o2.length();
			}	
        }
        
        /**
         * Let's add some elements
         */
        myList.clear();
        myList.add("AAA");
        myList.add("AA");
        myList.add("A");
        System.out.println(myList); // [AAA, AA, A]
        
        /**
         * Now sort them!
         */
        myList.sort(new LenCmp());
        System.out.println(myList); // [A, AA, AAA]
        
        /**
         * We have some helper methods in <code>Collections</code>
         */
        Collections.shuffle(myList); // shuffles items randomly around
        System.out.println(myList);
        
        
        /**
         * A set has no order and does NOT allow duplicates
         * or null values
         */
        Set<String> mySet = new HashSet<String>();
        
        mySet.add("Hello");
        mySet.add("Hello");
        System.out.println(mySet.size()); // 1
        stuff = mySet.toArray(new String[] {});
        System.out.println(Arrays.toString(stuff));
        
        /**
         * A Map maps a key to a value
         */
        Map<String, String> myMap = new HashMap<String, String>();
        
        /**
         * Map "Tristan" to "Apple"
         */
        myMap.put("Tristan", "Apple");
        System.out.println(myMap); // Tristan:Apple
        
        /**
         * A map has unique keys meaning if 
         * we put "Tristan" again we then
         * overwrite the previous entry there
         */
        myMap.put("Tristan", "Banana");
        System.out.println(myMap); // Tristan:Banana
        
        myMap.put("Vaughan", "Broccoli");
        System.out.println(myMap); // Tristan:Banana,Vaughan:Broccoli
        
        myMap.remove("Tristan");
        System.out.println(myMap); // Vaughan:Broccoli
        
        
        
        
        /**
         * A deque stands for "double-sided queue" meaning you can
         * insert/remove from the front and the back
         */
        Deque<String> myDeque = new ArrayDeque<String>();
        
        /**
         * Add to the front of the queue
         */
        myDeque.addFirst("Alice");
        System.out.println(myDeque); // [Alice]
        
        /**
         * Adds to the front of the queue
         */
        myDeque.addFirst("Eve"); //[Eve, Alice]
        System.out.println(myDeque);
        
        /**
         * Adds to the tail of the queue
         */
        myDeque.addLast("Bob");
        System.out.println(myDeque); // [Eve, Alice, Bob]
        
        /**
         * Adds to the tail of the queue
         */
        myDeque.addLast("Chad");
        System.out.println(myDeque); //[Eve, Alice, Bob, Chad]
        
        /**
         * This is an alis for adding to the tail of the queue
         */
        myDeque.add("John");
        System.out.println(myDeque); //[Eve, Alice, Bob, Chad, John]
        
        /**
         * Remove the head and then remove the tail
         */
        myDeque.removeFirst();
        myDeque.removeLast();
        System.out.println(myDeque); //[Alice, Bob, Chad]
        
        /**
         * Peek head or tail
         */
        System.out.println("Peeked: "+myDeque.peekFirst()); //Alice
        System.out.println("Peeked: "+myDeque.peekLast()); //Chad
        System.out.println("Peeked: "+myDeque.peek()); //Alice
        
        
    }
}
