package miklos.martin.learningandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Internal data manipulation example
 */
public class InternalData extends AbstractDataManipulation {

    FileOutputStream fos;
    public static String filename = "InternalData";

    @Override
    protected void initialize () {
        super.initialize();

        try {
            fos = openFileOutput( filename, Context.MODE_PRIVATE );
            fos.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bSave:
                String data = sharedData.getText().toString();
                try {
                    fos = openFileOutput( filename, Context.MODE_PRIVATE );
                    fos.write( data.getBytes() );
                    fos.close();
                } catch ( FileNotFoundException e ) {
                    e.printStackTrace();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }

                dataResults.setText( "Done" );
                break;
            case R.id.bLoad:
                new LoadSomeStuff().execute( filename );
                break;
        }
    }

    private class LoadSomeStuff extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute () {
            dialog = new ProgressDialog( InternalData.this );
            dialog.setProgressStyle( ProgressDialog.STYLE_HORIZONTAL );
            dialog.setMax( 100 );
            dialog.show();
        }

        @Override
        protected String doInBackground ( String... params ) {

            String message = "Nothing in here yet!";
            FileInputStream fis = null;

            for ( int i = 0; i < 20; i++ ) {
                publishProgress( 5 );

                try {
                    Thread.sleep( 100 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();

            try {
                fis = openFileInput( filename );
                if ( fis.available() > 0 ) {
                    byte[] bytes = new byte[fis.available()];

                    while ( fis.read( bytes ) != -1 ) {
                        message = new String( bytes );
                    }
                }
            } catch ( FileNotFoundException e ) {
                e.printStackTrace();
            } catch ( IOException e ) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return message;
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate ( Integer... progress ) {
            dialog.incrementProgressBy( progress[0] );
        }

        protected void onPostExecute ( String result ) {
            dataResults.setText( result );
        }
    }
}
