package miklos.martin.learningandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import miklos.martin.learningandroid.model.AwesomePerson;

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

    public ArrayList<AwesomePerson> getData () {

        Cursor cursor = database.query( DATABASE_TABLE, getColumns(), null, null, null, null, null );
        ArrayList<AwesomePerson> result = new ArrayList<AwesomePerson>();

        int iId = cursor.getColumnIndex( ID );
        int iName = cursor.getColumnIndex( NAME );
        int iAwesomeness = cursor.getColumnIndex( AWESOMENESS );

        for ( cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext() ) {
            result.add( new AwesomePerson( cursor.getInt( iId ), cursor.getString( iName ), cursor.getInt( iAwesomeness ) ) );
        }

        return result;
    }

    public AwesomePerson find ( long rowId ) throws SQLDataException {

        Cursor cursor = database.query( DATABASE_TABLE, getColumns(), ID + "=" + rowId, null, null, null, null );

        if ( cursor.getCount() == 0 ) {
            throw new SQLDataException( String.format( "Row not found by id [%d]!", rowId ) );
        }

        int iId = cursor.getColumnIndex( ID );
        int iName = cursor.getColumnIndex( NAME );
        int iAwesomeness = cursor.getColumnIndex( AWESOMENESS );

        cursor.moveToFirst();

        return new AwesomePerson( cursor.getInt( iId ), cursor.getString( iName ), cursor.getInt( iAwesomeness ) );
    }

    private String[] getColumns () {
        return new String[] { ID, NAME, AWESOMENESS };
    }

    public int update ( AwesomePerson row ) {

        ContentValues values = new ContentValues();
        values.put( NAME, row.getName() );
        values.put( AWESOMENESS, row.getAwesomeness() );

        return database.update( DATABASE_TABLE, values, ID + "=" + row.getId(), null );
    }

    public void delete ( AwesomePerson row ) {
        database.delete( DATABASE_TABLE, ID + "=" + row.getId(), null );
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper ( Context context ) {
            super( context, DATABASE_NAME, null, DATABASE_VERSION );
        }

        @Override
        public void onCreate ( SQLiteDatabase sqLiteDatabase ) {

            sqLiteDatabase.execSQL(
                "CREATE TABLE " + DATABASE_TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
