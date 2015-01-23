import java.util.Vector;

/**
 * Debug logging
 * @author Robert Timm | bobtimm@tamu.edu
 */
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
	 * @param integers Vector<Integer> integers - the vector to print
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
