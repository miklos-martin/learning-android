package miklos.martin.learningandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import miklos.martin.learningandroid.model.Vector;

/**
 * _View for Accelerate activity
 */
public class AcceleratedGreenBall extends GreenBallSurfaceView implements SensorEventListener {

    public AcceleratedGreenBall ( Context context ) {
        super( context );
    }

    @Override
    public void onSensorChanged ( SensorEvent sensorEvent ) {

        sleep();
        direction = new Vector( sensorEvent.values[0]*20, sensorEvent.values[1]*20 );
    }

    @Override
    public void onAccuracyChanged ( Sensor sensor, int i ) {

    }
}
