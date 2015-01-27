import java.util.TreeSet;

public class Set
{

	private int mHead;
	private int mTail;
	private TreeSet<Integer> mBody;

	public Set( int head, int tail )
	{
		mHead = head;
		mTail = tail;
		mBody = new TreeSet<Integer>();
	}

	/**
	 * Get the ID of the head element in this set
	 * @return int
	 */
	public int getHead()
	{
		return this.mHead;
	}

	/**
	 * Get the ID of the tail element in this set
	 * @return int
	 */
	public int getTail()
	{
		return this.mTail;
	}

	/**
	 * Get the body of this set
	 * @return TreeSet<Integer>
	 */
	public TreeSet<Integer> getBody()
	{
		return this.mBody;
	}

	/**
	 * Add a new ID to the body of this set
	 * @param id int
	 */
	public void addBody( int id )
	{
		mBody.add( id );
	}

	/**
	 * Is the specified point pair contained within the body of this set?
	 *  If so, it cannot by used!
	 * @param PointPair p
	 * @return boolean
	 */
	public boolean inBody( PointPair p )
	{
		return mBody.contains( p.getSource() )
				|| mBody.contains( p.getDestination() );
	}

	/**
	 * Is the specified point pair in the head and tail of this list?
	 * @param p PointPair
	 * @return boolean
	 */
	public boolean inHeadAndTail( PointPair p )
	{
		if ( p.getSource() == mHead && p.getDestination() == mTail )
		{
			return true;
		}
		if ( p.getDestination() == mHead && p.getSource() == mTail )
		{
			return true;
		}
		return false;
	}

	/**
	 * Attempt to add an edge to this set, return true if it is successful
	 * @param p PointPair
	 * @param pairAdded boolean - has a previous pair been appended to a list?
	 *  If so, this function will not append, but still report back its findings
	 * @return boolean - if added
	 */
	public boolean addEdge( PointPair p, boolean pairAdded )
	{
		if ( p.getSource() == mHead && p.getDestination() != mTail )
		{
			if ( pairAdded )
			{
				return true;
			}
			mBody.add( mHead );
			mHead = p.getDestination();
			return true;
		}

		if ( p.getSource() == mTail && p.getDestination() != mHead )
		{
			if ( pairAdded )
			{
				return true;
			}
			mBody.add( mTail );
			mTail = p.getDestination();
			return true;
		}

		if ( p.getDestination() == mHead && p.getSource() != mTail )
		{
			if ( pairAdded )
			{
				return true;
			}
			mBody.add( mHead );
			mHead = p.getSource();
			return true;
		}
		if ( p.getDestination() == mTail && p.getSource() != mHead )
		{
			if ( pairAdded )
			{
				return true;
			}
			mBody.add( mTail );
			mTail = p.getSource();
			return true;
		}
		return false;
	}

	/**
	 * Merge another set with this set (keeping this set as the primary)
	 * @param s Set
	 */
	public void merge( Set s )
	{
		Debug.log( "Merging: " + this.toString() + " & " + s );
		if ( s.getHead() == mHead )
		{
			mBody.add( mHead );
			mBody.addAll( s.getBody() );
			mHead = s.getTail();
			return;
		}
		if ( s.getHead() == mTail )
		{
			mBody.add( mTail );
			mBody.addAll( s.getBody() );
			mTail = s.getTail();
			return;
		}
		if ( s.getTail() == mTail )
		{
			mBody.add( mTail );
			mBody.addAll( s.getBody() );
			mTail = s.getHead();
			return;
		}
		if ( s.getTail() == mHead )
		{
			mBody.add( mHead );
			mBody.addAll( s.getBody() );
			mHead = s.getHead();
			return;
		}
	}

	@Override
	public String toString()
	{
		String s = mHead + "-";
		TreeSet<Integer> copy = new TreeSet<Integer>();
		copy.addAll( mBody );
		while ( copy.size() > 0 )
		{
			s += copy.pollFirst() + "-";
		}
		s += mTail;
		return s;
	}
}
