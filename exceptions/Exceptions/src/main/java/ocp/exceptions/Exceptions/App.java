package ocp.exceptions.Exceptions;

/**
 * Here we will play with exceptions
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /**
         * Let's do a basic exception handling here
         */
    	int number = 5;
    	
    	try
    	{
    		// Attempt division by a random number 0 to 5
    		number = number/(int)(Math.random()*6);
    	}
    	catch(ArithmeticException e)
    	{
    		System.out.println("Oh no division by zero!");
    	}
    	// The final step always runs, irrespective if try succeeded or not
    	finally
    	{
    		System.out.println("Final answer is: "+number);
    	}
    	
    	try
    	{
    		if((int)(Math.random()*2) == 0)
    		{
    			throw new RuntimeException("Yessir");
    		}
    		else
    		{
    			throw new IllegalAccessException("Oh no!");
    		}
    	}
    	/**
    	 * We can catch both types below, they will therefore
    	 * be of the <code>Throwable</code> type
    	 */
    	catch(RuntimeException | IllegalAccessException e)
    	{
    		
    	}
    	
    	
    	/**
    	 * Let's now create something, a <code>Box</code>
    	 * which implements <code>AutoCloseable</code> and
    	 * can therefore work with try-with-resources.
    	 * 
    	 * It will therefore, come error or none,
    	 * have its <code>close()</code> method called on
    	 * it once we exit the TRY-block.
    	 * 
    	 * If we have an error, the catch TRY first
    	 * closes the resources and THEN runs the catch block
    	 */
    	try(
    			Box b1 = new Box();
    			Box b2 = new Box();
    		)
    	{
    		b1.bad();
    	}
    	catch (Exception e)
    	{
			System.out.println(e);
		}
    	finally
    	{
    		System.out.println("Done, no errors");
    	}
    }
}
