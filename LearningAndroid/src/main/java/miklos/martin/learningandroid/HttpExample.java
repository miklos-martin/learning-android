package miklos.martin.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HttpCLient usage example
 */
public class HttpExample extends Activity {

    TextView display;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.httpexample );
        initialize();

        Data data = new Data();
        try {
            display.setText( data.getData() );
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }
    }

    private void initialize () {
        display = (TextView) findViewById( R.id.tvHttp );
    }

    private class Data {

        public String getData() throws URISyntaxException {

            BufferedReader in = null;
            String data = null;

            try {

                HttpClient client = new DefaultHttpClient();
                URI website = new URI( "http://google.com" );
                HttpGet request = new HttpGet();
                request.setURI( website );

                HttpResponse response = client.execute( request );

                in = new BufferedReader( new InputStreamReader( response.getEntity().getContent() ) );
                StringBuffer buffer = new StringBuffer( "" );
                String line = "";
                String nl = System.getProperty( "line.separator" );
                while ( ( line = in.readLine() ) != null ) {
                    buffer.append( line + nl );
                }

                data = buffer.toString();
            } catch ( IOException e ) {
                e.printStackTrace();
            } finally {
                if ( in != null ) {
                    try {
                        in.close();
                    } catch ( IOException e ) {
                        e.printStackTrace();
                    }
                }
            }

            return data;
        }
    }
}
