package miklos.martin.learningandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Model class for the awesome table
 */
public class AwesomeOrNot {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AWESOMENESS = "awesomeness";

    private static final String DATABASE_NAME = "awesome_or_not";
    private static final String DATABASE_TABLE = "awesome";
    private static final int DATABASE_VERSION = 1;

    private DbHelper helper;
    private final Context context;
    private SQLiteDatabase database;

    public AwesomeOrNot( Context c ) {
        context = c;
    }

    public AwesomeOrNot open() throws SQLException{
        helper = new DbHelper( context );
        database = helper.getWritableDatabase();

        return this;
    }

    public void close() {
        helper.close();
    }

    public void createNew( String name, int awesomeness ) {

        ContentValues parameters = new ContentValues();
        parameters.put( NAME, name );
        parameters.put( AWESOMENESS, awesomeness );

        database.insert( DATABASE_TABLE, null, parameters );
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper ( Context context ) {
            super( context, DATABASE_NAME, null, DATABASE_VERSION );
        }

        @Override
        public void onCreate ( SQLiteDatabase sqLiteDatabase ) {

            sqLiteDatabase.execSQL(
                "CREATE TABLE " + DATABASE_TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTO INCREMENT, " +
                    NAME + "  TEXT NOT NULL, " +
                    AWESOMENESS + "  INTEGER NOT NULL" +
                ");"
            );
        }

        @Override
        public void onUpgrade ( SQLiteDatabase sqLiteDatabase, int i, int i2 ) {

            sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + DATABASE_TABLE + ";" );
            onCreate( sqLiteDatabase );
        }
    }
}
