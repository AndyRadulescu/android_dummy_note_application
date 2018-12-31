package com.dragoiu.nodebackup_backend.controller;

import com.dragoiu.nodebackup_backend.model.Note;
import com.dragoiu.nodebackup_backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping(value = "/dummy")
    public String getDummy() {
        return "dummy";
    }

    @GetMapping(value = "/notes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping(value = "/note", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Note saveNote(@RequestBody Note note) {
        System.out.println(note);
        return noteService.saveNote(note);
    }
}
