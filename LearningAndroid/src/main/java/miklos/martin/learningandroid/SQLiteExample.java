package miklos.martin.learningandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SQLite example
 */
public class SQLiteExample extends Activity implements View.OnClickListener {

    EditText name, awesomeness;
    Button update, load;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sqliteexample );
        initialize();
    }

    private void initialize () {

        name = (EditText) findViewById( R.id.etSQLName );
        awesomeness = (EditText) findViewById( R.id.etSQLAwesomeness );

        update = (Button) findViewById( R.id.bUpdate );
        load = (Button) findViewById( R.id.bLoad );

        update.setOnClickListener( this );
        load.setOnClickListener( this );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bUpdate:

                boolean success = true;

                try {
                    String personName = name.getText().toString();
                    int personAwesomeness = Integer.parseInt( awesomeness.getText().toString() );

                    AwesomeOrNot entry = new AwesomeOrNot( SQLiteExample.this );
                    entry.open();

                    entry.createNew( personName, personAwesomeness );

                    entry.close();
                } catch ( Exception e ) {
                    success = false;
                }

                if ( success ) {
                    Dialog dialog = new Dialog( this );
                    dialog.setTitle( "Saved data!" );
                    TextView text = new TextView( this );
                    text.setText( "Successfully saved that nonsense you wrote." );

                    dialog.setContentView( text );
                    dialog.show();
                }

                break;
            case R.id.bLoad:
                Intent i = new Intent( SQLiteExample.this, SQLView.class );
                startActivity( i );
                break;
        }
    }
}
