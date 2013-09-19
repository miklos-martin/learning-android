package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.io.File;

/**
 * External data manipulation
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canRead, canWrite;
    private String state;
    private boolean readable, writeable;
    private Spinner spinner;
    private String[] paths = { "Music", "Pictures", "Downloads" };
    private File path = null;
    private Button confirm, save;
    private EditText filename;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.externaldata );
        initialize();
    }

    private void initialize () {

        canRead = (TextView) findViewById( R.id.tvCanRead );
        canWrite = (TextView) findViewById( R.id.tvCanWrite );
        checkState();

        spinner = (Spinner) findViewById( R.id.spinner );
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( ExternalData.this, android.R.layout.simple_spinner_item, paths );
        spinner.setAdapter( adapter );
        spinner.setOnItemSelectedListener( this );

        filename = (EditText) findViewById( R.id.etSaveAs );
        confirm = (Button) findViewById( R.id.bConfirm );
        save = (Button) findViewById( R.id.bSaveAs );

        confirm.setOnClickListener( this );
        save.setOnClickListener( this );
    }

    private void checkState () {

        state = Environment.getExternalStorageState();

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

    @Override
    public void onItemSelected ( AdapterView<?> adapterView, View view, int i, long l ) {

        int position = spinner.getSelectedItemPosition();

        switch ( position ) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC );
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS );
                break;
        }
    }

    @Override
    public void onNothingSelected ( AdapterView<?> adapterView ) {

    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bConfirm:
                save.setVisibility( View.VISIBLE );
                break;
            case R.id.bSaveAs:
                break;
        }
    }
}
