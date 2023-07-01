package ocp.io.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;;

/**
 * I/O
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException
    {
    	/**
    	 * Creates a new file
    	 */
        File dataFile = new File("/tmp/test.txt");
        dataFile.createNewFile();
        
        /**
         * Create a stream to write to the file
         * 
         * I will then use a kind-of Writer
         * to write to it.
         * 
         * The PrintWriter also flushes on
         * any newline as specified by
         * the argument provided below
         */
        OutputStream out = new FileOutputStream(dataFile);
        PrintWriter writer = new PrintWriter(out, true);
        writer.println("ABBA");
        writer.print("Gucci");
//        writer.println();
        
        /**
         * Close the stream causes a flush of
         * any buffered data
         */
        writer.close();
        
        
        /**
         * A cool thing we can do is take the stream
         * below and then wrap it in a BufferedInputStream
         * which will read a certain amount on the first call
         * to read (even more than we may have requested)
         * and fill up an internal buffer.
         * 
         * THis way if we seek back and forth and read
         * we need not do several more system calls to
         * request those bytes as they'd be buffered
         * in for us and we are just moving back and forth
         * in userspace memory.
         * 
         * However, instead of creating it directly below
         * I will actually use a similar mechanic provided
         * byBufferedReader (it has to buffer because it
         * can read delimited items - such as lines).
         */
//        InputStream in = new BufferedInputStream(new FileInputStream(dataFile));
        InputStream in = new FileInputStream(dataFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        
        /**
         * Read two lines
         * 
         * It has support for CR, LF, (we using this
         * as the PrintWriter writes according to what
         * is standard on your platform and on mine
         * it is Linux/POSIX hence an LF)
         * and CRLF
         */
        String line1 = reader.readLine();
        String line2 = reader.readLine();
        
        System.out.println("Line 1: '"+line1+"'");
        System.out.println("Line 2: '"+line2+"'");
        
        /**
         * Close it
         */
        reader.close();
    }
}
