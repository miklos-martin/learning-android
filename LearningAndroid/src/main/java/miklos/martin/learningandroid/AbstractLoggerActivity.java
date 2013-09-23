package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;

import hu.virgo.testjockeysdk.core.TestJockey;

/**
 * Parent for activities that sends logs to TestJockey on start
 */
public abstract class AbstractLoggerActivity extends Activity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        TestJockey.Log.i( "START_ACTTIVITY", this.getClass().toString() );
    }
}
