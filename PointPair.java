/**
 * A PointPair represents a set of points on an x/y plane and the 
 *  distance between them
 * @author Robert Timm | bobtimm@tamu.edu
 *
 */
public class PointPair implements Comparable<PointPair>
{
	protected int mSource;
	protected int mDestination;
	protected double mDistance;

	public PointPair( int source, int destination, double distance )
	{
		mSource = source;
		mDestination = destination;
		mDistance = distance;
	}

	/**
	 * Get the source point
	 * @return int
	 */
	public int getSource()
	{
		return mSource;
	}

	/**
	 * Get the destination point
	 * @return int
	 */
	public int getDestination()
	{
		return mDestination;
	}

	/**
	 * Does the specified ID exist in this pair?
	 * @param id int
	 * @return boolean
	 */
	public boolean contains( int id )
	{
		return (id == mSource || id == mDestination);
	}

	/**
	 * Get the distance between the two points
	 * @return double
	 */
	public double getDistance()
	{
		return mDistance;
	}

	@Override
	public String toString()
	{
		return String.format( "%6.2f:", mDistance ) + "(" + mSource + ","
				+ mDestination + ")";
	}

	@Override
	public int hashCode()
	{
		return (int) ((mSource * 2) + mDestination * 4 + (mSource * mDistance));
	}

	@Override
	public int compareTo( PointPair p )
	{
		return Double.compare( this.mDistance, p.mDistance );
	}

}
