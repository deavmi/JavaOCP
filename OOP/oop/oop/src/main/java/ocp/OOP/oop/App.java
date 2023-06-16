package ocp.OOP.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Practicing Java object-orientation
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>();
        
        /**
         * Add some kind-ofs <code>Person</code>(s)
         */
        people.add(new Bantu("Holomisa", 21));
        people.add(new Mongoloid("Wojo bing chillin", 100));
        people.add(new Caucasian("Abigail", 20));
        
        /**
         * Print out them all
         */
        System.out.println(people);
        
        /**
         * See everyone's greeting
         */
        for(Person person: people)
        {
        	person.greet();
        }
        
        /**
         * We can use <code>instanceof</code> to do
         * runtime type checks and we can use the new
         * pattern matching in order to actually
         * automatically cast it on success
         */
        for(Person person: people)
        {
        	if(person instanceof Bantu bantuPerson)
        	{
        		bantuPerson.amandla();
        	}
        }
        
        /**
         * Pattern matching goes a step further,
         * however. So let's add another bantu
         * person but with a different age
         */
        people.add(new Bantu("Buthelezi", 22, true));
        for(Person person: people)
        {
        	if(person instanceof Bantu bantuPerson && bantuPerson.isZulu())
        	{
        		System.out.println("Yes, found the Prince: "+person);
        	}
        	
        	if(person instanceof BraaiMaster braaier)
        	{
        		braaier.flip();
        		braaier.grill();
        		braaier.sear();
        		BraaiMaster.DoFlip(braaier);
        	}
        }
        
        /**
         * We're going to call a function to illustrate
         * another idea as well
         */
        process(people.get(0)); //Amandla
        process(people.get(1));
        process(people.get(2));
        process(people.get(3)); //Amandla
        
        /**
         * Checking the overriden equality operator
         */
        System.out.println(new Bantu("Holomisa", 21).equals(new Bantu("Holomisa", 21))); //true (as overridden)
        
        /**
         * Below are the enum tests I want to do
         */
        EnumTests.go();
        
        /**
    	 * Let's use a functional interface, hjere
    	 * we will construct a new instance of
    	 * <code>NameFilterer</code> and grab its
    	 * instance method to use as the filtering
    	 * predicate
    	 */
    	NameFilterer filtrerer = new NameFilterer(8);
    	PersonFilter filter = filtrerer::nameLen;
    	Person[] filtered = filterBy(people.toArray(new Person[] {}), filter);
    	System.out.println("NameFiltered: "+Arrays.toString(filtered));
    	
    	/**
    	 * Below I will use a static method reference
    	 * for filtering by
    	 * 
    	 * Before I do this, I just add something that
    	 * would be accepted by this filter
    	 */
    	people.add(new Caucasian("Brian", 13));
    	filter = App::ageFilter;
    	filtered = filterBy(people.toArray(new Person[] {}), filter);
    	System.out.println("AgeFiltered: "+Arrays.toString(filtered));
    	
    	
    	new Bantu("Test", 21).doThing(1, 2, 4, 8);
    	new Bantu("Test", 21).doThing(1.0, 2.0, 4.0, 8.0);
    	
    	/**
    	 * Below we are testing type inference
    	 */
    	var person = new Bantu("Test", 21);
    	person.amandla();
    	
    	/**
    	 * Make an instance of the record class
    	 */
    	SchoolGrade schoolGrade = new SchoolGrade(person, 50);
    	System.out.println(schoolGrade);
    	System.out.println(schoolGrade.equals(new SchoolGrade(person, 41))); // Not equals
    	System.out.println(schoolGrade.equals(new SchoolGrade(person, 50))); // Equals
    	System.out.println(schoolGrade == new SchoolGrade(person, 41)); // Not equals
    	System.out.println(schoolGrade == new SchoolGrade(person, 50)); // Not equals
    }
    
    /**
     * Filters by age
     * 
     * @param in the <code>Person</code> being considered
     * @return <code>true</code> if accepted, <code>false</code>
     * otherwise
     */
    private static boolean ageFilter(Person in)
    {
    	if(in.getAge() < 15)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    private static void process(Person person)
    {
    	/**
    	 * If one has a <code>return</code> or
    	 * <code>throw</code> in a case whereby
    	 * the instanceof is false, then we can
    	 * assume that the type cast would work
    	 * in false case and the variable will
    	 * be available outside the pattern's
    	 * match block
    	 */
    	if(!(person instanceof Bantu bantuPerson))
    	{
    		return;
    	}
    	
    	/**
    	 * Because we would only reach here when
    	 * we ARE an instance of Bantu we
    	 * can then assume it
    	 */
    	bantuPerson.amandla();
    }
    
    /**
     * Filters the array by the given filter
     * *
     * @param input the input array
     * @param filter the filter to use
     * @return the filtered array
     */
    private static Person[] filterBy(Person[] input, PersonFilter filter)
    {
    	List<Person> filtered = new ArrayList<Person>();
    	for(Person person: input)
    	{
    		if(filter.match(person))
    		{
    			filtered.add(person);
    		}
    	}
    	
    	return filtered.toArray(new Person[] {});
    }
}
