/**
 * Helper functions for dealing with integer operations
 */

import java.util.Vector;

public class IntegerHelper
{

	/**
	 * Get the set of points at the specified index 
	 * @param ints Vector<Integer> - list of integers
	 * @param index int - the index to create the points at
	 * @return int[] - 0 = x, 1 = y (went with primitive data structure
	 *  to save processing time.
	 */
	public static int[] toPoint( Vector<Integer> ints, int index )
	{
		int rsp[] = new int[2];
		rsp[0] = ints.get( index );
		rsp[1] = ints.get( index + 1 );
		return rsp;
	}

	/**
	 * get the total number of points in the list of integers
	 * @param ints Vector<Integer>
	 * @return int
	 */
	public static int totalPoints( Vector<Integer> ints )
	{
		return ints.size() / 2;
	}

	/**
	 * Create a new adjacency matrix based off the current size of a list of
	 *  points
	 * @param ints Vector<Integer>
	 * @return double[][]
	 */
	public static double[][] createAdjacencyMatrix( Vector<Integer> ints )
	{
		return new double[totalPoints( ints )][totalPoints( ints )];
	}

	/**
	 * Calculate the distance between two points.
	 * @param ints Vector<Integer> ints
	 * @param i int - the i offset in the list of ints
	 * @param j int - the j offset in the list of ints
	 * @return double
	 */
	public static double distance( Vector<Integer> ints, int i, int j )
	{
		return Math.sqrt( Math.pow( (ints.get( j * 2 ) - ints.get( i * 2 )), 2 )
				+ Math.pow( ints.get( (j * 2) + 1 ) - ints.get( (i * 2) + 1 ),
						2 ) );
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
