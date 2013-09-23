package miklos.martin.learningandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

public class Slider extends AbstractLoggerActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {

    Button handle1, handle2, handle3, handle4;
    CheckBox checkBox;
    SlidingDrawer drawer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);
        
        initialize();
    }

    private void initialize () {
        handle1 = (Button) findViewById( R.id.handle1 );
        handle2 = (Button) findViewById( R.id.handle2 );
        handle3 = (Button) findViewById( R.id.handle3 );
        handle4 = (Button) findViewById( R.id.handle4 );
        checkBox = (CheckBox) findViewById( R.id.cbSlideable );
        drawer = (SlidingDrawer) findViewById( R.id.slidingDrawer );

        handle1.setOnClickListener( this );
        handle2.setOnClickListener( this );
        handle3.setOnClickListener( this );
        handle4.setOnClickListener( this );
        checkBox.setOnCheckedChangeListener( this );
        drawer.setOnDrawerOpenListener( this );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.handle1:
                drawer.open();
                break;
            case R.id.handle2:
                break;
            case R.id.handle3:
                drawer.toggle();
                break;
            case R.id.handle4:
                drawer.close();
                break;
        }
    }

    @Override
    public void onCheckedChanged ( CompoundButton compoundButton, boolean b ) {

        if ( compoundButton.isChecked() ) {
            drawer.lock();
        } else {
            drawer.unlock();
        }
    }

    @Override
    public void onDrawerOpened () {
        MediaPlayer mp = MediaPlayer.create( this, R.raw.explosion );
        mp.start();
    }
}
