package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                break;
            case R.id.bLoad:
                break;
        }
    }
}
