package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This activity sends data to another
 */
public class Data extends Activity implements View.OnClickListener {

    EditText sendText;
    Button start, startForResult;
    TextView greeting, display;
    String name;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.get );

        initialize();
        greeting.setText( "Hey " + name + "!" );
    }

    private void initialize () {

        greeting = (TextView) findViewById( R.id.tvGreet );
        sendText = (EditText) findViewById( R.id.etSend );
        start = (Button) findViewById( R.id.bSA );
        startForResult = (Button) findViewById( R.id.bSAFR );
        display = (TextView) findViewById( R.id.tvData );

        start.setOnClickListener( this );
        startForResult.setOnClickListener( this );

        collectData();
    }

    private void collectData () {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( getBaseContext() );
        name = preferences.getString( "name", "Firstname" );
    }

    @Override
    public void onClick ( View view ) {

        String bread = sendText.getText().toString();
        Bundle basket = new Bundle();
        basket.putString( "bread", bread );
        Intent intent = new Intent( Data.this, Opened.class );
        intent.putExtras( basket );

        switch ( view.getId() ) {
            case R.id.bSA:
                startActivity( intent );
                break;
            case R.id.bSAFR:
                startActivityForResult( intent, 0 );
                break;
        }
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );

        if ( resultCode == RESULT_OK ) {
            Bundle extras = data.getExtras();
            display.setText( extras.getString( "answer" ) );
        }
    }
}
