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
}
