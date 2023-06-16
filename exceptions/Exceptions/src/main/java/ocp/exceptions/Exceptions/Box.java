package ocp.exceptions.Exceptions;

public class Box implements AutoCloseable
{

	@Override
	public void close() throws Exception
	{
		System.out.println("Hey, here is the close!");
	}

	public void bad() throws Exception
	{
		throw new Exception("Aaaah!");
	}

}
