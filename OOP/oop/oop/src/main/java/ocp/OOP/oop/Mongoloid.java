package ocp.OOP.oop;

public final class Mongoloid extends Person implements BraaiMaster
{
	public Mongoloid(String name, int age) 
	{
		super(name, age);
	}
	
	@Override
	public void greet()
	{
		System.out.println("Woooowowaaa wuuuuuurrrururuuu");
	}

	@Override
	public void flip()
	{
		System.out.println("The mongols actually eat a lot of meat, so flip it if you will");
	}

	@Override
	public void grill()
	{
		System.out.println("Mongolian shekels");
	}
	
	@Override
	public void sear()
	{
		System.out.println("Afrikaans mongol sears");
	}
}
