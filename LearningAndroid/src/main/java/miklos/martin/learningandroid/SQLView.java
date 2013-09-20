package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * Show data from SQLiite db
 */
public class SQLView extends Activity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sqlview );
    }
}
