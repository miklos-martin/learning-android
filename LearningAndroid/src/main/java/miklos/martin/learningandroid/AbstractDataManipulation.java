package miklos.martin.learningandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Parent for data manipulator activities
 */
public abstract class AbstractDataManipulation extends AbstractLoggerActivity implements View.OnClickListener {

    EditText sharedData;
    Button save, load;
    TextView dataResults;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sharedprefs );

        initialize();
    }

    protected void initialize () {

        sharedData = (EditText) findViewById( R.id.etSharedData );
        save = (Button) findViewById( R.id.bSave );
        load = (Button) findViewById( R.id.bLoad );
        dataResults = (TextView) findViewById( R.id.tvDataResults );

        save.setOnClickListener( this );
        load.setOnClickListener( this );
    }
}
