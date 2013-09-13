package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity with a custom SurfaceView
 */
public class GraphicsSurface extends Activity {

    AnimatedSurfaceView animatedSurfaceView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        animatedSurfaceView = new AnimatedSurfaceView( this );
        setContentView( animatedSurfaceView );
    }

    @Override
    protected void onPause () {
        super.onPause();

        animatedSurfaceView.pause();
    }

    @Override
    protected void onResume () {
        super.onResume();

        animatedSurfaceView.resume();
    }
}
