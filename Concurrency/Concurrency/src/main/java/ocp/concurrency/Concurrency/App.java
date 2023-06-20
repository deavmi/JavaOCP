package ocp.concurrency.Concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Concurrency practice
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        /**
         * The <code>Executor</code> provides us with a
         * task executing engine which let's us submit
         * tasks to it and manage them so forth.
         * 
         * The <code>ExecutorService</code> is a more
         * managed version of the above including
         * scheduling queues, the ability to submit tasks
         * and obtain a <code>Future</code> for them
         * (of which can be awaited upon once the task
         * completes). 
         */
    	ExecutorService executor;
    	
    	/**
    	 * We can use the static factory class <code>Executors</code>
    	 * to get an <code>ExcecutorService</code> configured to our
    	 * liking.
    	 * 
    	 * Let's use one that spawns 2 threads which will be used for
    	 * execution, and reused for the later submitted tasks. Therefore,
    	 * getting rid of any overhead of producing new ones and all
    	 * the memory copies and system calls involved with that
    	 */
    	executor = Executors.newFixedThreadPool(2);
    	
    	/**
    	 * Submit 4 of our <code>BasicTask</code>(s)
    	 * 
    	 * You can see the first two will print out
    	 * and then the next two afterwards as the
    	 * first two take up two of the allocated threads
    	 */
    	executor.submit(new BasicTask(2));
    	executor.submit(new BasicTask(4));
    	executor.submit(new BasicTask(6));
    	Future<Integer> taskFuture = executor.submit(new BasicTask(12));
    	System.out.println("Tasks submitted");
    	
    	/**
    	 * We obtain a <code>Future</code> for the submitted
    	 * task and can wait till it is done
    	 */
    	System.out.println("Waiting for result...");
		try
		{
			int result; result = taskFuture.get();
			System.out.println("Got result: '"+result+"'");
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		/**
		 * Submit some <code>Runnable</code>(s) here
		 * which return no value and which cannot throw
		 * an exception
		 */
		executor.submit(new BasicJob(5));
		executor.submit(new BasicJob(10));
		executor.submit(new BasicJob(15));
		
		/**
    	 * Shutdown the executor
    	 */
		executor.shutdown();
		
		/**
		 * We then will await termination of all jobs
		 * and throw an exception if we time out after
		 * 1 second of doing so.
		 * 
		 * This only works when <code>shutdown()</code>
		 * has been called FIRST
		 */
		try
		{
			boolean timedOut = executor.awaitTermination(1, TimeUnit.SECONDS);
			System.out.println("Did all jobs finish?: "+timedOut);
		}
		catch (InterruptedException e) 
		{
			
		}
    }
}
