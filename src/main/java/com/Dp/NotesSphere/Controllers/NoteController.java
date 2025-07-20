package com.Dp.NotesSphere.Controllers;

import com.Dp.NotesSphere.Modals.Note;
import com.Dp.NotesSphere.Modals.User;
import com.Dp.NotesSphere.Services.NoteServices;
import com.Dp.NotesSphere.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteServices noteServices;

    @Autowired
    private UserService userService;

    @PostMapping("{username}")
    public ResponseEntity<Note> create(@RequestBody Note note, @PathVariable String username){
        try {
            noteServices.save(note, username); // This method also updates the user
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("{username}")
    public ResponseEntity<List<Note>> getAllNotesOfUser(@PathVariable String username){
        User user = userService.getByname(username);
        List<Note> all = user.getNotes();

        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id){
        Optional<Note> note= Optional.ofNullable(noteServices.getById(id));
        if(note.isPresent()){
            return new ResponseEntity<>(note.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id,@RequestBody Note newNote,@PathVariable String username) {
        try {
            Note oldNote = noteServices.getById(id);

            if (oldNote != null) {
                oldNote.setTitle(
                        newNote.getTitle() != null && !newNote.getTitle().isEmpty() ? newNote.getTitle() : oldNote.getTitle()
                );
                oldNote.setContent(
                        newNote.getContent() != null && !newNote.getContent().isEmpty() ? newNote.getContent() : oldNote.getContent()
                );

                Note savedNote = noteServices.save(oldNote);
                return new ResponseEntity<>(savedNote, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{username}/{id}")
    public boolean deleteNote(@PathVariable String id,@PathVariable String username){
        noteServices.delete(id,username);
        return true;
    }
}
