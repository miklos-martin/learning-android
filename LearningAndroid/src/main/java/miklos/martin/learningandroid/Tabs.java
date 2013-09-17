package miklos.martin.learningandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class Tabs extends Activity implements View.OnClickListener {

    TabHost tabHost;
    Button start, stop, addTab;
    int tabsCounter = 0;

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

        start = (Button) findViewById( R.id.bStartWatch );
        stop = (Button) findViewById( R.id.bStopWatch );
        addTab = (Button) findViewById( R.id.bAddTab );

        addTab.setOnClickListener( this );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bStartWatch:
                break;
            case R.id.bStopWatch:
                break;
            case R.id.bAddTab:
                tabsCounter++;
                TabHost.TabSpec tabSpec = tabHost.newTabSpec( "newTab" + tabsCounter );
                tabSpec.setContent( new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent ( String s ) {

                        TextView textView = new TextView( Tabs.this );
                        textView.setText( "Hey, You've created a new tab!" );

                        return textView;
                    }
                } );

                tabSpec.setIndicator( "New tab " + tabsCounter );
                tabHost.addTab( tabSpec );

                break;
        }


    }
}
