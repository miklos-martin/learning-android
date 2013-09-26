package miklos.martin.learningandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.MotionEvent;

import miklos.martin.learningandroid.model.Coordinate;
import miklos.martin.learningandroid.model.Vector;

/**
 * _View for Accelerate activity
 */
public class AcceleratedGreenBall extends GreenBallSurfaceView implements SensorEventListener {

    private boolean ballIsLocked;

    public AcceleratedGreenBall ( Context context ) {
        super( context );
    }

    @Override
    public void onSensorChanged ( SensorEvent sensorEvent ) {
        sleep();
        direction = new Vector( sensorEvent.values[0]*-1, sensorEvent.values[1] );
    }

    @Override
    public void onAccuracyChanged ( Sensor sensor, int i ) {

    }

    @Override
    public boolean onTouchEvent ( MotionEvent event ) {

        switch (event.getAction()) {
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
    protected void calculateDrawPosition () {

        if ( !ballIsLocked ) {
            drawPosition.apply( direction );

            drawPosition.setBounds( new Coordinate( 0, 0 ), new Coordinate(
                    canvas.getWidth() - greenBall.getWidth(),
                    canvas.getHeight() - greenBall.getHeight()
            ) );
        }
    }
}
