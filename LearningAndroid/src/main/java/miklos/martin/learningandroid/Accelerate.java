package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

/**
 * Playing with accelerometer
 */
public class Accelerate extends Activity implements GreenBallListener {

    PowerManager.WakeLock wakeLock;
    AcceleratedGreenBall view;
    SensorManager sm;
    Sensor accelerometer;
    Vibrator vibrator;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {

        vibrator = (Vibrator) getSystemService( Context.VIBRATOR_SERVICE );
        PowerManager powerManager = (PowerManager) getSystemService( Context.POWER_SERVICE );
        wakeLock = powerManager.newWakeLock( PowerManager.FULL_WAKE_LOCK, "accelerate-activity" );

        super.onCreate( savedInstanceState );

        wakeLock.acquire();

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


        view = new AcceleratedGreenBall( this );
        sm = (SensorManager) getSystemService( Context.SENSOR_SERVICE );

        view.setGreenBallListener( this );

        List<Sensor> sensorList = sm.getSensorList( Sensor.TYPE_ACCELEROMETER );
        if ( sensorList.size() > 0 ) {
            accelerometer = sensorList.get( 0 );
        }

        setContentView( view );
    }

    @Override
    protected void onPause () {
        super.onPause();

        wakeLock.release();
        view.pause();
        if ( accelerometer != null ) {
            sm.unregisterListener( view );
        }
    }

    @Override
    protected void onResume () {
        super.onResume();

        wakeLock.acquire();
        view.resume();
        if ( accelerometer != null ) {
            sm.registerListener( view, accelerometer, SensorManager.SENSOR_DELAY_NORMAL );
        }
    }

    @Override
    public void onFellInHole () {
        vibrator.vibrate( 150 );
    }
}
