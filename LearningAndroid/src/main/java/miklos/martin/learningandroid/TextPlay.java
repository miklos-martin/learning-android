package miklos.martin.learningandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by miki on 9/7/13.
 */
public class TextPlay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.text );

        final EditText input = (EditText) findViewById( R.id.etCommands );
        final ToggleButton passTogg = (ToggleButton) findViewById( R.id.tbResults );
        final TextView display = (TextView) findViewById( R.id.tvResults );

        Button checkCommand = (Button) findViewById( R.id.bResults );

        passTogg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( passTogg.isChecked() ) {
                    input.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                } else {
                    input.setInputType( InputType.TYPE_CLASS_TEXT );
                }
            }
        });

        checkCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = input.getText().toString();
                if ( check.contentEquals("left") ) {
                    display.setGravity( Gravity.LEFT );
                    display.setText( "left" );
                } else if ( check.contentEquals("center") ) {
                    display.setGravity( Gravity.CENTER );
                    display.setText( "center" );
                } else if ( check.contentEquals( "right" ) ) {
                    display.setGravity( Gravity.RIGHT );
                    display.setText( "right" );
                } else if ( check.contentEquals( "blue" ) ) {
                    display.setTextColor(Color.BLUE);
                    display.setText( "blue" );
                } else {
                    display.setText( "Invalid" );
                }
            }
        });
    }
}
