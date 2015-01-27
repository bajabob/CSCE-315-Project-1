import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class SetManager
{
	private ArrayList<Set> mSets;

	private Stack<PointPair> mAddedSets;

	private int mTotalVertices;

	private double mTotalDistance;

	public SetManager( int totalVertices )
	{
		mTotalVertices = totalVertices;
		mSets = new ArrayList<Set>();
		mAddedSets = new Stack<PointPair>();
		mTotalDistance = 0;
	}

	/**
	 * Have we used every point in the coordinate system?
	 * @return boolean 
	 */
	public boolean isDone()
	{
		Debug.log( "total added sets: " + mAddedSets.size() );
		return (mAddedSets.size() >= mTotalVertices);
	}

	/**
	 * Print all the PointPairs currently stored in the added list
	 *  in order of how they should be traversed
	 */
	public void printAddedSetList( double distMatrix[][] )
	{
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add( mAddedSets.peek().getSource() );
		list.add( mAddedSets.pop().getDestination() );

		truncateAddedList( list );

		double distA = distMatrix[list.getFirst()][list.getLast()];
		double distB = distMatrix[list.getLast()][list.getFirst()];
		mTotalDistance += (distA == 0) ? distB : distA;

		for ( int i = 0; i < list.size(); i++ )
			System.out.println( list.get( i ) );
		System.out.println( list.get( 0 ) );
		System.out.println( mTotalDistance );
	}

	/**
	 * Truncate the added list, formulating the point connections as they 
	 *  are found within the linked list
	 * @param list LinkedList<Integer>
	 */
	private void truncateAddedList( LinkedList<Integer> list )
	{
		while ( mAddedSets.size() > 0 )
		{
			for ( int i = 0; i < mAddedSets.size(); i++ )
			{
				if ( mAddedSets.get( i ).getSource() == list.getFirst() )
				{
					list.addFirst( mAddedSets.remove( i ).getDestination() );
					continue;
				}
				if ( mAddedSets.get( i ).getSource() == list.getLast() )
				{
					list.addLast( mAddedSets.remove( i ).getDestination() );
					continue;
				}
				if ( mAddedSets.get( i ).getDestination() == list.getFirst() )
				{
					list.addFirst( mAddedSets.remove( i ).getSource() );
					continue;
				}
				if ( mAddedSets.get( i ).getDestination() == list.getLast() )
				{
					list.addLast( mAddedSets.remove( i ).getSource() );
					continue;
				}
			}
		}
	}

	/**
	 * Attempt to add a PointPair to the collection of sets
	 * @param p PointPair
	 */
	public void attemptAdd( PointPair p )
	{
		// If the point pair is contained within the body of any sets, 
		//  it cannot be used
		if ( !canAddEdge( p ) )
			return;

		// If the pair is associated with the head or tail of a set, add it
		//  keeping track of added sets (if we have two they need merged) 
		if ( canAddToExistingSets( p ) )
			return;

		// Else, create new set for these two points
		mSets.add( new Set( p.getSource(), p.getDestination() ) );
		mTotalDistance += p.getDistance();
		mAddedSets.push( p );
	}

	/**
	 * Can the point be added to an existing set or sets? If so, add it 
	 *  and merge the sets if necessary
	 * @param p PointPair
	 * @return boolean
	 */
	private boolean canAddToExistingSets( PointPair p )
	{
		ArrayList<Set> mFound = new ArrayList<Set>();
		for ( int i = 0; i < mSets.size(); i++ )
		{
			if ( mSets.get( i ).addEdge( p, (mFound.size() > 0) ) )
			{
				if ( mFound.size() == 0 )
				{ // only add sets once!
					mAddedSets.push( p );
					mTotalDistance += p.getDistance();
				}
				mFound.add( mSets.get( i ) );
			}
		}

		// if the point was added to multiple sets, merge the sets
		if ( mFound.size() > 1 )
		{
			mFound.get( 0 ).merge( mFound.get( 1 ) );
			mSets.remove( mFound.get( 1 ) );
			return true;
		}
		// a set had the point appended, no need to create a new set
		if ( mFound.size() == 1 )
		{
			return true;
		}
		return false;
	}

	/**
	 * Test whether or not an edge can be added to a set in the 
	 *  current collection
	 *  @param p PointPair
	 * @return boolean
	 */
	private boolean canAddEdge( PointPair p )
	{
		for ( int i = 0; i < mSets.size(); i++ )
		{
			if ( mSets.get( i ).inBody( p ) )
			{
				return false;
			}
			if ( mSets.get( i ).inHeadAndTail( p ) )
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString()
	{
		String s = "";
		for ( int i = 0; i < mSets.size(); i++ )
		{
			s += mSets.get( i ).toString() + "\n";
		}
		return s;
	}
}
