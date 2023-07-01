package ocp.concurrency.Locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Locks and thread-safety
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	/**
    	 * A ReentrantLock is the same as
    	 * the lock implicitly used for synchronized()
    	 * but it is an explicit handle on
    	 * one and also has extended capabilities
    	 */
    	ReentrantLock lock = new ReentrantLock();
    	
    	/**
    	 * First let's create a two threads
    	 * with which we can test this out
    	 */
    	Thread t1 = new WorkerThread(lock, 1);
    	Thread t2 = new WorkerThread(lock, 2);
//    	t1.start();
//    	t2.start();
//    	
//    	while(t1.isAlive() || t2.isAlive())
//    	{
//    		try
//    		{
//				t1.join();
//				t2.join();
//			} catch (InterruptedException e) {}
//    	}
//    	
    	System.out.println("-------------------------------");
    	
    	/**
    	 * It also provides an alternative
    	 * sync capability that prevents
    	 * a thread who JUST unlocked the
    	 * lock from locking it again
    	 * if there was someone else
    	 * (another thread) who during
    	 * this t1 execution, it (t2),
    	 * tried locking but failed.
    	 * 
    	 * It would then give t2 a chance
    	 * by virtue of not letting t1 lock
    	 * 
    	 * You can see this effect here
    	 * by the lack of starvation
    	 * that occurs. You will see
    	 * more flip-flopping between
    	 * the two
    	 */
    	lock = new ReentrantLock(true);
    	t1 = new WorkerThread(lock, 1);
    	t2 = new WorkerThread(lock, 2);
    	t1.start();
    	t2.start();
    	
    }
}
