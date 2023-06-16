package ocp.OOP.oop;

/**
 * Playing around with enums
 * 
 */
public class EnumTests
{
	/**
	 * You best believe the ordinality is 2
	 */
	public enum Gender
	{
		MALE,
		FEMALE
	}
	
	/**
	 * You can define a constructor for an enum
	 * then the values in () next to constants
	 * will be constructed using them as arguments
	 * to the `BodyType(int, int)` constructor.
	 * 
	 * After semi-colon it's like a normal class.
	 */
	public enum BodyType
	{
		SLIM (60, 182),
		NORMAL (75, 170),
		FAT (100, 160); // Last item needs semicolon before methods appear
		
		private int myWeight, myHeight;
		BodyType(int weight, int height)
		{
			myWeight = weight;
			myHeight = height;
		}
		
		public int getWeight()
		{
			return myWeight;
		}
		
		public int getHeight()
		{
			return myHeight;
		}
	}
	
	/**
	 * Enum-related tests
	 */
	public static void go()
	{
		/**
		 * An enum is a class-type hence every
		 * instance if a reference and the constants
		 * are unique objects so reference equality
		 * can be used
		 */
		Gender p1 = Gender.MALE;
		Gender p2 = Gender.MALE;
		Gender p3 = Gender.FEMALE;
		System.out.println(p1 == p2); //true
		System.out.println(p1 == p3); //false
		
		/**
		 * Let's make some
		 */
		BodyType b1 = BodyType.NORMAL;
		BodyType b2 = BodyType.NORMAL;
		BodyType b3 = BodyType.FAT;
		System.out.println(b1 == b2); //true
		System.out.println(b1 == b3); //false
		System.out.println(b1);
		
	}
}
