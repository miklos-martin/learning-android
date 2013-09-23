package miklos.martin.learningandroid;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * For some graphics
 */
public class Graphics extends AbstractLoggerActivity {

    AnimatedView animatedView;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {

        PowerManager powerManager = (PowerManager) getSystemService( Context.POWER_SERVICE );
        wakeLock = powerManager.newWakeLock( PowerManager.FULL_WAKE_LOCK, "graphics-activity" );

        super.onCreate( savedInstanceState );

        wakeLock.acquire();

        animatedView = new AnimatedView( this );
        setContentView( animatedView );
    }

    @Override
    protected void onPause () {
        super.onPause();

        wakeLock.release();
    }
}
