package miklos.martin.learningandroid.model;

/**
 * Simple vector class for the animation in GraphicsSurface Activvity
 */
public class Vector {
    private float deltaX, deltaY;

    public Vector ( float deltaX, float deltaY ) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void bounceX() {
        deltaX *= -1;
    }

    public void bounceY() {
        deltaY *= -1;
    }

    @Override
    public String toString () {
        return String.format( "Vector( %s, %s )", String.valueOf( deltaX ), String.valueOf( deltaY ) );
    }
}
