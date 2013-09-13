package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Animated surface view
 */
public class AnimatedSurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Thread animation = null;
    boolean isRunning = true;

    Bitmap greenBall;
    float posX = 0, posY = 0;

    public AnimatedSurfaceView ( Context context ) {
        super( context );

        holder = getHolder();
        greenBall = BitmapFactory.decodeResource( context.getResources(), R.drawable.greenball );
    }

    public void pause() {
        isRunning = false;

        while ( true ) {
            try {
                animation.join();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }

            break;
        }

        animation = null;
    }

    public void resume() {
        isRunning = true;
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
            canvas.drawColor( Color.BLUE );

            canvas.drawBitmap( greenBall, posX, posY, null );

            holder.unlockCanvasAndPost( canvas );
        }
    }

    @Override
    public boolean onTouchEvent ( MotionEvent event ) {
        super.onTouchEvent( event );

        posX = event.getX();
        posY = event.getY();

        return true;
    }
}
