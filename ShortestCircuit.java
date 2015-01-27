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
import java.util.PriorityQueue;
import java.util.Vector;

public class ShortestCircuit
{

	/** flag: compile in debug mode */
	public static final boolean IS_DEBUG_MODE = false;

	public static void main( String[] args )
	{
		Vector<Integer> userInputs = new Vector<Integer>();
		readSTDIN( userInputs );
		if ( !isValidInput( userInputs ) )
			return;

		// create a new matrix for processing point distances
		double distMatrix[][] = IntegerHelper
				.createAdjacencyMatrix( userInputs );

		// PQ for keeping track of the best distances and their point pairs
		PriorityQueue<PointPair> priorityQueue = new PriorityQueue<PointPair>();

		// calculate the total number of points provided by the user
		int totalPoints = IntegerHelper.totalPoints( userInputs );
		populateDistanceMatrixAndPriorityQueue( distMatrix, totalPoints,
				userInputs, priorityQueue );

		// create and merge disjoint sets
		SetManager sm = new SetManager( totalPoints );
		while ( priorityQueue.size() > 0 && !sm.isDone() )
		{
			sm.attemptAdd( priorityQueue.poll() );
		}
		sm.printAddedSetList( distMatrix );
	}

	/**
	 * Populate the distance matrix and priority queue
	 * @param distMatrix double[][]
	 * @param totalPoints int
	 * @param ints Vector<Integer>
	 * @param pq PriorityQueue<PointPair>
	 */
	private static void populateDistanceMatrixAndPriorityQueue(
			double distMatrix[][], int totalPoints, Vector<Integer> ints,
			PriorityQueue<PointPair> pq )
	{
		// Remove duplicate calculations by eliminating the lower triangle
		//  on the adjacency matrix (diagOffset)
		int diagOffset = 1;
		for ( int src = 0; src < totalPoints; src++ )
		{
			for ( int dest = diagOffset; dest < totalPoints; dest++ )
			{
				distMatrix[src][dest] = IntegerHelper
						.distance( ints, src, dest );
				pq.add( new PointPair( src, dest, distMatrix[src][dest] ) );
			}
			++diagOffset;
		}
	}

	/**
	 * Read a series of integers or other inputs from the STDIN
	 * @param integers Vector<Integer>
	 */
	private static void readSTDIN( Vector<Integer> integers )
	{
		// keep track of stdin line number
		int lineNumber = 1;
		try
		{
			BufferedReader br = new BufferedReader( new InputStreamReader(
					System.in, "UTF-8" ) );

			String input;
			while ( (input = br.readLine()) != null )
			{
				IntegerHelper.sanitizeAndStore( input, integers );
				lineNumber++;
			}

		} catch ( IOException io )
		{
			System.err.println( "There was an error with your input on or "
					+ "near line " + (lineNumber / 2) + ". Exiting." );
			return;
		}
	}

	/**
	 * Is the user input valid?
	 * @param boolean
	 */
	private static boolean isValidInput( Vector<Integer> integers )
	{
		// Program assumes that there is an even number of user entries
		//  as each point requires two coordinates. Ensure this is the case.
		if ( integers.size() % 2 != 0 )
		{
			System.err.println( "Please enter an even number coordinates. Can "
					+ "not create points. Exiting." );
			return false;
		}
		return true;
	}
}
