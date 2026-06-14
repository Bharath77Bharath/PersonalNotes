package com.bharath.PersonalNotes.note;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/createNote")
    public ResponseEntity<Note> createNote(@RequestBody Note note, Authentication authentication) {
        Note savedNote = noteService.createNoteService(note,authentication.getName());

        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @GetMapping("/getNote")
    public ResponseEntity<List<Note>> getNote(Authentication authentication) {
        String username = authentication.getName();

        return new ResponseEntity<>(noteService.getNoteService(username), HttpStatus.OK);
    }

    @GetMapping("/getNote/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id, Authentication authentication) {
        String username = authentication.getName();

        return new ResponseEntity<>(noteService.getNoteByIdService(id,username),HttpStatus.OK);
    }

    @PutMapping("/updateNote/{id}")
    public ResponseEntity<Note> updateNoteById(@PathVariable int id, @RequestBody Note updatedNote, Authentication authentication) {

        String username = authentication.getName();

        return new ResponseEntity<>(noteService.updateNoteByIdService(id, updatedNote, username),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable int id, Authentication authentication) {
        String username = authentication.getName();

        noteService.deleteNoteByIdService(id, username);

        return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
    }

}
