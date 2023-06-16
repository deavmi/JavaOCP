package ocp.streams.Streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.stream.Collectors;
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
    	
    	System.out.println();
    	System.out.println("---------------------------------------------------------");
    	System.out.println();
    	
    	/**
    	 * We can also do parallel streams. These
    	 * are streams which execute in parallel
    	 * (on separate threads).
    	 * 
    	 * A few threads will process elements from
    	 * the same stream.
    	 * 
    	 * For this reason I need a thread-safe way
    	 * of accessing a datatype and because 
    	 * <code>ArrayList</code> is not inherently
    	 * thread-safe I will have to do this with
    	 * Collections's <code>synchronizedList</code>.
    	 */
    	names = new ArrayList<String>();
    	names.add("Abby");
    	names.add("Becky");
    	names.add("Claire");
    	names.add("Dickhead");
    	names.add("Emma");
    	names.add("Fransisca");
    	names.add("Gabriella");
    	names.add("Jess");
    	names.add("Jane");
    	List<String> safeNames = Collections.synchronizedList(names);
    	
    	/**
    	 * Let's create our parallel stream now
    	 */
    	namesStream = safeNames.parallelStream();
    	
    	/**
    	 * Let's filter these on separate threads
    	 */
    	namesStream = namesStream.filter((name) -> (name.length() == 4 || name.length() == 5));
    	
    	/**
    	 * You can see the different threads race to
    	 * do the underlying system call to write to
    	 * the standard output and the race to this
    	 */
    	namesStream.forEach(System.out::println);
    	
    	
    	
    	/**
    	 * Let's do this again but use a collector as a way
    	 * of collecting all the results of the stream
    	 */
    	namesStream = safeNames.parallelStream()
    						   .filter((name) ->
    						   					{
    						   						return name.length() == 4 ||
    						   							   name.length() == 5;
    						   					});
    	
    	/**
    	 * We use a <code>Collector</code> as a way
    	 * to, well, collect the items produced
    	 * exhaustively by the stream
    	 * 
    	 * Now the combination technique used seems
    	 * to order them for the final list hence
    	 * we won't see any parallel artifacts
    	 */
    	List<String> collected = namesStream.collect(Collectors.toList());
    	System.out.println(collected);
    	
    	/**
    	 * Do example of reduction now
    	 */
    	reductionExample();
    	
    	/**
    	 * Do example of groupby now
    	 */
    	grouping();
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
    
    /**
     * Reduction usage
     */
    private static void reductionExample()
    {
    	System.out.println("-------");
    	
    	List<String> names = new ArrayList<String>();
    	names.add("Abby");
    	names.add("Becky");
    	names.add("Claire");
    	names.add("Dickhead");
    	names.add("Emma");
    	names.add("Fransisca");
    	names.add("Gabriella");
    	names.add("Jess");
    	names.add("Jane");
    	List<String> safeNames = Collections.synchronizedList(names);
    	
    	/**
    	 * Let's create our parallel stream now
    	 */
    	Stream<String> namesStream = safeNames.parallelStream();
    	
    	/**
    	 * Reduction allows one to perform an operation
    	 * of a function taking in (left, right) and
    	 * producing a result of the type of those two
    	 * elements (a <code>String</code> in this case).
    	 * 
    	 * However, the reduction part is that the first
    	 * argument to reduce is always `left` whilst the
    	 * `right` is (per iteration) a stream element
    	 */
    	String reduction = namesStream.reduce("Hello", (lStr, rStr) -> (lStr+rStr));
    	System.out.println(reduction);
    }
    
    public record Person(String name)
    {
    	
    }
    
    public record Receipt(Person person, int amount)
    {
    	
    }
    
    /**
     * Playing around with grouping
     */
    private static void grouping()
    {
    	System.out.println("-------");
    	
    	/**
    	 * Let's make a list of some receipts
    	 */
    	List<Receipt> receipts = new ArrayList<Receipt>();
    	receipts.add(new Receipt(new Person("Abby"), 100));
    	receipts.add(new Receipt(new Person("Becky"), 100));
    	receipts.add(new Receipt(new Person("Abby"), 60));
    	receipts.add(new Receipt(new Person("Claire"), 50));
    	receipts.add(new Receipt(new Person("Dickhead"), 100));
    	receipts.add(new Receipt(new Person("Abby"), 10));
    	receipts.add(new Receipt(new Person("Dickhead"), 100));
    	receipts.add(new Receipt(new Person("Emma"), 100));
    	receipts.add(new Receipt(new Person("Abby"), 1000));
    	receipts.add(new Receipt(new Person("Abby"), 1));
    	receipts.add(new Receipt(new Person("Dickhead"), 70));
    	receipts.add(new Receipt(new Person("Jess"), 100));
    	
    	
    	/**
    	 * Let's create a stream of <code>Receipt</code>'s
    	 */
    	List<Receipt> safeReceipts = Collections.synchronizedList(receipts);
    	Stream<Receipt> receiptStream = safeReceipts.parallelStream();
    	
    	/**
    	 * We will now collect these using a <code>groupby()</code>
    	 * <code>Collector</code>.
    	 * 
    	 * We will pass a function that will be passed the stream's
    	 * current element and return some value of type T which
    	 * will be used as the key in a map. Each time it matches
    	 * a given key it will append to a list of Receipts of
    	 * that key
    	 * 
    	 * For example, we will write a function which takes in a
    	 * <code>Receipt</code> and then we pass it the method
    	 * reference <code>Receipt::person</code> and it will
    	 * match all Receipt's with that person to a list for
    	 * receipts made by that person. This method reference
    	 * returns the current stream element's person object
    	 * and that will be placed as the key in a Map<T,List<Receipt>>
    	 * and because the equality of records is based on the
    	 * components of it (Person just has a name) it will find
    	 * multiple instances with matching `name`'s and hence
    	 * place those in the same bucket
    	 * 
    	 * There is a concurrent collector and non-concurrent,
    	 * the concurrent one is good to parallelize but of
    	 * course the underlying map implementation must be good.
    	 * 
    	 * But you can see how that affects the order of listed
    	 * receipts as the value for a given key.
    	 */
    	Map<Person, List<Receipt>> receiptsGrouped = receiptStream.collect(Collectors.groupingByConcurrent(Receipt::person));
    	
    	for(Entry<Person, List<Receipt>> entry: receiptsGrouped.entrySet())
    	{
    		System.out.println("Purchases by '"+entry.getKey()+"': "+entry.getValue());
    	}

    	
    	
    }
}
