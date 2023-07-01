package ocp.concurrency.Locks;

import java.util.concurrent.locks.ReentrantLock;

public class WorkerThread extends Thread
{
	private ReentrantLock lock;
	private int id;

	public WorkerThread(ReentrantLock lock, int id)
	{
		this.lock = lock;
		this.id = id;
	}
	
	public void run()
	{
		int i = 0;
		while(i < 100)
		{
			lock.lock();
			System.out.println("Thread '"+id+"' has lock");

			/**
			 * Hold count which be the number of times
			 * this thread has locked this lock. If it
			 * is not currently acquired the lock then it
			 * is 0, if it has called lock() once and
			 * acquired the lock then 1. Since this is
			 * a recursive lock it can increase
			 */
//			lock.lock();
			System.out.println("Thread '"+id+"' has '"+lock.getHoldCount()+"' many holds on this lock");
//			lock.unlock();
			
			System.out.println("Other threads waiting for lock acquisition: "+lock.getQueueLength());
			
			/**
			 * tryLock() can try and timeout for you
			 * and on timeout returns <code>false</code>
			 * but on lock acquisition returns <code>true</code>.
			 * 
			 * It doesn't, however, honor fairness.
			 * If other threads were in the queue
			 * for locking then you would "barage"
			 * it and just get the lock if allowed,
			 * irrespective if it would have otherwise
			 * blocked to allow another thread to
			 * lock it for fairness.
			 * 
			 * This can be useful if amongst your
			 * fair scheduling of perhaps some
			 * work-stealing algorithmn you want
			 * to have some higher priority
			 * on a certain thread that can come
			 * in and obtain the lock for some supposedly
			 * important task that needs to occur
			 * over all other normal tasks sharing
			 * the lock.
			 * 
			 * It does not respect or detect
			 * interruption.
			 */
			
			/**
			 * tryLock(long, TimeUnit) allows one to
			 * try and timeout. It also will throw
			 * an InterruptedException (unblock
			 * then throw it) if it, the calling
			 * thread, was interrupted.
			 */
			
			lock.unlock();
			i++;
		}
	}
}
