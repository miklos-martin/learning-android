package miklos.martin.learningandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Menu
 */
public class Menu extends ListActivity {

    String classes[] = { "MainActivity", "TextPlay", "Email" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter( new ArrayAdapter<String>( Menu.this, android.R.layout.simple_list_item_1, classes ));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        try {
            Class selectedClass = Class.forName( "miklos.martin.learningandroid." + classes[position] );
            Intent newIntent = new Intent( Menu.this, selectedClass );
            startActivity( newIntent );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }
}
