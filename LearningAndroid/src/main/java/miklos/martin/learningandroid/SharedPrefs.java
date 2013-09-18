package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Saving to and loading data from sharedpreferences
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    public static String filename = "MySharedString";
    SharedPreferences sharedPreferences;

    EditText sharedData;
    Button save, load;
    TextView dataResults;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sharedprefs );

        initialize();
    }

    private void initialize () {

        sharedData = (EditText) findViewById( R.id.etSharedData );
        save = (Button) findViewById( R.id.bSave );
        load = (Button) findViewById( R.id.bLoad );
        dataResults = (TextView) findViewById( R.id.tvDataResults );

        save.setOnClickListener( this );
        load.setOnClickListener( this );

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
