package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
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
        Button checkCommand = (Button) findViewById( R.id.bResults );
        final ToggleButton passTogg = (ToggleButton) findViewById( R.id.tbResults );
        TextView display = (TextView) findViewById( R.id.tvResults );

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
    }
}
