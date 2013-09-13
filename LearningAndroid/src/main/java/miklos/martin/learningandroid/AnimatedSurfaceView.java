package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Animated surface view
 */
public class AnimatedSurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Thread animation = null;
    boolean isRunning = true;

    public AnimatedSurfaceView ( Context context ) {
        super( context );

        holder = getHolder();
        animation = new Thread( this );
        animation.start();
    }

    @Override
    public void run () {

        while ( isRunning ) {

            if ( !holder.getSurface().isValid() ) {
                continue;
            }

            Canvas canvas = holder.lockCanvas();
            canvas.drawColor( Color.CYAN );
            holder.unlockCanvasAndPost( canvas );
        }
    }
}
