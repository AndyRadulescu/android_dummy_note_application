package dragoiu_raul.com.notesbackup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import dragoiu_raul.com.notesbackup.SqlLite.NoteDbHelper;

public class NotesMainActivity extends AppCompatActivity {

    TextView tvNotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        final Intent intent = new Intent(this, SaveNotesActivity.class);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Toast.makeText(NotesMainActivity.this, "Sugi pula", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        NoteDbHelper dbHelper = new NoteDbHelper(this);
        tvNotesList = findViewById(R.id.tvNotesList);
        tvNotesList.setText(dbHelper.getAllNotes().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvNotesList.setText("");
        NoteDbHelper dbHelper = new NoteDbHelper(this);
        tvNotesList.append(dbHelper.getAllNotes().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case (R.id.action_settings):
                Toast.makeText(NotesMainActivity.this, "Sugi pula", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.action_roulikpuzulik):
                Toast.makeText(NotesMainActivity.this, "putzulik", Toast.LENGTH_SHORT).show();


        }

        return super.onOptionsItemSelected(item);
    }
}
