package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import miklos.martin.learningandroid.model.Coordinate;
import miklos.martin.learningandroid.model.Vector;

/**
 * Animated surface view
 */
public abstract class GreenBallSurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Thread animation = null;
    boolean isRunning = false;

    Bitmap greenBall;
    Canvas canvas;
    Coordinate drawPosition;
    Vector correction, direction;

    float canvasWidth, canvasHeight;

    public GreenBallSurfaceView ( Context context ) {
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
    public void run () {

        while ( isRunning ) {

            if ( !holder.getSurface().isValid() ) {
                continue;
            }

            sleep();
            setUpCanvas();
            doRun();

            holder.unlockCanvasAndPost( canvas );
        }
    }

    protected void sleep () {
        try {
            Thread.sleep( 16 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    protected void setUpCanvas () {
        canvas = holder.lockCanvas();
        canvas.drawColor( Color.BLUE );

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        if ( drawPosition == null ) {
            centerBall();
        }
    }

    protected void centerBall () {
        drawPosition = new Coordinate( canvasWidth/2, canvasHeight/2, correction );
    }

    protected void doRun () {
        if ( direction != null ) calculateDrawPosition();
        if ( drawPosition != null ) drawBall();
    }

    protected void calculateDrawPosition () {
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
