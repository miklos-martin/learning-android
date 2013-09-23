package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Playing with the camera
 */
public class Camera extends AbstractLoggerActivity implements View.OnClickListener {

    ImageView picture;
    ImageButton capture;
    int cameraData = 0;
    Bitmap bmp;

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
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        startActivityForResult( intent, cameraData );
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );

        if ( resultCode == RESULT_OK ) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get( "data" );
            picture.setImageBitmap( bmp );
        }
    }
}
