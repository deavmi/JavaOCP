package ocp.streams.Streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.*;

/**
 * Here we will play with lambdas and streams!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	/**
    	 * Make an integer transform using
    	 * a lambda which doubles
    	 * 
    	 * Syntax: (<args>) -> (expression to return)
    	 */
    	int[] data1 = {1,2,3};
    	IntTransform doubler = (intIn) -> (intIn*2);
    	transformArray(doubler, data1);
    	System.out.println("Transformed: "+Arrays.toString(data1));
    	
    	/**
    	 * Let's try a different lambda
    	 */
    	int[] data2 = {1,2,3};
    	transformArray((intIn) -> intIn*(int)(Math.random()*3), data2);
    	System.out.println("Transformed: "+Arrays.toString(data2));
    	
    	/**
    	 * Let's play with the Streams API which basically
    	 * let's us pass lambads into things and work
    	 * on a element-by-element basis.
    	 * 
    	 * We can use the <code>Collection</code> interface
    	 * on any data type implementing it to do this
    	 */
    	List<String> names = new ArrayList<String>();
    	names.add("Abby");
    	names.add("Becky");
    	names.add("Claire");
    	names.add("Dickhead");
    	names.add("Emma");
    	names.add("Fransisca");
    	names.add("Gabriella");
    	
    	
    	/**
    	 * We get `Stream<String>` which has various operations
    	 * on it which we can use, it streams an element
    	 * by element
    	 */
    	Stream<String> namesStream = names.stream();
    	
    	/**
    	 * Now let's filter them for ones that are
    	 * only 5 or 4 of length. This now returns
    	 * another `Stream<String>` which has the
    	 * original one embedded in it to retrieve
    	 * from, then it will do test(String) and
    	 * if true, return to us, else drop.
    	 * 
    	 * All these things are just linking
    	 * streams with chained production
    	 * and then only producing if appropriate
    	 */
    	namesStream = namesStream.filter((name) -> (name.length() == 4 || name.length() == 5));
    	
    	/**
    	 * Let's now create a new Stream<Integer> which is derived
    	 * from creating a map using the `namesStream` as input
    	 * to the `Stream<Integer>`
    	 */
    	Stream<Integer> newStream = namesStream.map(App::NameToLen);
    	
    	
    	/**
    	 * We now run the stream till exhaustian with a `Consumer`
    	 * which matches the `Consumer<T>` interface, here it would
    	 * be `Consumer<Object>` of which exists as `println(Object)`
    	 * (as it is an `Integer`)
    	 */
    	newStream.forEach(System.out::println);
    	
    	/**
    	 * The <code>of()</code> method let's you easily create streams
    	 * based on iterables and so forth
    	 */
    	Stream<Integer> stream1 = Stream.of(new Integer[] {0,1,2,3,4});
    	Stream<Integer> stream2 = Stream.of(new Integer[] {9,5,6,7,8});
    	
    	/**
    	 * We can use <code>concat()</code> to combine two streams
    	 * such that the first will be exhausted and then moving
    	 * onto the next
    	 */
    	Stream<Integer> streamConcatted = Stream.concat(stream1, stream2);
    	
    	/**
    	 * Peeking (<code>peek()</code>) creates a new stream that
    	 * when activated will run our consumer (maybe to print out
    	 * the elements as they come through) but then also produce
    	 * it out its end so we can continue the streaming pipeline
    	 */
    	streamConcatted = streamConcatted.peek(App::peek);
    	
    	System.out.println("----------");
    	
    	/**
    	 * <code>skip(int)</code> produces a stream that drops the
    	 * first `n` elements produced by the source stream
    	 */
    	streamConcatted = streamConcatted.skip(5);
    	
    	/**
    	 * <code>sorted()</code> returns a new stream that will
    	 * have all elements it produces be in sorted order.
    	 * 
    	 * I PRESUME this will have to consume everything in the
    	 * source stream to do this. And voila! It must, it is
    	 * a example of a stateful operation. You can see this
    	 * affecting the peek oneshot through it all (exhausting
    	 * the source stream)
    	 */
    	streamConcatted = streamConcatted.sorted();
    	
    	/**
    	 * Now let's consume it all
    	 */
    	streamConcatted.forEach(System.out::println);
    }
    
    private static void peek(Integer i)
    {
    	System.out.println("peek: "+i);
    }
    
    private static void peekString(String i)
    {
    	System.out.println("parallelPeek: "+i);
    }
    
    private static Integer NameToLen(String name)
    {
    	return name.length();
    }
    
    /**
     * Transforms each number in the given array by the
     * provided transformer
     * 
     * @param transformer the transformer to use
     * @param data the data to transform
     */
    public static void transformArray(IntTransform transformer, int[] data)
    {
    	for(int i = 0; i < data.length; i++)
    	{
    		data[i] = transformer.transform(data[i]);
    	}
    }
}
