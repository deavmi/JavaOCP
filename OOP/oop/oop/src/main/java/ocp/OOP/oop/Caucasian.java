package ocp.OOP.oop;

public final class Caucasian extends Person implements BraaiMaster
{
	public Caucasian(String name, int age) 
	{
		super(name, age);
	}
	
	@Override
	public void greet()
	{
		System.out.println("Good day sir");
	}

	@Override
	public void flip()
	{
		System.out.println("Epic white man flips a burger");
	}

	@Override
	public void grill()
	{
		System.out.println("Caucasian griller? I think not");
	}
}
