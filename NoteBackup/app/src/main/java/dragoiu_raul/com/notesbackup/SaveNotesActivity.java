package dragoiu_raul.com.notesbackup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.UUID;

import dragoiu_raul.com.notesbackup.RestConnection.AsyncResponse;
import dragoiu_raul.com.notesbackup.RestConnection.NotePostForBackend;
import dragoiu_raul.com.notesbackup.SqlLite.Note;
import dragoiu_raul.com.notesbackup.SqlLite.NoteDbHelper;

public class SaveNotesActivity extends AppCompatActivity implements AsyncResponse {

    EditText edNote;
    NotePostForBackend nodePost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edNote = findViewById(R.id.etNnote);
        nodePost = new NotePostForBackend();
        nodePost.setDelegate(this);
    }

    public void saveNote(View view) {
        String noteContent = edNote.getText().toString();
        if (noteContent.equals("")) {
            Toast.makeText(this, "Please add text to the note", Toast.LENGTH_SHORT).show();
            return;
        }
        //save to the database
        NoteDbHelper dbHelper = new NoteDbHelper(this);
        Note note = new Note(UUID.randomUUID(), "new Note", noteContent);
        if (dbHelper.insertNote(note) != -1) {
            Toast.makeText(this, "Saved to the database", Toast.LENGTH_SHORT).show();
            //async task
            nodePost.execute("http://192.168.0.196:8080/note", new Gson().toJson(note));
            finish();
        } else {
            Toast.makeText(this, "Database down", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void processFinish(String output) {
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
    }
}
