package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    TextView display;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.get );

        initialize();
    }

    private void initialize () {

        sendText = (EditText) findViewById( R.id.etSend );
        start = (Button) findViewById( R.id.bSA );
        startForResult = (Button) findViewById( R.id.bSAFR );
        display = (TextView) findViewById( R.id.tvData );

        start.setOnClickListener( this );
        startForResult.setOnClickListener( this );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bSA:
                String bread = sendText.getText().toString();
                Bundle basket = new Bundle();
                basket.putString( "bread", bread );
                Intent intent = new Intent( Data.this, Opened.class );
                intent.putExtras( basket );
                startActivity( intent );
                break;
            case R.id.bSAFR:
                break;
        }
    }
}
