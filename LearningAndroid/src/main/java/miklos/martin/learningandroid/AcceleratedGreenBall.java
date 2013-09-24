package miklos.martin.learningandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * _View for Accelerate activity
 */
public class AcceleratedGreenBall extends GreenBallSurfaceView implements SensorEventListener {

    public AcceleratedGreenBall ( Context context ) {
        super( context );
    }

    @Override
    public void onSensorChanged ( SensorEvent sensorEvent ) {

    }

    @Override
    public void onAccuracyChanged ( Sensor sensor, int i ) {

    }
}
