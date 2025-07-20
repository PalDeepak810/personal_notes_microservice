package com.Dp.NotesSphere.Services;

import com.Dp.NotesSphere.Modals.Note;
import com.Dp.NotesSphere.Modals.User;
import com.Dp.NotesSphere.Repositories.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteServices {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void save(Note note, String username){
        try {
            User user = userService.getByname(username);
            Note n = noteRepo.save(note);
            user.getNotes().add(n);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the Entry",e);
        }
    }

    public Note save(Note note){
        noteRepo.save(note);
        return note;
    }


    public List<Note> getAll(){
        return noteRepo.findAll();
    }

    public Note getById(String id){
        return noteRepo.findById(id).orElse(null);
    }

   public  void delete(String id, String username){
        User user = userService.getByname(username);
        user.getNotes().removeIf(x->x.getId().equals(id));
        userService.saveUser(user);
        noteRepo.deleteById(id);
   }
}
