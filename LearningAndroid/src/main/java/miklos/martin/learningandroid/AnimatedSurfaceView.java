package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import miklos.martin.learningandroid.model.Coordinate;
import miklos.martin.learningandroid.model.Vector;

/**
 * Animated surface view
 */
public class AnimatedSurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Thread animation = null;
    boolean isRunning = false;

    Bitmap greenBall;
    Canvas canvas;
    Coordinate startPosition, endPosition, drawPosition;
    Vector correction, direction;
    long startTime = 0;

    public AnimatedSurfaceView ( Context context ) {
        super( context );

        holder = getHolder();
        greenBall = BitmapFactory.decodeResource( context.getResources(), R.drawable.greenball );
        correction = new Vector( greenBall.getWidth()/-2, greenBall.getHeight()/-2 );
    }

    public void pause () {
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

    public void resume () {
        isRunning = true;
        animation = new Thread( this );
        animation.start();
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
    public void run () {

        while ( isRunning ) {

            if ( !holder.getSurface().isValid() ) {
                continue;
            }

            sleep();
            setUpCanvas();
            if ( endPosition instanceof Coordinate ) calculateDrawPosition();
            if ( drawPosition instanceof Coordinate ) drawBall();

            holder.unlockCanvasAndPost( canvas );
        }
    }

    private void sleep () {
        try {
            Thread.sleep( 16 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    private void setUpCanvas () {
        canvas = holder.lockCanvas();
        canvas.drawColor( Color.BLUE );
    }

    private void calculateDrawPosition () {
        drawPosition.apply( direction );
        bounce();
    }

    private void bounce () {

        if ( drawPosition.getX() <= 0 || drawPosition.getX() >= ( canvas.getWidth() - greenBall.getWidth() ) ) {
            direction.bounceX();
        }

        if ( drawPosition.getY() <= 0 || drawPosition.getY() >= ( canvas.getHeight() - greenBall.getHeight() ) ) {
            direction.bounceY();
        }
    }

    private void drawBall () {
        canvas.drawBitmap( greenBall, drawPosition.getX(), drawPosition.getY(), null );
    }
}
