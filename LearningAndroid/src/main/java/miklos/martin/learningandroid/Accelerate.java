package miklos.martin.learningandroid;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Playing with accelerometer
 */
public class Accelerate extends Activity implements SensorEventListener {

    GreenBallSurfaceView view;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        view = new GreenBallSurfaceView( this );
        setContentView( view );
    }

    @Override
    protected void onPause () {
        super.onPause();

        view.pause();
    }

    @Override
    protected void onResume () {
        super.onResume();

        view.resume();
    }

    @Override
    public void onSensorChanged ( SensorEvent sensorEvent ) {

    }

    @Override
    public void onAccuracyChanged ( Sensor sensor, int i ) {

    }
}
