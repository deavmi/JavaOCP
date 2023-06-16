package ocp.streams.Streams;

/**
 * This is a functional interface for
 * the integer transformer which is
 * to take a number in, transform it
 * and spit a new number out
 */
@FunctionalInterface
public interface IntTransform
{
	public int transform(int in);
}
