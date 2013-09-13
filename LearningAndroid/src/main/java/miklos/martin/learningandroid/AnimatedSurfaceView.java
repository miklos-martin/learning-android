package miklos.martin.learningandroid;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Animated surface view
 */
public class AnimatedSurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Thread animation = null;

    public AnimatedSurfaceView ( Context context ) {
        super( context );

        holder = getHolder();
        animation = new Thread( this );
        animation.start();
    }

    @Override
    public void run () {

    }
}
