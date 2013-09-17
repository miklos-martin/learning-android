package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Simple Browser
 */
public class Browser extends Activity implements View.OnClickListener {

    EditText url;
    Button go;
    ImageButton back, forward, refresh, clear;
    WebView browser;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.browser );
        initialize();

        browser.loadUrl( "http://google.com" );
    }

    private void initialize () {

        url = (EditText) findViewById( R.id.etUrl );
        go = (Button) findViewById( R.id.bGo );
        back = (ImageButton) findViewById( R.id.bBack );
        forward = (ImageButton) findViewById( R.id.bForward );
        refresh = (ImageButton) findViewById( R.id.bRefresh );
        clear = (ImageButton) findViewById( R.id.bClearHistory );
        browser = (WebView) findViewById( R.id.wvBrowser );

        go.setOnClickListener( this );
        back.setOnClickListener( this );
        forward.setOnClickListener( this );
        refresh.setOnClickListener( this );
        clear.setOnClickListener( this );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bGo:
                break;
            case R.id.bBack:
                break;
            case R.id.bForward:
                break;
            case R.id.bRefresh:
                break;
            case R.id.bClearHistory:
                break;
        }
    }
}
