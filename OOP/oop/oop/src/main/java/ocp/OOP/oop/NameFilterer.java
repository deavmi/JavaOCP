package ocp.OOP.oop;

/**
 * This is going to be used for the non-static (instance)
 * method reference for the <code>PersonFilter</code> system
 */
public class NameFilterer
{
	private int filterLen;
	
	public NameFilterer(int filterLen)
	{
		this.filterLen = filterLen;
	}
	
	public boolean nameLen(Person in)
	{
		if(in.getName().length() < filterLen)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
