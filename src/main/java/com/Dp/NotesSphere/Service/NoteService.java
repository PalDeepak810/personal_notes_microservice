package com.Dp.NotesSphere.Service;

import com.Dp.NotesSphere.Modals.Note;
import com.Dp.NotesSphere.Repository.NoteRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    public Note save(Note note, String username) {
        note.setUsername(username);
        return noteRepo.save(note);
    }

    public List<Note> getAll(String username) {
        return noteRepo.findByUsername(username);
    }

    public Note getById(String id, String username) {
        return noteRepo.findByIdAndUsername(id, username).orElse(null);
    }

    public void delete(String id, String username) {
        noteRepo.deleteByIdAndUsername(id, username);
    }
}
