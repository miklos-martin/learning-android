package miklos.martin.learningandroid.model;

/**
 * Simple coordinate for the animation in GraphicsSurface Activity
 */
public class Coordinate {
    private float x, y;

    public Coordinate( float x, float y ) {
        this.x = x;
        this.y = y;
    }

    public Coordinate( float x, float y, Vector correction ) {
        this(x, y);
        apply( correction );
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

    public void apply( Vector vector ) {
        x += vector.getDeltaX();
        y += vector.getDeltaY();
    }

    /**
     * Do not let this coordinate out of the bouds defined by theese two coordinates
     *
     * @param topLeft
     * @param bottomRight
     */
    public void setBounds ( Coordinate topLeft, Coordinate bottomRight ) {
        x = Math.max( topLeft.getX(), x );
        x = Math.min( bottomRight.getX(), x );

        y = Math.max( topLeft.getY(), y );
        y = Math.min( bottomRight.getY(), y );
    }

    @Override
    public String toString () {
        return String.format( "Coordinate( %s, %s )", String.valueOf( x ), String.valueOf( y ) );
    }
}
