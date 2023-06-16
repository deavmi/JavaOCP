package ocp.collections.Arrays;

import java.util.Arrays;

/**
 * Here we play with Java arrays and the Java Collections API
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/**
         * Arrays in java are pretty easy to work with
         */
    	String[] myArray = new String[] {"Alice", "Bob"};
    	System.out.println(Arrays.toString(myArray)); // [Alice, Bob]
    	
    	/**
    	 * Any array is an object too, hence we can store
    	 * it in the <code>Object</code> super-type
    	 */
    	Object thing = myArray;
    	
    	/**
    	 * You can also cast the array <code>T[]</code>
    	 * to <code>A[]</code> so long as T is a kind-of
    	 * A (which is true as T=String, and A=CharSequence)
    	 * and String is a kind-of CharSequence
    	 */
    	CharSequence[] thing2 = myArray;
    	thing2[0] = "Eve";
    	System.out.println(Arrays.toString(myArray)); // [Eve, Bob]
    	
    	/**
    	 * Remember the object's actual type is
    	 * <code>String[]</code>, so we must check
    	 * when we downcast
    	 */
    	if(thing2 instanceof String[])
    	{
    		String[] myCastedArray = (String[])thing2;
    		myCastedArray[1] = "Peter";
    		System.out.println(Arrays.toString(myCastedArray)); // [Eve, Peter]
    	}
    	
    	/**
    	 * We can also do it via <code>thing</code>
    	 */
    	if(thing instanceof CharSequence[])
    	{
    		CharSequence[] myCastedArray = (CharSequence[])thing;
    		myCastedArray[1] = "John";
    		System.out.println(Arrays.toString(myCastedArray)); // [Eve, John]
    	}
    	
    	/**
    	 * We can also do it via <code>thing</code>
    	 */
    	if(thing instanceof String[])
    	{
    		String[] myCastedArray = (String[])thing;
    		myCastedArray[0] = "Nick";
    		System.out.println(Arrays.toString(myCastedArray)); // [Nick, John]
    	}
    	
    	/**
    	 * The Arrays class has a bunch of helper methods that we can
    	 * use to help us working with arrays
    	 * 
    	 * The binarySearch does a binary search on our array
    	 */
    	int[] numbers = {5,4,1,2,1};
    	int pos = Arrays.binarySearch(numbers, 0);
    	System.out.println("Position: "+pos); // -1 because not found
    	pos = Arrays.binarySearch(numbers, 1);
    	System.out.println("Position: "+pos); // non-negative because found
    	
    	/**
    	 * Apply in-place sorting
    	 */
    	System.out.println("Before sorting: "+Arrays.toString(numbers));
    	Arrays.sort(numbers);
    	System.out.println("After sorting: "+Arrays.toString(numbers));
    	
    	/**
    	 * You can make copies of arrays using copyof
    	 * which takes in the original array, the
    	 * length to copy and then a Class object
    	 * to specify the new array to be made
    	 */
    	
    	Object[] bruh = new Object[] {new Object(), new Object()};
    	Object[] bruhCopy = Arrays.copyOf(bruh, 2, bruh.getClass());
    	System.out.println(bruh == bruhCopy); // false
    	
    	/**
    	 * The equals method will look at two arrays
    	 * and if the elements are objects, check equality
    	 * between them.
    	 * 
    	 * However an array of arrays, means that the references
    	 * will be checked on the inner array (as they are outer
    	 * array elements).
    	 * 
    	 * For a full equality we use deepEquals
    	 * 
    	 */
    	Arrays.deepEquals(bruh, bruhCopy);
    	Arrays.equals(bruh, bruhCopy);
    }
}
