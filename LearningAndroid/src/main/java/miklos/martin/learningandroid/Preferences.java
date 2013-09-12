package miklos.martin.learningandroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Preferences handling
 */
public class Preferences extends PreferenceActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        addPreferencesFromResource( R.xml.preferences );
    }
}
