/**
 * Debug logging
 * @author Robert Timm | bobtimm@tamu.edu
 */
import java.util.Vector;

public class Debug
{

	/**
	 * Log a debug message to standard out only if debug mode is enabled
	 * @param msg String - the message
	 */
	public static void log( String msg )
	{
		if ( ShortestCircuit.IS_DEBUG_MODE )
		{
			System.out.println( msg );
		}
	}

	/**
	 * Log a debug message to standard out only if debug mode is enabled
	 * @param matrix[][] double - the matrix to display
	 */
	public static void log( double matrix[][] )
	{
		if ( ShortestCircuit.IS_DEBUG_MODE )
		{
			for ( int i = 0; i < matrix.length; i++ )
			{
				for ( int j = 0; j < matrix[0].length; j++ )
				{
					System.out.print( String.format( "%6.2f ", matrix[i][j] ) );
				}
				System.out.print( "\n" );
			}
		}
	}

	/**
	 * Log a debug message to standard out only if debug mode is enabled
	 * @param integers Vector<Integer> - the vector to print
	 */
	public static void log( Vector<Integer> integers )
	{
		if ( ShortestCircuit.IS_DEBUG_MODE )
		{
			System.out.println( "List of integers: " );
			for ( Integer i : integers )
			{
				System.out.println( i );
			}
		}
	}
}
