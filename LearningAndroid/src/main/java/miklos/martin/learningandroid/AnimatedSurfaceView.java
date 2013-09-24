package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import miklos.martin.learningandroid.model.Coordinate;
import miklos.martin.learningandroid.model.Vector;

/**
 * Animated surface view
 */
public class AnimatedSurfaceView extends GreenBallSurfaceView implements Runnable {

    Coordinate startPosition, endPosition;
    long startTime = 0;

    public AnimatedSurfaceView ( Context context ) {
        super( context );
    }

    @Override
    public boolean onTouchEvent ( MotionEvent event ) {
        super.onTouchEvent( event );

        drawPosition = new Coordinate( event.getX(), event.getY(), correction );

        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                startPosition = new Coordinate( event.getX(), event.getY() );
                endPosition = null;
                direction = null;
                break;
            case MotionEvent.ACTION_UP:
                long v = ( System.currentTimeMillis() - startTime ) / 50;

                endPosition = new Coordinate( event.getX(), event.getY() );
                direction = new Vector(
                        ( endPosition.getX() - startPosition.getX() ) / v,
                        ( endPosition.getY() - startPosition.getY() ) / v
                );
                break;
        }

        return true;
    }

    @Override
    protected void doRun () {
        if ( endPosition instanceof Coordinate ) calculateDrawPosition();
        super.doRun();
    }
}
