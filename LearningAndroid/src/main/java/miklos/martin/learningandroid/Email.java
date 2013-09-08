package miklos.martin.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Sending mail
 */
public class Email extends Activity implements View.OnClickListener {

    EditText recipent, subject, message;
    String recipentString, subjectString, messageString;

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.email );

        initMembers();
    }

    private void initMembers() {
        recipent = (EditText) findViewById( R.id.etRecipent );
        subject = (EditText) findViewById( R.id.etSubject );
        message = (EditText) findViewById( R.id.etMessage );
        send = (Button) findViewById( R.id.bSend );
    }
    
    private void convertTexts() {
        recipentString = recipent.getText().toString();
        subjectString = subject.getText().toString();
        messageString = message.getText().toString();
    }

    @Override
    public void onClick(View view) {

        convertTexts();
        String adresses[] = { recipentString };
        Intent emailIntent = new Intent( Intent.ACTION_SEND );

        emailIntent.putExtra( Intent.EXTRA_EMAIL, adresses );
        emailIntent.putExtra( Intent.EXTRA_SUBJECT, subjectString );
        emailIntent.setType( "text/plain" );
        emailIntent.putExtra( Intent.EXTRA_TEXT, messageString );

        startActivity( emailIntent );
    }
}
