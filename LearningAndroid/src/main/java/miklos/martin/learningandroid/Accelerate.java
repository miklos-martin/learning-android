package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

/**
 * Playing with accelerometer
 */
public class Accelerate extends Activity {

    AcceleratedGreenBall view;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        view = new AcceleratedGreenBall( this );

        SensorManager sm = (SensorManager) getSystemService( Context.SENSOR_SERVICE );
        List<Sensor> sensorList = sm.getSensorList( Sensor.TYPE_ACCELEROMETER );
        if ( sensorList.size() > 0 ) {
            Sensor accelerometer = sensorList.get( 0 );
            sm.registerListener( view, accelerometer, SensorManager.SENSOR_DELAY_NORMAL );
        }

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
}
