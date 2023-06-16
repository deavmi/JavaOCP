package ocp.OOP.oop;

import java.time.Instant;

public sealed class Person permits Bantu, Caucasian, Mongoloid
{
	private String name;
	private int age;
	
	private static String myField1 = Instant.now().toString();
	private static String myField2;
	
	/**
	 * Static blocks run as part of static initialization
	 */
	static
	{
		myField2 = Instant.now().toString();
		
		System.out.println("This is a static init");
	}
	
	/**
	 * Instance block run per instance construction
	 */
	{
		System.out.println("Static field 1: "+myField1);
		System.out.println("Static field 2: "+myField2);
	}
	
	public Person(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	
	public void greet()
	{
		System.out.println("Hello from earth");
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void doThingInteger(int a)
	{
		System.out.println("I did a thing with '"+a+"'");
	}
	
	public void doThingDouble(double a)
	{
		System.out.println("I did a thing with '"+a+"'");
	}
	
	/**
	 * This is a var-arg method
	 * 
	 * @param a
	 */
	public void doThing(int... a)
	{
		for(int item: a)
		{
			doThingInteger(item);
		}
	}
	
	/**
	 * This is a var-arg overload
	 * 
	 * @param a
	 */
	public void doThing(double... a)
	{
		for(double item: a)
		{
			doThingDouble(item);
		}
	}
	
	@Override
	public boolean equals(Object lhs)
	{
		// If the left-hand side is not a person then false
		if(!(lhs instanceof Person person))
		{
			return false;
		}
		
		// Compare fields
		return this.name.equals(person.name) && this.age == person.age;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getName()+" [name: "+name+", age: "+age+"]";
	}
}
