package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Vibrator;
import android.view.MotionEvent;

import java.util.Random;

import miklos.martin.learningandroid.model.Coordinate;
import miklos.martin.learningandroid.model.Vector;

/**
 * _View for Accelerate activity
 */
public class AcceleratedGreenBall extends GreenBallSurfaceView implements SensorEventListener {

    private boolean ballIsLocked;
    Coordinate holePosition;
    double radius;
    private Vibrator vibrator;

    public AcceleratedGreenBall ( Context context ) {
        super( context );
        radius = (greenBall.getWidth()/2) * 1.2;
    }

    public void setVibrator ( Vibrator vibrator ) {
        this.vibrator = vibrator;
    }

    @Override
    public void onSensorChanged ( SensorEvent sensorEvent ) {
        sleep();
        direction = new Vector( sensorEvent.values[0] * -1, sensorEvent.values[1] );
    }

    @Override
    public void onAccuracyChanged ( Sensor sensor, int i ) {

    }

    @Override
    public boolean onTouchEvent ( MotionEvent event ) {

        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                ballIsLocked = true;
                break;
            case MotionEvent.ACTION_UP:
                ballIsLocked = false;
                break;
        }

        return true;
    }

    @Override
    protected void setUpCanvas () {
        super.setUpCanvas();

        if ( holePosition == null ) {
            rePosititonHole();
        }
    }

    @Override
    protected void calculateDrawPosition () {

        if ( !ballIsLocked ) {
            drawPosition.apply( direction );

            drawPosition.setBounds( new Coordinate( 0, 0 ), new Coordinate(
                    canvasWidth - greenBall.getWidth(),
                    canvasHeight - greenBall.getHeight()
            ) );
        }
    }

    @Override
    protected void doRun () {
        drawHole();
        super.doRun();

        if ( ballIsInHole() ) {
            vibrate();
            rePosititonHole();
            centerBall();
        }
    }

    private void vibrate () {

        if ( vibrator != null ) {
            vibrator.vibrate( 300 );
        }
    }

    private void drawHole () {

        Path hole = new Path();
        hole.addCircle( holePosition.getX(), holePosition.getY(), (float) radius, Path.Direction.CW );
        Paint holeStyle = new Paint();
        holeStyle.setColor( Color.BLACK );

        canvas.drawPath( hole, holeStyle );
    }

    private boolean ballIsInHole () {

        return
                // In hole horizontally
                drawPosition.getX() >= ( holePosition.getX() - radius )
                        && drawPosition.getX() <= ( holePosition.getX() + radius - greenBall.getWidth() )
                        // In hoole vertically
                        && drawPosition.getY() >= ( holePosition.getY() - radius )
                        && drawPosition.getY() <= ( holePosition.getY() + radius - greenBall.getHeight() )
                ;
    }

    protected void rePosititonHole () {
        Random random = new Random();

        holePosition = new Coordinate( random.nextInt( (int) canvasWidth ), random.nextInt( (int) canvasHeight ) );
        holePosition.setBounds(
                new Coordinate( greenBall.getWidth(), greenBall.getHeight() ),
                new Coordinate( canvasWidth - greenBall.getWidth(), canvasHeight - greenBall.getHeight() )
        );
    }
}
