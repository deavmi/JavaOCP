package ocp.OOP.oop;

/**
 * A record is really just a simple way of writing a class
 * 
 * The definition includes the constructor, this will
 * then automatically create a constructor for us to set
 * these <code>final</code> fields, the getters to retrieve
 * them, a toString() method to print them and an equals
 * method that checks equality field wise
 * 
 * All fields are final.
 * 
 * You can implement the getters yourself too and ad your
 * own methods. No instance variables of your own may be
 * declared however.
 * 
 * Static fields can be declared as well.
 * 
 * Also, any record declared in a nested manner are implicitly
 * static (unlike nested classes), meaning a static method in
 * SchoolGrade could access the nested record, instead of needing
 * a pre-existing record instance to access it for us.
 */
public record SchoolGrade(Person person, int finalMark)
{
	
	record Thing(int i)
	{
		
	}
	
	/**
	 * here we show how a static method can access the nested
	 * record as it is implicitly static
	 */
	public static Thing getAThing()
	{
		return new Thing(10);
	}
	
	/**
	 * NOTE: This is not needed below!!! But you can declare it
	 * to perform some checking before you save your values
	 */
//	public SchoolGrade(Person person, int finalMark)
//	{
//		this.person = person;
//		this.finalMark = finalMark;
//	}
	
	/**
	 * NOTE:
	 * 
	 * You can declare a record constructor as shown below
	 * then it will automatically scope in the formal
	 * parameters for you and automatically set them
	 * at the end.
	 */
	public SchoolGrade
	{
//		if(finalMark > 1)
//		{
//			person = null;
//		}
		
		/**
		 * Implicitly below we have
		 * 
		 * this.person = person;
		 * thos.finalMark = finalMark;
		 */
	}
}