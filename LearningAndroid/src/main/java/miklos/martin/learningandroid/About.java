package miklos.martin.learningandroid;

import android.os.Bundle;

/**
 * Dialog
 */
public class About extends AbstractLoggerActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.about );
    }
}
