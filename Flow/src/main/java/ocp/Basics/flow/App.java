package ocp.Basics.flow;

/**
 * I am aware of most control-flow in Java
 * so this will really just be practicing
 * the things I don't know
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/**
    	 * Switch statements
    	 */
    	switchTests();
    }

    /**
     * Switch-statement related tests
     */
	private static void switchTests()
	{
		/**
		 * Re-call you need to have the break statements else it falls
		 * right through
		 */
		int number = (int)(Math.random()*2);
		switch(number)
		{
			case 0:
				System.out.println("Zero is the hero!");
				break;
			case 1:
				System.out.println("1 gets the bun 🍞️");	
		}
		
		/**
		 * One can also have a `default` case which is,
		 * well, the match all case
		 */
		number = (int)(Math.random()*4);
		switch(number)
		{
			case 0:
				System.out.println("Zero is the hero!");
				break;
			case 1:
				System.out.println("1 gets the bun 🍞️");
				break;
			/**
			 * Equivalent to <code>case 2: case 3:</code>
			 * which falls through from 2 to 3 to the end
			 * of the switch case 
			 */
			default:
				System.out.println("Default! "+number);
		}
		
		/**
		 * One can also switch on strings, in which case
		 * it is checking the <code>.equals</code> method
		 * on these items
		 */
		String text = (int)(Math.random()*2) == 0 ? "Hello" : "Bye";
		switch(text)
		{
			case "Hello":
				System.out.println("Hi there");
				break;
			case "Bye":
				System.out.println("Bye then!");
		}
		
		/**
		 * Below we will investigate the use of the so-called
		 * "switch expression" which let's us use a switch case
		 * as part of an expression with the resulting case being
		 * the evaluation 
		 */
		String result = switch(text)
				{
					case "Hello" -> "Result is Hi there!";
					case "Bye" -> "Result is Bye then!";
					default -> "Ek weet nie";
				};
		System.out.println("Result: '"+result+"'");
		
		/**
		 * We also can do the switch expressions WHILST still doing
		 * some other code before returning by using the <code>yield</code>
		 * keyword
		 */
		result = switch(text)
				{
					case "Hello":
						System.out.println("Hiiiiii");
						yield "We got Hiiiii";
					case "Bye":
						System.out.println("Biiiii");
						yield "We got Biiiii";
					default:
						yield "Niks";
				};
		System.out.println("Result: '"+result+"'");
		

		/**
		 * Now the only problem with the above is forgetting a
		 * <code>yield</code> means it can fall through, but
		 * if we write it as follows then the compiler will always
		 * warn us
		 */
		result = switch(text)
				{
					case "Hello" ->
					{
						System.out.println("Hiiiiii");
						yield "We got Hiiiii";
					}
					case "Bye" ->
					{
						System.out.println("Biiiii");
						yield "We got Biiiii";
					}
					default ->
					{
						yield "Niks";
					}
				};
		System.out.println("Result: '"+result+"'");

	}
}
