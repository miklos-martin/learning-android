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
    boolean isRunning = false, moving = false;

    Bitmap greenBall;
    float
        posX = 0, posY = 0, correctX, correctY, startX = 0, startY = 0, finishX = 0, finishY = 0,
        changeX = 0, changeY = 0, animateX = 0, animateY = 0, scaleX = 0, scaleY = 0
    ;

    public AnimatedSurfaceView ( Context context ) {
        super( context );

        holder = getHolder();
        greenBall = BitmapFactory.decodeResource( context.getResources(), R.drawable.greenball );
        correctX = greenBall.getWidth()/2;
        correctY = greenBall.getHeight()/2;
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

            if ( posX != 0 && posY != 0 ) {
                canvas.drawBitmap( greenBall, posX - correctX, posY - correctY, null );
            }

            if ( finishX != 0 && finishY != 0 ) {
                canvas.drawBitmap( greenBall, finishX - correctX - animateX, finishY - correctY - animateY, null );
            }

            animateX += scaleX;
            animateY += scaleY;

            holder.unlockCanvasAndPost( canvas );
        }
    }

    @Override
    public boolean onTouchEvent ( MotionEvent event ) {
        super.onTouchEvent( event );

        posX = event.getX();
        posY = event.getY();

        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                startX = event.getX();
                startY = event.getY();
                scaleX = scaleY = animateX = animateY = finishX = finishY = 0;
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                finishX = event.getX();
                finishY = event.getY();
                changeX = finishX - startX;
                changeY = finishY - startY;
                scaleX = changeX/30;
                scaleY = changeY/30;
                posX = posY = 0;
                break;
        }

        return true;
    }
}
