package dragoiu_raul.com.notesbackup.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import static dragoiu_raul.com.notesbackup.SqlLite.NoteDbContract.*;
import static dragoiu_raul.com.notesbackup.SqlLite.NoteDbContract.NoteTable.COLUMN_NAME_CONTENT;
import static dragoiu_raul.com.notesbackup.SqlLite.NoteDbContract.NoteTable.COLUMN_NAME_TITLE;
import static dragoiu_raul.com.notesbackup.SqlLite.NoteDbContract.NoteTable.TABLE_NAME;

public class NoteDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Note.db";

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertNote(String title, String content) {
        // Create a new map of values, where column names are the keys
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_CONTENT, content);

// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_NAME, null, values);
    }

    public List<Note> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                NoteTable.COLUMN_NAME_TITLE,
                NoteTable.COLUMN_NAME_CONTENT
        };

// Filter results WHERE "title" = 'My Title'
//        String selection = NoteTable.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                NoteTable.COLUMN_NAME_CONTENT + " DESC";

        Cursor cursor = db.query(
                NoteTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Note> notes = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(NoteTable._ID));
            String itemTitle = cursor.getString(cursor.getColumnIndexOrThrow(NoteTable.COLUMN_NAME_TITLE));
            String itemContent = cursor.getString(cursor.getColumnIndexOrThrow(NoteTable.COLUMN_NAME_CONTENT));
            notes.add(new Note(itemId, itemTitle, itemContent));
        }
        cursor.close();
        return notes;
    }
}