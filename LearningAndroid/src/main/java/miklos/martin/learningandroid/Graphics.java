package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * For some graphics
 */
public class Graphics extends Activity {

    AnimatedView animatedView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        animatedView = new AnimatedView( this );
        setContentView( animatedView );
    }
}
