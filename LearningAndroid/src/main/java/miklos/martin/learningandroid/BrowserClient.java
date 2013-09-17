package miklos.martin.learningandroid;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Own WebVIewClient for the Browser activity
 */
public class BrowserClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading ( WebView view, String url ) {
        view.loadUrl( url );
        return true;
    }
}
