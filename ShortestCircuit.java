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
	public static final boolean IS_DEBUG_MODE = true;

	public static void main( String[] args )
	{

		Debug.log( "Running in Debug Mode" );

		// keep track of stdin line number
		int lineNumber = 1;

		// Store all coordinates linearly until later when they
		//  are used in the creation of a matrix. Saves processing 
		//  by not creating Point objects
		Vector<Integer> integers = new Vector<Integer>();

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

		Debug.log( integers );

		// Program assumes that there is an even number of user entries
		//  as each point requires two coordinates. Ensure this is the case.
		if ( integers.size() % 2 != 0 )
		{
			System.err.println( "Please enter an even number coordinates. Can "
					+ "not create points. Exiting." );
			return;
		}

		int processors = Runtime.getRuntime().availableProcessors();
		Debug.log( "Number of processors available: " + processors );

		// create a new matrix for processing point distances
		double adjMatrix[][] = IntegerHelper.createAdjacencyMatrix( integers );

		// PQ for keeping track of the best distances and their point pairs
		PriorityQueue<PointPair> priorityQueue = new PriorityQueue<PointPair>();

		// Remove duplicate calculations by eliminating the lower triangle
		//  on the adjacency matrix (diagOffset)
		int diagOffset = 1;
		int totalPoints = IntegerHelper.totalPoints( integers );
		for ( int src = 0; src < totalPoints; src++ )
		{
			for ( int dest = diagOffset; dest < totalPoints; dest++ )
			{
				adjMatrix[src][dest] = IntegerHelper.distance( integers, src,
						dest );
				priorityQueue.add( new PointPair( src, dest,
						adjMatrix[src][dest] ) );
			}
			++diagOffset;
		}

		Debug.log( "Total Distances: " + priorityQueue.size() );

		if ( IS_DEBUG_MODE )
		{
			PriorityQueue<PointPair> copy = new PriorityQueue<PointPair>();
			copy.addAll( priorityQueue );
			int count = 1;
			while ( copy.size() > 0 )
			{
				System.out.println( (count++) + " " + copy.poll() );
			}
		}

		SetManager sm = new SetManager();
		while ( priorityQueue.size() > 0 )
		{
			sm.attemptAdd( priorityQueue.poll() );
			Debug.log( sm + "" );
			Debug.log( "---------------------" );
		}

	}
}
