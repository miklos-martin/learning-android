package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * This activity receives data from another
 */
public class Opened extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView question, display;
    RadioGroup answer;
    RadioButton what, ok, dont;
    Button returnButton;
    String gotBread;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.send );

        initialize();
        collectData();
        question.setText( "What do you think about " + gotBread );
    }

    private void initialize () {

        question = (TextView) findViewById( R.id.tvQuestion );
        answer = (RadioGroup) findViewById( R.id.rgAnswer );
        what = (RadioButton) findViewById( R.id.rbWhat );
        ok = (RadioButton) findViewById( R.id.rbOK );
        dont = (RadioButton) findViewById( R.id.rbDont );
        returnButton = (Button) findViewById( R.id.bReturn );
        display = (TextView) findViewById( R.id.tvSent );

        returnButton.setOnClickListener( this );
        answer.setOnCheckedChangeListener( this );
    }

    @Override
    public void onClick ( View view ) {

    }

    @Override
    public void onCheckedChanged ( RadioGroup radioGroup, int i ) {

        switch ( i ) {
            case R.id.rbWhat:
                break;
            case R.id.rbOK:
                break;
            case R.id.rbDont:
                break;
        }
    }

    public void collectData () {
        Bundle gotBasket = getIntent().getExtras();
        gotBread = gotBasket.getString( "bread" );
    }
}
