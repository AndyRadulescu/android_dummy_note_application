package dragoiu_raul.com.notesbackup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dragoiu_raul.com.notesbackup.SqlLite.NoteDbHelper;

public class SaveNotesActivity extends AppCompatActivity {

    EditText edNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edNote = findViewById(R.id.etNnote);
    }

    public void saveNote(View view) {
        String noteContent = edNote.getText().toString();
        if (noteContent.equals("")) {
            Toast.makeText(this, "Please add text to the note", Toast.LENGTH_SHORT).show();
            return;
        }
        //save to the database
        NoteDbHelper dbHelper = new NoteDbHelper(this);
        if (dbHelper.insertNote("new Note", noteContent) != -1) {
            Toast.makeText(this, "Saved to the database", Toast.LENGTH_SHORT).show();
            //async task
            finish();
        } else {
            Toast.makeText(this, "Database down", Toast.LENGTH_SHORT).show();

        }
    }
}
