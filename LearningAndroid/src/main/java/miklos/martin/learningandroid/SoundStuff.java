package miklos.martin.learningandroid;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Playing little sounds
 */
public class SoundStuff extends Activity implements View.OnClickListener {

    SoundPool soundPool;
    int explosion = 0;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        View view = new View( this );
        view.setOnClickListener( this );
        setContentView( view );

        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC, 0 );
        explosion = soundPool.load( this, R.raw.explosion, 1 );
    }

    @Override
    public void onClick ( View view ) {

        if ( explosion != 0 ) {
            soundPool.play( explosion, 1, 1, 0, 0, 1 );
        }
    }
}
