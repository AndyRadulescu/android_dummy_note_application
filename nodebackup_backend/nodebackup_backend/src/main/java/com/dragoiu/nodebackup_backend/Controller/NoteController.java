package com.dragoiu.nodebackup_backend.Controller;

import com.dragoiu.nodebackup_backend.Model.Note;
import com.dragoiu.nodebackup_backend.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping(value = "/dummy")
    public String getDummy(){
        return "dummy";
    }

    @GetMapping(value = "/notes")
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping(value = "/note")
    public Note saveNote(Note note){
        return noteService.saveNote(note);
    }
}
