package miklos.martin.learningandroid;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * External data manipulation
 */
public class ExternalData extends AbstractLoggerActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canRead, canWrite;
    private String state;
    private boolean readable, writeable;
    private Spinner spinner;
    private String[] paths = { "Music", "Pictures", "Downloads" };
    private File path = null;
    private File file = null;
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
                String filename = save.getText().toString();

                if ( !filename.endsWith( ".png" ) ) {
                    filename += ".png";
                }

                file = new File( path, filename );
                checkState();

                if ( readable && writeable ) {

                    path.mkdirs();

                    try {
                        InputStream is = getResources().openRawResource( R.drawable.greenball );
                        OutputStream os = new FileOutputStream( file );
                        byte[] data = new byte[ is.available() ];

                        is.read( data );
                        os.write( data );

                        is.close();
                        os.close();

                        Toast toast = Toast.makeText( ExternalData.this, "File has been saved", Toast.LENGTH_LONG );
                        toast.show();

                        MediaScannerConnection.scanFile(
                                ExternalData.this,
                                new String[] { file.toString() },
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted ( String s, Uri uri ) {
                                        Toast toast = Toast.makeText( ExternalData.this, "Scan completed", Toast.LENGTH_LONG );
                                        toast.show();
                                    }
                                }
                        );

                    } catch ( FileNotFoundException e ) {
                        e.printStackTrace();
                    } catch ( IOException e ) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }
}
