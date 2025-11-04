package com.Dp.NotesSphere.Controller;


import com.Dp.NotesSphere.Modals.Note;
import com.Dp.NotesSphere.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteServices;


    @PostMapping
    public ResponseEntity<Note> create(
            @RequestBody Note note,
            @RequestHeader("X-User-Name") String username) {

        if (username == null || username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        note.setUsername(username);
        Note savedNote = noteServices.save(note, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }


    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(@RequestHeader("X-User-Name") String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Note> notes = noteServices.getAll(username);
        if (notes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(notes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(
            @PathVariable String id,
            @RequestHeader("X-User-Name") String username) {

        Note note = noteServices.getById(id, username);
        if (note == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(note);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Note> update(
            @PathVariable String id,
            @RequestBody Note updatedNote,
            @RequestHeader("X-User-Name") String username) {

        Note existingNote = noteServices.getById(id, username);
        if (existingNote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        Note saved = noteServices.save(existingNote, username);

        return ResponseEntity.ok(saved);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader("X-User-Name") String username) {

        noteServices.delete(id, username);
        return ResponseEntity.noContent().build();
    }
}
