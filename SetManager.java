import java.util.ArrayList;

public class SetManager
{
	private ArrayList<Set> mSets;

	public SetManager()
	{
		mSets = new ArrayList<Set>();
	}

	public void attemptAdd( PointPair p )
	{
		Debug.log( p + "" );

		// If the point pair is contained within the body of any sets, 
		//  it cannot be used
		for ( int i = 0; i < mSets.size(); i++ )
		{
			if ( mSets.get( i ).inBody( p ) )
			{
				return;
			}
			if ( mSets.get( i ).inHeadAndTail( p ) )
			{
				return;
			}

		}

		// If the pair is associated with the head or tail of a set, add it
		//  keeping track of added sets (if we have two they need merged) 
		ArrayList<Set> mFound = new ArrayList<Set>();
		for ( int i = 0; i < mSets.size(); i++ )
		{
			if ( mSets.get( i ).addEdge( p, (mFound.size() > 0) ) )
			{
				mFound.add( mSets.get( i ) );
			}
		}

		// if the point was added to multiple sets, merge the sets
		if ( mFound.size() > 1 )
		{
			mFound.get( 0 ).merge( mFound.get( 1 ) );
			mSets.remove( mFound.get( 1 ) );
			return;
		}

		// a set had the point appended, no need to create a new set
		if ( mFound.size() == 1 )
		{
			return;
		}

		// Else, create new set for these two points
		mSets.add( new Set( p.getSource(), p.getDestination() ) );
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
