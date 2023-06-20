package ocp.concurrency.Concurrency;

public class BasicJob implements Runnable
{
	private final int number;
	
	public BasicJob(int number)
	{
		this.number = number;
	}

	@Override
	public void run()
	{
		// Pretend to do some work
		try { Thread.sleep(2000); } catch (InterruptedException e) { }
		
		System.out.println("Exiting '"+number+"'...");
	}
}
