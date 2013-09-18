package miklos.martin.learningandroid;

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

        @Override
        protected void onPreExecute () {
        }

        @Override
        protected String doInBackground ( String... params ) {

            String message = "NOthing in here yet!";
            FileInputStream fis = null;
            try {
                fis = openFileInput( filename );
                byte[] bytes = new byte[fis.available()];

                while ( fis.read(bytes) != -1 ) {
                    message = new String(bytes);
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
        protected void onProgressUpdate( Integer... progress ) {

        }

        protected void onPostExecute( String result ) {
            dataResults.setText( result );
        }
    }
}
