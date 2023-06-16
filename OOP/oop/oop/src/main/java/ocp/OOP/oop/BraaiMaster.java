package ocp.OOP.oop;

/**
 * Implementing this means you can braai
 */
public interface BraaiMaster
{
	public void flip();
	public void grill();
	
	public default void sear()
	{
		System.out.println("I present the world's most basic boer");
	}
	
	public static void DoFlip(BraaiMaster e)
	{
		e.flip();
	}
}
