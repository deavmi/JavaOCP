package ocp.basics;

/**
 * Basics
 * 
 * Practicing the absolute basics of Java
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        // Basics of primitives and Wrapper types
    	basicTypes();
    	
    	// Math API
    	basicMath();
    	
    	// Promotions and implicit widening rules
    	basicPromotion();
    }
    
    /**
     * Rules regarding basic type promotion
     */
    private static void basicPromotion()
    {
    	/**
    	 * The integral types are signed which
    	 * implies going from <code>byte</code>
    	 * to some _bigger_ types must sign-extend
    	 * into the bigger type
    	 */
    	byte small = -1;
    	System.out.println("small="+small);
    	
    	/**
    	 * It is for this reason that the above
    	 * number will not zero-extend to the
    	 * short below and become <code>255</code>
    	 * but rather extending the sign-bit to
    	 * the next higher byte
    	 */
    	short bigger = small;
    	System.out.println("bigger="+bigger);
    	
    	/**
    	 * The only unsigned type in Java is <code>char</code>
    	 * which is 16-bits (2 bytes), effectively making it
    	 * an unsigned <code>short</code>
    	 * 
    	 * Below we will cast the below (all 1-bits)
    	 * to a 16-bit char. If we print it directly then
    	 * it will be the Unicode representation of it
    	 * 
    	 * However, because it is unsigned, if we promote
    	 * it it will zero-extend upwards, maintaining its
    	 * value 65535.
    	 * 
    	 * After that we cast to a <code>short</code> which
    	 * is the same size but signed interpretation, hence
    	 * the <code>println()</code> overload will render
    	 * it as such
    	 */
    	char character = (char)-1;
    	System.out.println(character);
    	System.out.println((int)character);
    	System.out.println((short)character);
    	
    	
    	/**
    	 * Below we take x, a number of 10 digits, and store
    	 * it inside of y.
    	 * 
    	 * When we print it we get `1.23456794E9` which is
    	 * `1234567940.0`. We can see that having stored this
    	 * number in the float type it has lost precision.
    	 */
    	int x = 1234567890;
    	float y = x;
    	System.out.println(y);
    	
    	/**
    	 * Now if we do the following, the reason it seems we HAVEN'T lost precision
    	 * isn't that actually. It is because the implicit type coercion of the integer
    	 * x to that same inprecise value we saw earlier hence the two inprecise values
    	 * are of course equal
    	 */
    	System.out.println(x-y);
    	
    	/**
    	 * However, if we cast y back to an int then we can see if it maintained its value
    	 * or not, below we can see that that has not been the case as y's integer value
    	 * is not equal to x's
    	 */
    	System.out.println(x-(int)y);
    	
//    	x-=2;
//    	System.out.println(x-y);   
    }
    
    /**
     * The <code>Math</code> API
     */
    private static void basicMath()
    {
    	/**
    	 * Numerical constants
    	 */
    	double pi = Math.PI, euler = Math.E;
    	System.out.printf("pi=%f e=%f\n", pi, euler);
    	
    	/**
    	 * Mathematical functions
    	 */
    	int number = -5;
    	System.out.printf("%s=%d\n", "number", Math.abs(number));
    	
    	
    	/**
    	 * Below we shall overflow our primitive integer
    	 */
    	number = Integer.MAX_VALUE;
    	System.out.println("(before overflow) number="+number);
    	
    	/**
    	 * After overflow
    	 * 
    	 * No exception is thrown for an overflow
    	 */
    	number++;
    	System.out.println("(after overflow) number="+number);
    	
    	/**
    	 * How to detect an overflow though? Well, there is a
    	 * method in the <code>Math</code> API known as
    	 * <code>Math.addExact(int, int)</code> which adds two
    	 * <code>int</code> and if an overflow occurs throws an
    	 * exception
    	 */
    	try
    	{
    		Math.addExact(Integer.MAX_VALUE, 1);
    	}
    	catch(ArithmeticException e)
    	{
    		System.out.println("Error: "+e);
    	}
    	
    }
    
    /**
     * Basic types: Primitives and Wrapper types
     */
    private static void basicTypes()
    {
    	/**
         * Basic types we know of
         */
    	int number = 21;
    	System.out.println("number="+number);
    	
    	/**
    	 * Wrapper types are object versions
    	 * of primtives such as <code>Integer</code>
    	 * 
    	 * And they can be auto-boxed as shown below
    	 */
    	Integer numberW = number;
    	System.out.println("numberW="+numberW);
    	
    	// They can be explicitly boxed too
    	numberW = Integer.valueOf(number);
    	System.out.println("numberW="+numberW);
    	
    	/**
    	 * Automatic unboxing
    	 */
    	number = numberW;
    	System.out.println("number="+number);
    	
    	/**
    	 * Explicit unboxing
    	 */
    	number = numberW.intValue();
    	System.out.println("number="+number);
    }
}
