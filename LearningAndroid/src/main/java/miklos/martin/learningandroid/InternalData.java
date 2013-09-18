package miklos.martin.learningandroid;

import android.content.Context;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by miki on 9/18/13.
 */
public class InternalData extends AbstractDataManipulation {

    FileOutputStream fos;
    public static String filename = "InternalData";

    @Override
    protected void initialize () {
        super.initialize();

        try {
            fos = openFileOutput( filename, Context.MODE_PRIVATE );
            fos.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bSave:
                break;
            case R.id.bLoad:
                break;
        }
    }
}
