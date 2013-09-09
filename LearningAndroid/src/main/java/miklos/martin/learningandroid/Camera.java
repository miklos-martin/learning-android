package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Playing with the camera
 */
public class Camera extends Activity implements View.OnClickListener {

    ImageView picture;
    ImageButton capture;
    int result = 0;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.camera );

        initMembers();
        capture.setOnClickListener( this );
    }

    private void initMembers () {
        picture = (ImageView) findViewById( R.id.ivPicture );
        capture = (ImageButton) findViewById( R.id.ibCapture );
    }

    @Override
    public void onClick ( View view ) {
        Intent intent = new Intent( MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA );
        startActivityForResult( intent, result );
    }
}
