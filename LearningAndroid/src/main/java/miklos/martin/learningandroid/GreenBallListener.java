package miklos.martin.learningandroid;

/**
 * Seems more appropriate to use a listener instead injecting system services in a view
 */
public interface GreenBallListener {
    public void onFellInHole();
}
