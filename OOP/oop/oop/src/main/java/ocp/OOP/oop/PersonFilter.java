package ocp.OOP.oop;

/**
 * This is a functional interface which means it has a single
 * abstract method declared and an be used to match to method
 * references of the same signature
 */
@FunctionalInterface
public interface PersonFilter
{
	public boolean match(Person in);
}
