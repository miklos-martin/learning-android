package miklos.martin.learningandroid;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activity with a custom SurfaceView
 */
public class GraphicsSurface extends AbstractLoggerActivity {

    AnimatedSurfaceView animatedSurfaceView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

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
