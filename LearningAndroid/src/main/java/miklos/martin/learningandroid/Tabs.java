package miklos.martin.learningandroid;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TabHost;

public class Tabs extends Activity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.tabs );

        initialize();
    }

    private void initialize () {
        tabHost = (TabHost) findViewById( R.id.tabHost );
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec( "tag1" );
        tabSpec.setContent( R.id.tab1 );
        tabSpec.setIndicator( "StopWatch" );

        tabHost.addTab( tabSpec );

        tabSpec = tabHost.newTabSpec( "tag2" );
        tabSpec.setContent( R.id.tab2 );
        tabSpec.setIndicator( "Tab2" );

        tabHost.addTab( tabSpec );

        tabSpec = tabHost.newTabSpec( "tag3" );
        tabSpec.setContent( R.id.tab3 );
        tabSpec.setIndicator( "Add a tab" );

        tabHost.addTab( tabSpec );
    }
}
