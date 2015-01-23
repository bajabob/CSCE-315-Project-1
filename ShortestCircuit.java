/**
 * Main entry point for application
 * For more information pertaining to this 
 * 	application please refer to the README
 * 
 * @author Robert Timm | bobtimm@tamu.edu 
 * CSCE-315-501 
 * Due: January 30, 2015
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class ShortestCircuit
{

	/** flag: compile in debug mode */
	public static final boolean IS_DEBUG_MODE = true;

	public static void main( String[] args )
	{

		Debug.log( "Running in Debug Mode" );

		// keep track of stdin line number
		int lineNumber = 1;

		Vector<Integer> integers = new Vector<Integer>();

		try
		{
			BufferedReader br = new BufferedReader( new InputStreamReader(
					System.in, "UTF-8" ) );

			String input;

			//printLineNumber( lineNumber++ );

			while ( (input = br.readLine()) != null )
			{
				//printLineNumber( lineNumber++ );
				sanitizeAndStore( input, integers );
			}

		} catch ( IOException io )
		{
			System.err.println( "There was an error with your input on line "
					+ lineNumber );
		}

		Debug.log( integers );

	}

	/**
	 * Print a formated line number to standard out
	 * @param lineNumber int - the line number to print
	 */
	private static void printLineNumber( int lineNumber )
	{
		System.out.print( String.format( "%4d: ", lineNumber ) );
	}

	/**
	 * Check whether or not the supplied string is an integer
	 * @param s String
	 * @return boolean
	 */
	public static boolean isInteger( String s )
	{
		try
		{
			int i = Integer.parseInt( s );
		} catch ( NumberFormatException e )
		{
			return false;
		}
		return true;
	}

	/**
	 * Sanitize a user input of everything except whole numbers
	 *  and store in supplied vector. 
	 * @param input String - the user input
	 * @param ints Vector<Integer> - for storing integer data
	 */
	public static void sanitizeAndStore( String input, Vector<Integer> ints )
	{
		// disregard comments and null inputs
		if ( input == null || input.contains( "#" ) )
		{
			return;
		}

		// replace all non-numeric characters with a dash
		String sanitized = input.replaceAll( "[^0-9.]", "-" );

		// split all items by a dash into a sub array
		String[] split = sanitized.split( "-" );

		for ( int i = 0; i < split.length; i++ )
		{
			if ( isInteger( split[i] ) )
			{
				ints.add( Integer.parseInt( split[i] ) );
			}

		}
	}

}
