package com.dragoiu.nodebackup_backend.Service;

import com.dragoiu.nodebackup_backend.Model.Note;
import com.dragoiu.nodebackup_backend.Persistence.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

}
