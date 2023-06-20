package ocp.concurrency.Concurrency;

import java.util.concurrent.Callable;

public class BasicTask implements Callable<Integer>
{
	private final int number;
	
	public BasicTask(int number)
	{
		this.number = number;
	}

	@Override
	public Integer call() throws Exception
	{
		System.out.println("Running for number: '"+number+"'");
		
		// Simulate some work being done
		Thread.sleep(2000);
		
		// Return your number multiplied by 2
		return number*2;
	}
}
