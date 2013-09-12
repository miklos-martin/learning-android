package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Splash screen
 */
public class Splash extends Activity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash );

        Thread timer = new Thread() {
            @Override
            public void run () {
                try {
                    sleep( 500 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                } finally {
                    Intent openMain = new Intent( Splash.this, Menu.class );
                    startActivity( openMain );
                }
            }
        };

        timer.start();
    }

    @Override
    protected void onPause () {
        super.onPause();
        finish();
    }
}
