package miklos.martin.learningandroid;

import android.content.SharedPreferences;
import android.view.View;

/**
 * Saving to and loading data from sharedpreferences
 */
public class SharedPrefs extends AbstractDataManipulation {

    public static String filename = "MySharedString";
    SharedPreferences sharedPreferences;

    @Override
    protected void initialize () {
        super.initialize();

        sharedPreferences = getSharedPreferences( filename, MODE_PRIVATE );
    }

    @Override
    public void onClick ( View view ) {

        String message = dataResults.getText().toString();

        switch ( view.getId() ) {
            case R.id.bSave:
                String data = sharedData.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "sharedString", data );
                editor.commit();
                message = "Done.";
                break;
            case R.id.bLoad:
                sharedPreferences = getSharedPreferences( filename, MODE_PRIVATE );
                message = sharedPreferences.getString( "sharedString", "Nothing here yet!" );
                break;
        }

        dataResults.setText( message );
    }
}
