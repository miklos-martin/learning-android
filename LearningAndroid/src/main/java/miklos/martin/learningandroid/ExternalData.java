package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

/**
 * External data manipulation
 */
public class ExternalData extends Activity {

    private TextView canRead, canWrite;
    private String state;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.externaldata );
        initialize();
    }

    private void initialize () {

        canRead = (TextView) findViewById( R.id.tvCanRead );
        canWrite = (TextView) findViewById( R.id.tvCanWrite );
        state = Environment.getExternalStorageState();

        checkState();
    }

    private void checkState () {
        if ( state.equals( Environment.MEDIA_MOUNTED ) ) {
            canRead.setText( "YES" );
            canWrite.setText( "YES" );
        } else if ( state.equals( Environment.MEDIA_MOUNTED_READ_ONLY ) ) {
            canRead.setText( "YES" );
            canWrite.setText( "NO" );
        } else {
            canRead.setText( "NO" );
            canWrite.setText( "NO" );
        }
    }
}
