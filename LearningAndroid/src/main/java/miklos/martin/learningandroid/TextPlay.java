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

import java.util.HashMap;
import java.util.Random;

/**
 * Playing with text
 */
public class TextPlay extends Activity implements View.OnClickListener {

    EditText input;
    Button checkCommand;
    ToggleButton passwordToggle;
    TextView display;
    HashMap<String, View.OnClickListener> commands;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.text );

        initMembers();

        passwordToggle.setOnClickListener( this );
        checkCommand.setOnClickListener( this );
    }

    private void initMembers () {
        input = (EditText) findViewById( R.id.etCommands );
        passwordToggle = (ToggleButton) findViewById( R.id.tbResults );
        display = (TextView) findViewById( R.id.tvResults );
        checkCommand = (Button) findViewById( R.id.bResults );

        registerCommands();
    }

    private void registerCommands () {
        commands = new HashMap<String, View.OnClickListener>();

        commands.put( "left", new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                display.setGravity( Gravity.LEFT );
                display.setText( "left" );
            }
        } );

        commands.put( "right", new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                display.setGravity( Gravity.RIGHT );
                display.setText( "right" );
            }
        } );

        commands.put( "center", new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                display.setGravity( Gravity.CENTER );
                display.setText( "center" );
            }
        } );

        commands.put( "blue", new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                display.setTextColor( Color.BLUE );
                display.setText( "blue" );
            }
        } );

        final Random random = new Random();
        commands.put( "WTF", new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {

                display.setText( "WTF!" );
                display.setTextSize( random.nextInt( 75 ) );
                display.setTextColor( Color.rgb( random.nextInt( 255 ), random.nextInt( 255 ), random.nextInt( 255 ) ) );

                int verticalGravities[] = { Gravity.CENTER_VERTICAL, Gravity.TOP, Gravity.BOTTOM };
                int horizontalGravities[] = { Gravity.LEFT, Gravity.CENTER_HORIZONTAL, Gravity.RIGHT };
                display.setGravity(
                        horizontalGravities[random.nextInt( horizontalGravities.length )]
                                | verticalGravities[random.nextInt( verticalGravities.length )]
                );
            }
        } );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bResults:
                onCheckCommandClick( view );
                break;
            case R.id.tbResults:
                onPasswordToggleClick();
                break;
        }
    }

    private void onCheckCommandClick ( View view ) {

        String cmd = input.getText().toString();

        if ( commands.containsKey( cmd ) ) {
            View.OnClickListener action = commands.get( cmd );
            action.onClick( view );
        } else {
            display.setText( "Invalid" );
        }
    }

    private void onPasswordToggleClick () {

        if ( passwordToggle.isChecked() ) {
            input.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        } else {
            input.setInputType( InputType.TYPE_CLASS_TEXT );
        }
    }
}
