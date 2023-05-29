package ocp.Strings;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Basics
 * 
 * Practicing things like <code>String</code>
 * <code>StringBuilder</code> and text blocks
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	/**
    	 * String-related
    	 */
    	stringTests();
    	
    	/**
    	 * Text blocks
    	 */
    	textBlocksTests();
    	
    	/**
    	 * <code>StringBuilder</code>-related
    	 */
    	stringBuilder();
    }
    
    /**
     * String builder
     */
    private static void stringBuilder()
    {
    	/**
    	 * <code>StringBuilder</code> provides a thread-unsafe (hence
    	 * only one thread at a time should use it) mutable character
    	 * sequence that can be efficiently used for removing replacing
    	 * and adding characters to
    	 */
    	StringBuilder builder = new StringBuilder();
    	
    	// Append a string
    	builder.append("Hello");
    	
    	// Manipulate characters in the buffer
    	builder.setCharAt(2, 'B');
    	builder.setCharAt(3, 'B');
    	
    	// Remove a character
    	builder.deleteCharAt(0);
    	
    	// Now create a string from current buffer state
    	System.out.println(builder.toString());
    	
    	// Insert a string in this place and push everything up
    	builder.insert(1, "II");
    	
    	// Now create a string from current buffer state
    	System.out.println(builder.toString());
    }
    
    /**
     * Learning about text blocks
     */
    private static void textBlocksTests()
    {
    	// Below is a normal string literal
    	String str = "Hello there";
    	System.out.printf("'%s'\n", str);
    	
    	// Below is a textblock
    	
    	/**
    	 * Below is a textblock
    	 * 
    	 * It is 11 characters, Hello (5) \n (1) and there (5)
    	 * implying the textblock ALWAYS starts on a new line
    	 * after the `"""` upon which then the actual string starts.
    	 * 
    	 * If one consistently aligns the first character of each
    	 * line that continues after the opening `"""` then there
    	 * will be no padding and it will mean that we have "Hello\nthere"
    	 * 
    	 * Also any stuff appearing AFTER the line which is
    	 * trailing whitesoace is stripped
    	 * 
    	 * They must be aligned with the SAME character
    	 */
    	str = """
      		  Hello  
      		  there  """;
      	System.out.printf("'%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); // 11
      	
      	// Ditto below alignment
      	str = """
      			Hello
      			there""";
      	System.out.printf("'%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); // 11
      	
      	// Ditto below alignment
      	str = """
         Hello
         there""";
      	System.out.printf("'%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); // 11
      	
      	
    	
    	/**
    	 * Below we slightly offset
    	 * 
    	 * Then starts the actual counting of whitespace
    	 */
    	str = """
      		  Hello
      		   there""";
      	System.out.printf("'%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); // 12
      	
      	/**
      	 * The great thing about text blocks is that we need not escape
      	 * 
      	 * Also note the last `"""` implies that a newline will be added
      	 * if it occurs on a new line.
      	 * 
      	 * Another note, anything after (space or tabs) a `"""` in the 
      	 * opening clause adds nothing and before closing `"""` the
      	 * same applies. I mean this as shown below.
      	 * 
      	 * 
      	 */
      	str = """   
      			"This is epic"
      			d
      			 		""";
      	System.out.printf("String needing no escaping: '%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); //17
      	
      	/**
      	 * HOWEVER, as soon as you place something before the whitespace
      	 * that comes before the CLOSING `"""` then you will count it
      	 */
      	str = """          
      			This
      			 is
      			my
      			 		string""";
      	System.out.printf("String needing no escaping: '%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); //21
      	
      	/**
      	 * Opting out of incidental spacing can be done by making the
      	 * CLOSING `"""` appear with no whitespace whatsoever before it
      	 */
      	str = """
      		 This
      		 is     
""";
      	System.out.printf("String needing no escaping: '%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); //26
      	
      	
      	/**
      	 * A neat feature to format text in a code-readbale
      	 * way but to not have it actually insert more stuff
      	 * is to place the <code>\</code> line terminators
      	 * at the end which stops a linefeed being inserted
      	 */
      	str = """
      		This\
      		 Is \
      		Epic""";
      	System.out.printf("String needing no escaping: '%s'\n", str);
      	System.out.println("Len of textblock: "+str.length()); //12
    }
    
    /**
     * Learning how to work with strings, indexing into
     * them, converting, interning, comparing
     */
    private static void stringTests()
    {
    	/**
         * A <code>String</code> constant is already interned
         * (compile time will generate code to do so for us)
         * 
         * Here we have two variables of type <code>String</code>
         * and both are set to the same string constant "Hello"
         * which means that compiler will generate a single instance
         * of <code>String</code> and assign that reference to both
         * str1 and str2. This is confirmed by reference equality
         * which follows.
         */
    	String str1 = "Hello";
    	String str2 = "Hello";
    	System.out.println(str1 == str2);
    	
    	/**
    	 * What is actually happening is the following
    	 * 
    	 * 1. We first have "Hello" translated into
    	 * `new char[]{'H', 'e', 'l', 'l', 'o'}`
    	 * and then to the String constructor as follows
    	 * 2. If we now look the references are not equal
    	 * but the character sets are
    	 */
    	char[] data = new char[] {'H', 'e', 'l', 'l', 'o'};
    	String str3 = new String(data);
    	System.out.println(str2 == str3);
    	
    	/**
    	 * ... Continuing we shall find then that what it
    	 * does upon construction is actually this
    	 * (shown below). This will obviously mean the 
    	 * String created from `new String(data)` has
    	 * no more reference to it and will get GC'd
    	 * and we'd be left with the interned string
    	 * pointed to by str1, str2 and (now) str4
    	 * 
    	 * Hence `"Hello"` is equivalent to `new String(char[]).intern()` 
    	 */
    	String str4 = new String(data).intern();
    	System.out.println(str2 == str4);
    	
    	/**
    	 * Some useful string methods
    	 * 
    	 * Equality, comparison etc.
    	 */
    	String s1 = "A", s3 = "A", s4 = "a";
    	String s2 = "C";
    	System.out.println(s1.compareTo(s2)); // negative integer as s1 < s2
    	System.out.println(s2.compareTo(s1)); // positive integer as s2 > s1
    	System.out.println(s1.compareTo(s3)); // 0 as equal
    	System.out.println(s1.equals(s2)); // false (as unequal)
    	System.out.println(s1.equals(s3)); // true (as equal)
    	System.out.println(s1.equalsIgnoreCase(s4)); // true (as equal case-insensitively)
    	System.out.println(Arrays.toString("Hello".toCharArray())); // Gives us the string as a char array
    	
    	/**
    	 * Some more useful, lesser-known methods
    	 */
    	String myString = "  Spaced  out       ";
    	System.out.printf("myString='%s'\n", myString);
    	System.out.printf("myString(trim)='%s'\n", myString.trim());
    	
    	/**
    	 * Conversions and so on
    	 * 
    	 * Remember UTF-16 is used (so two byte pairs) for
    	 * the Basic Multilingual Plane. Fore more letters/characters
    	 * surrogate pairs are used which Java then knows means
    	 * it is building a higher Unicode character out of
    	 * (from the Unicode plane). This would then be, on Linux,
    	 * converted to UTF-8 when written to stdout for rendering
    	 * as the VT100 renders bytes based on UTF-8 when mapping
    	 * to character/font map
    	 */
    	String complex = "בג"; //Cursor moves right-to-left where right most is first
    	System.out.printf("complex='%s'\n", complex);
    	
    	/**
    	 * Below we shall convert the Hebrew BMP
    	 * characters to bytes and we shall see they
    	 * use two byte pairs to make up a single
    	 * character, high byte will be non-zero too
    	 * 
    	 * We convert to UTF-16LE which is a nop-conversion
    	 * 
    	 * (We do LE as UTF-16 places a BOM or byte-order mark
    	 *  such that it knows which ordering was used for pairs
    	 *  and big endian systems must read them in flipped
    	 *  order then)
    	 *  
    	 * We shall see for the two characters `בג` we have
    	 * two bytes per-each. Therefore <code>charAt(x)</code>
    	 * works by providing us with the letter at position
    	 * x (not the byte position)
    	 */
    	byte[] bytes = complex.getBytes(Charset.forName("UTF-16LE"));
    	System.out.println(Arrays.toString(bytes));
    	System.out.println("complex(0)='"+complex.charAt(0)+"'"); // ב
    	System.out.println("complex(1)='"+complex.charAt(1)+"'"); // ג
    	
    	/**
    	 * We can also do some substrings
    	 */
    	String text = "Hello there friend";
    	System.out.println(text.substring(6)); // "there friend"
    	System.out.println(text.substring(6, 11)); // "there"
    	
    	/**
    	 * If you want regex matching as well
    	 * 
    	 * FIXME: Play with this and regex in general
    	 */
    	String text2 = "Hound Bound Round Mound Found Ground";
    	System.out.println("Match: " +text2.matches(".*ound")); //anything that ends in `ound`
    	
    	/**
    	 * Indenting strings
    	 */
    	String text3 = "Hello\nWorld";
    	System.out.println("Before indent");
    	System.out.println(text3);
    	System.out.println("After indent");
    	System.out.println(text3.indent(2));
    }
}
