package ocp.OOP.oop;

/**
 * Because Person is sealed we must decide now whether
 * we allow inheriting from US (non-sealed), no inheritance
 * (final) or further constraining downwards (sealed+ permits again)
 *
 */
public non-sealed class Bantu extends Person
{
	private boolean zulu;

	public Bantu(String name, int age) 
	{
		super(name, age);
	}
	
	public Bantu(String name, int age, boolean zulu)
	{
		this(name, age);
		this.zulu = zulu;
	}
	
	@Override
	public void greet()
	{
		System.out.println("Hello, I speak Igbo even though not Bantu");
	}
	
	public void amandla()
	{
		System.out.println("Amaaandla!");
	}
	
	public boolean isZulu()
	{
		return zulu;
	}
}
