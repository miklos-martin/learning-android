package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Show model from SQLiite db
 */
public class SQLView extends Activity {

    TextView result;
    AwesomeOrNot model;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sqlview );

        initialize();
    }

    private void initialize () {
        result = (TextView) findViewById( R.id.tvSQLResult );

        model = new AwesomeOrNot( this );
        try {
            model.open();
            String data = model.getData();
            model.close();

            result.setText( data );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }
}
