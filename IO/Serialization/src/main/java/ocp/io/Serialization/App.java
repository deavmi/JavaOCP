package ocp.io.Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;

/**
 * Serialization and deserialization
 *
 */
public class App 
{
	
//	public record Person(String name, int age, boolean isMale)
//	{
//		
//	}
	
	public static class TestObj implements Serializable
	{
//		private static final long serialVersionUID = -8345846901675560226L;
		/**
		 * This is important. If one written an object 
		 * of this type to a file it is tagged as `testObj`
		 * and such a class will be looked for during
		 * deserialization
		 * 
		 * However, they could differ from the encoded version
		 * in terms of their members.
		 * 
		 * This ID is to be unique for this class such that
		 * two classes named TestObj can be checked at runtime
		 * during deserialization that it matches the remote
		 * version's serialization ID, and because these
		 * differ if not then it fails.
		 * 
		 * Serializabtion IDs match when the fields
		 * and their types are the same. Which is what
		 * we want to ensure as then the process works the
		 * same, else if we had same class anme but
		 * different fields it can throw the state of
		 * the deserializer off expecting something that
		 * is part of the next encoded object for example
		 */
		private static final long serialVersionUID = 8625120292857736370L;
		
		public int number = 2;
		public String text = "ABBA";
		
		public String toString()
		{
			return "[number: "+number+", text: "+text+"]";
		}
	}
	
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        /**
         * Anything which implements the
         * `Serializable` interface has
         * the ability to be written to
         * an object stream or read back
         * in from one and reconstructed
         */
    	File objectFile = new File("/tmp/objectFile.obj");
    	ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(objectFile));
    	
    	/**
    	 * Writing the same object twice means
    	 * that it is stored once and then
    	 * refereneced twice in the data format
    	 */
    	TestObj testObj = new TestObj();
    	objOut.writeObject(testObj);
    	objOut.writeObject(testObj);
    	TestObj testOb2 = new TestObj();
    	testOb2.text = "Howsit";
    	testOb2.number = 420;
    	objOut.writeObject(testOb2);
    	
    	objOut.close();
    	
    	/**
    	 * Open up an object input stream 
    	 * and read these back
    	 * 
    	 * You can see the affect of it
    	 * caching the first reconstructed
    	 * (deserialized) object by their
    	 * reference equality
    	 */
    	ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(objectFile));
    	TestObj objIn1 = (TestObj)objIn.readObject();
    	TestObj objIn2 = (TestObj)objIn.readObject();
    	System.out.println(objIn1 == objIn2);
    	System.out.println(objIn1);
    	
    	TestObj objIn3 = (TestObj)objIn.readObject();
    	System.out.println(objIn3);
    	
    	/**
    	 * Close the stream
    	 */
    	objIn.close();
    }
}
