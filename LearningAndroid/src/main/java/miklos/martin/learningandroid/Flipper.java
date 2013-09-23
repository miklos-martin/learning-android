package miklos.martin.learningandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * ViewFlipper example
 */
public class Flipper extends AbstractLoggerActivity implements View.OnClickListener {

    ViewFlipper flipper;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.flipper );

        initialize();
    }

    private void initialize () {

        flipper = (ViewFlipper) findViewById( R.id.vfFlipper );

        flipper.setOnClickListener( this );
    }

    @Override
    public void onClick ( View view ) {
        flipper.showNext();
    }
}
