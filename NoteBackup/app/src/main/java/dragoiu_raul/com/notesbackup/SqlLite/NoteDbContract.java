package dragoiu_raul.com.notesbackup.SqlLite;

import android.provider.BaseColumns;

public final class NoteDbContract {

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteTable.TABLE_NAME + " (" +
                    NoteTable._ID + " TEXT PRIMARY KEY," +
                    NoteTable.COLUMN_NAME_TITLE + " TEXT," +
                    NoteTable.COLUMN_NAME_CONTENT + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteTable.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private NoteDbContract() {}

    /* Inner class that defines the table contents */
    public static class NoteTable implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}
