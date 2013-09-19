package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * External data manipulation
 */
public class ExternalData extends Activity {

    private TextView canRead, canWrite;
    private String state;
    private boolean readable, writeable;
    private Spinner spinner;
    String[] paths = { "Music", "Pictures", "Downloads" };

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

        spinner = (Spinner) findViewById( R.id.spinner );
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( ExternalData.this, android.R.layout.simple_spinner_item, paths );
        spinner.setAdapter( adapter );
    }

    private void checkState () {
        if ( state.equals( Environment.MEDIA_MOUNTED ) ) {
            canRead.setText( "YES" );
            canWrite.setText( "YES" );
            readable = writeable = true;
        } else if ( state.equals( Environment.MEDIA_MOUNTED_READ_ONLY ) ) {
            canRead.setText( "YES" );
            canWrite.setText( "NO" );

            readable = true;
            writeable = false;
        } else {
            canRead.setText( "NO" );
            canWrite.setText( "NO" );
            readable = writeable = false;
        }
    }
}
