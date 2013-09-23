package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import hu.virgo.testjockeysdk.PlayCallback;
import hu.virgo.testjockeysdk.core.TestJockey;

/**
 * Splash screen
 */
public class Splash extends Activity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // make it fullscreen
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView( R.layout.splash );
        TestJockey.play( new PlayCallback() {
            @Override
            public void onSuccess () {
                Intent openMain = new Intent( Splash.this, Menu.class );
                startActivity( openMain );
            }

            @Override
            public boolean onFail () {
                System.out.println("faszkivan");
                return false;
            }

            @Override
            public void onWarning ( String s ) {
                System.out.println(s);
            }
        }, this, getResources().getString( R.string.testjockey_api_key ), false );
    }

    @Override
    protected void onPause () {
        super.onPause();
        finish();
    }
}
