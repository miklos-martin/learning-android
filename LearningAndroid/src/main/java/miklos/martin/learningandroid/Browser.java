package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    String homepage;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.browser );
        initialize();

        browser.loadUrl( homepage );
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

        browser.getSettings().setJavaScriptEnabled( true );
        browser.getSettings().setLoadWithOverviewMode( true );
        browser.getSettings().setUseWideViewPort( true );

        browser.setWebViewClient( new BrowserClient() );

        collectData();
    }

    private void collectData () {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( getBaseContext() );
        homepage = preferences.getString( "homepage", "http://google.com" );
    }

    @Override
    public void onClick ( View view ) {

        switch ( view.getId() ) {
            case R.id.bGo:
                browser.loadUrl( url.getText().toString() );
                break;
            case R.id.bBack:
                if ( browser.canGoBack() ) {
                    browser.goBack();
                }
                break;
            case R.id.bForward:
                if ( browser.canGoForward() ) {
                    browser.goForward();
                }
                break;
            case R.id.bRefresh:
                browser.reload();
                break;
            case R.id.bClearHistory:
                browser.clearHistory();
                break;
        }
    }
}
